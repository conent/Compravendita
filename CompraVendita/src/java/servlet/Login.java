/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DB.DbManager;
import DB.User;
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
        this.manager = (DbManager) super.getServletContext().getAttribute("DbManager");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user;
        try {
            user = manager.autenticazione(username, password);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
        if (user== null){
            req.setAttribute("message", "Usern/password errati");
            RequestDispatcher rd= req.getRequestDispatcher("/errorelogin");
            rd.forward(req, resp);
        }else {
            HttpSession session= req.getSession(true);
            session.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath()+"/Welcome");
        }
    }
}
