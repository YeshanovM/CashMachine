package model;

import dao.*;
import model.entity.*;
import org.apache.logging.log4j.*;

import javax.naming.*;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class ReportModel {

    private static final Logger logger = LogManager.getLogger("MODEL");

    public ReportModel() {
    }

    public Report XReport() {
        ReportDAO reportDAO = new ReportDAO(getConnection());
        CheckDAO checkDAO = new CheckDAO(getConnection());
        List<Report> reports = reportDAO.findAll();
        Report report = new Report();
        report.setClosed(false);
        report.setEndTime(new Timestamp(System.currentTimeMillis()));
        if (reports.size() == 0) {
            report.setStartTime(new Timestamp(1000));
        }
        else {
            Report lastReport = reports.stream()
                    .max(Comparator.comparingLong(a -> a.getEndTime().getTime()))
                    .get();
            report.setStartTime(lastReport.getEndTime());
        }
        List<Check> checks = checkDAO.findAll();
        checks = checks.stream()
                .filter(check -> check.getTimestamp().getTime() < report.getEndTime().getTime()
                        && check.getTimestamp().getTime() > report.getStartTime().getTime())
                .collect(Collectors.toList());
        double incomes = 0, expenses = 0;
        for(Check check : checks) {
            if(check.isCanceled())
                expenses += getCheckSum(check.getId());
            else
                incomes += getCheckSum(check.getId());
        }
        report.setExpenses(expenses);
        report.setIncomes(incomes);
        reportDAO.close();
        checkDAO.close();
        return report;
    }

    public Report ZReport() {
        ReportDAO reportDAO = new ReportDAO(getConnection());
        Report report = XReport();
        reportDAO.create(report);
        reportDAO.close();
        return report;
    }

    private double getCheckSum(int checkId) {
        SoldProductDAO soldProductDAO = new SoldProductDAO(getConnection());
        List<SoldProduct> soldProducts = soldProductDAO.findAllByCheckId(checkId);
        double sum = soldProducts.stream().mapToDouble(x -> x.getQuantity() * x.getPrice()).sum();
        soldProductDAO.close();
        return sum;
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
