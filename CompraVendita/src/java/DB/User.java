/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.io.Serializable;

/**
 *
 * @author Alberto
 */
public class User implements Serializable{
    private String username, password;
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername( String Username){
        this.username= Username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword( String Password){
        this.password= Password;
    }    
}
