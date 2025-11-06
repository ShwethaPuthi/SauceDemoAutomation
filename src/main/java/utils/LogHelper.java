package utils;

import org.apache.logging.log4j.Logger;

public class LogHelper {

    /*Logs an informational message to both Log4j and ExtentReports*/
    public static void info(Logger log, String message) {
        if (log != null) {
            log.info(message);
        }
        ReportManager.logInfo(message);
    }

    /*Logs a PASS message to both Log4j and ExtentReports*/
    public static void pass(Logger log, String message) {
        if (log != null) {
            log.info("[PASS] " + message);
        }
        ReportManager.logPass(message);
    }

    /*Logs a FAIL message to both Log4j and ExtentReports*/
    public static void fail(Logger log, String message) {
        if (log != null) {
            log.error("[FAIL] " + message);
        }
        ReportManager.logFail(message);
    }

    /*Logs a WARN message to both Log4j and ExtentReports.*/
    public static void warn(Logger log, String message) {
        if (log != null) {
            log.warn(message);
        }
        ReportManager.logInfo("[WARNING] " + message);
    }

    /*Logs a DEBUG message to both Log4j and ExtentReports*/
    public static void debug(Logger log, String message) {
        if (log != null) {
            log.debug(message);
        }
        ReportManager.logInfo("[DEBUG] " + message);
    }
}