package ci.inventory.entity;

import java.time.LocalDateTime;
import java.sql.Time;

public class Logs_detail {
	
	//Properties
	private int id;
	private Time creationtime;
	private int ocurence_id;
	private String description;
	private String table_affected;
	private LocalDateTime createdate;
	private LocalDateTime modifydate;
	private int idusers;
	private int idlogs;
	private int ideventoccur;
	
	//Constructors
	public Logs_detail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Logs_detail(Integer id, Time creationtime, int ocurence_id, String description, String table_affected,
			Integer idusers, Integer idlogs, Integer ideventoccur) {
		super();
		this.id = id;
		this.creationtime = creationtime;
		this.ocurence_id = ocurence_id;
		this.description = description;
		this.table_affected = table_affected;
		this.idusers = idusers;
		this.idlogs = idlogs;
		this.ideventoccur = ideventoccur;
	}
	
	@Override
	public String toString() {
		return "id "+ id + " creationtime "+ creationtime+ " ocurence_id "+ ocurence_id + " description "+ description + " table_affected "
				+ table_affected+" idlogs " + idlogs + " ideventoccur "+ ideventoccur+ " idusers "+ idusers;
	}
	
	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Time getCreationtime() {
		return creationtime;
	}
	public void setCreationtime(Time creationtime) {
		this.creationtime = creationtime;
	}
	public int getOcurence_id() {
		return ocurence_id;
	}
	public void setOcurence_id(int ocurence_id) {
		this.ocurence_id = ocurence_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTable_affected() {
		return table_affected;
	}
	public void setTable_affected(String table_affected) {
		this.table_affected = table_affected;
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
	public int getIdusers() {
		return idusers;
	}
	public void setIdusers(int idusers) {
		this.idusers = idusers;
	}
	public int getIdlogs() {
		return idlogs;
	}
	public void setIdlogs(int idlogs) {
		this.idlogs = idlogs;
	}
	public int getIdeventoccur() {
		return ideventoccur;
	}
	public void setIdeventoccur(int ideventoccur) {
		this.ideventoccur = ideventoccur;
	}
	
	
}
