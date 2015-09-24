/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.access;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Adam
 */
public class AuthorDao implements DaoStrategy {
    private DbStrategy db;
    private String driverClass;
    private String url;
    private String username;
    private String password;

    public AuthorDao() {
    }

    public AuthorDao(DbStrategy db, String driverClass, String url, String username, String password) {
        //Validation/Getters/Setters
        this.db = db;
        this.driverClass = driverClass;
        this.url = url;
        this.username = username;
        this.password = password;
    }
    
    @Override
    public final List<Author> getAllAuthors() throws Exception {
        db.openConnection(driverClass, url, username, password);
        List<Author> records = new ArrayList<>();
        
        List<Map<String,Object>> rawData = db.findAllRecords("author");
        for(Map rawRec :rawData) {
            Author author = new Author();
            Object obj = rawRec.get("author_id");
            author.setAuthorId(Integer.parseInt(obj.toString()));
            
            obj = rawRec.get("author_name") == null ? "" : rawRec.get("author_name");
            author.setAuthorName(obj.toString());
            
            obj = rawRec.get("date_added") == null ? new Date() : rawRec.get("date_added");
            author.setDateAdded((Date)obj);
            
            records.add(author);
        }
        
        db.closeConnection();
        return records;
    }
    
    @Override
    public final void deleteAuthorRecord(String tableName, String primaryKey, Object id) throws Exception {
        db.openConnection(driverClass, url, username, password);
        db.deleteOneAuthorRecord(tableName, primaryKey, id);
        db.closeConnection();
    }
    
    @Override
    public final void createNewAuthorRecord(String tableName, List fields, List values) throws Exception {
        db.openConnection(driverClass, url, username, password);
        db.insertNewRecord(tableName, fields, values);
        db.closeConnection();
    }
    
    @Override
    public final void updateAuthorRecord(String tableName, List fields, List values, String whereField, Object whereValue) throws Exception {
        db.openConnection(driverClass, url, username, password);
        db.updateRecord(tableName, fields, values, whereField, whereValue);
        db.closeConnection();
    }
    
    public static void main(String[] args) throws Exception {
//        DbStrategy db = new MySqlDb();
//        AuthorDao dao = new AuthorDao(db,"com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book" , "amitchell8", "password");
//        System.out.println(dao.getAllAuthors());
    }
    
}
