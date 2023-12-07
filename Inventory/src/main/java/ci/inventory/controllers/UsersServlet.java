package ci.inventory.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;

import ci.inventory.entity.Message;
import ci.inventory.entity.TypeMessage;
import ci.inventory.entity.Users;
import ci.inventory.entity.Usersrole;
import ci.inventory.entity.Userstatus;
import ci.inventory.services.UsersService;
import ci.inventory.services.UsersroleService;
import ci.inventory.services.UserstatusService;
import ci.inventory.utility.PasswordEncryption;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@WebServlet("/users")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private UsersService serviveUsers;
	private UserstatusService serviceUserStatus;
	private UsersroleService serviceUsersRole;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init du LoginServlet");
		serviveUsers = new UsersService();
		serviceUsersRole = new UsersroleService();
		serviceUserStatus = new UserstatusService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		session = request.getSession(false);
		String action = request.getParameter("action");

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
				List<Userstatus> userStatus = serviceUserStatus.getAll();
				List<Usersrole> userRoles = serviceUsersRole.getAll();

				request.setAttribute("userStatus", userStatus);
				request.setAttribute("updateUser", new Users());
				request.setAttribute("userRoles", userRoles);

				request.getRequestDispatcher("users.jsp").forward(request, response);
			}else if(action.equals("update")){

				int id = Integer.parseInt(request.getParameter("id"), 10);
				Users updateUser = serviveUsers.get(id);
				updateUser.setPassword("");

				List<Userstatus> userStatus = serviceUserStatus.getAll();
				List<Usersrole> userRoles = serviceUsersRole.getAll();

				request.setAttribute("userStatus", userStatus);
				request.setAttribute("userRoles", userRoles);
				request.setAttribute("updateUser", updateUser);
				request.getRequestDispatcher("users.jsp").forward(request, response);
			}else if(action.equals("profil")){

				//request.setAttribute("connectUser", (Users)session.getAttribute("user"));
				request.getRequestDispatcher("userprofil.jsp").forward(request, response);
			}else {
				List<Users> listusers = serviveUsers.getAll();
				request.setAttribute("listusers", listusers);
				request.getRequestDispatcher("userslist.jsp").forward(request, response);
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {

		session = request.getSession(false);
		System.out.println("Post ");
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

			Users connectuser = (Users)session.getAttribute("user");
			String action = request.getParameter("action");
			
			if(action.equals("create")) {
				Users createUser = new Users();
				List<Userstatus> userStatus = serviceUserStatus.getAll();
				List<Usersrole> userRoles = serviceUsersRole.getAll();
				Message message;
				Boolean errorfield = false;
				StringBuffer errormessage = new StringBuffer();

				String firstname = request.getParameter("firstname");
				String lastname = request.getParameter("lastname");
				String birthday = request.getParameter("birthday");
				String login = request.getParameter("login");
				String password = request.getParameter("password");
				int idrole = Integer.parseInt(request.getParameter("idrole"), 10); 
				int idstatus = Integer.parseInt(request.getParameter("idstatus"), 10);

				if(firstname.isEmpty()) {
					errormessage.append("First name empty,\n");
					errorfield = true;
				}

				if(lastname.isEmpty()) {
					errormessage.append("Last name empty, \n");
					errorfield = true;
				}

				if(birthday.isEmpty()) {
					errormessage.append("Birthday name empty, \n");
					errorfield = true;
				}

				if(login.isEmpty()) {
					errormessage.append("Login name empty, \n");
					errorfield = true;
				}

				if(password.isEmpty()) {
					errormessage.append("Password name empty, \n");
					errorfield = true;
				}

				if(idrole == 0) {
					errormessage.append("User Role invalid, \n");
					errorfield = true;
				}

				if(idstatus == 0) {
					errormessage.append("User Status invalid, \n");
					errorfield = true;
				}

				if(errorfield) {
					message = new Message(TypeMessage.error, "Please check the required fields ! " + errormessage);

					request.setAttribute("users", new Users());
					request.setAttribute("message", message);
					request.setAttribute("userStatus", userStatus);
					request.setAttribute("userRoles", userRoles);
					request.getRequestDispatcher("users.jsp").forward(request, response);
				}else {
					createUser.setFisrtname(firstname);
					createUser.setLastname(lastname);
					createUser.setBirthday(birthday);
					createUser.setLogin(login);
					try {
						createUser.setPassword(PasswordEncryption.encrypt(password));
					} catch (Exception e) {
						System.out.println("Error while encrypting the password: " + e.getMessage());
					}
					createUser.setIdusersrole(idrole);
					createUser.setIduserstatus(idstatus);
					createUser.setIdusers(connectuser.getId());

					serviveUsers.create(createUser);

					message = new Message(TypeMessage.success, "User created successfully !");

					request.setAttribute("users", new Users());		
					request.setAttribute("message", message);
					request.setAttribute("userStatus", userStatus);
					request.setAttribute("userRoles", userRoles);
					request.getRequestDispatcher("users.jsp").forward(request, response);
				}
			}else {
				doPut(request, response);
			}

		}

	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession(false);
		System.out.println("PUT ");

		Users connectuser = (Users)session.getAttribute("user");

		List<Userstatus> userStatus = serviceUserStatus.getAll();
		List<Usersrole> userRoles = serviceUsersRole.getAll();
		Message message;

		int id = Integer.parseInt(request.getParameter("id"), 10);
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String birthday = request.getParameter("birthday");
		//String login = request.getParameter("login");
		String password = request.getParameter("password");
		int idrole = Integer.parseInt(request.getParameter("idrole"), 10); 
		int idstatus = Integer.parseInt(request.getParameter("idstatus"), 10);

		String newpassword = request.getParameter("newpassword") == null ? "" : request.getParameter("newpassword");
		String repeatpassword = request.getParameter("repeatpassword") == null ? "" : request.getParameter("repeatpassword");

		Users updateUser = serviveUsers.get(id);
		try {
			updateUser = serviveUsers.connect(updateUser.getLogin(), PasswordEncryption.encrypt(password));
		} catch (Exception e1) {
			System.out.println("Error while encrypting the password: "+ e1.getMessage());
		}

		if(updateUser != null) {
			if(newpassword.isEmpty()) {
				updateUser.setFisrtname(firstname);
				updateUser.setLastname(lastname);
				updateUser.setBirthday(birthday);
				//updateUser.setLogin(login);
				updateUser.setIdusersrole(idrole);
				updateUser.setIduserstatus(idstatus);
				updateUser.setIdusers(connectuser.getId());

				serviveUsers.update(updateUser);
				updateUser.setPassword("");
				
				//Check if the connected user is the one who has been update and refresh user data in the session
				if(connectuser.getId() == updateUser.getId())
				{
					session.setAttribute("user", updateUser);
				}

				message = new Message(TypeMessage.success, "User updated successfully !");
			}else if(newpassword.equals(repeatpassword)){
				try {
					updateUser.setFisrtname(firstname);
					updateUser.setLastname(lastname);
					updateUser.setBirthday(birthday);
					//updateUser.setLogin(login);
					updateUser.setIdusersrole(idrole);
					updateUser.setIduserstatus(idstatus);
					updateUser.setIdusers(connectuser.getId());
					updateUser.setPassword(PasswordEncryption.encrypt(newpassword));

					serviveUsers.update(updateUser);
					updateUser.setPassword("");
					
					//Check if the connected user is the one who has been update and refresh user data in the session
					if(connectuser.getId() == updateUser.getId())
					{
						session.setAttribute("user", updateUser);
					}

					message = new Message(TypeMessage.success, "User updated successfully !");
				} catch (Exception e) {

					System.out.println("Error while encrypting the password: "+ e.getMessage());
					message = new Message(TypeMessage.error, "Update IMPOSSIBLE, please conctat the administrator !!!");

				}
			}else {
				message = new Message(TypeMessage.warning, "Check, The new password is different to the repeat password !");
			}

		}else {
			message = new Message(TypeMessage.error, "Password incorrect, please try again !");
		}
		System.out.println("Message "+message);
		request.setAttribute("message", message);
		request.setAttribute("updateUser", updateUser);
		request.setAttribute("userStatus", userStatus);
		request.setAttribute("userRoles", userRoles);
		request.getRequestDispatcher("userprofil.jsp").forward(request, response);
	}


	public void destroy() {

		System.out.println("in destroy method");
	}
}
