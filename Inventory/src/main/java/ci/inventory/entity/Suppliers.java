package ci.inventory.entity;

import java.time.LocalDateTime;

public class Suppliers {
	
	//Properties
	private int id;
	private String suppliersname;
	private String phone;
	private String address;
	private int idusers;
	private LocalDateTime createdate;
	private LocalDateTime modifydate;
	
	//Constructors
	public Suppliers() {
		super();
	}
	public Suppliers(Integer id, String suppliersname, String phone, String address, int idusers) {
		super();
		this.id = id;
		this.suppliersname = suppliersname;
		this.phone = phone;
		this.address = address;
		this.idusers = idusers;
	}
	
	@Override
	public String toString() {
		return "id "+id + " suppliersname "+ suppliersname+ " phone "+ phone +" address "+ address+ " idusers "+ idusers;
	}
	
	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSuppliersname() {
		return suppliersname;
	}
	public void setSuppliersname(String suppliersname) {
		this.suppliersname = suppliersname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
