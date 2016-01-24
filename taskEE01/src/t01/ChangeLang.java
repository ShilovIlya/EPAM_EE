package t01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/lang")
public class ChangeLang extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest (HttpServletRequest request, HttpServletResponse response) throws IOException {
        String lang = request.getParameter("locale");
        HttpSession session = request.getSession(true);
        if (lang.equals("ru")) {
            session.setAttribute("locale", "ru");
        } else {
            session.setAttribute("locale", "en");
        }
        response.sendRedirect("/");
    }
}