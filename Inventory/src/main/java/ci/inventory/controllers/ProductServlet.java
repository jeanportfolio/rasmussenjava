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
import ci.inventory.services.CategoryproductService;
import ci.inventory.services.ProductsService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private CategoryproductService serviceCategory;
	private ProductsService serviceProduct;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init du LoginServlet");
		serviceCategory = new CategoryproductService();
		serviceProduct = new ProductsService();
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
				List<Categoryproduct> listcategories = serviceCategory.getAll();

				request.setAttribute("product", new Products());
				request.setAttribute("listcategories", listcategories);
				
				request.getRequestDispatcher("product.jsp").forward(request, response);
			}else if(action.equals("list")){
				
				List<Products> listproduct = serviceProduct.getAll();
				request.setAttribute("listproduct", listproduct);
				request.getRequestDispatcher("productlist.jsp").forward(request, response);
			}else {
				int id = Integer.parseInt(request.getParameter("id"), 10);
				List<Categoryproduct> listcategories = serviceCategory.getAll();

				Products product = serviceProduct.get(id);
				request.setAttribute("product", product);
				request.setAttribute("listcategories", listcategories);
				request.getRequestDispatcher("product.jsp").forward(request, response);
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

			List<Categoryproduct> listcategories = serviceCategory.getAll();

			Products product = new Products();
			Message message;
			Boolean errorfield = false;
			StringBuffer errormessage = new StringBuffer();

			String designation = request.getParameter("designation");
			String description = request.getParameter("description");
			BigDecimal price = new BigDecimal(request.getParameter("price"));
			BigDecimal saleprice = new BigDecimal(request.getParameter("saleprice"));
			
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

				request.setAttribute("listcategories", listcategories);
				request.setAttribute("message", message);
				request.setAttribute("product", product);
				request.getRequestDispatcher("product.jsp").forward(request, response);
			}else {
				
				String action = request.getParameter("action");
				product.setDesignation(designation);
				product.setDescription(description);
				product.setPrice(price);
				product.setSaleprice(saleprice);
				product.setIdcategory(idcategory);
				product.setIdusers(user.getId());
				
				//Check if the action to perform is an update or insertion
				if(action.equals("create")) {
					serviceProduct.create(product);
					message = new Message(TypeMessage.success, "product created successfully !");
				}else {
					
					int id = Integer.parseInt(request.getParameter("id"), 10);
					product.setId(id);
					serviceProduct.update(product);
					message = new Message(TypeMessage.success, "product updated successfully !");
				}
				
				request.setAttribute("message", message);
				request.setAttribute("product", product);
				request.setAttribute("listcategories", listcategories);
				request.getRequestDispatcher("product.jsp").forward(request, response);
			}
		}

	}

	public void destroy() {

		System.out.println("in destroy method");
	}
}
