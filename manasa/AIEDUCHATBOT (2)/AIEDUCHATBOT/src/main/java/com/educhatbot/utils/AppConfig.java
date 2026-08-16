package com.educhatbot.utils;

/**
 * AppConfig - Configuration constants for the application
 */
public class AppConfig {
    
    // Application metadata
    public static final String APP_NAME = "AI Educational Chatbot";
    public static final String APP_VERSION = "1.0.0";
    public static final String APP_AUTHOR = "Educational AI Team";
    
    // UI Configuration
    public static final int WINDOW_WIDTH = 900;
    public static final int WINDOW_HEIGHT = 700;
    public static final int MINIMUM_WINDOW_WIDTH = 700;
    public static final int MINIMUM_WINDOW_HEIGHT = 500;
    
    // Colors
    public static final String PRIMARY_COLOR = "#3498db";
    public static final String SECONDARY_COLOR = "#2c3e50";
    public static final String SUCCESS_COLOR = "#27ae60";
    public static final String WARNING_COLOR = "#e74c3c";
    public static final String BACKGROUND_COLOR = "#f5f5f5";
    public static final String DARK_TEXT = "#2c3e50";
    public static final String LIGHT_TEXT = "#ecf0f1";
    
    // Chatbot Configuration
    public static final int MAX_MESSAGE_LENGTH = 1000;
    public static final int MIN_MESSAGE_LENGTH = 1;
    public static final long MESSAGE_DELAY = 300; // milliseconds
    
    // Topics
    public static final String[] SUPPORTED_SUBJECTS = {
        "Mathematics",
        "Physics",
        "Chemistry",
        "Biology",
        "History",
        "Geography",
        "Literature",
        "General Knowledge"
    };
    
    // Knowledge base size
    public static final int KNOWLEDGE_BASE_SIZE = 20;
    
    /**
     * Get application title
     */
    public static String getApplicationTitle() {
        return APP_NAME + " v" + APP_VERSION;
    }
    
    /**
     * Get supported subjects as string
     */
    public static String getSupportedSubjectsAsString() {
        return String.join(", ", SUPPORTED_SUBJECTS);
    }
}
