package ci.inventory.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import ci.inventory.entity.Categoryproduct;
import ci.inventory.entity.Message;
import ci.inventory.entity.TypeMessage;
import ci.inventory.entity.Users;
import ci.inventory.entity.Usersrole;
import ci.inventory.entity.Userstatus;
import ci.inventory.services.CategoryproductService;
import ci.inventory.services.UsersService;
import ci.inventory.services.UsersroleService;
import ci.inventory.services.UserstatusService;
import ci.inventory.utility.PasswordEncryption;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@WebServlet("/category")
public class CategoryProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private UsersService serviveUsers;
	private CategoryproductService serviceCategory;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init du LoginServlet");
		serviveUsers = new UsersService();
		serviceCategory = new CategoryproductService();
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
				request.setAttribute("category", new Categoryproduct());
				request.getRequestDispatcher("category.jsp").forward(request, response);
			}else if(action.equals("list")){
				
				List<Categoryproduct> listcategory = serviceCategory.getAll();
				request.setAttribute("listcategory", listcategory);
				request.getRequestDispatcher("categorylist.jsp").forward(request, response);
			}else {
				
				int id = Integer.parseInt(request.getParameter("id"), 10);
				Categoryproduct category = serviceCategory.get(id);
				request.setAttribute("category", category);
				request.getRequestDispatcher("category.jsp").forward(request, response);
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

			Categoryproduct category = new Categoryproduct();
			Message message;
			Boolean errorfield = false;
			StringBuffer errormessage = new StringBuffer();

			
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			
			if(title.isEmpty()) {
				errormessage.append("Title empty,\n");
				errorfield = true;
			}

			if(errorfield) {
				message = new Message(TypeMessage.error, "Please check the required fields ! " + errormessage);

				request.setAttribute("category", category);
				request.setAttribute("message", message);
				request.getRequestDispatcher("category.jsp").forward(request, response);
			}else {
				
				String action = request.getParameter("action");
				category.setTitle(title);
				category.setDescription(description);
				category.setIdusers(user.getId());
				
				//Check if the action to perform is an update or insertion
				if(action.equals("create")) {
					
					serviceCategory.create(category);
					category = new Categoryproduct();
					message = new Message(TypeMessage.success, "Category created successfully !");
				}else {
					int id = Integer.parseInt(request.getParameter("id"), 10);
					category.setId(id);
					serviceCategory.update(category);
					message = new Message(TypeMessage.success, "Category updated successfully !");
				}

				request.setAttribute("category", category);		
				request.setAttribute("message", message);
				request.getRequestDispatcher("category.jsp").forward(request, response);
			}
		}

	}
	
	public void destroy() {

		System.out.println("in destroy method");
	}
}
