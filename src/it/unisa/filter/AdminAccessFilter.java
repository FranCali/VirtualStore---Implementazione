package it.unisa.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unisa.beans.ClientBean;
import it.unisa.model.ClientModelDM;

@WebFilter(filterName = "AdminAccessFilter", urlPatterns = { "/admin/*" })
public class AdminAccessFilter implements Filter {

	private ClientModelDM clientModelDM = new ClientModelDM();
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httprequest = (HttpServletRequest) request;
		HttpServletResponse httpresponse = (HttpServletResponse) response;

		HttpSession session = httprequest.getSession();

		ClientBean user = (ClientBean) session.getAttribute("user");

		try {
			if (!(clientModelDM.checkIfAdmin(user) || clientModelDM.checkIfManager(user)))
				httpresponse.sendError(401, "Non autorizzato");
			else
				chain.doFilter(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
