package com.educhatbot;

import com.educhatbot.ui.ChatbotGUI;
import com.educhatbot.utils.AppConfig;
import com.educhatbot.utils.Logger;
import javafx.application.Application;

/**
 * ChatbotApplication - Main entry point for AI Educational Chatbot
 */
public class ChatbotApplication {
    
    /**
     * Main method - Entry point for the application
     */
    public static void main(String[] args) {
        try {
            // Log startup
            Logger.info("====================================");
            Logger.info("AI Educational Chatbot Starting...");
            Logger.info("Version: " + AppConfig.APP_VERSION);
            Logger.info("Author: " + AppConfig.APP_AUTHOR);
            Logger.info("====================================");
            
            // Log system information
            Logger.info("Java Version: " + System.getProperty("java.version"));
            Logger.info("Operating System: " + System.getProperty("os.name"));
            Logger.info("User: " + System.getProperty("user.name"));
            
            // Launch JavaFX application
            Logger.info("Launching GUI...");
            ChatbotGUI.launch(ChatbotGUI.class, args);
            
        } catch (Exception e) {
            Logger.error("Fatal error during application startup:");
            Logger.error("Exception: " + e.getClass().getName());
            Logger.error("Message: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    /**
     * Shutdown hook for graceful termination
     */
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Logger.info("====================================");
            Logger.info("AI Educational Chatbot Shutting Down");
            Logger.info("====================================");
        }));
    }
}
