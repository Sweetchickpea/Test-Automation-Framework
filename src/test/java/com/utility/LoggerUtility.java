package com.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtility {
	
	//responsible for creating logger, to use in diff classes
	//follows Singleton Design pattern
	
	//global setup for logger
	
	//private static Logger logger;
	
	private LoggerUtility() {
		
	}
	public static Logger getLogger(Class<?> clazz) {
		Logger logger= null;
		if(logger==null) {
		logger = LogManager.getLogger();
		}
		return logger;
	}

}
