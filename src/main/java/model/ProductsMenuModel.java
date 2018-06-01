package model;

import dao.WarehouseProductDAO;
import model.entity.*;
import org.apache.logging.log4j.*;

import javax.naming.*;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class ProductsMenuModel {
    private static final Logger logger = LogManager.getLogger("MODEL");

    public ProductsMenuModel() {
    }

    public List<WarehouseProduct> getAllProducts() {
        WarehouseProductDAO warehouseProductDAO = new WarehouseProductDAO(getConnection());
        List<WarehouseProduct> result = warehouseProductDAO.findAll();
        warehouseProductDAO.close();
        return result;
    }

    public WarehouseProduct getProductByCode(String code) {
        WarehouseProductDAO warehouseProductDAO = new WarehouseProductDAO(getConnection());
        WarehouseProduct product = warehouseProductDAO.findById(code);
        warehouseProductDAO.close();
        return product;
    }

    public void updateProduct(WarehouseProduct product) {
        WarehouseProductDAO warehouseProductDAO = new WarehouseProductDAO(getConnection());
        warehouseProductDAO.update(product, product.getCode());
        warehouseProductDAO.close();
    }

    public boolean createProduct(WarehouseProduct product) {
        WarehouseProductDAO warehouseProductDAO = new WarehouseProductDAO(getConnection());
        boolean result = warehouseProductDAO.create(product);
        warehouseProductDAO.close();
        return result;
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
