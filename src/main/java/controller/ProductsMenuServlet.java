package controller;

import model.*;
import model.entity.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductsMenuServlet")
public class ProductsMenuServlet extends HttpServlet {

    private static final String PRODUCTS_MENU_URL = "/WEB-INF/view/productsMenu.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = "";
        if(request.getParameterMap().containsKey("editProduct")) {
            try {
                double quantity = Double.parseDouble((String) request.getParameter("quantity"));
                ProductsMenuModel model = new ProductsMenuModel();
                WarehouseProduct product = model.getProductByCode(request.getParameter("code"));
                if (!product.isWeighty() && quantity != (int) quantity)
                    error = "Error! Product is not weighty, so its quantity must be an integer value.";
                else {
                    product.setQuantity(quantity);
                    model.updateProduct(product);
                }
            } catch (NumberFormatException e) {
                error = "Error! Invalid number format.";
            }
        }
        if(request.getParameterMap().containsKey("addProduct")) {
            boolean result = false;
            try {
                WarehouseProduct product = new WarehouseProduct();
                product.setCode(request.getParameter("code"));
                product.setName(request.getParameter("name"));
                product.setQuantity(Double.parseDouble(request.getParameter("quantity")));
                product.setPrice(Double.parseDouble(request.getParameter("price")));
                product.setWeighty(request.getParameterMap().containsKey("isWeighty"));
                if (!product.isWeighty() && product.getQuantity() != (int) product.getQuantity())
                    error = "Error! Product is not weighty, so its quantity must be an integer value.";
                else {
                    result = new ProductsMenuModel().createProduct(product);
                    if(!result)
                        error = "Error! This might happen in case product with this name or code already exists.";
                }
            } catch (NumberFormatException e) {
                error = "Error! Invalid number format.";
            }
            if(!result) {
                request.setAttribute("code", request.getParameter("code"));
                request.setAttribute("name", request.getParameter("name"));
                request.setAttribute("quantity", request.getParameter("quantity"));
                request.setAttribute("price", request.getParameter("price"));
            }
        }
        List<WarehouseProduct> allProducts = new ProductsMenuModel().getAllProducts();
        request.setAttribute("allProducts", allProducts);
        request.setAttribute("error", error);
        request.getRequestDispatcher(PRODUCTS_MENU_URL).forward(request, response);
    }
}
