package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.unisa.beans.ClientBean;
import it.unisa.beans.ContentBean;
import it.unisa.beans.DeletionAccountRequest;
import it.unisa.beans.ReviewBean;
import it.unisa.model.ClientModelDM;
import it.unisa.model.ContentModelDM;
import it.unisa.model.DeletionAccountRequestModelDM;
import it.unisa.model.ReviewModelDM;

/**
 * Servlet implementation class ControlPanelTables
 */
@WebServlet("/ControlPanelTables")
public class ControlPanelTables extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONArray array;
	JSONObject obj;
	ContentModelDM contentModel = new ContentModelDM();
	ClientModelDM clientModelDM = new ClientModelDM();
	ReviewModelDM reviewModel = new ReviewModelDM();
	DeletionAccountRequestModelDM accountRequestModelDM = new DeletionAccountRequestModelDM();

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		LinkedList<ContentBean> contents;
		LinkedList<ClientBean> users;
		LinkedList<ReviewBean> reviews;
		LinkedList<DeletionAccountRequest> requests;

		if (action != null) {
			String output = null;
			array = new JSONArray();
			if (action.equals("createDeletionRequestsTable")) {
				try {
					requests = (LinkedList<DeletionAccountRequest>) accountRequestModelDM.doRetrieveAll();
					users = (LinkedList<ClientBean>) clientModelDM.doRetriveAll("nome");

					for (ClientBean client : users) {

						for (DeletionAccountRequest deletionRequest : requests) {
							if (client.getAccount().getEmail().equals(deletionRequest.getClientEmail())) {
								obj = new JSONObject();
								obj.put("client_name", client.getName());
								obj.put("client_surname", client.getSurname());
								obj.put("client_email", client.getAccount().getEmail());
								obj.put("request_date", deletionRequest.getRequestDate());
								
								array.add(obj);
							}
						}

					}
					output = array.toJSONString();

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
			if (action.equals("createContentsTable")) {

				try {
					contents = (LinkedList<ContentBean>) contentModel.doRetrieveAll("nome");

					for (ContentBean content : contents) {
						obj = new JSONObject();

						obj.put("content_name", content.getName());
						obj.put("content_id", content.getId());
						obj.put("content_price", content.getPrice());
						obj.put("content_type", content.getType());

						array.add(obj);
					}
					output = array.toJSONString();

				} catch (SQLException e) {
					e.printStackTrace();
				}

			} else if (action.equals("createUsersTable")) {
				try {
					users = (LinkedList<ClientBean>) clientModelDM.doRetriveAll("nome");

					for (ClientBean user : users) {
						obj = new JSONObject();

						obj.put("user_name", user.getName());
						obj.put("user_surname", user.getSurname());
						obj.put("user_email", user.getAccount().getEmail());

						if (!clientModelDM.checkIfAdmin(user) && !clientModelDM.checkIfManager(user))
							array.add(obj);
					}
					output = array.toJSONString();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (action.equals("createReviewsTable")) {
				String search = request.getParameter("search");

				try {
					int content_id = 0;

					contents = (LinkedList<ContentBean>) contentModel.doRetrieveAll(null);

					for (ContentBean content : contents) {
						if (content.getName().equalsIgnoreCase(search))
							content_id = content.getId();
					}

					if (content_id != 0) {
						reviews = (LinkedList<ReviewBean>) reviewModel.doRetrieveByContentId(content_id);

						if (reviews.size() > 0) {
							for (ReviewBean review : reviews) {
								obj = new JSONObject();

								obj.put("review_id", review.getContent_id());
								obj.put("title", review.getTitle());
								obj.put("author", review.getUser_email());
								obj.put("description", review.getDescription());
								obj.put("rating", review.getRating());

								array.add(obj);
							}
							output = array.toJSONString();
						}
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (output != null)
				response.getWriter().write(output);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
