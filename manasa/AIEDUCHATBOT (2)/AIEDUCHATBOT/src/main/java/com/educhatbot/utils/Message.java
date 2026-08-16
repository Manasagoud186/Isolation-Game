package com.educhatbot.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Message - Represents a single chat message
 */
public class Message {
    
    private final String sender;
    private final String content;
    private final LocalDateTime timestamp;
    private final MessageType type;
    
    public enum MessageType {
        USER_MESSAGE,
        BOT_RESPONSE,
        SYSTEM_MESSAGE,
        MOTIVATIONAL
    }
    
    /**
     * Constructor for Message
     */
    public Message(String sender, String content, MessageType type) {
        this.sender = sender;
        this.content = content;
        this.type = type;
        this.timestamp = LocalDateTime.now();
    }
    
    // Getters
    public String getSender() {
        return sender;
    }
    
    public String getContent() {
        return content;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public MessageType getType() {
        return type;
    }
    
    /**
     * Get formatted message for display
     */
    public String getFormattedMessage() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String time = timestamp.format(formatter);
        
        return String.format("[%s] %s (%s): %s", time, sender, type, content);
    }
    
    @Override
    public String toString() {
        return getFormattedMessage();
    }
}
