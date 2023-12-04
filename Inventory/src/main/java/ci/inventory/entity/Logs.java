package ci.inventory.entity;

import java.time.LocalDateTime;;

public class Logs {
	
	//Properties
	private int id;
	private LocalDateTime createdate;
	private LocalDateTime modifydate;
	private int iduser;
	
	//Constructors
	public Logs() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Logs(Integer id, Integer iduser) {
		super();
		this.id = id;
		this.iduser = iduser;
	}
	
	@Override
	public String toString() {
		return id + " iduser "+ iduser;
	}
	
	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
