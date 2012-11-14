/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Listener;

/**
 *
 * @author Alberto
 */
import Db.DbManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class WebappContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("sono in webappcontextlistener ------------ asd");
        String dburl = sce.getServletContext().getInitParameter("dburl");
        try {
            DbManager manager = new DbManager(dburl);
            sce.getServletContext().setAttribute("dbmanager", manager);//pubblico l'attributo per il context
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).severe(ex.toString());
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
// Il database Derby deve essere "spento" tentando di connettersi al database con shutdown = true
        DbManager.shutdown();
    }
}
