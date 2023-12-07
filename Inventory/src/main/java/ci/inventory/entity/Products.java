package ci.inventory.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import ci.inventory.services.CategoryproductService;
import ci.inventory.services.UsersService;

/**
 * {@summary This class is for managing the products data} 
 *
 */
public class Products {
	
	//Properties
	private int id;
	private String designation;
	private String description;
	private BigDecimal price;
	private BigDecimal saleprice;
	private int idcategory;
	private int idusers;
	private Categoryproduct category;
	private Users user;
	private LocalDateTime createdate;
	private LocalDateTime modifydate;
	
	//Constructors
	public Products() {
		super();
	}
	public Products(Integer id, String designation, String description,BigDecimal saleprice, BigDecimal price, Integer idcategory, Integer idusers) {
		super();
		this.id = id;
		this.designation = designation;
		this.description = description;
		this.saleprice = saleprice;
		this.price = price;
		this.idcategory = idcategory;
		this.idusers = idusers;
	}
	
	// Methods
	@Override
	public String toString() {
		return id + " designation "+ designation+ " description "+ description +" price "+ price + " saleprice "+saleprice
				+ " idcategory "+ idcategory + " idusers "+ idusers;
	}
	
	//Getters and Setters
	public Categoryproduct getCategory() {
		return new CategoryproductService().get(idcategory);
	}
	
	public Users getUser() {
		return new UsersService().get(idusers);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public BigDecimal getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(BigDecimal saleprice) {
		this.saleprice = saleprice;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getIdcategory() {
		return idcategory;
	}
	public void setIdcategory(int idcategory) {
		this.idcategory = idcategory;
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
