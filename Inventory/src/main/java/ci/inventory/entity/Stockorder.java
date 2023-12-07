package ci.inventory.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import ci.inventory.services.UsersService;

public class Stockorder {
	
	//Properties
	private int id;
	private BigDecimal totalamount;
	private String stockordernumber;
	private int idsuppliers;
	private int idusers;
	private LocalDateTime createdate;
	private LocalDateTime modifydate;
	private Users user;
	
	//Constructors
	public Stockorder() {
		super();
	}
	public Stockorder(Integer id, BigDecimal totalamount, Integer idsuppliers, Integer idusers) {
		super();
		this.id = id;
		this.totalamount = totalamount;
		this.idsuppliers = idsuppliers;
		this.idusers = idusers;
	}
	
	@Override
	public String toString() {
		return id + " totalamount "+ totalamount+ " idsuppliers "+ idsuppliers + " idusers "+ idusers;
	}
	
	
	public Users getUser() {
		return  new UsersService().get(idusers);
	}
	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStockordernumber() {
		return stockordernumber;
	}
	public void setStockordernumber(String stockordernumber) {
		this.stockordernumber = stockordernumber;
	}
	public BigDecimal getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(BigDecimal totalamount) {
		this.totalamount = totalamount;
	}
	public int getIdsuppliers() {
		return idsuppliers;
	}
	public void setIdsuppliers(int idsuppliers) {
		this.idsuppliers = idsuppliers;
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
