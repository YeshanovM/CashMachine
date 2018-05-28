package dao;

import model.entity.*;
import java.sql.*;
import java.util.*;

public class WarehouseProductDAO extends AbstractDAO<String, WarehouseProduct> {

    private static final String CODE_COLUMN = "product_code";
    private static final String NAME_COLUMN = "name";
    private static final String IS_WEIGHTY_COLUMN = "is_weighty";
    private static final String QUANTITY_COLUMN = "quantity";
    private static final String PRICE_COLUMN = "price";
    private static final String TABLE_NAME = "warehouse_products";

    public WarehouseProductDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<WarehouseProduct> findAll() {
        List<WarehouseProduct> result = new ArrayList<>();
        Statement statement = null;
        String query = "SELECT * FROM `" + TABLE_NAME + "`";
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            while(set.next()) {
                WarehouseProduct warehouseProduct = new WarehouseProduct();
                warehouseProduct.setCode(set.getString(CODE_COLUMN));
                warehouseProduct.setName(set.getString(NAME_COLUMN));
                warehouseProduct.setWeighty(set.getBoolean(IS_WEIGHTY_COLUMN));
                warehouseProduct.setQuantity(set.getDouble(QUANTITY_COLUMN));
                warehouseProduct.setPrice(set.getDouble(PRICE_COLUMN));
                result.add(warehouseProduct);
            }
        } catch(SQLException e) {
            logger.info("Unable to execute query: \"" + query + "\"");
        }
        closeStatement(statement);
        return result;
    }

    @Override
    public WarehouseProduct findById(String id) {
        WarehouseProduct result = null;
        Statement statement = null;
        String query = "SELECT * FROM `" + TABLE_NAME + "`"
                + " WHERE " + CODE_COLUMN + " = '" + id + "'";
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            if(set.next()) {
                result = new WarehouseProduct();
                result.setCode(set.getString(CODE_COLUMN));
                result.setName(set.getString(NAME_COLUMN));
                result.setWeighty(set.getBoolean(IS_WEIGHTY_COLUMN));
                result.setQuantity(set.getDouble(QUANTITY_COLUMN));
                result.setPrice(set.getDouble(PRICE_COLUMN));
            }
        } catch(SQLException e) {
            logger.info("Unable to execute query: \"" + query + "\"");
        }
        closeStatement(statement);
        return result;
    }

    public WarehouseProduct findByName(String name) {
        WarehouseProduct result = null;
        Statement statement = null;
        String query = "SELECT * FROM `" + TABLE_NAME + "`"
                + " WHERE " + NAME_COLUMN + " = '" + name + "'";
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            if(set.next()) {
                result = new WarehouseProduct();
                result.setCode(set.getString(CODE_COLUMN));
                result.setName(set.getString(NAME_COLUMN));
                result.setWeighty(set.getBoolean(IS_WEIGHTY_COLUMN));
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
    public boolean delete(String id) {
        boolean result = false;
        Statement statement = null;
        String query = "DELETE FROM `" + TABLE_NAME + "`"
                + " WHERE " + CODE_COLUMN + " = '" + id + "'";
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
    public boolean delete(WarehouseProduct entity) {
        boolean result = false;
        Statement statement = null;
        String query = "DELETE FROM `" + TABLE_NAME + "`"
                + " WHERE " + CODE_COLUMN + " = '" + entity.getCode() + "'"
                + " AND " + NAME_COLUMN + " = '" + entity.getName() + "'"
                + " AND " + IS_WEIGHTY_COLUMN + " = " + entity.isWeighty()
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
    public boolean create(WarehouseProduct entity) {
        boolean result = false;
        Statement statement = null;
        String query = "INSERT INTO `" + TABLE_NAME + "`"
                + " (" + CODE_COLUMN
                + ", " + NAME_COLUMN
                + ", " + IS_WEIGHTY_COLUMN
                + ", " + QUANTITY_COLUMN
                + ", " + PRICE_COLUMN
                + ")"
                + " VALUES ('" + entity.getCode() + "'"
                + ", '" + entity.getName() + "'"
                + ", " + entity.isWeighty()
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
    public boolean update(WarehouseProduct entity, String id) {
        boolean result = false;
        Statement statement = null;
        String query = "UPDATE `" + TABLE_NAME + "`"
                + " SET " + NAME_COLUMN +  " = '" + entity.getName() + "'"
                + ", " + IS_WEIGHTY_COLUMN + " = " + entity.isWeighty()
                + ", " + QUANTITY_COLUMN + " = " + entity.getQuantity()
                + ", " + PRICE_COLUMN + " = " + entity.getPrice()
                + " WHERE " + CODE_COLUMN + " = '" + id + "'";
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
