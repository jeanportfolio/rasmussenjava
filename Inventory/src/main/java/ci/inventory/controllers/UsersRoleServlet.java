package ci.inventory.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import ci.inventory.entity.Message;
import ci.inventory.entity.TypeMessage;
import ci.inventory.entity.Users;
import ci.inventory.entity.Usersrole;
import ci.inventory.services.UsersroleService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@WebServlet("/usersrole")
public class UsersRoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private UsersroleService serviceUsersrole;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init du LoginServlet");
		serviceUsersrole = new UsersroleService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		session = request.getSession(false);
		String action = request.getParameter("action");

		System.out.println("Action : "+ action);
		if(session == null) {
			System.out.println("La session /user null / "+ session);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else if(!request.isRequestedSessionIdValid()){
			System.out.println("La session non nul /user isRequestedSessionIdValid() / "+ session);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else if(session.getAttribute("user") == null) {
			System.out.println("La session non null /user user null / "+ session);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else {
			//Check if the request concern a list or an add
			if(action == null) {
				request.setAttribute("usersrole", new Usersrole());
				request.getRequestDispatcher("usersrole.jsp").forward(request, response);
			}else if(action.equals("list")){
				
				List<Usersrole> listusersrole = serviceUsersrole.getAll();
				request.setAttribute("listusersrole", listusersrole);
				request.getRequestDispatcher("usersrolelist.jsp").forward(request, response);
			}else {
				
				int id = Integer.parseInt(request.getParameter("id"), 10);
				Usersrole usersrole = serviceUsersrole.get(id);
				request.setAttribute("usersrole", usersrole);
				request.getRequestDispatcher("usersrole.jsp").forward(request, response);
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {

		session = request.getSession(false);

		if(session == null) {
			System.out.println("La session /user null / "+ session);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else if(!request.isRequestedSessionIdValid()){
			System.out.println("La session non nul /user isRequestedSessionIdValid() / "+ session);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else if(session.getAttribute("user") == null) {
			System.out.println("La session non null /user user null / "+ session);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else {

			Users user = (Users)session.getAttribute("user");

			Usersrole usersrole = new Usersrole();
			Message message;
			Boolean errorfield = false;
			StringBuffer errormessage = new StringBuffer();

			String title = request.getParameter("title");
			String description = request.getParameter("description");
			
			if(title.isEmpty()) {
				errormessage.append("Title empty,\n");
				errorfield = true;
			}
			if(description.isEmpty()) {
				errormessage.append("Description empty,\n");
				errorfield = true;
			}

			if(errorfield) {
				message = new Message(TypeMessage.error, "Please check the required fields ! " + errormessage);

				request.setAttribute("usersrole", usersrole);
				request.setAttribute("message", message);
				request.getRequestDispatcher("usersrole.jsp").forward(request, response);
			}else {
				
				String action = request.getParameter("action");
				usersrole.setTitle(title);
				usersrole.setDescription(description);
				usersrole.setIduser(user.getId());
				
				//Check if the action to perform is an update or insertion
				if(action.equals("create")) {
					
					serviceUsersrole.create(usersrole);
					usersrole = new Usersrole();
					message = new Message(TypeMessage.success, "Usersrole created successfully !");
					System.out.println("Usersrole created successfully !");
				}else {
					int id = Integer.parseInt(request.getParameter("id"), 10);
					usersrole.setId(id);
					serviceUsersrole.update(usersrole);
					message = new Message(TypeMessage.success, "Usersrole updated successfully !");
					System.out.println("Usersrole updated successfully !");
				}

				request.setAttribute("usersrole", usersrole);		
				request.setAttribute("message", message);
				request.getRequestDispatcher("usersrole.jsp").forward(request, response);
			}
		}

	}
	
	public void destroy() {

		System.out.println("in destroy method");
	}
}
