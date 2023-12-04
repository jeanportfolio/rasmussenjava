package ci.inventory.controllers;


import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

/**
 * Servlet implementation class TestRest
 */
@Path("/TestRest")
public class TestRest{

    /**
     * Default constructor. 
     */
	@GET
	@Produces("text/plain")
    public String doGet() {
		return "Get me";
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@POST
	@Produces("text/plain")
	public String doPost() {
		return "Post me";
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	@PUT
	@Produces("text/plain")
	public String doPut() {

		return "Put me";
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	@DELETE
	@Produces("text/plain")
	public String doDelete() {
		return "{\"name\":\"greeting\", \"message\":\"Bonjour tout le monde!\"}";
	}

}
