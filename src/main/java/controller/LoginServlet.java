package controller;

import model.LoginModel;
import org.apache.logging.log4j.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger("CONTROLLER");
    private static final String HOME_SERVLET_URL = "/";

    public LoginServlet() {
        super();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uid = request.getParameter("uid");
        String password = request.getParameter("password");
        int access = new LoginModel().authorize(uid, password);
        if(access == -1) {
            request.setAttribute("error", "Incorrect UID or password");
            request.setAttribute("uid", uid);
            logger.info(uid + " failed to authorize");
        }
        else {
            authorize(request, access, uid);
            logger.info(uid + " authorized successfully");
        }
        request.getRequestDispatcher(HOME_SERVLET_URL).forward(request, response);
    }

    private void authorize(HttpServletRequest request, int access, String uid) {
        HttpSession session = request.getSession();
        session.setAttribute("access", access);
        session.setAttribute("uid", uid);
    }
}