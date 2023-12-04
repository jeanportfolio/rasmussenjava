package ci.inventory.utility.log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@SuppressWarnings("static-access")
public class Logging{

	private	static Logger logger;
	private static LogManager loggermanager;
	
	static {
		try {
			
			logger = Logger.getLogger("");
			loggermanager = LogManager.getLogManager();
			loggermanager.getLogManager().readConfiguration(new FileInputStream("src/main/resources/mylogging.properties"));
			
			MyHandler fileHandler = new MyHandler(new FileOutputStream("src/main/resources/logger.log", true), new MyLogFormatter());
			logger.addHandler(fileHandler);
		} catch (SecurityException | IOException e1) {
			logger.log(Level.SEVERE, e1.getMessage(), e1);
		}
	}
	
	/*
	 * @param name = Class Name
	 */
	public static Logger setLoggerName(String name){
		loggermanager.addLogger(Logger.getLogger(name));
		logger = loggermanager.getLogger(name);
		//logger.setLevel(Level.INFO);
		return logger;
	}
	
}