package ci.inventory.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Customersorder {
	
	// Properties
	private int id;
	private BigDecimal totalamount;
	private int idcustomers;
	private int idusers;
	private LocalDateTime createdate;
	private LocalDateTime modifydate;
	
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
		return id + " "+ totalamount+ " "+ idcustomers + " "+ idusers;
	}
	
	// Getters and Setters
	public int getId() {
		return id;
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
