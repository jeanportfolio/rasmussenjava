package ci.inventory.controllers;

import java.io.IOException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;

import ci.inventory.entity.Message;
import ci.inventory.entity.TypeMessage;
import ci.inventory.entity.Users;
import ci.inventory.services.UsersService;
import ci.inventory.utility.PasswordEncryption;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private UsersService serviveUsers;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init du LoginServlet");
		serviveUsers = new UsersService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("logout") != null && session != null) {
			// Disconnection

			if (request.isRequestedSessionIdValid()) {
				session.invalidate();
				System.out.println("Disconnection");
				session = null;
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} else {
			if (session != null && (Users) session.getAttribute("user") != null) {
				
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {

		if (request.getParameter("logout") != null && session != null) {
			// Disconnect user
			System.out.println("Disconnection");
			if (!request.isRequestedSessionIdValid())
				session.invalidate();
			session = null;
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			if (session == null || request.isRequestedSessionIdValid()) {
				String login = request.getParameter("login");
				String password = request.getParameter("password");
				Users user = null;
				System.out.println("login and password "+ login+" " + password);
				//Retrieve the user corresponding to the login and password given
				try {
					//user = serviveUsers.connect(login, PasswordEncryption.encrypt(password));
					user = serviveUsers.connect(login, password);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				if (user == null) {
					Message message = new Message(TypeMessage.error, "Login/Password invalid please try again");
					request.setAttribute("message", message);
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} else {
					session = request.getSession(false);					
					
					Message message = new Message(TypeMessage.info, "Welcome "+ user.getFisrtname());
					
					request.setAttribute("message", message);
					session.setAttribute("user", user);

					request.getRequestDispatcher("index.jsp").forward(request, response);
				}

			} else {
				if (session.getAttribute("user") != null) {
					request.getRequestDispatcher("index.jsp").forward(request, response);
				} else {
					Message message = new Message(TypeMessage.warning, "Your session expired please connect again");
					request.setAttribute("message", message);
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			}
		}

	}

	public void destroy() {
		
		System.out.println("in destroy method");
	}
}
