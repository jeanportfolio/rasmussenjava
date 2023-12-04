package ci.inventory.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Stockorderitems {
	
	//Properties
	private int id;
	private int idstockorder;
	private int idproduct;
	private int quantity;
	private BigDecimal price;
	private int idusers;
	private LocalDateTime createdate;
	private LocalDateTime modifydate;
	
	//Constructors
	public Stockorderitems() {
		super();
	}
	public Stockorderitems(Integer id, Integer idstockorder, Integer idproduct, Integer quantity, BigDecimal price, Integer idusers) {
		super();
		this.id = id;
		this.idstockorder = idstockorder;
		this.idproduct = idproduct;
		this.quantity = quantity;
		this.price = price;
		this.idusers = idusers;
	}
	
	@Override
	public String toString() {
		return id + " idstockorder " + idstockorder+ " idproduct "+ idproduct + " quantity"+ quantity+ " price "+ price+ " idusers "+ idusers;
	}
	
	// Getters and Setters 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdstockorder() {
		return idstockorder;
	}
	public void setIdstockorder(int idstockorder) {
		this.idstockorder = idstockorder;
	}
	public int getIdproduct() {
		return idproduct;
	}
	public void setIdproduct(int idproduct) {
		this.idproduct = idproduct;
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
