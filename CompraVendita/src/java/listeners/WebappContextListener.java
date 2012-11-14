/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import DB.DbManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Alberto
 */
public class WebappContextListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String Dburl= sce.getServletContext().getInitParameter("Dburl");
        try{
            DbManager manager = new DbManager(Dburl);
            sce.getServletContext().setAttribute("DbManager", manager);
        }catch(SQLException ex) {
        Logger.getLogger(getClass().getName()).severe(ex.toString());
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DbManager.shutdown();
    }
    

    
}
