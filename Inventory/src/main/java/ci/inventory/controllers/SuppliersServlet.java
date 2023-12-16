package ci.inventory.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import ci.inventory.entity.Message;
import ci.inventory.entity.Suppliers;
import ci.inventory.entity.TypeMessage;
import ci.inventory.entity.Users;
import ci.inventory.services.SuppliersService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@WebServlet("/supplier")
public class SuppliersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private SuppliersService serviceSupplier;
	private static String LIST = "supplierlist.jsp";
	private static String INSERT_UPDATE = "supplier.jsp";

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init du LoginServlet");
		serviceSupplier = new SuppliersService();
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
			//Check if the request concern a list or a data persistence
			if(action == null) {
				request.setAttribute("supplier", new Suppliers());				
				request.getRequestDispatcher(INSERT_UPDATE).forward(request, response);
			}else if(action.equals("list")){
				
				List<Suppliers> listsupplier = serviceSupplier.getAll();
				request.setAttribute("listsupplier", listsupplier);
				request.getRequestDispatcher(LIST).forward(request, response);
			}else {
				int id = Integer.parseInt(request.getParameter("id"), 10);

				Suppliers supplier = serviceSupplier.get(id);
				request.setAttribute("supplier", supplier);
				request.getRequestDispatcher(INSERT_UPDATE).forward(request, response);
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

			Suppliers supplier = new Suppliers();
			Message message;
			Boolean errorfield = false;
			StringBuffer errormessage = new StringBuffer();

			String suppliername = request.getParameter("suppliername");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone"); 

			if(suppliername.isEmpty()) {
				errormessage.append("Supplier name empty,\n");
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
				request.setAttribute("supplier", supplier);
				request.getRequestDispatcher(INSERT_UPDATE).forward(request, response);
			}else {
				
				String action = request.getParameter("action");
				supplier.setAddress(address);
				supplier.setSuppliersname(suppliername);
				supplier.setPhone(phone);
				supplier.setIdusers(user.getId());
				
				//Check if the action to perform is an update or insertion
				if(action.equals("create")) {
					serviceSupplier.create(supplier);
					message = new Message(TypeMessage.success, "supplier created successfully !");
				}else {
					
					int id = Integer.parseInt(request.getParameter("id"), 10);
					supplier.setId(id);
					serviceSupplier.update(supplier);
					message = new Message(TypeMessage.success, "supplier updated successfully !");
				}
				
				request.setAttribute("message", message);
				request.setAttribute("supplier", supplier);
				request.getRequestDispatcher(INSERT_UPDATE).forward(request, response);
			}
		}

	}

	public void destroy() {

		System.out.println("in destroy method");
	}
}
