package ci.inventory.entity;

import java.time.LocalDateTime;

import ci.inventory.services.UsersService;

public class Event_occur {
	
	//Properties
	private int id;
	private String title;
	private LocalDateTime createdate;
	private LocalDateTime modifydate;
	private int idusers;
	
	// Constructors
	public Event_occur() {
		super();
		
	}

	public Event_occur(Integer id, String title, Integer idusers) {
		super();
		this.id = id;
		this.title = title;
		this.idusers = idusers;
	}
	
	@Override
	public String toString() {
		return id + " "+ title+ " "+ " "+ idusers;
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
	
	
}
