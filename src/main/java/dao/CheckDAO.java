package dao;

import model.entity.*;

import java.sql.*;
import java.util.*;

public class CheckDAO extends AbstractDAO<Integer, Check> {

    private static final String ID_COLUMN = "check_id";
    private static final String CASHIER_UID_COLUMN = "cashier_uid";
    private static final String TIMESTAMP_COLUMN = "timestamp";
    private static final String IS_CANCELED_COLUMN = "is_canceled";
    private static final String TABLE_NAME = "checks";

    public CheckDAO(Connection connection) {
        super(connection);
    }

    public int createAndGetId(Check entity) {
        Statement statement = null;
        ResultSet rs;
        int result = -1;
        String query = "INSERT INTO `" + TABLE_NAME + "`"
                + " (" + CASHIER_UID_COLUMN
                + ", " + TIMESTAMP_COLUMN
                + ", " + IS_CANCELED_COLUMN
                + ")"
                + " VALUES ('" + entity.getCashierId() + "'"
                + ", '" + entity.getTimestamp() + "'"
                + ", " + entity.isCanceled() + ")";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            rs = statement.getGeneratedKeys();
            rs.next();
            result = rs.getInt(1);
        } catch (SQLException e) {
            logger.info("Unable to execute query: \"" + query + "\"");
        }
        closeStatement(statement);
        return result;
    }

    @Override
    public List<Check> findAll() {
        List<Check> result = new ArrayList<>();
        Statement statement = null;
        String query = "SELECT * FROM `" + TABLE_NAME + "`";
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            while(set.next()) {
                Check check = new Check();
                check.setId(set.getInt(ID_COLUMN));
                check.setCashierId(set.getString(CASHIER_UID_COLUMN));
                check.setTimestamp(set.getTimestamp(TIMESTAMP_COLUMN));
                check.setCanceled(set.getBoolean(IS_CANCELED_COLUMN));
                result.add(check);
            }
        } catch(SQLException e) {
            logger.info("Unable to execute query: \"" + query + "\"");
        }
        closeStatement(statement);
        return result;
    }

    @Override
    public Check findById(Integer id) {
        Check result = null;
        Statement statement = null;
        String query = "SELECT * FROM `" + TABLE_NAME + "`"
                + " WHERE " + ID_COLUMN + " = " + id + "";
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            if(set.next()) {
                result = new Check();
                result.setId(set.getInt(ID_COLUMN));
                result.setCashierId(set.getString(CASHIER_UID_COLUMN));
                result.setTimestamp(set.getTimestamp(TIMESTAMP_COLUMN));
                result.setCanceled(set.getBoolean(IS_CANCELED_COLUMN));
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
                + " WHERE " + ID_COLUMN + " = " + id + "";
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
    public boolean delete(Check entity) {
        boolean result = false;
        Statement statement = null;
        String query = "DELETE FROM `" + TABLE_NAME + "`"
                + " WHERE " + ID_COLUMN + " = " + entity.getId()
                + " AND " + IS_CANCELED_COLUMN + " = " + entity.isCanceled()
                + " AND " + CASHIER_UID_COLUMN + " = '" + entity.getCashierId() + "'"
                + " AND " + TIMESTAMP_COLUMN + " = '" + entity.getTimestamp() + "'";
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
    public boolean create(Check entity) {
        boolean result = false;
        Statement statement = null;
        String query = "INSERT INTO `" + TABLE_NAME + "`"
                + " (" + CASHIER_UID_COLUMN
                + ", " + TIMESTAMP_COLUMN
                + ", " + IS_CANCELED_COLUMN
                + ")"
                + " VALUES ('" + entity.getCashierId() + "'"
                + ", '" + entity.getTimestamp() + "'"
                + ", " + entity.isCanceled() + ")";
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
    public boolean update(Check entity, Integer id) {
        boolean result = false;
        Statement statement = null;
        String query = "UPDATE `" + TABLE_NAME + "`"
                + " SET " + CASHIER_UID_COLUMN +  " = '" + entity.getCashierId() + "'"
                + ", " + TIMESTAMP_COLUMN + " = '" + entity.getTimestamp() + "'"
                + ", " + IS_CANCELED_COLUMN + " = " + entity.isCanceled()
                + " WHERE " + ID_COLUMN + " = " + id + "";
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
