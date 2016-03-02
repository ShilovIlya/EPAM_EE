package t01;

import org.apache.log4j.Logger;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Wais on 02.03.2016.
 */

@WebListener
public class RequestListener implements ServletRequestListener{

    private static final Logger log = Logger.getLogger(RequestListener.class);

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        log.info("RequestURI = " + request.getRequestURI());
        log.info("login = " + request.getParameter("login"));
        log.info("password = " + request.getParameter("password"));
    }
}
