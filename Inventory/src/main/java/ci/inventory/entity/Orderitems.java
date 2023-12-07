package ci.inventory.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import ci.inventory.services.UsersService;

public class Orderitems {
	
	//Properties
	private int id;
	private int idproduct;
	private int idcustomerorder;
	private int quantity;
	private BigDecimal price;
	private int idusers;
	private LocalDateTime createdate;
	private LocalDateTime modifydate;
	
	//Constructors
	public Orderitems() {
		super();
	}
	public Orderitems(Integer id, Integer idproduct, Integer idcustomerorder, Integer quantity, BigDecimal price, Integer idusers) {
		super();
		this.id = id;
		this.idproduct = idproduct;
		this.idcustomerorder = idcustomerorder;
		this.quantity = quantity;
		this.price = price;
		this.idusers = idusers;
	}
	
	@Override
	public String toString() {
		return id + " "+ idproduct+ " "+ idcustomerorder + " "+ quantity + " "+ price+ " "+ idusers;
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
	public int getIdproduct() {
		return idproduct;
	}
	public void setIdproduct(int idproduct) {
		this.idproduct = idproduct;
	}
	public int getIdcustomerorder() {
		return idcustomerorder;
	}
	public void setIdcustomerorder(int idcustomerorder) {
		this.idcustomerorder = idcustomerorder;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
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
