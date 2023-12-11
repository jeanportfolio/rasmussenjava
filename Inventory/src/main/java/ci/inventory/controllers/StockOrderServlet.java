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
import ci.inventory.entity.Products;
import ci.inventory.entity.Stockorder;
import ci.inventory.entity.Stockorderitems;
import ci.inventory.entity.Suppliers;
import ci.inventory.entity.TypeMessage;
import ci.inventory.entity.Users;
import ci.inventory.services.ProductsService;
import ci.inventory.services.StockorderService;
import ci.inventory.services.StockorderitemsService;
import ci.inventory.services.SuppliersService;
import ci.inventory.utility.Autotreatment;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@WebServlet("/stockorder")
public class StockOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private static StockorderService serviveStockorder;
	private static StockorderitemsService serviveStockorderitem;
	private static ProductsService serviceProduct;
	private static SuppliersService serviceSupplier;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init du LoginServlet");
		serviveStockorder = new StockorderService();
		serviveStockorderitem = new StockorderitemsService();
		serviceProduct = new ProductsService();
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
				
				List<Products> listproduct = serviceProduct.getAll();
				List<Stockorderitems> liststockorderitem = new ArrayList<Stockorderitems>();
				List<Suppliers> listsupplier = serviceSupplier.getAll();

				request.setAttribute("stockorder", new Stockorder());
				request.setAttribute("liststockorderitem", liststockorderitem);
				request.setAttribute("listproduct", listproduct);
				request.setAttribute("listsupplier", listsupplier);
				request.getRequestDispatcher("stockorder.jsp").forward(request, response);
			}else if(action.equals("list")){
				
				List<Stockorder> liststockorder = serviveStockorder.getAll();
				request.setAttribute("liststockorder", liststockorder);
				request.getRequestDispatcher("stockorderlist.jsp").forward(request, response);
			}else {
				int id = Integer.parseInt(request.getParameter("id"), 10);
				List<Products> listproduct = serviceProduct.getAll();
				List<Suppliers> listsupplier = serviceSupplier.getAll();
				List<Stockorderitems> liststockorderitem = serviveStockorderitem.getAllByStockorder(id);
				Stockorder stockorder = serviveStockorder.get(id);
				
				request.setAttribute("stockorder", stockorder);
				request.setAttribute("liststockorderitem", liststockorderitem);
				request.setAttribute("listproduct", listproduct);
				request.setAttribute("listsupplier", listsupplier);
				request.getRequestDispatcher("stockorder.jsp").forward(request, response);
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
			List<Suppliers> listsupplier = serviceSupplier.getAll();

			Stockorder stockorder = new Stockorder();
			Message message;
			Boolean errorfield = false;
			StringBuffer errormessage = new StringBuffer();

			BigDecimal totalamount = new BigDecimal(request.getParameter("totalamount"));
			String ordernumber = request.getParameter("ordernumber");
			int idsupplier = Integer.parseInt(request.getParameter("idsupplier"),10);

			String[] idproducts = request.getParameterValues("idproduct[]");
			String[] prices = request.getParameterValues("price[]");
			String[] quantities = request.getParameterValues("quantity[]");
			String[] idstockorderitem = request.getParameterValues("idstockorderitem[]");
			
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
				request.setAttribute("stockorder", stockorder);
				request.setAttribute("liststockorderitem", new ArrayList<Stockorderitems>());
				request.setAttribute("listproduct", listproduct);
				request.setAttribute("listsupplier", listsupplier);
				request.getRequestDispatcher("stockorder.jsp").forward(request, response);
			}else {
				
				String action = request.getParameter("action");
				List<Stockorderitems> liststockitems = new ArrayList<>();
				for(int i = 0; i < idproducts.length; i++) {
					Stockorderitems item = new Stockorderitems();
					item.setIdproduct(Integer.parseInt(idproducts[i], 10));
					item.setIdstockorder(i);
					item.setPrice(new BigDecimal(prices[i]));
					item.setQuantity(Integer.parseInt(quantities[i],10 ));
					item.setId(idstockorderitem[i].isEmpty()?0:Integer.parseInt(idstockorderitem[i], 10));
					item.setIdusers(user.getId());
					liststockitems.add(item);
				}
				stockorder.setTotalamount(totalamount);
				if(ordernumber != "")
					stockorder.setStockordernumber(ordernumber);
				else
					stockorder.setStockordernumber(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) +Autotreatment.getAlphaNumericString(5));
				stockorder.setIdsuppliers(idsupplier);
				stockorder.setIdusers(user.getId());
				
				//Check if the action to perform is an update or insertion
				if(action.equals("create")) {
					
					serviveStockorder.create(stockorder, liststockitems);
					System.out.println("Stock order created ");
					message = new Message(TypeMessage.success, "Stock order created successfully !");
				}else {
					
					int id = Integer.parseInt(request.getParameter("id"), 10);
					stockorder.setId(id);
					serviveStockorder.update(stockorder, liststockitems);
					System.out.println("Stock order Save ");
					message = new Message(TypeMessage.success, "Stock order updated successfully !");
				}
				
				List<Stockorderitems> liststockorderitem  = serviveStockorderitem.getAllByStockorder(stockorder.getId());
				
				request.setAttribute("message", message);
				request.setAttribute("stockorder", stockorder);
				request.setAttribute("liststockorderitem", liststockorderitem);
				request.setAttribute("listproduct", listproduct);
				request.setAttribute("listsupplier", listsupplier);
				request.getRequestDispatcher("stockorder.jsp").forward(request, response);
			}
		}

	}

	public void destroy() {

		System.out.println("in destroy method");
	}
}
