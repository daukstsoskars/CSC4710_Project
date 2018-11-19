package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import database.InitializeDB;
import database.MakeDbConnection;

@WebServlet("/InitializeDBServ")
public class InitializeDBServ extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        InitializeDB initDB = new InitializeDB();
        try {
            initDB.createTable(MakeDbConnection.getConnection());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/jsps/post_login.jsp");
    }
}