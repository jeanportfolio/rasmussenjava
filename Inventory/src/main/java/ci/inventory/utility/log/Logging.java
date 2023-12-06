package ci.inventory.utility.log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@SuppressWarnings("static-access")
public class Logging{

	private	static Logger logger;
	private static LogManager loggermanager;
	private static MyHandler fileHandler;

	
	static {
		try {
			
			Path basepath = Path.of("src/main/resources/").toAbsolutePath();
			

			System.out.println(basepath);
			 
			// Initialize a default logger C:/Users/Jeanvy/git/repository/Inventory/
			logger = Logger.getLogger("");
			loggermanager = LogManager.getLogManager();
			loggermanager.getLogManager().readConfiguration(new FileInputStream(basepath+"/mylogging.properties"));
			
			//Initialize my customize handler
			fileHandler = new MyHandler(new FileOutputStream(basepath.toString()+"/logger.log", true), new MyLogFormatter());
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