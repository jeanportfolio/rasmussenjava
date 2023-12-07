package ci.inventory.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import ci.inventory.entity.Categoryproduct;
import ci.inventory.entity.Message;
import ci.inventory.entity.Products;
import ci.inventory.entity.TypeMessage;
import ci.inventory.entity.Users;
import ci.inventory.entity.Usersrole;
import ci.inventory.entity.Userstatus;
import ci.inventory.services.CategoryproductService;
import ci.inventory.services.ProductsService;
import ci.inventory.services.UsersService;
import ci.inventory.services.UsersroleService;
import ci.inventory.services.UserstatusService;
import ci.inventory.utility.PasswordEncryption;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@WebServlet("/customerorder")
public class CustomerOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private UsersService serviveUsers;
	private CategoryproductService serviceCategory;
	private ProductsService serviceProduct;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init du LoginServlet");
		serviveUsers = new UsersService();
		serviceCategory = new CategoryproductService();
		serviceProduct = new ProductsService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		session = request.getSession(false);
		String list = request.getParameter("list");

		System.out.println("list users : "+ list);
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
			if(list == null) {
				List<Categoryproduct> categories = serviceCategory.getAll();

				request.setAttribute("product", new Products());
				request.setAttribute("categories", categories);
				
				request.getRequestDispatcher("product.jsp").forward(request, response);
			}else if(list.equals("profil")){
				
				request.setAttribute("product", new Products());
				request.getRequestDispatcher("product.jsp").forward(request, response);
			}else {
				List<Products> listproduct = serviceProduct.getAll();
				request.setAttribute("listproduct", listproduct);
				request.getRequestDispatcher("productlist.jsp").forward(request, response);
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


			List<Categoryproduct> categories = serviceCategory.getAll();

			request.setAttribute("users", new Users());
			request.setAttribute("categories", categories);
			Message message;
			Boolean errorfield = false;
			StringBuffer errormessage = new StringBuffer();

			String designation = request.getParameter("designation");
			String description = request.getParameter("description");
			BigDecimal price = new BigDecimal(request.getParameter("birthday"));
			BigDecimal saleprice = new BigDecimal(request.getParameter("login"));
			
			int idcategory = Integer.parseInt(request.getParameter("idcategory"), 10); 

			if(designation.isEmpty()) {
				errormessage.append("First name empty,\n");
				errorfield = true;
			}

			if(description.isEmpty()) {
				errormessage.append("Description empty, \n");
				errorfield = true;
			}

			if(saleprice.equals("")) {
				errormessage.append("Sale Price empty, \n");
				errorfield = true;
			}
			
			if(price.equals("")) {
				errormessage.append("Price empty, \n");
				errorfield = true;
			}

			if(idcategory == 0) {
				errormessage.append("User Status invalid, \n");
				errorfield = true;
			}

			if(errorfield) {
				message = new Message(TypeMessage.error, "Please check the required fields ! " + errormessage);

				request.setAttribute("categories", categories);
				request.setAttribute("message", message);
				request.getRequestDispatcher("product.jsp").forward(request, response);
			}else {
				Products product = new Products();
				
				product.setDesignation(designation);
				product.setDescription(description);
				product.setPrice(price);
				product.setSaleprice(saleprice);
				product.setIdcategory(idcategory);
				product.setIdusers(user.getId());

				serviceProduct.create(product);

				message = new Message(TypeMessage.success, "User created successfully !");

						
				request.setAttribute("message", message);
				request.setAttribute("categories", categories);
				request.getRequestDispatcher("product.jsp").forward(request, response);
			}
		}

	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			Users updateUser = new Users();
			List<Categoryproduct> categories = serviceCategory.getAll();
			Message message;

			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String birthday = request.getParameter("birthday");
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			int idrole = Integer.parseInt(request.getParameter("idrole"), 10); 
			int idstatus = Integer.parseInt(request.getParameter("idstatus"), 10);

			String oldpwd = request.getParameter("oldpwd");
			String newpwd = request.getParameter("newpwd") == null ? "" : request.getParameter("newpwd");
			String repeatpwd = request.getParameter("repeatpwd") == null ? "" : request.getParameter("repeatpwd");

			try {
				updateUser = serviveUsers.connect(user.getLogin(), PasswordEncryption.encrypt(oldpwd));
			} catch (Exception e1) {
				System.out.println("Error while encrypting the password: "+ e1.getMessage());
			}

			if(updateUser != null) {
				if(newpwd.isEmpty()) {
					updateUser.setFisrtname(firstname);
					updateUser.setLastname(lastname);
					updateUser.setBirthday(birthday);
					updateUser.setLogin(login);
					updateUser.setIdusersrole(idrole);
					updateUser.setIduserstatus(idstatus);
					updateUser.setIdusers(user.getId());

					serviveUsers.update(user);
					user.setPassword("");

					message = new Message(TypeMessage.success, "User updated successfully !");
				}else if(newpwd.equals(repeatpwd)){
					try {
						updateUser.setFisrtname(firstname);
						updateUser.setLastname(lastname);
						updateUser.setBirthday(birthday);
						updateUser.setLogin(login);
						updateUser.setIdusersrole(idrole);
						updateUser.setIduserstatus(idstatus);
						updateUser.setIdusers(user.getId());
						updateUser.setPassword(PasswordEncryption.encrypt(password));

						serviveUsers.update(updateUser);
						user.setPassword("");

						message = new Message(TypeMessage.success, "User updated successfully !");
					} catch (Exception e) {

						System.out.println("Error while encrypting the password: "+ e.getMessage());
						message = new Message(TypeMessage.error, "Update IMPOSSIBLE, please conctat the administrator !!!");

					}
					request.setAttribute("message", message);
					request.setAttribute("categories", categories);
					request.getRequestDispatcher("product.jsp").forward(request, response);

				}else {
					message = new Message(TypeMessage.warning, "Check, The new password is different to the repeat password !");
				}

			}else {
				message = new Message(TypeMessage.error, "Password incorrect, please try again !");
			}
			request.setAttribute("message", message);
			request.setAttribute("categories", categories);

			request.getRequestDispatcher("userprofil.jsp").forward(request, response);
		}
	}

	public void destroy() {

		System.out.println("in destroy method");
	}
}
