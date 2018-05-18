package dao;

import model.entity.*;
import org.apache.logging.log4j.*;

import java.sql.*;
import java.util.*;

public abstract class AbstractDAO <K, V extends Entity> {

    Connection connection;
    static final Logger logger = LogManager.getLogger("DATABASE");

    AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    public abstract List<V> findAll();
    public abstract V findById(K id);
    public abstract boolean delete(K id);
    public abstract boolean delete(V entity);
    public abstract boolean create(V entity);
    public abstract boolean update(V entity, K id);

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.warn("Unable to close connection with database");
        }

    }

    void closeStatement(Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            logger.warn("Unable to close statement");
        }
    }
}
