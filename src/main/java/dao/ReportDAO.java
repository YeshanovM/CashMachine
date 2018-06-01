package dao;

import model.entity.*;
import java.sql.*;
import java.util.*;

public class ReportDAO extends AbstractDAO<Integer, Report> {

    private static final String ID_COLUMN = "report_id";
    private static final String START_COLUMN = "start_time";
    private static final String END_COLUMN = "end_time";
    private static final String INCOMES_COLUMN = "incomes";
    private static final String EXPENSES_COLUMN = "expenses";
    private static final String IS_CLOSED_COLUMN = "is_closed";
    private static final String TABLE_NAME = "reports";

    public ReportDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Report> findAll() {
        List<Report> result = new ArrayList<>();
        Statement statement = null;
        String query = "SELECT * FROM `" + TABLE_NAME + "`";
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            while(set.next()) {
                Report report = new Report();
                report.setId(set.getInt(ID_COLUMN));
                report.setStartTime(set.getTimestamp(START_COLUMN));
                report.setEndTime(set.getTimestamp(END_COLUMN));
                report.setIncomes(set.getDouble(INCOMES_COLUMN));
                report.setExpenses(set.getDouble(EXPENSES_COLUMN));
                report.setClosed(set.getBoolean(IS_CLOSED_COLUMN));
                result.add(report);
            }
        } catch(SQLException e) {
            logger.info("Unable to execute query: \"" + query + "\"");
        }
        closeStatement(statement);
        return result;
    }

    @Override
    public Report findById(Integer id) {
        Report result = null;
        Statement statement = null;
        String query = "SELECT * FROM `" + TABLE_NAME + "`"
                + " WHERE " + ID_COLUMN + " = " + id + "";
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            if(set.next()) {
                result = new Report();
                result.setId(set.getInt(ID_COLUMN));
                result.setStartTime(set.getTimestamp(START_COLUMN));
                result.setEndTime(set.getTimestamp(END_COLUMN));
                result.setIncomes(set.getDouble(INCOMES_COLUMN));
                result.setExpenses(set.getDouble(EXPENSES_COLUMN));
                result.setClosed(set.getBoolean(IS_CLOSED_COLUMN));
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
    public boolean delete(Report entity) {
        boolean result = false;
        Statement statement = null;
        String query = "DELETE FROM `" + TABLE_NAME + "`"
                + " WHERE " + ID_COLUMN + " = " + entity.getId()
                + " AND " + START_COLUMN + " = " + entity.getStartTime()
                + " AND " + END_COLUMN + " = " + entity.getEndTime()
                + " AND " + INCOMES_COLUMN + " = " + entity.getIncomes()
                + " AND " + EXPENSES_COLUMN + " = " + entity.getExpenses()
                + " AND " + IS_CLOSED_COLUMN + " = " + entity.isClosed();
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
    public boolean create(Report entity) {
        boolean result = false;
        Statement statement = null;
        String query = "INSERT INTO `" + TABLE_NAME + "`"
                + " (" + START_COLUMN
                + ", " + END_COLUMN
                + ", " + INCOMES_COLUMN
                + ", " + EXPENSES_COLUMN
                + ", " + IS_CLOSED_COLUMN
                + ")"
                + " VALUES ('" + entity.getStartTime() + "'"
                + ", '" + entity.getEndTime() + "'"
                + ", " + entity.getIncomes()
                + ", " + entity.getExpenses()
                + ", " + entity.isClosed() + ")";
        try {
            statement = connection.createStatement();
            int count = statement.executeUpdate(query);
            result = count > 0;
        } catch(SQLException e) {
            e.printStackTrace();
            logger.info("Unable to execute query: \"" + query + "\"");
        }
        closeStatement(statement);
        return result;
    }

    @Override
    public boolean update(Report entity, Integer id) {
        boolean result = false;
        Statement statement = null;
        String query = "UPDATE `" + TABLE_NAME + "`"
                + " SET " + START_COLUMN +  " = '" + entity.getStartTime() + "'"
                + ", " + END_COLUMN + " = '" + entity.getEndTime() + "'"
                + ", " + INCOMES_COLUMN + " = " + entity.getIncomes()
                + ", " + EXPENSES_COLUMN + " = " + entity.getExpenses()
                + ", " + IS_CLOSED_COLUMN + " = " + entity.isClosed()
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
