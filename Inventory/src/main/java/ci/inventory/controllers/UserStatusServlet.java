package ci.inventory.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import ci.inventory.entity.Message;
import ci.inventory.entity.Stock_movement;
import ci.inventory.entity.TypeMessage;
import ci.inventory.entity.Users;
import ci.inventory.entity.Userstatus;
import ci.inventory.services.UserstatusService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@WebServlet("/userstatus")
public class UserStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private UserstatusService serviceUserstatus;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init du LoginServlet");
		serviceUserstatus = new UserstatusService();
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
				request.setAttribute("userstatus", new Userstatus());
				request.getRequestDispatcher("userstatus.jsp").forward(request, response);
			}else if(action.equals("list")){
				
				List<Userstatus> listuserstatus = serviceUserstatus.getAll();
				request.setAttribute("listuserstatus", listuserstatus);
				request.getRequestDispatcher("userstatuslist.jsp").forward(request, response);
			}else {
				
				int id = Integer.parseInt(request.getParameter("id"), 10);
				Userstatus userstatus = serviceUserstatus.get(id);
				request.setAttribute("userstatus", userstatus);
				request.getRequestDispatcher("userstatus.jsp").forward(request, response);
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

			Userstatus userstatus = new Userstatus();
			Message message;
			Boolean errorfield = false;
			StringBuffer errormessage = new StringBuffer();

			
			String title = request.getParameter("title");
			
			if(title.isEmpty()) {
				errormessage.append("Title empty,\n");
				errorfield = true;
			}

			if(errorfield) {
				message = new Message(TypeMessage.error, "Please check the required fields ! " + errormessage);

				request.setAttribute("userstatus", userstatus);
				request.setAttribute("message", message);
				request.getRequestDispatcher("userstatus.jsp").forward(request, response);
			}else {
				
				String action = request.getParameter("action");
				userstatus.setTitle(title);
				userstatus.setIdusers(user.getId());
				
				//Check if the action to perform is an update or insertion
				if(action.equals("create")) {
					
					serviceUserstatus.create(userstatus);
					userstatus = new Userstatus();
					message = new Message(TypeMessage.success, "Userstatus created successfully !");
					System.out.println("Userstatus created successfully !");
				}else {
					int id = Integer.parseInt(request.getParameter("id"), 10);
					userstatus.setId(id);
					serviceUserstatus.update(userstatus);
					message = new Message(TypeMessage.success, "Userstatus updated successfully !");
					System.out.println("Userstatus updated successfully !");
				}

				request.setAttribute("userstatus", userstatus);		
				request.setAttribute("message", message);
				request.getRequestDispatcher("userstatus.jsp").forward(request, response);
			}
		}

	}
	
	public void destroy() {

		System.out.println("in destroy method");
	}
}
