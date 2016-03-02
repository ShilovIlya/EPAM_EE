package t01;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;


@WebServlet("/index.jsp")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        authorize(request, response);
    }

    private void authorize(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(true);

        PrintWriter out = response.getWriter();

        Locale locale;
        String localeAttribute = (String)session.getAttribute("locale");
        if (localeAttribute != null) {
            switch (localeAttribute) {
                case "ru":
                    locale = new Locale("ru", "RU");
                    break;
                case "en":
                    locale = new Locale("en", "US");
                    break;
                default:
                    locale = Locale.getDefault();
                    break;
            }
        } else {
            locale = Locale.getDefault();
        }


        ResourceBundle text = ResourceBundle.getBundle("t01.text", locale);

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        String loginStatus = "";
        Users users = Users.getInstance();

        if (users.authorized) {
            loginStatus = text.getString("login.already");
        } else {
            for (Users.User user : users.usersArray) {
                if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                    loginStatus = text.getString("login.success");
                    user.SignIn();
                    users.authorized = true;
                }
            }
            if (!users.authorized) {
                loginStatus = text.getString("login.error");
            }
        }

        if ((login == null) && (password == null)) {
            loginStatus = "";
        }

        out.println("<html>" +
                "<head>" +
                    "<title>" + "Main Page" + "</title>" +
                "</head>" +
                "<body>" +
                    "<form method='POST'>" +
                        "<input type='text' name='login' /><br>" +
                        "<input type='text' name='password' /><br>" +
                        "<input type='submit' />" +
                    "</form>" +
                    "<p>" + loginStatus + "</p>" +
                    "<a href='/lang?locale=ru'>ru</a>" + "\n" +
                    "<a href='/lang?locale=en'>en</a>" +
                    "<br><a href='/logout'>" + text.getString("logout.link") +"</a>" +
                "</body>" +
                "</html>");
    }
}
