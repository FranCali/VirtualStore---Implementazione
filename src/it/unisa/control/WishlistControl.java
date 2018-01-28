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

import it.unisa.beans.ClientBean;
import it.unisa.beans.ContentBean;
import it.unisa.beans.WishlistBean;
import it.unisa.model.ContentModelDM;
import it.unisa.model.WishlistModelDM;

/**
 * Servlet implementation class WishlistControl
 */
@WebServlet("/WishlistControl")
public class WishlistControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		WishlistModelDM wishModel = new WishlistModelDM();
		ContentModelDM contentModel = new ContentModelDM();
		WishlistBean wishlist = null;
		ClientBean client = (ClientBean) session.getAttribute("user");
		ContentBean content = null;
		String action = request.getParameter("action");
		String piva = request.getParameter("piva");
		String page = request.getParameter("page");

		if (session.getAttribute("wishlist") == null) {
			wishlist = new WishlistBean();
		} else {
			wishlist = (WishlistBean) session.getAttribute("wishlist");

			if (action.equals("insert")) {

				int id = Integer.parseInt(request.getParameter("id"));

				if (wishlist.getElementById(id) == null) {
					try {
						// ADD TO WISHLIST CLASS
						content = contentModel.doRetrieveByKey(id);
						wishlist.insertContent(content);

						// ADD TO DB
						wishModel.doInsertContent(client.getAccount().getEmail(), content.getId());

						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(
								"/ContentShowControl?action=showContent&id=" + id + "&piva=" + piva);
						dispatcher.forward(request, response);

					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} else if (action.equals("remove")) {
				int id = Integer.parseInt(request.getParameter("id"));

				try {

					// REMOVE FROM WISHLIST CLASS
					wishlist.deleteContent(contentModel.doRetrieveByKey(id));

					// REMOVE FROM DB
					wishModel.doRemoveContent(client.getAccount().getEmail(), id);
					System.out.println(page);
					if (page.equals("ContentView")) {
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(
								"/ContentShowControl?action=showContent&id=" + id);
						dispatcher.forward(request, response);
					} else if (page.equals("WishlistView"))
						response.sendRedirect("WishlistView.jsp");

				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (action.equals("removeAll")) {

				try {
					// REMOVE ALL FROM WISHLIST CLASS
					wishlist.deleteAllContents();

					// REMOVE FROM DB
					wishModel.doRemoveAllContents(client.getAccount().getEmail());

					response.sendRedirect("WishlistView.jsp");

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
