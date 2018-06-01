package controller;

import model.CancelCheck;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CancelCheckServlet")
public class CancelCheckServlet extends HttpServlet {

    private static final String HOME_URL = "/WEB-INF/view/home.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String checkId = request.getParameter("checkId");
        try {
            boolean checkCanceled = new CancelCheck().cancelCheck(Integer.parseInt(checkId));
            if(!checkCanceled)
                request.setAttribute("checkCancelResult", "Error! No such check found.");
            else
                request.setAttribute("checkCancelResult", "Check " + checkId + " successfully canceled");
        } catch(NumberFormatException e) {
            request.setAttribute("checkCancelResult", "Error! Invalid number format.");
        }
        request.getRequestDispatcher(HOME_URL).forward(request, response);
    }
}
