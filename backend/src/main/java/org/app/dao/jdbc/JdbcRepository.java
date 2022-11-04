package org.app.dao.jdbc;

import org.app.dao.CrudRepository;
import org.app.domain.DomainEntity;
//import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public abstract class JdbcRepository<T extends DomainEntity, Long> implements CrudRepository<T> {
    private DataSource dataSource;
    private Class<T> entityClass;
    protected String tableName;
    protected String idFieldName;

    // TODO: What is this jdbc for, configuration connection with the database? H2 database, are we using PostgresSQL instead?
    // What dependecies do we have to setup for the XML
    protected JdbcRepository(Class<T> entityClass, String tableName, String idFieldName) {
        this.entityClass = entityClass;
        this.tableName = tableName;
        this.idFieldName = idFieldName;
//        var dataSource = new JdbcDataSource();
//        dataSource.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
//        this.dataSource = dataSource;
    }

    protected static void executeDdl(Connection c, String ddlSql) throws SQLException {
        if (!c.getMetaData().getTables(null, null, "tbl_user", null).next()) {
            try (Statement statement = c.createStatement()) {
                statement.executeUpdate(ddlSql);
            }
        }
    }

    protected Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }


    protected abstract T resultSetToEntity(ResultSet resultSet) throws SQLException;

    @Override
    public Collection<T> findAll() {
        try (PreparedStatement statement = getConnection().prepareStatement(
                "SELECT * FROM " + tableName
        )) {
            var ret = new ArrayList<T>();
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ret.add(resultSetToEntity(resultSet));
                }
            }
            return ret;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
