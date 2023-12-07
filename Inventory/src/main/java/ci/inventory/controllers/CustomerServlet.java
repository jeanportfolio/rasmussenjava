package ci.inventory.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import ci.inventory.entity.Customers;
import ci.inventory.entity.Message;
import ci.inventory.entity.TypeMessage;
import ci.inventory.entity.Users;
import ci.inventory.services.CustomersService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private CustomersService serviveCustomer;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init du LoginServlet");
		serviveCustomer = new CustomersService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		session = request.getSession(false);
		String action = request.getParameter("action");

		System.out.println("action : "+ action);
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
				
				request.setAttribute("customer", new Customers());
				request.getRequestDispatcher("customer.jsp").forward(request, response);
			}else if(action.equals("list")){
				List<Customers> listcustomer = serviveCustomer.getAll();
				request.setAttribute("listcustomer", listcustomer);
				request.getRequestDispatcher("customerlist.jsp").forward(request, response);
				
			}else {
				int id = Integer.parseInt(request.getParameter("id"), 10);
				Customers customer = serviveCustomer.get(id);
				request.setAttribute("customer", customer);
				request.getRequestDispatcher("customer.jsp").forward(request, response);
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
			Customers customer = new Customers();
			Message message;
			Boolean errorfield = false;
			StringBuffer errormessage = new StringBuffer();

			String customername = request.getParameter("customername");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");

			if(customername.isEmpty()) {
				errormessage.append("Customer name empty,\n");
				errorfield = true;
			}

			if(address.isEmpty()) {
				errormessage.append("Address empty, \n");
				errorfield = true;
			}
			if(phone.isEmpty()) {
				errormessage.append("Phone empty, \n");
				errorfield = true;
			}


			if(errorfield) {
				message = new Message(TypeMessage.error, "Please check the required fields ! " + errormessage);

				request.setAttribute("message", message);
				request.setAttribute("customer", customer);
				request.getRequestDispatcher("customer.jsp").forward(request, response);
			}else {
				
				String action = request.getParameter("action");
				customer.setAddress(address);
				customer.setCustomername(customername);
				customer.setPhone(phone);
				customer.setIdusers(user.getId());
				
				//Check if the action to perform is an update or insertion
				if(action.equals("create")) {
					serviveCustomer.create(customer);
					message = new Message(TypeMessage.success, "Customer created successfully !");
				}else {
					
					int id = Integer.parseInt(request.getParameter("id"), 10);
					customer.setId(id);
					serviveCustomer.update(customer);
					message = new Message(TypeMessage.success, "Cupplier updated successfully !");
				}
				
				request.setAttribute("message", message);
				request.setAttribute("customer", customer);
				request.getRequestDispatcher("customer.jsp").forward(request, response);
			}
		}

	}

	public void destroy() {

		System.out.println("in destroy method");
	}
}
