package dao;

import model.entity.*;
import java.sql.*;
import java.util.*;

public class SoldProductDAO extends AbstractDAO<Integer, SoldProduct> {

    private static final String ID_COLUMN = "id";
    private static final String PRODUCT_CODE_COLUMN = "code";
    private static final String CHECK_ID_COLUMN = "check_id";
    private static final String QUANTITY_COLUMN = "quantity";
    private static final String PRICE_COLUMN = "price";
    private static final String TABLE_NAME = "sold_products";

    SoldProductDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<SoldProduct> findAll() {
        List<SoldProduct> result = new ArrayList<>();
        Statement statement = null;
        String query = "SELECT * FROM `" + TABLE_NAME + "`";
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            while(set.next()) {
                SoldProduct product = new SoldProduct();
                product.setId(set.getInt(ID_COLUMN));
                product.setProductCode(set.getString(PRODUCT_CODE_COLUMN));
                product.setCheckId(set.getInt(CHECK_ID_COLUMN));
                product.setQuantity(set.getDouble(QUANTITY_COLUMN));
                product.setPrice(set.getDouble(PRICE_COLUMN));
                result.add(product);
            }
        } catch(SQLException e) {
            logger.info("Unable to execute query: \"" + query + "\"");
        }
        closeStatement(statement);
        return result;
    }

    @Override
    public SoldProduct findById(Integer id) {
        SoldProduct result = null;
        Statement statement = null;
        String query = "SELECT * FROM `" + TABLE_NAME + "`"
                + " WHERE " + ID_COLUMN + " = " + id;
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            if(set.next()) {
                result = new SoldProduct();
                result.setId(set.getInt(ID_COLUMN));
                result.setProductCode(set.getString(PRODUCT_CODE_COLUMN));
                result.setCheckId(set.getInt(CHECK_ID_COLUMN));
                result.setQuantity(set.getDouble(QUANTITY_COLUMN));
                result.setPrice(set.getDouble(PRICE_COLUMN));
            }
        } catch(SQLException e) {
            logger.info("Unable to execute query: \"" + query + "\"");
        }
        closeStatement(statement);
        return result;
    }

    @Override
    public boolean delete(Integer id) {
        boolean result = false;
        Statement statement = null;
        String query = "DELETE FROM `" + TABLE_NAME + "`"
                + " WHERE " + ID_COLUMN + " = " + id;
        try {
            statement = connection.createStatement();
            int count = statement.executeUpdate(query);
            result = count > 0;
        } catch(SQLException e) {
            logger.info("Unable to execute query: \"" + query + "\"");
        }
        closeStatement(statement);
        return result;
    }

    @Override
    public boolean delete(SoldProduct entity) {
        boolean result = false;
        Statement statement = null;
        String query = "DELETE FROM `" + TABLE_NAME + "`"
                + " WHERE " + ID_COLUMN + " = " + entity.getId()
                + " AND " + PRODUCT_CODE_COLUMN + " = '" + entity.getProductCode() + "'"
                + " AND " + CHECK_ID_COLUMN + " = " + entity.getCheckId()
                + " AND " + QUANTITY_COLUMN + " = " + entity.getQuantity()
                + " AND " + PRICE_COLUMN + " = " + entity.getPrice();
        try {
            statement = connection.createStatement();
            int count = statement.executeUpdate(query);
            result = count > 0;
        } catch(SQLException e) {
            logger.info("Unable to execute query: \"" + query + "\"");
        }
        closeStatement(statement);
        return result;
    }

    @Override
    public boolean create(SoldProduct entity) {
        boolean result = false;
        Statement statement = null;
        String query = "INSERT INTO `" + TABLE_NAME + "`"
                + " (" + ID_COLUMN
                + ", " + PRODUCT_CODE_COLUMN
                + ", " + CHECK_ID_COLUMN
                + ", " + QUANTITY_COLUMN
                + ", " + PRICE_COLUMN
                + ")"
                + " VALUES (" + entity.getId()
                + ", '" + entity.getProductCode() + "'"
                + ", " + entity.getCheckId()
                + ", " + entity.getQuantity()
                + ", " + entity.getPrice() + ")";
        try {
            statement = connection.createStatement();
            int count = statement.executeUpdate(query);
            result = count > 0;
        } catch(SQLException e) {
            logger.info("Unable to execute query: \"" + query + "\"");
        }
        closeStatement(statement);
        return result;
    }

    @Override
    public boolean update(SoldProduct entity, Integer id) {
        boolean result = false;
        Statement statement = null;
        String query = "UPDATE `" + TABLE_NAME + "`"
                + " SET " + PRODUCT_CODE_COLUMN +  " = '" + entity.getProductCode() + "'"
                + ", " + CHECK_ID_COLUMN + " = " + entity.getCheckId()
                + ", " + QUANTITY_COLUMN + " = " + entity.getQuantity()
                + ", " + PRICE_COLUMN + " = " + entity.getPrice()
                + " WHERE " + ID_COLUMN + " = " + id;
        try {
            statement = connection.createStatement();
            int count = statement.executeUpdate(query);
            result = count > 0;
        } catch(SQLException e) {
            logger.info("Unable to execute query: \"" + query + "\"");
        }
        closeStatement(statement);
        return result;
    }
}
