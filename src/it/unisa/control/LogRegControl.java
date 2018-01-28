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

import it.unisa.beans.AccountBean;
import it.unisa.beans.ClientBean;
import it.unisa.beans.ContentBean;
import it.unisa.beans.PaymentBean;
import it.unisa.beans.WishlistBean;
import it.unisa.model.AccountModelDM;
import it.unisa.model.ClientModelDM;
import it.unisa.model.DownloadModelDM;
import it.unisa.model.PaymentModelDM;
import it.unisa.model.WishlistModelDM;
import it.unisa.util.Encryptor;
import it.unisa.util.InfoDownload;

@WebServlet("/LogRegControl")
public class LogRegControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (action.equals("Login")) {

			// ************************LOGIN*********************************************************

			HttpSession session = request.getSession();
			ClientModelDM clientModelDM = new ClientModelDM();
			PaymentModelDM paymentModel = new PaymentModelDM();
			DownloadModelDM downloadModel = new DownloadModelDM();

			try {
				ClientBean clientDB = clientModelDM.doRetriveByEmail(email);
				if (clientDB != null) {
					if (clientDB.getAccount().getEmail().equals(email)
							&& clientDB.getAccount().getPassword().equals(Encryptor.encryptPassword(password))) {
						//Controllo privilegi
						
						// INITIALIZE WISHLIST
						WishlistBean wishlist = new WishlistBean();
						wishlist.setContents((LinkedList<ContentBean>) new WishlistModelDM().doRetriveAll(email));
						session.setAttribute("wishlist", wishlist);

						// INITIALIZE PAYMENT
						PaymentBean payment = paymentModel.doRetriveByEmail(email);
						if (payment != null)
							session.setAttribute("method", payment);

						// INITIALIZE DOWNLOAD
						LinkedList<InfoDownload> downloads = (LinkedList<InfoDownload>) downloadModel
								.doRetriveAllByEmail(email);
						session.setAttribute("downloads", downloads);
						session.setAttribute("user", clientDB);
						if (clientModelDM.checkIfAdmin(clientDB))
							session.setAttribute("privilege", "Admin");
						else if(clientModelDM.checkIfManager(clientDB))
							session.setAttribute("privilege", "Manager");
						else
							session.setAttribute("privilege", "Client");
							
						response.sendRedirect("index.jsp");

					} else {
						if (!clientDB.getAccount().getEmail().equals(email)
								|| !clientDB.getAccount().getPassword().equals(Encryptor.encryptPassword(password))) {
							request.setAttribute("login_error", "incorrect email or password");
							RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/LoginView.jsp");
							dispatcher.forward(request, response);
						}
					}
				} else {
					request.setAttribute("login_error", "User not exists");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/LoginView.jsp");
					dispatcher.forward(request, response);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (action.equals("Register")) {

			// ************************REGISTER*********************************************************

			AccountBean account = null;
			AccountModelDM accountModelDM = new AccountModelDM();
			WishlistModelDM wishlistModel = new WishlistModelDM();
			String name = request.getParameter("nome");
			String surname = request.getParameter("cognome");
			String againpassword = request.getParameter("againpassword");

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/RegisterView.jsp");

			try {

				account = accountModelDM.doRetrieveByEmail(email);
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}

			// Controllo la lunghezza di nome e cognome
			if (name.length() < 2) {
				request.setAttribute("register_error", "Error: Name length must be almost 2");
				dispatcher.forward(request, response);
				return;
			} else if (surname.length() < 2) {
				request.setAttribute("register_error", "Error: Surname length must be almost 2");
				dispatcher.forward(request, response);
				return;
			}

			// Controllo lunghezza password
			if (password.length() < 8) {
				request.setAttribute("register_error", "Error: Password length must be almost 8");
				dispatcher.forward(request, response);
				return;
			}

			// Controllo match delle due password
			if (!password.equals(againpassword)) {
				request.setAttribute("register_error", "Error: Passwords not matching");
				dispatcher.forward(request, response);
				return;
			}

			// Controlla se la mail esiste Già
			if (account != null) {
				request.setAttribute("register_error", "User: " + account.getEmail() + " already exists!");
				dispatcher.forward(request, response);
				return;
			}

			/******************************************************************************/
			AccountBean newAccount = new AccountBean(email, Encryptor.encryptPassword(password));
			ClientBean client = new ClientBean(name, surname, newAccount);
			ClientModelDM clientModelDM = new ClientModelDM();

			try {
				clientModelDM.doInsert(client);
				wishlistModel.doInsert(client.getAccount().getEmail());
				RequestDispatcher dispatcher2 = getServletContext().getRequestDispatcher("/index.jsp");
				dispatcher2.forward(request, response);
				return;
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
