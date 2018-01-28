package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unisa.beans.ClientBean;
import it.unisa.beans.PaymentBean;
import it.unisa.model.PaymentModelDM;

@WebServlet("/MethodPaymentControl")
public class MethodPaymentControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		PaymentModelDM paymentmodel = new PaymentModelDM();
		ClientBean client = (ClientBean) session.getAttribute("user");
		PaymentBean payment = (PaymentBean) session.getAttribute("method");
		String action = request.getParameter("action");

		if (action != null) {

			if (action.equals("controlPayment")) {
				try {
					payment = paymentmodel.doRetriveByEmail(client.getAccount().getEmail());
					if (payment == null) {
						response.sendRedirect("PaymentView.jsp");

					} else {
						session.removeAttribute("method");
						session.setAttribute("method", payment);
						response.sendRedirect("PaymentView.jsp");
					}

				} catch (SQLException e) {
					System.out.println(e.getMessage());
					request.setAttribute("error", e.getMessage());
				}

			} else if (action.equals("insertMethod")) {

				payment = new PaymentBean();

				payment.setEmail(client.getAccount().getEmail());
				payment.setIdentifier(request.getParameter("nCarta"));
				payment.setSecurityCode(request.getParameter("cSicurezza"));
				payment.setExpireDate(request.getParameter("dScadenza"));

				try {
					paymentmodel.doInsert(payment);
					session.setAttribute("method", payment);
					response.sendRedirect("PaymentView.jsp");

				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (action.equals("removeMethod")) {

				if (payment != null) {
					try {
						paymentmodel.doDelete(client.getAccount().getEmail(), payment.getIdentifier());
						session.removeAttribute("method");
						response.sendRedirect("PaymentView.jsp");

					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
