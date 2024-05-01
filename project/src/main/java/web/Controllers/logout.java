package web.Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.Models.LogoutModel;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/Add")
public class logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   
	    request.getRequestDispatcher("/WEB-INF/Index.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        if (session.getAttribute("login") != null) {
            String name = request.getParameter("Name");
            String surname = request.getParameter("Surname");
            String date = request.getParameter("Date");
            String email = request.getParameter("Email");
            String password = request.getParameter("Password");
            try {
                LogoutModel.addUser(name, surname, date, email, password);
                request.getRequestDispatcher("/WEB-INF/Index.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("Auth.jsp");
        }
    }
}