package controller;

import model.CloseCheckModel;
import model.entity.Check;
import model.entity.WarehouseProduct;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "CloseCheckServlet")
public class CloseCheckServlet extends HttpServlet {

    private static final String CHECK_URL = "/WEB-INF/view/check.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<WarehouseProduct> products = (ArrayList<WarehouseProduct>) session.getAttribute("productsInCheck");
        Check check = new CloseCheckModel().closeCheck(products, (String) session.getAttribute("uid"));
        session.removeAttribute("productsInCheck");
        request.setAttribute("productsInCheck", products);
        request.setAttribute("check", check);
        request.getRequestDispatcher(CHECK_URL).forward(request, response);
    }
}
