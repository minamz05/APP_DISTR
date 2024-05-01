package web.Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.Models.Authmodel;

import java.io.IOException;

/**
 * Servlet implementation class login
 */
@WebServlet("/Login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
	    request.getRequestDispatcher("/WEB-INF/Auth.jsp").forward(request, response);
	}

	
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String login = request.getParameter("login");
	        String passw = request.getParameter("pwd");
	        if (Authmodel.authenticate(login, passw)) {
	            HttpSession session = request.getSession();
	            session.setAttribute("login", login);
	            request.getRequestDispatcher("/WEB-INF/HomePage.jsp").forward(request, response);
	        } else {
	            request.getRequestDispatcher("/WEB-INF/Auth.jsp").forward(request, response);
	        }
		}
}
