/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.access;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Adam
 */
public interface DbStrategy {

    public abstract void closeConnection() throws SQLException;

    public abstract List<Map<String, Object>> findAllRecords(String tableName) throws SQLException;

    public abstract void openConnection(String driverClass, String url, String username, String password) throws Exception;
    
    public abstract void deleteOneAuthorRecord(String tableName, String primaryKey, Object id) throws SQLException;
    
    public abstract void insertNewRecord(String tableName, List fields, List values) throws SQLException;
    
    public abstract void updateRecord(String tableName, List fields, List values, String field, Object value) throws SQLException;
    
}
