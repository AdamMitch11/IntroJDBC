/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.access;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adam
 */
public class AuthorService {
    private DbStrategy db;
    private DaoStrategy dao;
    private String driverClass;
    private String url;
    private String username;
    private String password;

    public AuthorService() {
    }

    public AuthorService(DaoStrategy dao) {
        this.dao = dao;
    }
    
    public final List<Author> getAuthorList() throws Exception {
        return dao.getAllAuthors();
    }
    
    public final void deleteAuthorRecord(String tableName, String primaryKey, Object id) throws Exception {
        dao.deleteAuthorRecord(tableName, primaryKey, id);
    }
    
    public final void createNewAuthorRecord(String tableName, List fields, List values) throws Exception {
        dao.createNewAuthorRecord(tableName, fields, values);
    }
//    
    public final void updateExistingAuthorRecord(String tableName, List fields, List values, String whereField, Object whereValue) throws Exception {
        dao.updateAuthorRecord(tableName, fields, values, whereField, whereValue);
    }
    
    public static void main(String[] args) throws Exception {
        DbStrategy db = new MySqlDb();
        AuthorDao dao = new AuthorDao(db,"com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book" , "amitchell8", "password");
        AuthorService srv = new AuthorService(dao);
        //Test Read
//        System.out.println(srv.getAuthorList());
        
        //Test Delete
//        srv.deleteAuthorRecord("author", "author_id", 6);
        
        //Test Create
//        List fields = new ArrayList();
//        fields.add("author_name");
//        fields.add("date_added");
//        List values = new ArrayList();
//        values.add("Adam Mitchell");
//        values.add("2015-09-24");
//        srv.createNewAuthorRecord("author", fields, values);
        
        //Test Update
//        List fields = new ArrayList();
//        fields.add("author_name");
//        List values = new ArrayList();
//        values.add("Samantha Mitchell");
//        Object name = "Adam Mitchell";
//        srv.updateExistingAuthorRecord("author", fields, values, "author_name", name);
    }
}
