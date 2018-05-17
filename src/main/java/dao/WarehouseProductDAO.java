package dao;

import model.entity.*;
import java.sql.*;
import java.util.*;

public class WarehouseProductDAO extends AbstractDAO<String, Product> {

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
    public List<Product> findAll() {
        List<Product> result = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM `" + TABLE_NAME + "`");
            while(set.next()) {
                Product product = new Product();
                product.setCode(set.getString(CODE_COLUMN));
                product.setName(set.getString(NAME_COLUMN));
                product.setWeighty(set.getBoolean(IS_WEIGHTY_COLUMN));
                product.setQuantity(set.getDouble(QUANTITY_COLUMN));
                product.setPrice(set.getDouble(PRICE_COLUMN));
                result.add(product);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Product findById(String id) {
        Product result = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM `" + TABLE_NAME + "`"
                    + " WHERE " + CODE_COLUMN + " = `" + id + "`");
            if(set.next()) {
                result = new Product();
                result.setCode(set.getString(CODE_COLUMN));
                result.setName(set.getString(NAME_COLUMN));
                result.setWeighty(set.getBoolean(IS_WEIGHTY_COLUMN));
                result.setQuantity(set.getDouble(QUANTITY_COLUMN));
                result.setPrice(set.getDouble(PRICE_COLUMN));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            int count = statement.executeUpdate("DELETE FROM `" + TABLE_NAME + "`"
                    + " WHERE " + CODE_COLUMN + " = `" + id + "`");
            result = count > 0;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(Product entity) {
        return delete(entity.getCode());
    }

    @Override
    public boolean create(Product entity) {
        boolean result = false;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            int count = statement.executeUpdate("INSERT INTO `" + TABLE_NAME + "`"
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
                    + ", " + entity.getPrice()
            );
            result = count > 0;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Product entity, String id) {
        boolean result = false;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            int count = statement.executeUpdate("UPDATE `" + TABLE_NAME + "`"
                    + " SET " + NAME_COLUMN +  " = `" + entity.getName() + "`"
                    + ", " + IS_WEIGHTY_COLUMN + " = " + entity.isWeighty()
                    + ", " + QUANTITY_COLUMN + " = " + entity.getQuantity()
                    + ", " + PRICE_COLUMN + " = " + entity.getPrice()
                    + " WHERE " + CODE_COLUMN + " = `" + id + "`"
            );
            result = count > 0;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
