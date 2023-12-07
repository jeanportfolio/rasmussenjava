package ci.inventory.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import ci.inventory.entity.Message;
import ci.inventory.entity.Products;
import ci.inventory.entity.Stockinventory;
import ci.inventory.entity.TypeMessage;
import ci.inventory.entity.Users;
import ci.inventory.services.ProductsService;
import ci.inventory.services.StockinventoryService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@WebServlet("/stockinventory")
public class StockInventoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private StockinventoryService serviveStockInventory;
	private ProductsService serviceProduct;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init du LoginServlet");
		serviceProduct = new ProductsService();
		serviveStockInventory = new StockinventoryService();
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
			
				List<Products> listproduct = serviceProduct.getAll();
				request.setAttribute("listproduct", listproduct);
				request.setAttribute("stockinventory", new Stockinventory());
				request.getRequestDispatcher("stockinventory.jsp").forward(request, response);
			}else if(action.equals("list")){
				List<Stockinventory> liststockinventory = serviveStockInventory.getAll();
				System.out.println(liststockinventory.size());
				request.setAttribute("liststockinventory", liststockinventory);
				request.getRequestDispatcher("stockinventorylist.jsp").forward(request, response);
			}else {
				int id = Integer.parseInt(request.getParameter("id"), 10);
				Stockinventory stockinventory = serviveStockInventory.get(id);
				List<Products> listproduct = serviceProduct.getAll();
				request.setAttribute("stockinventory", stockinventory);
				request.setAttribute("listproduct", listproduct);
				request.getRequestDispatcher("stockinventory.jsp").forward(request, response);
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

			List<Products> listproduct = serviceProduct.getAll();

			Stockinventory stockinventory = new Stockinventory();
			Message message;
			Boolean errorfield = false;
			StringBuffer errormessage = new StringBuffer();

			String title = request.getParameter("title");
			int availableqty = Integer.parseInt(request.getParameter("availableqty"),10);
			int minstock = Integer.parseInt(request.getParameter("minstock"),10);
			int maxstock = Integer.parseInt(request.getParameter("maxstock"),10);
			
			int idproduct = Integer.parseInt(request.getParameter("idproduct"), 10); 

			if(title.isEmpty()) {
				errormessage.append("Title empty,\n");
				errorfield = true;
			}
			if(availableqty < 0) {
				errormessage.append("available quantity invalid, \n");
				errorfield = true;
			}
			if(minstock < 0) {
				errormessage.append("Minimum stock invalid, \n");
				errorfield = true;
			}
			if(maxstock < 0) {
				errormessage.append("Maximum stock invalid, \n");
				errorfield = true;
			}
			if(idproduct <= 0) {
				errormessage.append("Product invalid, \n");
				errorfield = true;
			}

			if(errorfield) {
				message = new Message(TypeMessage.error, "Please check the required fields ! " + errormessage);

				request.setAttribute("listproduct", listproduct);
				request.setAttribute("message", message);
				request.setAttribute("stockinventory", stockinventory);
				request.getRequestDispatcher("stockinventory.jsp").forward(request, response);
			}else {
				
				String action = request.getParameter("action");
				stockinventory.setTitle(title);
				stockinventory.setAvailablequantity(availableqty);
				stockinventory.setMinstocklevel(minstock);
				stockinventory.setMaxstocklevel(maxstock);
				stockinventory.setIdproduct(idproduct);
				stockinventory.setIduser(user.getId());
				
				//Check if the action to perform is an update or insertion
				if(action.equals("create")) {
					serviveStockInventory.create(stockinventory);
					message = new Message(TypeMessage.success, "stockinventory created successfully !");
				}else {
					
					int id = Integer.parseInt(request.getParameter("id"), 10);
					stockinventory.setId(id);
					serviveStockInventory.update(stockinventory);
					message = new Message(TypeMessage.success, "stockinventory updated successfully !");
				}
				
				request.setAttribute("message", message);
				request.setAttribute("stockinventory", stockinventory);
				request.setAttribute("listproduct", listproduct);
				request.getRequestDispatcher("stockinventory.jsp").forward(request, response);
			}
		}

	}

	public void destroy() {

		System.out.println("in destroy method");
	}
}
