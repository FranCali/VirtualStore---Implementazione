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

import it.unisa.beans.ContentBean;
import it.unisa.model.ContentModelDM;

@WebServlet("/createHintsControl")
public class createHintsControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ContentModelDM contentModel = new ContentModelDM();

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String substring = request.getParameter("string");
		LinkedList<ContentBean> contents = null;

		JSONArray hints = new JSONArray();
		JSONObject entry;

		try {
			contents = (LinkedList<ContentBean>) contentModel.doSearch(substring, "nome");

			for (ContentBean content : contents) {
				entry = new JSONObject();
				entry.put("nome", content.getName());
				entry.put("id", content.getId());
				hints.add(entry);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.setContentType("application/json");
		response.getWriter().write(hints.toJSONString());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
