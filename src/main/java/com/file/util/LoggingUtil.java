package com.file.util;

import com.file.commons.LogFilter;
import com.file.commons.LogFormatter;
import com.file.handler.LogHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static com.file.constants.ApplicationConstants.LOG_PATH;

public class LoggingUtil {

    static Logger logger = Logger.getLogger(LoggingUtil.class.getName());
    
    public static void log(String logEvent, String message, Level logLevel) {
        try {
            LoggingUtil self = new LoggingUtil();
            LogManager.getLogManager().readConfiguration(new FileInputStream(self.getPropertiesFile()));
        } catch (SecurityException | IOException ex) {
            ex.printStackTrace();
        }
        logger.setLevel(Level.FINE);
        logger.addHandler(new ConsoleHandler());
        //adding custom handler
        logger.addHandler(new LogHandler());
        try {
            //FileHandler file name with max size and number of log files limit
            Handler fileHandler = new FileHandler(LOG_PATH, 2000, 5);
            fileHandler.setFormatter(new LogFormatter());
            //setting custom filter for FileHandler
            fileHandler.setFilter(new LogFilter());
            logger.addHandler(fileHandler);
            logger.log(logLevel, "Msg: "+ logEvent+ ": " + message);
            logger.log(Level.CONFIG, "Config data");
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    // get file from classpath, resources folder
    private String getPropertiesFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("application.properties");
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return resource.getFile();
        }
    }

}