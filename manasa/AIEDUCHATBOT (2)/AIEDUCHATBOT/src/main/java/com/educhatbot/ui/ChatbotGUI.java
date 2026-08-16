package com.educhatbot.ui;

import com.educhatbot.ai.ChatbotEngine;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * ChatbotGUI - Main JavaFX Application for Educational Chatbot
 */
public class ChatbotGUI extends Application {
    
    private ChatbotEngine chatbot;
    private TextArea chatDisplay;
    private TextField userInput;
    private Button sendButton;
    private Button clearButton;
    private Button tipsButton;
    private Button motivateButton;
    private Label statusLabel;
    
    @Override
    public void start(Stage primaryStage) {
        // Initialize chatbot engine
        chatbot = new ChatbotEngine();
        
        // Create main layout
        BorderPane mainLayout = createMainLayout();
        
        // Create scene
        Scene scene = new Scene(mainLayout, 900, 700);
        
        // Apply stylesheet
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        
        // Set up stage
        primaryStage.setTitle("Educational AI Assistant");
        primaryStage.setScene(scene);
        primaryStage.setWidth(900);
        primaryStage.setHeight(700);
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        
        // Show welcome message
        addMessageToChat("Bot", chatbot.getResponse("hello"));
        
        // Show stage
        primaryStage.show();
    }
    
    /**
     * Create the main layout of the application
     */
    private BorderPane createMainLayout() {
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #f5f5f5;");
        
        // Top: Header
        borderPane.setTop(createHeader());
        
        // Center: Chat area
        borderPane.setCenter(createChatArea());
        
        // Bottom: Input area
        borderPane.setBottom(createInputArea());
        
        return borderPane;
    }
    
    /**
     * Create header section
     */
    private VBox createHeader() {
        VBox header = new VBox();
        header.setPadding(new Insets(15));
        header.setStyle("-fx-background-color: linear-gradient(to right, #2c3e50, #3498db); -fx-border-color: #34495e; -fx-border-width: 0 0 2 0;");
        header.setAlignment(Pos.CENTER);
        
        Label title = new Label("🤖 Educational AI Chatbot");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setTextFill(Color.WHITE);
        
        Label subtitle = new Label("Powered by Artificial Intelligence - Learn & Explore with AI Assistance");
        subtitle.setFont(Font.font("Arial", 12));
        subtitle.setTextFill(Color.web("#ecf0f1"));
        
        header.getChildren().addAll(title, subtitle);
        
        return header;
    }
    
    /**
     * Create chat display area
     */
    private VBox createChatArea() {
        VBox chatArea = new VBox();
        chatArea.setPadding(new Insets(15));
        chatArea.setStyle("-fx-background-color: white;");
        chatArea.setSpacing(10);
        
        // Chat display
        chatDisplay = new TextArea();
        chatDisplay.setWrapText(true);
        chatDisplay.setEditable(false);
        chatDisplay.setStyle("-fx-font-size: 12px; -fx-font-family: 'Segoe UI'; -fx-text-fill: #2c3e50;");
        chatDisplay.setPrefHeight(400);
        
        // Scroll pane for chat
        ScrollPane scrollPane = new ScrollPane(chatDisplay);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-control-inner-background: white; -fx-border-color: #ecf0f1;");
        
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        chatArea.getChildren().add(scrollPane);
        
        return chatArea;
    }
    
    /**
     * Create input and button area
     */
    private VBox createInputArea() {
        VBox inputArea = new VBox();
        inputArea.setPadding(new Insets(15));
        inputArea.setStyle("-fx-background-color: #ecf0f1; -fx-border-color: #bdc3c7; -fx-border-width: 2 0 0 0;");
        inputArea.setSpacing(10);
        
        // User input field
        userInput = new TextField();
        userInput.setPromptText("Type your academic question here...");
        userInput.setStyle("-fx-font-size: 13px; -fx-padding: 10px; -fx-border-radius: 5;");
        userInput.setPrefHeight(40);
        userInput.setOnKeyPressed(e -> {
            if (e.getCode().toString().equals("ENTER")) {
                sendMessage();
            }
        });
        
        // Button area
        HBox buttonArea = createButtonArea();
        
        // Status label
        statusLabel = new Label("Ready to help! 📚");
        statusLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: #7f8c8d;");
        
        inputArea.getChildren().addAll(userInput, buttonArea, statusLabel);
        
        return inputArea;
    }
    
    /**
     * Create button area with various options
     */
    private HBox createButtonArea() {
        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        
        // Send button
        sendButton = new Button("📤 Send");
        sendButton.setPrefWidth(100);
        sendButton.setPrefHeight(35);
        sendButton.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-background-color: #3498db; -fx-text-fill: white; -fx-border-radius: 5; -fx-cursor: hand;");
        sendButton.setOnMouseEntered(e -> sendButton.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-background-color: #2980b9; -fx-text-fill: white; -fx-border-radius: 5; -fx-cursor: hand;"));
        sendButton.setOnMouseExited(e -> sendButton.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-background-color: #3498db; -fx-text-fill: white; -fx-border-radius: 5; -fx-cursor: hand;"));
        sendButton.setOnAction(e -> sendMessage());
        
        // Tips button
        tipsButton = new Button("💡 Study Tips");
        tipsButton.setPrefWidth(120);
        tipsButton.setPrefHeight(35);
        tipsButton.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-background-color: #27ae60; -fx-text-fill: white; -fx-border-radius: 5; -fx-cursor: hand;");
        tipsButton.setOnMouseEntered(e -> tipsButton.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-background-color: #229954; -fx-text-fill: white; -fx-border-radius: 5; -fx-cursor: hand;"));
        tipsButton.setOnMouseExited(e -> tipsButton.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-background-color: #27ae60; -fx-text-fill: white; -fx-border-radius: 5; -fx-cursor: hand;"));
        tipsButton.setOnAction(e -> askAboutTips());
        
        // Motivate button
        motivateButton = new Button("⭐ Motivate Me");
        motivateButton.setPrefWidth(120);
        motivateButton.setPrefHeight(35);
        motivateButton.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-background-color: #e74c3c; -fx-text-fill: white; -fx-border-radius: 5; -fx-cursor: hand;");
        motivateButton.setOnMouseEntered(e -> motivateButton.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-background-color: #c0392b; -fx-text-fill: white; -fx-border-radius: 5; -fx-cursor: hand;"));
        motivateButton.setOnMouseExited(e -> motivateButton.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-background-color: #e74c3c; -fx-text-fill: white; -fx-border-radius: 5; -fx-cursor: hand;"));
        motivateButton.setOnAction(e -> getMotivation());
        
        // Clear button
        clearButton = new Button("🗑️ Clear");
        clearButton.setPrefWidth(100);
        clearButton.setPrefHeight(35);
        clearButton.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-background-color: #95a5a6; -fx-text-fill: white; -fx-border-radius: 5; -fx-cursor: hand;");
        clearButton.setOnMouseEntered(e -> clearButton.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-background-color: #7f8c8d; -fx-text-fill: white; -fx-border-radius: 5; -fx-cursor: hand;"));
        clearButton.setOnMouseExited(e -> clearButton.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-background-color: #95a5a6; -fx-text-fill: white; -fx-border-radius: 5; -fx-cursor: hand;"));
        clearButton.setOnAction(e -> clearChat());
        
        buttonBox.getChildren().addAll(sendButton, tipsButton, motivateButton, clearButton);
        
        return buttonBox;
    }
    
    /**
     * Handle sending a message
     */
    private void sendMessage() {
        String input = userInput.getText().trim();
        
        if (input.isEmpty()) {
            statusLabel.setText("Please type a question!");
            return;
        }
        
        // Display user message
        addMessageToChat("You", input);
        
        // Get bot response
        String response = chatbot.getResponse(input);
        
        // Display bot response with slight delay for better UX
        addMessageToChat("Bot", response);
        
        // Clear input
        userInput.clear();
        
        // Update status
        statusLabel.setText("✓ Message sent! Ask another question.");
        
        // Auto-scroll to bottom
        chatDisplay.setScrollTop(Double.MAX_VALUE);
    }
    
    /**
     * Handle study tips button
     */
    private void askAboutTips() {
        userInput.setText("Study tips");
        sendMessage();
    }
    
    /**
     * Handle motivation button
     */
    private void getMotivation() {
        addMessageToChat("Bot", chatbot.getMotivationalMessage());
        statusLabel.setText("💪 Stay motivated!");
        chatDisplay.setScrollTop(Double.MAX_VALUE);
    }
    
    /**
     * Clear chat display
     */
    private void clearChat() {
        chatDisplay.clear();
        statusLabel.setText("Chat cleared!");
        addMessageToChat("Bot", "Chat cleared. Ready for a fresh start! What would you like to learn?");
    }
    
    /**
     * Add a message to the chat display
     */
    private void addMessageToChat(String sender, String message) {
        String formattedMessage;
        
        if (sender.equals("Bot")) {
            formattedMessage = String.format("\n[Bot] 🤖\n%s\n%s\n", message, "─".repeat(80));
        } else {
            formattedMessage = String.format("\n[You] 👤\n%s\n%s\n", message, "─".repeat(80));
        }
        
        chatDisplay.appendText(formattedMessage);
    }
    
    /**
     * Main entry point
     */
    public static void main(String[] args) {
        launch(args);
    }
}
