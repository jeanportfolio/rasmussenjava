package ci.inventory.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import ci.inventory.entity.Message;
import ci.inventory.entity.Stock_movement;
import ci.inventory.entity.TypeMessage;
import ci.inventory.entity.Users;
import ci.inventory.services.Stock_movementService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@WebServlet("/stockmovement")
public class StockMovementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private Stock_movementService serviceStockmovement;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init du LoginServlet");
		serviceStockmovement = new Stock_movementService();
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
				request.setAttribute("stockmovement", new Stock_movement());
				request.getRequestDispatcher("stockmovement.jsp").forward(request, response);
			}else if(action.equals("list")){
				
				List<Stock_movement> liststockmovement = serviceStockmovement.getAll();
				request.setAttribute("liststockmovement", liststockmovement);
				request.getRequestDispatcher("stockmovementlist.jsp").forward(request, response);
			}else {
				
				int id = Integer.parseInt(request.getParameter("id"), 10);
				Stock_movement stockmovement = serviceStockmovement.get(id);
				request.setAttribute("stockmovement", stockmovement);
				request.getRequestDispatcher("stockmovement.jsp").forward(request, response);
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

			Stock_movement stockmovement = new Stock_movement();
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

				request.setAttribute("stockmovement", stockmovement);
				request.setAttribute("message", message);
				request.getRequestDispatcher("stockmovement.jsp").forward(request, response);
			}else {
				
				String action = request.getParameter("action");
				stockmovement.setTitle(title);
				stockmovement.setIdusers(user.getId());
				
				//Check if the action to perform is an update or insertion
				if(action.equals("create")) {
					
					serviceStockmovement.create(stockmovement);
					stockmovement = new Stock_movement();
					message = new Message(TypeMessage.success, "Category created successfully !");
				}else {
					int id = Integer.parseInt(request.getParameter("id"), 10);
					stockmovement.setId(id);
					serviceStockmovement.update(stockmovement);
					message = new Message(TypeMessage.success, "Category updated successfully !");
				}

				request.setAttribute("stockmovement", stockmovement);		
				request.setAttribute("message", message);
				request.getRequestDispatcher("stockmovement.jsp").forward(request, response);
			}
		}

	}
	
	public void destroy() {

		System.out.println("in destroy method");
	}
}
