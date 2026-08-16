package com.educhatbot.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Logger - Simple logging utility for the application
 */
public class Logger {
    
    private static final List<String> logs = new ArrayList<>();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public enum LogLevel {
        INFO,
        WARNING,
        ERROR,
        SUCCESS,
        DEBUG
    }
    
    /**
     * Log a message with specified level
     */
    public static void log(LogLevel level, String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logMessage = String.format("[%s] %s: %s", timestamp, level, message);
        logs.add(logMessage);
        System.out.println(logMessage);
    }
    
    /**
     * Log info level message
     */
    public static void info(String message) {
        log(LogLevel.INFO, message);
    }
    
    /**
     * Log warning level message
     */
    public static void warning(String message) {
        log(LogLevel.WARNING, message);
    }
    
    /**
     * Log error level message
     */
    public static void error(String message) {
        log(LogLevel.ERROR, message);
    }
    
    /**
     * Log success level message
     */
    public static void success(String message) {
        log(LogLevel.SUCCESS, message);
    }
    
    /**
     * Log debug level message
     */
    public static void debug(String message) {
        log(LogLevel.DEBUG, message);
    }
    
    /**
     * Get all logs
     */
    public static List<String> getLogs() {
        return new ArrayList<>(logs);
    }
    
    /**
     * Clear all logs
     */
    public static void clearLogs() {
        logs.clear();
    }
    
    /**
     * Get logs as string
     */
    public static String getLogsAsString() {
        StringBuilder sb = new StringBuilder();
        for (String log : logs) {
            sb.append(log).append("\n");
        }
        return sb.toString();
    }
}
