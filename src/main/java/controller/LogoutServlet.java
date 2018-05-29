package controller;

import org.apache.logging.log4j.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LogoutServlet")
public class LogoutServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger("CONTROLLER");
    private static final String HOME_SERVLET_URL = "/";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String uid = (String) session.getAttribute("uid");
        session.setAttribute("access", null);
        session.setAttribute("uid", null);
        logger.info(uid + " logged out");
        request.getRequestDispatcher("HOME_SERVLET_URL").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
