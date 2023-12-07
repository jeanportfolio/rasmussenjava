package ci.inventory.entity;

import java.time.LocalDateTime;

import ci.inventory.services.UsersService;

public class Stock_movement {
	
	//Properties
	private int id;
	private String title;
	private int idusers;
	private LocalDateTime createdate;
	private LocalDateTime modifydate;
	
	//Constructors
	public Stock_movement() {
		super();
	}
	public Stock_movement(Integer id, String title, Integer idusers) {
		super();
		this.id = id;
		this.title = title;
		this.idusers = idusers;
	}
	
	@Override
	public String toString() {
		return "id "+ id + " title "+ title+ " idusers "+ idusers;
	}
	
	//Getters and Setters
	public Users getUser() {
		return  new UsersService().get(idusers);
	}
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
	public int getIdusers() {
		return idusers;
	}
	public void setIdusers(int idusers) {
		this.idusers = idusers;
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
	
	
}
