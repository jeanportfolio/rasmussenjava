package ci.inventory.entity;

import java.time.LocalDateTime;

public class Usersrole {
	
	//Properties
	private int id;
	private String title;
	private String description;
	private LocalDateTime createdate;
	private LocalDateTime modifydate;
	private int iduser;
	
	//Constructors
	public Usersrole() {
		super();
	}
	public Usersrole(Integer id, String title, String description, int iduser) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.iduser = iduser;
	}
	
	@Override
	public String toString() {
		return id + " " + title+ " " + description + " " + createdate + " " + modifydate + " "+ iduser;
	}
	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public int getIduser() {
		return iduser;
	}
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
	
	
}
