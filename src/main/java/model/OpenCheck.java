package model;

import dao.WarehouseProductDAO;
import model.entity.WarehouseProduct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.*;
import javax.sql.*;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class OpenCheck {

    private static final Logger logger = LogManager.getLogger("MODEL");

    public OpenCheck() {
    }

    public List<String> getProductsNames() {
        List<String> result;
        WarehouseProductDAO wpDAO = new WarehouseProductDAO(getConnection());
        result = wpDAO.findAll()
                .stream()
                .map(WarehouseProduct::getName)
                .collect(Collectors.toList());
        wpDAO.close();
        return result;
    }

    public WarehouseProduct getProductByCode(String code, double quantity) {
        WarehouseProductDAO wpDAO = new WarehouseProductDAO(getConnection());
        WarehouseProduct product = wpDAO.findById(code);
        wpDAO.close();
        if(product == null)
            return null;
        if(product.getQuantity() < quantity)
            return null;
        product.setQuantity(quantity);
        if(quantity != (int) quantity && !product.isWeighty())
            return null;
        wpDAO.close();
        return product;
    }

    public WarehouseProduct getProductByName(String name, double quantity) {
        WarehouseProductDAO wpDAO = new WarehouseProductDAO(getConnection());
        WarehouseProduct product = wpDAO.findByName(name);
        wpDAO.close();
        if(product == null)
            return null;
        if(product.getQuantity() < quantity)
            return null;
        product.setQuantity(quantity);
        if(quantity != (int) quantity && !product.isWeighty())
            return null;
        wpDAO.close();
        return product;
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/cashMachine");
            connection = ds.getConnection();
        } catch (NamingException e) {
            logger.error("Unable to access context");
        } catch (SQLException e) {
            logger.error("Unable to get database connection from pool");
        }
        return connection;
    }
}
