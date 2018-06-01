package controller;

import model.OpenCheckModel;
import model.entity.WarehouseProduct;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "OpenCheckServlet")
public class OpenCheckServlet extends HttpServlet {

    private static final String OPENED_CHECK_URL = "/WEB-INF/view/openedCheck.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameterMap().containsKey("canceledProductName")) {
            HttpSession session = request.getSession();
            ArrayList<WarehouseProduct> products = (ArrayList<WarehouseProduct>) session.getAttribute("productsInCheck");
            products.removeIf(product -> product.getName().equals(request.getParameter("canceledProductName")));
            session.setAttribute("productsInCheck", products);
            request.setAttribute("productsNames", new OpenCheckModel().getProductsNames());
            request.getRequestDispatcher(OPENED_CHECK_URL).forward(request, response);
            return;
        }
        boolean searchByCode = request.getParameter("searchBy").equals("code");
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String error = "";
        boolean hasError = false;
        double quantity = 0;
        try {
            quantity = Double.parseDouble(request.getParameter("quantity"));
        } catch(NumberFormatException e) {
            error += "Invalid number format.";
            hasError = true;
        }
        OpenCheckModel model = new OpenCheckModel();
        WarehouseProduct product = searchByCode ?
                model.getProductByCode(code, quantity) :
                model.getProductByName(name, quantity);
        if(product == null) {
            error += "\nError! This might happen if product does not exists" +
                    ", or is not weighty, but its quantity is decimal" +
                    ", or if specified quantity is bigger than available in the warehouse";
            hasError = true;
        }
        if(hasError) {
            request.setAttribute("error", error);
            request.setAttribute("quantity", quantity);
            request.setAttribute("code", code);
        } else {
            HttpSession session = request.getSession();
            ArrayList<WarehouseProduct> products = (ArrayList<WarehouseProduct>) session.getAttribute("productsInCheck");
            products.add(product);
            session.setAttribute("productsInCheck", products);
        }
        request.setAttribute("productsNames", model.getProductsNames());
        request.getRequestDispatcher(OPENED_CHECK_URL).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("productsNames", new OpenCheckModel().getProductsNames());
        HttpSession session = request.getSession();
        session.setAttribute("productsInCheck", new ArrayList<WarehouseProduct>());
        request.getRequestDispatcher(OPENED_CHECK_URL).forward(request, response);
    }
}