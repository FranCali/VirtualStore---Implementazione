package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import it.unisa.beans.ClientBean;
import it.unisa.beans.ContentBean;
import it.unisa.model.ContentModelDM;
import it.unisa.model.DownloadModelDM;
import it.unisa.model.ReviewModelDM;
import it.unisa.util.InfoDownload;

@WebServlet("/ContentShowControl")
public class ContentShowControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ContentModelDM contentModel = new ContentModelDM();
	ReviewModelDM reviewModel = new ReviewModelDM();
	DownloadModelDM downloadModel = new DownloadModelDM();

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		LinkedList<ContentBean> contents = null;
		String sort = request.getParameter("sort");

		if (action != null) {

			try {
				if (action.equals("showContent")) {

					int id = Integer.parseInt(request.getParameter("id"));
					ClientBean client = (ClientBean) session.getAttribute("user");

					try {
						request.setAttribute("content", contentModel.doRetrieveByKey(id));

						if (client != null) {
							request.setAttribute("user_review",
									reviewModel.doRetrieveByContentIdandUserEmail(id, client.getAccount().getEmail()));
							LinkedList<InfoDownload> downloads = (LinkedList<InfoDownload>) downloadModel
									.doRetriveAllByEmail(client.getAccount().getEmail());
							session.setAttribute("downloads", downloads);
						}
						request.setAttribute("reviews", reviewModel.doRetrieveByContentId(id));

					} catch (SQLException e) {
						System.out.println(e.getMessage());
						request.setAttribute("error", e.getMessage());
					}

					RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/ContentView.jsp");
					dispatcher.forward(request, response);
				} else if (action.equals("showApps")) {
					contents = (LinkedList<ContentBean>) contentModel.doRetrieveByType("Applicazione", "nome");
					request.removeAttribute("contents");
					request.setAttribute("contents", contents);
					request.setAttribute("type", "Applicazione");
					request.setAttribute("type-active", "App");
					request.setAttribute("sort", "nome");

					RequestDispatcher dispatcher = request.getServletContext()
							.getRequestDispatcher("/ContentTypeView.jsp");
					dispatcher.forward(request, response);
				} else if (action.equals("showMusics")) {
					contents = (LinkedList<ContentBean>) contentModel.doRetrieveByType("Musica", "nome");
					request.removeAttribute("contents");
					request.setAttribute("contents", contents);
					request.setAttribute("type", "Musica");
					request.setAttribute("type-active", "Music");
					request.setAttribute("sort", "nome");

					RequestDispatcher dispatcher = request.getServletContext()
							.getRequestDispatcher("/ContentTypeView.jsp");
					dispatcher.forward(request, response);
				} else if (action.equals("showFilms")) {
					contents = (LinkedList<ContentBean>) contentModel.doRetrieveByType("Film", "nome");
					request.removeAttribute("contents");
					request.setAttribute("contents", contents);
					request.setAttribute("type", "Film");
					request.setAttribute("type-active", "Film");
					request.setAttribute("sort", "nome");

					RequestDispatcher dispatcher = request.getServletContext()
							.getRequestDispatcher("/ContentTypeView.jsp");
					dispatcher.forward(request, response);
				} else if (action.equals("showBooks")) {
					contents = (LinkedList<ContentBean>) contentModel.doRetrieveByType("Libro", "nome");
					request.removeAttribute("contents");
					request.setAttribute("contents", contents);
					request.setAttribute("type", "Libro");
					request.setAttribute("type-active", "Book");
					request.setAttribute("sort", "nome");

					RequestDispatcher dispatcher = request.getServletContext()
							.getRequestDispatcher("/ContentTypeView.jsp");
					dispatcher.forward(request, response);
				} else if (action.equals("showWishlist")) {

					ClientBean client = (ClientBean) session.getAttribute("user");

					if (client == null) {
						request.removeAttribute("error");
						request.setAttribute("error", "Login to show your contents");
					} else
						response.sendRedirect("WishlistView.jsp");
				} else if (action.equals("search")) {
					String substring = request.getParameter("string");
					contents = (LinkedList<ContentBean>) contentModel.doSearch(substring, "nome");

					if (contents.size() == 0) {
						request.removeAttribute("error");
						request.removeAttribute("contents");
						request.setAttribute("error", "No results found");
					} else {
						request.removeAttribute("contents");
						request.setAttribute("contents", contents);
						request.setAttribute("sort", "nome");
					}

					RequestDispatcher dispatcher = request.getServletContext()
							.getRequestDispatcher("/ContentTypeView.jsp");
					dispatcher.forward(request, response);
				} else if (action.equals("showMostSold")) {

					DownloadModelDM downloadModel = new DownloadModelDM();
					Object[] result = downloadModel.doRetriveByMostSold();
					contents = (LinkedList<ContentBean>) result[0];
					LinkedList<String> numDownload = (LinkedList<String>) result[1];
					request.setAttribute("contents", contents);
					request.setAttribute("numDownload", numDownload);
					RequestDispatcher dispatcher = request.getServletContext()
							.getRequestDispatcher("/ContentTypeView.jsp");
					dispatcher.forward(request, response);
				} else if (action.equals("showPopularApps")) {

					ReviewModelDM reviewModelDM = new ReviewModelDM();
					Object[] result = reviewModelDM.doRetriveAllByAverage();
					contents = (LinkedList<ContentBean>) result[0];
					LinkedList<Float> average = (LinkedList<Float>) result[1];
					request.setAttribute("contents", contents);
					request.setAttribute("average", average);
					RequestDispatcher dispatcher = request.getServletContext()
							.getRequestDispatcher("/ContentTypeView.jsp");
					dispatcher.forward(request, response);
				} 
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				request.setAttribute("error", e.getMessage());
			}

		}

		try {
			if (sort != null) {

				if (sort.equals("nome"))
					contents = (LinkedList<ContentBean>) contentModel.doRetrieveByType(request.getParameter("type"),
							"nome");
				else if (sort.equals("prezzo"))
					contents = (LinkedList<ContentBean>) contentModel.doRetrieveByType(request.getParameter("type"),
							"prezzo");

				request.removeAttribute("contents");
				request.setAttribute("contents", contents);
				request.setAttribute("type", request.getParameter("type"));
				request.setAttribute("sort", sort);

				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/ContentTypeView.jsp");
				dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
