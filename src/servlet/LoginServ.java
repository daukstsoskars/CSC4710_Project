package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.MakeDbConnection;
import database.DB_Query;


@WebServlet("/LoginServ")
public class LoginServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServ() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Create connection between database in order for Login to authenticate the user
		MakeDbConnection dbConnection = new MakeDbConnection();
		dbConnection.InitDB();
		DB_Query user = new DB_Query();
		boolean accepted = false;
		try {
			accepted = user.findByUsername(request.getParameter("username"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		if(!request.getParameter("password").equals("pass1234")){
			accepted = false;
		}

		if(accepted){
				request.getSession().setAttribute("session_user", user);
				request.getRequestDispatcher("/jsps/post_login.jsp").forward(request, response);
		}
		else{
			request.setAttribute("msg", "Wrong credentials");
			request.getRequestDispatcher("/jsps/login.jsp").forward(request, response);
		}
		
		
		
	}

}
