package ci.inventory.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import ci.inventory.entity.Message;
import ci.inventory.entity.Orderitems;
import ci.inventory.entity.Products;
import ci.inventory.entity.Customers;
import ci.inventory.entity.Customersorder;
import ci.inventory.entity.TypeMessage;
import ci.inventory.entity.Users;
import ci.inventory.services.CustomersService;
import ci.inventory.services.CustomersorderService;
import ci.inventory.services.OrderitemsService;
import ci.inventory.services.ProductsService;
import ci.inventory.utility.Autotreatment;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@WebServlet("/customerorder")
public class CustomerOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private static CustomersorderService serviveOrder;
	private static OrderitemsService serviveOrderitem;
	private static ProductsService serviceProduct;
	private static CustomersService serviceCustomer;
	private static String INSERT_UPDATE = "customerorder.jsp";
	private static String LIST = "customerorderlist.jsp";

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init du LoginServlet");
		serviveOrder = new CustomersorderService();
		serviveOrderitem = new OrderitemsService();
		serviceProduct = new ProductsService();
		serviceCustomer = new CustomersService();
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
				
				List<Products> listproduct = serviceProduct.getAll();
				List<Orderitems> listcustomerorderitem = new ArrayList<Orderitems>();
				List<Customers> listcustomer = serviceCustomer.getAll();

				request.setAttribute("customerorder", new Customersorder());
				request.setAttribute("listcustomerorderitem", listcustomerorderitem);
				request.setAttribute("listproduct", listproduct);
				request.setAttribute("listcustomer", listcustomer);
				request.getRequestDispatcher(INSERT_UPDATE).forward(request, response);
			}else if(action.equals("list")){
				
				List<Customersorder> listcustomerorder = serviveOrder.getAll();
				request.setAttribute("listcustomerorder", listcustomerorder);
				request.getRequestDispatcher(LIST).forward(request, response);
			}else {
				int id = Integer.parseInt(request.getParameter("id"), 10);
				List<Products> listproduct = serviceProduct.getAll();
				List<Customers> listcustomer = serviceCustomer.getAll();
				List<Orderitems> listorderitem = serviveOrderitem.getAllByCustomerOrder(id);
				Customersorder customerorder = serviveOrder.get(id);
				
				request.setAttribute("customerorder", customerorder);
				request.setAttribute("listorderitem", listorderitem);
				request.setAttribute("listproduct", listproduct);
				request.setAttribute("listcustomer", listcustomer);
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
			List<Products> listproduct = serviceProduct.getAll();
			List<Customers> listcustomer = serviceCustomer.getAll();

			Customersorder customerorder = new Customersorder();
			Message message;
			Boolean errorfield = false;
			StringBuffer errormessage = new StringBuffer();

			BigDecimal totalamount = new BigDecimal(request.getParameter("totalamount"));
			String ordernumber = request.getParameter("ordernumber");
			int idsupplier = Integer.parseInt(request.getParameter("idsupplier"),10);

			String[] idproducts = request.getParameterValues("idproduct[]");
			String[] prices = request.getParameterValues("price[]");
			String[] quantities = request.getParameterValues("quantity[]");
			String[] idorderitem = request.getParameterValues("idorderitem[]");
			
			if(idproducts.length == 0) {
				errormessage.append("No Product selected,\n");
				errorfield = true;
			}else {
				for(int i = 0; i < idproducts.length; i++) {
					if(idproducts[i] == null || idproducts[i].isEmpty()) {
						errormessage.append("No Product selected,\n");
						errorfield = true;
						break;
					}
				}
				for(int i = 0; i < prices.length; i++) {
					if(prices[i] == null || prices[i].isEmpty()) {
						errormessage.append("No Product selected,\n");
						errorfield = true;
						break;
					}
				}
				for(int i = 0; i < quantities.length; i++) {
					if(quantities[i] == null || quantities[i].isEmpty()) {
						errormessage.append("No Product selected,\n");
						errorfield = true;
						break;
					}
				}
				
			}
			
			if(totalamount.equals("")) {
				errormessage.append("Totalamount empty, \n");
				errorfield = true;
			}
			
			if(idsupplier <= 0) {
				errormessage.append("Id suppliers invalid, \n");
				errorfield = true;
			}

			if(errorfield) {
				message = new Message(TypeMessage.error, "Please check the required fields ! " + errormessage);
				System.out.println("Erreur "+message.getMessage());
				request.setAttribute("message", message);
				request.setAttribute("customerorder", customerorder);
				request.setAttribute("listorderitem", new ArrayList<Orderitems>());
				request.setAttribute("listproduct", listproduct);
				request.setAttribute("listcustomer", listcustomer);
				request.getRequestDispatcher(INSERT_UPDATE).forward(request, response);
			}else {
				
				String action = request.getParameter("action");
				List<Orderitems> listorderitem = new ArrayList<>();
				for(int i = 0; i < idproducts.length; i++) {
					Orderitems item = new Orderitems();
					item.setIdproduct(Integer.parseInt(idproducts[i], 10));
					item.setIdcustomerorder(i);
					item.setPrice(new BigDecimal(prices[i]));
					item.setQuantity(Integer.parseInt(quantities[i],10 ));
					item.setId(idorderitem[i].isEmpty()?0:Integer.parseInt(idorderitem[i], 10));
					item.setIdusers(user.getId());
					listorderitem.add(item);
				}
				customerorder.setTotalamount(totalamount);
				if(ordernumber != "")
					customerorder.setCustomerordernumber(ordernumber);
				else
					customerorder.setCustomerordernumber(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) +Autotreatment.getAlphaNumericString(5));
				customerorder.setIdcustomers(idsupplier);
				customerorder.setIdusers(user.getId());
				
				//Check if the action to perform is an update or insertion
				if(action.equals("create")) {
					
					serviveOrder.create(customerorder, listorderitem);
					System.out.println("Stock order created ");
					message = new Message(TypeMessage.success, "Stock order created successfully !");
				}else {
					
					int id = Integer.parseInt(request.getParameter("id"), 10);
					customerorder.setId(id);
					serviveOrder.update(customerorder, listorderitem);
					System.out.println("Stock order Save ");
					message = new Message(TypeMessage.success, "Stock order updated successfully !");
				}
				
				listorderitem  = serviveOrderitem.getAllByCustomerOrder(customerorder.getId());
				
				request.setAttribute("message", message);
				request.setAttribute("customerorder", customerorder);
				request.setAttribute("listorderitem", listorderitem);
				request.setAttribute("listproduct", listproduct);
				request.setAttribute("listcustomer", listcustomer);
				request.getRequestDispatcher(INSERT_UPDATE).forward(request, response);
			}
		}

	}

	public void destroy() {

		System.out.println("in destroy method");
	}
}
