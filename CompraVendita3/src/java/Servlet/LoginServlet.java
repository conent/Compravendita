/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Db.ConnectionFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public LoginServlet() {
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        String userName = request.getParameter("userName");
        String userPass = request.getParameter("userPassWord");
        String userRePass = request.getParameter("userRePassWord");

        try {
            String queryString = "SELECT username FROM users WHERE VendorPass=?";
            connection = ConnectionFactory.getInstance().getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, userPass);
            resultSet = ptmt.executeQuery();
            // Creating Servlet Context object
            if (resultSet.next()
                    && userName.equalsIgnoreCase(resultSet
                    .getString("username"))) {
                HttpSession session = request.getSession(true);
                session.setAttribute("logged", resultSet.getString(1));

                ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context
                        .getRequestDispatcher("/success");
                dispatcher.forward(request, response);
                // or you can write whole thing in one line as ........

                // getServletContext().getRequestDispatcher("/success").forward(request,
                // response);

            } else {
                request.setAttribute("wrongUser", userName);

                ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context
                        .getRequestDispatcher("/fail");
                dispatcher.forward(request, response);

                // or you can write whole thing in one line as ........
                // getServletContext().getRequestDispatcher("/fail").forward(request,
                // response);
            }

        } catch (SQLException e) {
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }
}