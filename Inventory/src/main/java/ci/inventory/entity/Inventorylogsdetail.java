package ci.inventory.entity;

import java.time.LocalDateTime;

import ci.inventory.services.UsersService;

public class Inventorylogsdetail {
	
	//Properties
	private int id;
	private String description;
	private int idstockmovement;
	private int idinventorylogs;
	private int idorderitem;
	private int idstockorderitem;
	private int idusers;
	private int oldstocklevel;
	private int newstocklevel;
	private LocalDateTime createdate;
	private LocalDateTime modifydate;
	
	//Constructors
	public Inventorylogsdetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Inventorylogsdetail(Integer id, String description, Integer idstockmovement, Integer idinventorylogs, Integer idorderitem,
			Integer idstockorderitem, Integer idusers, Integer oldstocklevel, Integer newstocklevel) {
		super();
		this.id = id;
		this.description = description;
		this.idstockmovement = idstockmovement;
		this.idinventorylogs = idinventorylogs;
		this.idorderitem = idorderitem;
		this.idstockorderitem = idstockorderitem;
		this.idusers = idusers;
		this.oldstocklevel = oldstocklevel;
		this.newstocklevel = newstocklevel;
	}
	
	@Override
	public String toString() {
		return id + " description "+ description+ " idstockmovement "+ idstockmovement + " idinventorylogs "+ idinventorylogs 
				+ " idorderitem "+ idorderitem+ " oldstocklevel "+ oldstocklevel +" newstocklevel "+ newstocklevel + " idusers "+ idusers;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIdstockmovement() {
		return idstockmovement;
	}
	public void setIdstockmovement(int idstockmovement) {
		this.idstockmovement = idstockmovement;
	}
	public int getIdinventorylogs() {
		return idinventorylogs;
	}
	public void setIdinventorylogs(int idinventorylogs) {
		this.idinventorylogs = idinventorylogs;
	}
	public int getIdorderitem() {
		return idorderitem;
	}
	public void setIdorderitem(int idorderitem) {
		this.idorderitem = idorderitem;
	}
	public int getIdstockorderitem() {
		return idstockorderitem;
	}
	public void setIdstockorderitem(int idstockorderitem) {
		this.idstockorderitem = idstockorderitem;
	}
	public int getIdusers() {
		return idusers;
	}
	public void setIdusers(int idusers) {
		this.idusers = idusers;
	}
	public int getOldstocklevel() {
		return oldstocklevel;
	}
	public void setOldstocklevel(int oldstocklevel) {
		this.oldstocklevel = oldstocklevel;
	}
	public int getNewstocklevel() {
		return newstocklevel;
	}
	public void setNewstocklevel(int newstocklevel) {
		this.newstocklevel = newstocklevel;
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
