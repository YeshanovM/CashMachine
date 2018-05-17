package dao;

import model.entity.*;

import java.sql.*;
import java.util.*;

public abstract class AbstractDAO <K, V extends Entity> {

    protected Connection connection;

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    public abstract List<V> findAll();
    public abstract V findById(K id);
    public abstract boolean delete(K id);
    public abstract boolean delete(V entity);
    public abstract boolean create(V entity);
    public abstract boolean update(V entity, K id);

}
