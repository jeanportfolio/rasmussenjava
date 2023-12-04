package ci.inventory.entity;

import java.time.LocalDateTime;

public class Customers {
	//Properties
	private int id;
	private String customername;
	private String phone;
	private String address;
	private int idusers;
	private LocalDateTime createdate;
	private LocalDateTime modifydate;
	
	//Constructors
	public Customers() {
		super();
	}

	public Customers(Integer id, String customername, String phone, String address, Integer idusers) {
		super();
		this.id = id;
		this.customername = customername;
		this.phone = phone;
		this.address = address;
		this.idusers = idusers;
	}
	
	@Override
	public String toString() {
		return id + " "+ customername+ " "+ phone + " "+ address + " "+ idusers;
	}
	
	//Getters and Setters 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
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
