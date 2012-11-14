/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Db.DbManager;
import Db.User;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alberto
 */
public class Login extends HttpServlet {

    private DbManager manager;


    @Override
    public void init() throws ServletException {
// inizializza il DBManager dagli attributi di Application
        this.manager = (DbManager)super.getServletContext().getAttribute("DbManager");
        System.out.println("sono in init "+ (DbManager)super.getServletContext().getAttribute("dbmanager"));
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        String username = req.getParameter("userName");
        String password = req.getParameter("password");
// controllo nel DB se esiste un utente con lo stesso username + password
        User user;
        System.out.println("username: "+username+" pass: "+ password);
        try {
            user = manager.authenticate(username, password);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
// se non esiste, ridirigo verso pagina di login con messaggio di errore
        if (user == null) {
            req.setAttribute("message", "Username/password non esistente !");
            RequestDispatcher rd = req.getRequestDispatcher("/errorelogin");
            rd.forward(req, resp);
        } else {

// imposto l'utente connesso come attributo di sessione
// per adesso e' solo un oggetto String con il nome dell'utente,
//            ma posso metterci anche un oggetto User // con, ad esempio, il timestamp di login
                    HttpSession session = req.getSession(true);
            session.setAttribute("user", user);
// mando un redirect alla servlet che carica i prodotti
            resp.sendRedirect(req.getContextPath() + "/Welcome");
        }
    }
}