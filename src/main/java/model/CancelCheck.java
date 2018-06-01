package model;

import dao.*;
import model.entity.*;
import org.apache.logging.log4j.*;

import javax.naming.*;
import javax.sql.DataSource;
import java.sql.*;

public class CancelCheck {

    private static final Logger logger = LogManager.getLogger("MODEL");

    public CancelCheck() {
    }

    public boolean cancelCheck(int checkId) {
        boolean result = true;
        SoldProductDAO soldProductDAO = new SoldProductDAO(getConnection());
        CheckDAO checkDAO = new CheckDAO(getConnection());
        result = result && soldProductDAO.deleteAllByCheckId(checkId);
        Check check = checkDAO.findById(checkId);
        check.setCanceled(true);
        result = result && checkDAO.update(check, checkId);
        checkDAO.close();
        soldProductDAO.close();
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
