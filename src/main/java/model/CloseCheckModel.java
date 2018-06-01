package model;

import dao.CheckDAO;
import dao.SoldProductDAO;
import dao.WarehouseProductDAO;
import model.entity.*;
import org.apache.logging.log4j.*;

import javax.naming.*;
import javax.sql.DataSource;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class CloseCheckModel {

    private static final Logger logger = LogManager.getLogger("MODEL");

    public CloseCheckModel() {
    }

    public Check closeCheck(List<WarehouseProduct> warehouseProducts, String cashierId) {
        Check check = new Check();
        check.setCanceled(false);
        check.setCashierId(cashierId);
        check.setTimestamp(new Timestamp(System.currentTimeMillis()));
        CheckDAO checkDAO = new CheckDAO(getConnection());
        int checkId = checkDAO.createAndGetId(check);
        check.setId(checkId);

        SoldProductDAO soldProductDAO = new SoldProductDAO(getConnection());
        WarehouseProductDAO warehouseProductDAO = new WarehouseProductDAO(getConnection());
        warehouseProducts.forEach(product -> {
            SoldProduct soldProduct = new SoldProduct();
            soldProduct.setProductCode(product.getCode());
            soldProduct.setPrice(product.getPrice());
            soldProduct.setQuantity(product.getQuantity());
            soldProduct.setCheckId(checkId);
            soldProductDAO.create(soldProduct);
            WarehouseProduct warehouseProduct = warehouseProductDAO.findById(product.getCode());
            warehouseProduct.setQuantity(warehouseProduct.getQuantity() - soldProduct.getQuantity());
            warehouseProductDAO.update(warehouseProduct, warehouseProduct.getCode());
        });

        warehouseProductDAO.close();
        checkDAO.close();
        soldProductDAO.close();
        return check;
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
