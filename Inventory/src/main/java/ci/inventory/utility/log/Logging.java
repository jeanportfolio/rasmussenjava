package ci.inventory.utility.log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@SuppressWarnings("static-access")
public class Logging{

	private	static Logger logger;
	private static LogManager loggermanager;
	
	static {
		try {
			/*
			 * Path path = Paths.get("src/main/resources/mylogging.properties");
			 * System.out.println(path.getFileName());
			 */
			// Initialize a default logger
			logger = Logger.getLogger("");
			loggermanager = LogManager.getLogManager();
			loggermanager.getLogManager().readConfiguration(new FileInputStream("src/main/resources/mylogging.properties"));
			
			//Initialize my customize handler
			MyHandler fileHandler = new MyHandler(new FileOutputStream("src/main/resources/logger.log", true), new MyLogFormatter());
			logger.addHandler(fileHandler);
		} catch (SecurityException | IOException e1) {
			logger.log(Level.SEVERE, e1.getMessage(), e1);
		}
	}
	
	/**
	 * @param name Class Name
	 * @return Logger
	 */
	public static Logger setLoggerName(String name){
		loggermanager.addLogger(Logger.getLogger(name));
		logger = loggermanager.getLogger(name);
		return logger;
	}	
}