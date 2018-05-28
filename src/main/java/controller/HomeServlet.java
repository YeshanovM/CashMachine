package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "HomeServlet")
public class HomeServlet extends HttpServlet {

    private static final String LOGIN_URL = "/WEB-INF/view/login.jsp";
    private static final String HOME_URL = "/WEB-INF/view/home.jsp";

    public HomeServlet() {
        super();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("access") == null || (Integer) session.getAttribute("access") == -1)
            request.getRequestDispatcher(LOGIN_URL).forward(request, response);
        else
            request.getRequestDispatcher(HOME_URL).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
