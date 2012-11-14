/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Db;

/**
 *
 * @author Alberto
 */
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DbManager implements Serializable {
// transient == non viene serializzato

    private transient Connection con;

    public DbManager(String dburl) throws SQLException {
        System.out.println("sono in DbManger ----------------------------------------------------asd");
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver", true, getClass().getClassLoader());
        } catch (Exception e) {
            throw new RuntimeException(e.toString(), e);
        }
        Connection con = DriverManager.getConnection(dburl);
        this.con = con;
    }

    public static void shutdown() {
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).info(ex.getMessage());
        }
    }

    /**
     * Autentica un utente in base a un nome utente e a una password
     *     
* @param username il nome utente
     * @param password la password
     * @return null se l'utente non è autenticato, un oggetto User se l'utente
     * esiste ed è autenticato
     */
    public User authenticate(String username, String password) throws SQLException {
// usare SEMPRE i PreparedStatement, anche per query banali.
// *** MAI E POI MAI COSTRUIRE LE QUERY CONCATENANDO STRINGHE !!!!
        PreparedStatement stm = con.prepareStatement(
                "SELECT * FROM users WHERE username =  ? AND  password =  ?");
        try {
            stm.setString(1, username);
            stm.setString(2, password);
            
            System.out.println("stm= "+stm.toString());
            ResultSet rs = stm.executeQuery();
            try {
               if(rs.next()) {
                    User user = new User();
                    user.setUserName(username);
                    return user;
                } else {
                    return null;
                }
            } finally {
// ricordarsi SEMPRE di chiudere i ResultSet in un blocco finally 
                rs.close();
            }
        } finally { // ricordarsi SEMPRE di chiudere i PreparedStatement in un blocco finally
            stm.close();
        }
    }
    
    
}
