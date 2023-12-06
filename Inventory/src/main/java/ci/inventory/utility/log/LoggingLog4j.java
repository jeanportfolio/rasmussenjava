package ci.inventory.utility.log;


import java.io.FileInputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.xml.XmlConfigurationFactory;


public class LoggingLog4j{

	private static Logger logger;
	private static Path usersdirectory;
	private static ConfigurationFactory factory;
	private static ConfigurationSource configurationSource;
	private static Configuration configuration;
	private static LoggerContext context;
	
	static {
		try {
			/*FileSystem fs = FileSystems.getDefault();
		    //usersdirectory = fs.getPath(System.getProperty("user.home"));
			
		    // Get instance of configuration factory XMLConfigurationFactory
	    	factory =  XmlConfigurationFactory.getInstance();
	    	// Locate the source of this configuration, this located file is dummy file contains just an empty configuration Tag
	    	//configurationSource = new ConfigurationSource(new FileInputStream(usersdirectory + "/configuration.xml"));
	    	
	    	// Get context instance
	    	context = new LoggerContext("JournalDevLoggerContext");
	 
	    	// Get a reference from configuration
	    	//configuration = factory.getConfiguration(context, configurationSource);
	    	
	    	// Start logging system
	    	context.start(configuration);
	    	
	    	/* Create default console appender
	    	ConsoleAppender appender = ConsoleAppender.createDefaultAppenderForLayout(PatternLayout.createDefaultLayout());
	 
	    	// Add console appender into configuration
	    	configuration.addAppender(appender);
	 
	    	// Create loggerConfig
	    	LoggerConfig loggerConfig = new LoggerConfig("ci.inventory.controllers",Level.FATAL,false);
	 
	    	// Add appender
	    	loggerConfig.addAppender(appender,null,null);
	 
	    	// Add logger and associate it with loggerConfig instance
	    	configuration.addLogger("ci.inventory.controllers", loggerConfig);
	    	*/
			 
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
	}
	
	
	public Logger getLogger(String... classe) {
		logger = LogManager.getLogger(classe);
        return logger;
        
    }
}