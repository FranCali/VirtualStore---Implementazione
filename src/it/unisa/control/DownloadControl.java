package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.unisa.beans.ClientBean;
import it.unisa.beans.PaymentBean;
import it.unisa.model.ContentModelDM;
import it.unisa.model.DownloadModelDM;
import it.unisa.util.Data;
import it.unisa.util.InfoDownload;

/**
 * Servlet implementation class DownloadControl
 */
@WebServlet("/DownloadControl")
public class DownloadControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DownloadModelDM downloadDM = new DownloadModelDM();
		ContentModelDM contentModel = new ContentModelDM();
		InfoDownload info;

		String action = (String) request.getParameter("action");
		String piva = request.getParameter("piva");

		if (action != null && !action.equals("")) {
			if (action.equals("downloadContent")) {
				info = new InfoDownload();
				info.setEmail((String) request.getParameter("email"));
				try {
					info.setContent(
							contentModel.doRetrieveByKey(Integer.parseInt((String) request.getParameter("id"))));
				} catch (NumberFormatException | SQLException e1) {
					e1.printStackTrace();
				}

				info.setDate(Data.creaData(LocalDate.now().getYear(), LocalDate.now().getMonthValue(),
						LocalDate.now().getDayOfMonth()));
				try {
					if ((downloadDM.doRetriveByIdandEmail(info.getEmail(), info.getContent().getId())) == null)
						downloadDM.doSave(info);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
							"/ContentShowControl?action=showContent&id=" + info.getContent().getId() + "&piva=" + piva);
					dispatcher.forward(request, response);
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			if (action.equals("buyContent")) {
				info = new InfoDownload();
				info.setEmail((String) request.getParameter("email"));
				try {
					info.setContent(
							contentModel.doRetrieveByKey(Integer.parseInt((String) request.getParameter("id"))));
				} catch (NumberFormatException | SQLException e1) {
					e1.printStackTrace();
				}

				info.setDate(Data.creaData(LocalDate.now().getYear(), LocalDate.now().getMonthValue(),
						LocalDate.now().getDayOfMonth()));
				PaymentBean userPayment = (PaymentBean) request.getSession().getAttribute("method");
				if (userPayment == null) {
					response.sendRedirect("./PaymentView.jsp");
				} else {
					try {

						if (downloadDM.doRetriveByIdandEmail(info.getEmail(), info.getContent().getId()) == null)
							downloadDM.doSave(info);
						RequestDispatcher dispatcher = getServletContext()
								.getRequestDispatcher("/ContentShowControl?action=showContent&id="
										+ info.getContent().getId() + "&piva=" + piva);
						dispatcher.forward(request, response);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			if (action.equals("showDownloads")) {
				ClientBean client = (ClientBean) request.getSession().getAttribute("user");
				try {
					request.getSession().removeAttribute("downloads");
					request.getSession().setAttribute("downloads", downloadDM.doRetriveAllByEmail(client.getAccount().getEmail()));
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/DownloadView.jsp");
					dispatcher.forward(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
