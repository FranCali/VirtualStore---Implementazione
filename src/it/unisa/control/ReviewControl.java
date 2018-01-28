package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.beans.ReviewBean;
import it.unisa.model.ReviewModelDM;
import it.unisa.util.Data;

@WebServlet("/ReviewControl")
public class ReviewControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ReviewModelDM reviewModel = new ReviewModelDM();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		String title = request.getParameter("review_title");
		String description = request.getParameter("review_description");
		String userEmail = request.getParameter("user_email");
		String date = Data.creaData(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(),
				LocalDateTime.now().getDayOfMonth());

		int id = Integer.parseInt(request.getParameter("content_id"));

		int rating = Integer.parseInt(request.getParameter("review_rating"));
		String piva = request.getParameter("piva");

		if (action != null) {
			if (action.equals("addReview")) {

				if (title.length() > 4 && title.length() < 20 && description.length() > 20) {

					ReviewBean review = new ReviewBean();
					System.out.println(description);
					review.setContent_id(id);
					review.setTitle(title);
					review.setDescription(description);
					review.setReview_date(date);
					review.setRating(rating);
					review.setUser_email(userEmail);

					ReviewBean userReview;

					try {
						userReview = reviewModel.doRetrieveByContentIdandUserEmail(id, userEmail);
						if (userReview != null) {
							// THIS REVIEW ALREADY EXIST
							reviewModel.doUpdate(review);
						} else {
							reviewModel.doSave(review);
						}

					} catch (SQLException e) {

						e.printStackTrace();
					}
				}

				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher("/ContentShowControl?action=showContent&id=" + id + "&piva=" + piva);
				dispatcher.forward(request, response);
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
