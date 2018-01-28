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
import it.unisa.beans.AccountBean;
import it.unisa.beans.ClientBean;
import it.unisa.model.AccountModelDM;
import it.unisa.util.Encryptor;


@WebServlet("/UserProfileControl")
public class UserProfileControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String current = request.getParameter("currentPassword");
		String newPassword = request.getParameter("password");
		ClientBean client = (ClientBean) session.getAttribute("user");
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/UserProfileView.jsp");

		if (!current.equals("") && current != null) {
			if (!Encryptor.encryptPassword(current).equals(client.getAccount().getPassword())) {
				request.setAttribute("error", "password mismatching");
				dispatcher.forward(request, response);
			}
			try {
				AccountBean newAccount = new AccountBean(client.getAccount().getEmail(), newPassword);
				new AccountModelDM().doUpdatePassword(newAccount);
				response.sendRedirect("UserProfileView.jsp");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
