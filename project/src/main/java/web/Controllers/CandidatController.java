package web.Controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import web.Models.CandidatDB;
import web.Models.Candidat;
/**
 * Servlet implementation class CandidatController
 */
@WebServlet("/Candidat")
public class CandidatController extends HttpServlet {
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CandidatDB candidatDB;
	
	public void init() {
		candidatDB = new CandidatDB();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/insert":
				insertCandidat(request, response);
				break;
			case "/delete":
				deleteCandidat(request, response);
				break;
			
			case "/update":
				updateCandidat(request, response);
				break;
			default:
				listCandidat(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listCandidat(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {
	    List<Candidat> listCandidat = candidatDB.selectAllCandidats();
	    request.setAttribute("listCandidat", listCandidat);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Candidat.jsp");
	    dispatcher.forward(request, response);
	}

	
	private void insertCandidat(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String Name = request.getParameter("Name");
		String Surname = request.getParameter("Surname");
		String Position = request.getParameter("Position");
		Candidat newCandidat = new Candidat(Name, Surname, Position);
		candidatDB.insertCandidat(newCandidat);
		response.sendRedirect("list");
	}

	private void updateCandidat(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int IdCandidat = Integer.parseInt(request.getParameter("IdCandidat"));
		String Name = request.getParameter("Name");
		String Surname = request.getParameter("Surname");
		String Position = request.getParameter("Position");

		Candidat book = new Candidat(IdCandidat, Name, Surname, Position);
		candidatDB.updateCandidat(book);
		response.sendRedirect("list");
	}

	private void deleteCandidat(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int IdCandidat = Integer.parseInt(request.getParameter("IdCandidat"));
		candidatDB.deleteCandidat(IdCandidat);
		response.sendRedirect("list");

	}
	}
	