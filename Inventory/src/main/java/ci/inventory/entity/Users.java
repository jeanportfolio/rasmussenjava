package ci.inventory.entity;

import java.time.LocalDateTime;

import ci.inventory.services.UsersService;
import ci.inventory.services.UsersroleService;
import ci.inventory.services.UserstatusService;

public class Users {
	
	//Properties
	private int id;
	private String login;
	private String password;
	private String fisrtname;
	private String lastname;
	private String birthday;
	private int idusersrole;
	private LocalDateTime createdate;
	private LocalDateTime modifydate;
	private int idusers;
	private int iduserstatus;
	private Users users;
	private Usersrole role;
	private Userstatus status;
	
	//Constructors 
	public Users() {
		super();
	}
	public Users(Integer id, String login, String password, String fisrtname, String lastname, String birthday,
			int idusersrole, int idusers, int iduserstatus) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.fisrtname = fisrtname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.idusersrole = idusersrole;
		this.idusers = idusers;
		this.iduserstatus = iduserstatus;
	}
	
	
	@Override
	public String toString() {
		return id + " " + login + " " + password + " " + fisrtname + " " + lastname + " " + birthday + " " + idusersrole + " " + idusers
				 + " " + iduserstatus+ " " +createdate + " "+modifydate;
	}
	
	//Getters and Setters
	
	public Users getUsers() {
		return new UsersService().get(idusers);
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public Usersrole getRole() {
		return new UsersroleService().get(idusersrole);
	}
	public void setRole(Usersrole role) {
		this.role = role;
	}
	public Userstatus getStatus() {
		return new UserstatusService().get(iduserstatus);
	}
	public void setStatus(Userstatus status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFisrtname() {
		return fisrtname;
	}
	public void setFisrtname(String fisrtname) {
		this.fisrtname = fisrtname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public int getIdusersrole() {
		return idusersrole;
	}
	public void setIdusersrole(int idusersrole) {
		this.idusersrole = idusersrole;
	}
	public LocalDateTime getCreatedate() {
		return createdate;
	}
	public void setCreatedate(LocalDateTime createdate) {
		this.createdate = createdate;
	}
	public LocalDateTime getModifydate() {
		return modifydate;
	}
	public void setModifydate(LocalDateTime modifydate) {
		this.modifydate = modifydate;
	}
	public int getIdusers() {
		return idusers;
	}
	public void setIdusers(int idusers) {
		this.idusers = idusers;
	}
	public int getIduserstatus() {
		return iduserstatus;
	}
	public void setIduserstatus(int iduserstatus) {
		this.iduserstatus = iduserstatus;
	}
	
}
