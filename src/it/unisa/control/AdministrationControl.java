package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import it.unisa.beans.ClientBean;
import it.unisa.beans.ContentBean;
import it.unisa.beans.ReviewBean;
import it.unisa.model.ClientModelDM;
import it.unisa.model.ContentModelDM;
import it.unisa.model.ReviewModelDM;

@WebServlet("/AdministrationControl")
public class AdministrationControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		ContentModelDM contentModel = new ContentModelDM();
		ReviewModelDM reviewModel = new ReviewModelDM();
		ClientModelDM clientModelDM = new ClientModelDM();
		ClientBean client = (ClientBean) request.getSession().getAttribute("user");

		try {
			if (client == null || (!clientModelDM.checkIfAdmin(client) && !clientModelDM.checkIfManager(client))) {
				System.err.println("problema");
				return;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		if (action != null) {

			ContentBean content;

			try {

				if (action.equals("removeRequest")) {
					String email = request.getParameter("email");
					
					if(clientModelDM.doDelete(clientModelDM.doRetriveByEmail(email)));
						response.getWriter().write(email);

				}
				if (action.equals("addContent")) {

					float price = (float) Double.parseDouble(request.getParameter("price"));
					String name = request.getParameter("name");
					String type = request.getParameter("type");
					String icon = request.getParameter("icon");
					String description = request.getParameter("description");
					int size = Integer.parseInt(request.getParameter("size"));

					content = new ContentBean();

					content.setType(type);
					content.setIcon(icon);
					content.setSize(size);
					content.setName(name);
					content.setPrice(price);
					content.setIcon(icon);
					content.setDescription(description);

					contentModel.doSave(content);

				} else if (action.equals("deleteContent")) {
					int id = Integer.parseInt(request.getParameter("id"));

					JSONObject obj = new JSONObject();
					content = contentModel.doRetrieveByKey(id);

					if (contentModel.doDelete(content)) {
						obj.put("content_name", content.getName());
						obj.put("content_id", content.getId());

						response.getWriter().write(obj.toJSONString());
					}
				} else if (action.equals("deleteUser")) {
					String email = request.getParameter("email");
					System.out.println("sto nel metodo");
					if (clientModelDM.doDelete(clientModelDM.doRetriveByEmail(email)))
						response.getWriter().write(email);
				}

				else if (action.equals("deleteReview")) {
					int contentId = Integer.parseInt(request.getParameter("content_id"));
					String userEmail = request.getParameter("email");

					ReviewBean review = reviewModel.doRetrieveByContentIdandUserEmail(contentId, userEmail);

					if (reviewModel.doDelete(review))
						response.getWriter().write(userEmail);
				}

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
