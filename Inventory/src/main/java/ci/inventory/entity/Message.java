package ci.inventory.entity;

public class Message {
	private TypeMessage typeMessage;
	private String message;
	public Message(TypeMessage typeMessage, String message) {
		super();
		this.typeMessage = typeMessage;
		this.message = message;
	}
	public Message() {
		this.typeMessage = TypeMessage.info;
		this.message = "";
	}
	public TypeMessage getTypeMessage() {
		return typeMessage;
	}
	public void setTypeMessage(TypeMessage typeMessage) {
		this.typeMessage = typeMessage;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
