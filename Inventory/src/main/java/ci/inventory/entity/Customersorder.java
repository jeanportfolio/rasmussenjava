package ci.inventory.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import ci.inventory.services.CustomersService;
import ci.inventory.services.UsersService;

public class Customersorder {
	
	// Properties
	private int id;
	private BigDecimal totalamount;
	private String customerordernumber;
	private int idcustomers;
	private int idusers;
	private LocalDateTime createdate;
	private LocalDateTime modifydate;
	@SuppressWarnings("unused")
	private Users user;
	@SuppressWarnings("unused")
	private Customers customer;
	
	// Constructors
	public Customersorder() {
		super();
		
	}

	public Customersorder(Integer id, BigDecimal totalamount, Integer idcustomers, Integer idusers) {
		super();
		this.id = id;
		this.totalamount = totalamount;
		this.idcustomers = idcustomers;
		this.idusers = idusers;
	}
	
	@Override
	public String toString() {
		return id + " "+ totalamount+ " " +customerordernumber + " "+ idcustomers + " "+ idusers;
	}
	
	// Getters and Setters
	
	public int getId() {
		return id;
	}

	public Customers getCustomer() {
		return new CustomersService().get(idcustomers);
	}

	public String getCustomerordernumber() {
		return customerordernumber;
	}

	public void setCustomerordernumber(String customerordernumber) {
		this.customerordernumber = customerordernumber;
	}

	public Users getUser() {
		return new UsersService().get(idusers);
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(BigDecimal totalamount) {
		this.totalamount = totalamount;
	}

	public int getIdcustomers() {
		return idcustomers;
	}

	public void setIdcustomers(int idcustomers) {
		this.idcustomers = idcustomers;
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
