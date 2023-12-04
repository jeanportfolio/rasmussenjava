package ci.inventory.utility.log;

import java.io.OutputStream;
import java.util.logging.Formatter;
import java.util.logging.StreamHandler;

public class MyHandler extends StreamHandler {

	public MyHandler() {
		super();
	}
	
	public MyHandler(OutputStream out, Formatter formatter) {
		super(out, formatter);
	}

}
