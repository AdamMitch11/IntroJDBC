/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.access;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Adam
 */
public class MySqlDb implements DbStrategy {
//    private String driverClass;
//    private String url;
//    private String username;
//    private String password;
    private Connection conn;
    
    
    @Override
    public final void openConnection(String driverClass, String url, String username, String password) throws Exception {
        
        //Insert Validation
        
        Class.forName (driverClass);
        conn = DriverManager.getConnection(url, username, password);
    }
    
    @Override
    public final void closeConnection() throws SQLException {
        conn.close();
    }
    
    @Override
    public final List<Map<String,Object>> findAllRecords(String tableName) throws SQLException{
        
        //Validate tableName
        
        String sql ="SELECT * FROM " + tableName;
        List<Map<String,Object>> recordList = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData metaData = rs.getMetaData(); //Determines column names
        final int fields = metaData.getColumnCount(); //Returns int for total column count
        
        while(rs.next()) {
            Map<String,Object> record = new HashMap<>();
            for(int i=1; i <= fields; i++) {
                record.put(metaData.getColumnName(i), rs.getObject(i));
            }
            recordList.add(record);
        }
        return recordList;
    }
    
    @Override
    public final void deleteOneAuthorRecord(String tableName, String primaryKey, Object id) throws SQLException {
        //String sql = "DELETE FROM " + tableName + " WHERE " + primaryKey + " = ";
        // + primaryKey.toString() + " = " + id;
        
//        if (id instanceof String) {
//            sql+="'" + id.toString() + "'";
//        } else {
//            sql+= id.toString();
//        }
        
        //Statement stmt = conn.createStatement();
        //ResultSet rs = stmt.executeQuery(sql);
        
        //PreparedStatement pstmt = null;
        //pstmt = buildDeleteStatement(conn,tableName,primaryKey);
        
        //stmt.executeUpdate(sql);
        
        //Using Prepared Statements
        String sql2 = "DELETE FROM " + tableName + " WHERE " + primaryKey + " = ?";
        PreparedStatement ps = conn.prepareStatement(sql2);
        
        ps.setObject(1, id);
        ps.executeUpdate();
        
    }
    
    @Override
    public final void insertNewRecord(String tableName, List fields, List values) throws SQLException {
//        String sql = "INSERT INTO " + tableName + " (" + fields + ") VALUES (" + values + ")";
//        //Could also use 1 Map vs 2 Lists
//        Statement stmt = conn.createStatement();
//        
//        stmt.execute(sql);
        
        PreparedStatement ps = null;
        
        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(tableName).append(" (");
        for (Object header : fields) {
            sb.append(header.toString()).append(", ");    
        }
        sb = new StringBuffer( (sb.toString()).substring( 0,(sb.toString()).lastIndexOf(", ") ) + ") VALUES (" );
        for (Object header : fields) {
            sb.append("?, ");
        }
        final String sqli=(sb.toString()).substring(0,(sb.toString()).lastIndexOf(", ")) + ")";
        int count=1;
        System.out.println(sqli);
        ps = conn.prepareStatement(sqli);
        for (Object v : values) {
            ps.setObject(count, v);
            count++;
        }
        
        ps.executeUpdate();
    }
    
    @Override
    public final void updateRecord(String tableName, List fields, List values, String field, Object value) throws SQLException{
    //To be continued...
        PreparedStatement ps = null;
        StringBuffer sb = new StringBuffer("UPDATE ");
        sb.append(tableName).append(" SET ");
        for (Object f : fields) {
            sb.append(f.toString()).append(" = ?, ");
        }
        sb = new StringBuffer((sb.toString()).substring(0,(sb.toString()).lastIndexOf(", ")));
        sb.append(" WHERE ").append(field).append(" = ?");
        
        final String sbf = sb.toString();
        ps = conn.prepareStatement(sbf);
        int count=1;
        
        for (Object v: values) {
            ps.setObject(count, v);
            count++;
        }
        ps.setObject(count, value);
        System.out.println(ps);
        ps.executeUpdate();
    }
    
    public static void main(String[] args) throws Exception {
        MySqlDb db = new MySqlDb();
        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book" , "amitchell8", "password");
        //TEST C
//        List fields = new ArrayList();
//        List values = new ArrayList();
//        fields.add("author_name");
//        fields.add("date_added");
//        values.add("Adam Mitchell");
//        values.add("2015-09-23");
//        db.insertNewRecord("author", fields, values);
        
        //TEST R
//        List<Map<String,Object>> records = db.findAllRecords("author");
//        for (Map record: records) {
//            System.out.println(record);
//        }
        //List fields = new ArrayList();
        //fields.add("")
        
        //TEST U
//        List fields = new ArrayList();
//        fields.add("author_name");
//        List values = new ArrayList();
//        values.add("Samantha Mitchell");
//        Object name = "Adam Mitchell";
//        db.updateRecord("author", fields, values, "author_name", name);
        
        //TEST D
//        db.deleteOneAuthorRecord("author", "author_id", 6);
        
        db.closeConnection();

    }
}
