package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtils {
    public static Logger getLogger(Class<?> cls) {
        return LogManager.getLogger(cls);
    }
}
//Class<?> cls → this takes the class reference (e.g., LoginTest.class), so Log4j knows which class the log messages are coming from
//LogManager.getLogger(cls) → this is a Log4j factory method that creates or returns an existing logger for the specified class