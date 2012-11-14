/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.io.Serializable;
import java.sql.*;
import java.util.logging.Logger;

/**
 *
 * @author Alberto
 */
public class DbManager implements Serializable {

    private transient Connection con;

    public DbManager(String Dburl) throws SQLException {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver", true, getClass().getClassLoader());
        } catch (Exception e) {
            throw new RuntimeException(e.toString(), e);
        }
        Connection con = DriverManager.getConnection(Dburl);
        this.con = con;
    }

    //shutdown
    public static void shutdown() {
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).info(ex.getMessage());
        }
    }

    //autenticazione
    public User autenticazione(String Username, String Password) throws SQLException {
        PreparedStatement stm = con.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
        try {
            stm.setString(1, Username);
            stm.setString(2, Password);
            ResultSet rs = stm.executeQuery();
            try {
                if (rs.next()) {
                    User user = new User();
                    user.setUsername(Username);
                    user.setPassword(Password);

                    return user;
                } else {
                    return null;
                }
            } finally {
                rs.close();
            }
        } finally {
            stm.close();
        }
    }
}
