/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.access;

import java.util.List;

/**
 *
 * @author Adam
 */
public interface DaoStrategy {

    public abstract List<Author> getAllAuthors() throws Exception;
    
    public abstract void updateAuthorRecord(String tableName, List fields, List values, String whereField, Object whereValue) throws Exception;
    
    public abstract void createNewAuthorRecord(String tableName, List fields, List values) throws Exception;
    
    public abstract void deleteAuthorRecord(String tableName, String primaryKey, Object id) throws Exception;

    
    


    
}
