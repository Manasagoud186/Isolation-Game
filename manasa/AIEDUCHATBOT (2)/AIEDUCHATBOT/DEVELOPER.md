# 👨‍💻 Developer Guide

## Contributing to AI Educational Chatbot

This guide provides instructions for developers who want to contribute, enhance, or maintain the chatbot.

## Table of Contents
1. [Development Setup](#development-setup)
2. [Project Structure](#project-structure)
3. [Adding Features](#adding-features)
4. [Code Standards](#code-standards)
5. [Testing](#testing)
6. [Building & Packaging](#building--packaging)

## Development Setup

### Prerequisites
- Java Development Kit (JDK) 11+
- Maven 3.6.0+
- Git (optional, for version control)
- IDE: IntelliJ IDEA or Eclipse recommended

### IDE Setup

#### IntelliJ IDEA
1. Open project: `File > Open > Select project folder`
2. Configure JDK: `File > Project Structure > Project > SDK`
3. Select JDK 11+
4. Maven should auto-configure

#### Eclipse
1. Import project: `File > Import > Maven > Existing Maven Projects`
2. Select project folder
3. Finish
4. Configure JDK if needed

#### VS Code
1. Install extensions:
   - Extension Pack for Java
   - JavaFX Support
2. Open project folder
3. Let VS Code configure automatically

### Initial Build
```bash
mvn clean install -DskipTests
```

## Project Structure

```
AIEDUCHATBOT/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/educhatbot/
│   │   │       ├── Application.java           (Main entry point)
│   │   │       ├── ai/
│   │   │       │   └── ChatbotEngine.java     (AI logic)
│   │   │       ├── ui/
│   │   │       │   └── ChatbotGUI.java        (GUI)
│   │   │       └── utils/
│   │   │           ├── Message.java
│   │   │           ├── AppConfig.java
│   │   │           └── Logger.java
│   │   └── resources/
│   │       └── styles.css                     (Styling)
│   └── test/
│       └── java/
│           └── com/educhatbot/
│               └── (test classes)
├── pom.xml
├── README.md
├── ARCHITECTURE.md
└── DEVELOPER.md
```

## Adding Features

### Feature 1: Add New Knowledge Base Topic

#### Step 1: Open ChatbotEngine.java
```java
// In initializeKnowledgeBase() method
knowledgeBase.put("your topic key", new String[]{
    "Response option 1",
    "Response option 2",
    "Response option 3"
});
```

#### Step 2: Example
```java
// Add programming topic
knowledgeBase.put("what is java", new String[]{
    "Java is a versatile, object-oriented programming language...",
    "Java is platform-independent and runs on the Java Virtual Machine...",
    "Java is widely used for web applications, mobile apps, and enterprise systems..."
});
```

#### Step 3: Test
```bash
# Run and ask: "What is Java?"
mvn javafx:run
```

### Feature 2: Add New Study Tips

#### Step 1: Open ChatbotEngine.java
```java
// In initializeStudyTips() method
studyTips.put("time management", new String[]{
    "🕐 Tip 1: Use time-blocking to schedule study sessions...",
    "🕐 Tip 2: Prioritize important tasks first...",
    "🕐 Tip 3: Take regular breaks to maintain focus..."
});
```

#### Step 2: Test
Ask the chatbot: "Time management tips"

### Feature 3: Add New GUI Button

#### Step 1: Add button in createButtonArea()
```java
// Add new button
Button newButton = new Button("🎯 New Feature");
newButton.setPrefWidth(120);
newButton.setPrefHeight(35);
newButton.setStyle("-fx-font-size: 12px; ...");
newButton.setOnAction(e -> handleNewFeature());
buttonBox.getChildren().add(newButton);
```

#### Step 2: Implement handler method
```java
private void handleNewFeature() {
    String input = "new feature request";
    addMessageToChat("You", input);
    String response = chatbot.getResponse(input);
    addMessageToChat("Bot", response);
}
```

#### Step 3: Test
```bash
mvn javafx:run
```

### Feature 4: Add Logging

#### Usage
```java
// Import
import com.educhatbot.utils.Logger;

// Log messages
Logger.info("Application started");
Logger.warning("Warning message");
Logger.error("Error occurred");
Logger.success("Operation successful");
Logger.debug("Debug information");

// Get logs
List<String> allLogs = Logger.getLogs();
String logContent = Logger.getLogsAsString();
```

### Feature 5: Database Integration

#### Current: In-Memory
```java
Map<String, String[]> knowledgeBase = new HashMap<>();
```

#### Future: Database
```java
// Replace with database queries
public String getResponse(String input) {
    String response = database.query(input);
    if (response != null) {
        return response;
    }
    return getDefaultResponse(input);
}
```

## Code Standards

### Naming Conventions
```java
// Classes: PascalCase
public class ChatbotEngine { }

// Methods: camelCase
public void sendMessage() { }

// Variables: camelCase
String userInput;
int messageCount;

// Constants: UPPER_SNAKE_CASE
public static final int WINDOW_WIDTH = 900;
public static final String APP_NAME = "AI Educational Chatbot";
```

### Documentation Standards
```java
/**
 * Comprehensive method documentation
 * 
 * @param input User input string
 * @return Response string from chatbot
 */
public String getResponse(String input) {
    // Implementation
}
```

### Code Style
```java
// Always use meaningful names
Good: if (isUserLoggedIn && hasValidCredentials) { }
Bad:  if (a && b) { }

// Keep methods focused
Good: Method does one thing well
Bad:  Method does 10 different things

// Error handling
try {
    // Code
} catch (SpecificException e) {
    Logger.error("Specific error: " + e.getMessage());
} catch (Exception e) {
    Logger.error("General error: " + e.getMessage());
}
```

## Testing

### Unit Testing Example

#### Create Test File
```
src/test/java/com/educhatbot/ai/ChatbotEngineTest.java
```

#### Test Code
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChatbotEngineTest {
    
    private ChatbotEngine chatbot;
    
    @BeforeEach
    public void setUp() {
        chatbot = new ChatbotEngine();
    }
    
    @Test
    public void testGreetingResponse() {
        String response = chatbot.getResponse("hello");
        assertNotNull(response);
        assertFalse(response.isEmpty());
    }
    
    @Test
    public void testAlgebraResponse() {
        String response = chatbot.getResponse("what is algebra");
        assertTrue(response.contains("algebra"));
    }
    
    @Test
    public void testNullInput() {
        String response = chatbot.getResponse(null);
        assertNotNull(response);
    }
    
    @Test
    public void testEmptyInput() {
        String response = chatbot.getResponse("");
        assertNotNull(response);
    }
}
```

### Run Tests
```bash
# Run all tests
mvn test

# Run specific test
mvn test -Dtest=ChatbotEngineTest

# Run with coverage
mvn test jacoco:report
```

## Building & Packaging

### Development Build
```bash
mvn clean install
```

### Production Build
```bash
mvn clean package -DskipTests
```

### Create Executable JAR
```bash
mvn clean package
# Result: target/educhatbot-uber.jar
```

### Create Distribution
```bash
# With assembly
mvn assembly:single

# With shade
mvn shade:shade

# Result: target/educhatbot-uber.jar
```

## Debugging

### Enable Debug Mode
```bash
mvn -X javafx:run
```

### Debug in IDE

#### IntelliJ IDEA
1. Set breakpoints (click line number)
2. Run > Debug 'ChatbotGUI'
3. Use Debug panel to step through code

#### Eclipse
1. Set breakpoints
2. Debug > Debug As > Java Application
3. Use Debug perspective

### Print Debugging
```java
// Add debug output
Logger.debug("Variable value: " + variable);
System.out.println("Debug: " + value);
```

## Performance Optimization

### Current Performance
- Response time: <10ms
- Memory: ~50MB

### Optimization Tips

#### 1. Caching
```java
private Map<String, String> responseCache = new HashMap<>();

public String getResponse(String input) {
    if (responseCache.containsKey(input)) {
        return responseCache.get(input);
    }
    String response = generateResponse(input);
    responseCache.put(input, response);
    return response;
}
```

#### 2. Lazy Loading
```java
private Map<String, String[]> knowledgeBase;
private boolean loaded = false;

public void loadKnowledgeBase() {
    if (!loaded) {
        initializeKnowledgeBase();
        loaded = true;
    }
}
```

#### 3. Async Processing
```java
// Use ExecutorService for async tasks
ExecutorService executor = Executors.newFixedThreadPool(2);
executor.submit(() -> {
    String response = chatbot.getResponse(input);
    Platform.runLater(() -> displayResponse(response));
});
```

## Refactoring Guidelines

### Separate Concerns
```java
// Bad: Mixed responsibilities
public class Chatbot {
    public void processInput() { }
    public void displayOutput() { }
    public void saveToDatabase() { }
}

// Good: Separated concerns
public class ChatbotEngine {
    public String getResponse(String input) { }
}

public class ChatbotGUI {
    public void displayMessage(String message) { }
}

public class DatabaseManager {
    public void save(String data) { }
}
```

### Extract Methods
```java
// Before: Long method
public void sendMessage() {
    String input = userInput.getText();
    if (input.isEmpty()) return;
    addMessageToChat("You", input);
    String response = chatbot.getResponse(input);
    addMessageToChat("Bot", response);
    userInput.clear();
}

// After: Extracted methods
public void sendMessage() {
    String input = getAndValidateInput();
    if (input.isEmpty()) return;
    processUserMessage(input);
}

private String getAndValidateInput() {
    return userInput.getText().trim();
}

private void processUserMessage(String input) {
    addMessageToChat("You", input);
    String response = chatbot.getResponse(input);
    addMessageToChat("Bot", response);
    userInput.clear();
}
```

## Git Workflow

### Create Feature Branch
```bash
git checkout -b feature/add-new-topic
```

### Make Changes
```bash
# Edit files
git add .
git commit -m "Add new knowledge base topic"
```

### Push Changes
```bash
git push origin feature/add-new-topic
```

### Create Pull Request
- On GitHub: Click "New Pull Request"
- Select your branch
- Add description
- Request review

## Common Development Tasks

### Add New Subject Area
1. Edit `ChatbotEngine.java`
2. Add entries to `knowledgeBase.put()`
3. Add study tips if relevant
4. Test with sample questions
5. Update documentation

### Modify UI Layout
1. Edit `ChatbotGUI.java`
2. Modify layout methods (createHeader, etc.)
3. Update `styles.css` if needed
4. Test responsive design
5. Check on different screen sizes

### Add Configuration Option
1. Edit `AppConfig.java`
2. Add public static final constant
3. Use in relevant classes
4. Document in README

### Performance Improvement
1. Profile with JProfiler or YourKit
2. Identify bottlenecks
3. Optimize (caching, async, etc.)
4. Test performance improvement
5. Document changes

## Troubleshooting Development

### Build Fails
```bash
# Clean and rebuild
mvn clean install

# Check dependencies
mvn dependency:resolve

# Update Maven
mvn clean -U install
```

### Tests Fail
```bash
# Run single test
mvn test -Dtest=TestClassName

# Run with debugging
mvn test -X

# Skip tests temporarily
mvn install -DskipTests
```

### Application Won't Start
```bash
# Check Java version
java -version

# Check Maven config
mvn --version

# Run with debug
mvn -X javafx:run
```

## Version Management

### Update Version
```xml
<!-- In pom.xml -->
<version>1.0.1</version>
```

### Update in Code
```java
// In AppConfig.java
public static final String APP_VERSION = "1.0.1";
```

## Release Checklist

- [ ] All tests passing
- [ ] Code review completed
- [ ] Documentation updated
- [ ] Version bumped
- [ ] Build verified
- [ ] JAR tested
- [ ] README updated
- [ ] CHANGELOG updated

## Further Resources

### Learning Resources
- [JavaFX Documentation](https://openjfx.io/)
- [Maven Guide](https://maven.apache.org/guides/)
- [Java Design Patterns](https://www.digitalocean.com/community/tutorials/java-design-patterns)

### Tools
- IntelliJ IDEA - IDE
- Maven - Build tool
- JUnit - Testing framework
- Git - Version control

---

Happy coding! 🚀✨

For questions, refer to:
- [README.md](README.md) - Overview
- [ARCHITECTURE.md](ARCHITECTURE.md) - System design
- [INSTALLATION.md](INSTALLATION.md) - Setup guide
