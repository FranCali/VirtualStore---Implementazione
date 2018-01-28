package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unisa.model.ContentModelDM;


@WebServlet(urlPatterns = "/home")
public class ContentIndexControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    static ContentModelDM contentModelDM = new ContentModelDM();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try{
			session.setAttribute("role", "Guest");
			request.setAttribute("cover_contents", contentModelDM.doRetrieveByCovers());
			request.setAttribute("contents", contentModelDM.doRetrieveAll(null));
			
		}catch(SQLException e){
			System.out.println(e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
		
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
