package ci.inventory.entity;

import java.time.LocalDateTime;

public class Userstatus {
	
	//Properties
	private int id;
	private String title;
	private LocalDateTime createdate;
	private LocalDateTime modifydate;
	private int idusers;
	
	//Constructors
	public Userstatus() {
		super();
	}

	public Userstatus(Integer id, String title, int idusers) {
		super();
		this.id = id;
		this.title = title;
		this.idusers = idusers;
	}

	
	@Override
	public String toString() {
		return id + " " + title+ " " + createdate + " " + modifydate + " "+ idusers;
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
