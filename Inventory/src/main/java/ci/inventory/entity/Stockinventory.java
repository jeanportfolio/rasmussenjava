package ci.inventory.entity;

import java.time.LocalDateTime;

import ci.inventory.services.ProductsService;
import ci.inventory.services.UsersService;

/**
 * {@summary This class is for managing the stock of products data} 
 *
 */
public class Stockinventory {
	
	//Properties
	private int id;
	private int idproduct;
	private int availablequantity;
	private String title;
	private int minstocklevel;
	private int maxstocklevel;
	private int iduser;
	private LocalDateTime createdate;
	private LocalDateTime modifydate;
	private Products product;
	private Users user;	
	
	//Constructors
	public Stockinventory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Stockinventory(Integer id, Integer idproduct, Integer availablequantity, String title, Integer minstocklevel,
			int maxstocklevel, int iduser) {
		super();
		this.id = id;
		this.idproduct = idproduct;
		this.availablequantity = availablequantity;
		this.title = title;
		this.minstocklevel = minstocklevel;
		this.maxstocklevel = maxstocklevel;
		this.iduser = iduser;
	}
	
	@Override
	public String toString() {
		return id + " idproduct "+ idproduct+ " availablequantity "+ availablequantity +" title "+ title+ " minstocklevel "+ minstocklevel
				+ " maxstocklevel "+ maxstocklevel+ " iduser "+ iduser;
	}
	
	//Getters and Setters
	
	
	public Products getProduct() {
		return new ProductsService().get(idproduct);
	}
	public Users getUser() {
		return new UsersService().get(iduser);
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
	public int getAvailablequantity() {
		return availablequantity;
	}
	public void setAvailablequantity(int availablequantity) {
		this.availablequantity = availablequantity;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getMinstocklevel() {
		return minstocklevel;
	}
	public void setMinstocklevel(int minstocklevel) {
		this.minstocklevel = minstocklevel;
	}
	public int getMaxstocklevel() {
		return maxstocklevel;
	}
	public void setMaxstocklevel(int maxstocklevel) {
		this.maxstocklevel = maxstocklevel;
	}
	public int getIduser() {
		return iduser;
	}
	public void setIduser(int iduser) {
		this.iduser = iduser;
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
