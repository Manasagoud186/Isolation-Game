# 🏗️ Architecture Documentation

## System Architecture

```
┌─────────────────────────────────────────────────────┐
│         AI Educational Chatbot System               │
└─────────────────────────────────────────────────────┘
                        │
        ┌───────────────┼───────────────┐
        │               │               │
    ┌───▼────┐    ┌────▼──────┐    ┌──▼──────────┐
    │   UI   │    │   Engine  │    │   Utilities │
    │ Layer  │    │   Layer   │    │   Layer     │
    └────────┘    └───────────┘    └─────────────┘
        │               │                   │
        │               │                   │
        │        ┌──────▼──────┐            │
        │        │ Knowledge   │            │
        │        │ Base        │            │
        │        └─────────────┘            │
        │                                   │
    User ◄──────────────────────────────────┘
  Interaction
```

## Layered Architecture

### 1. **Presentation Layer (UI)**
- **Component**: `ChatbotGUI.java`
- **Framework**: JavaFX
- **Responsibilities**:
  - Display chat interface
  - Handle user input
  - Render messages
  - Manage button interactions
  - Visual feedback

### 2. **Business Logic Layer (Engine)**
- **Component**: `ChatbotEngine.java`
- **Responsibilities**:
  - Process user queries
  - Match patterns against knowledge base
  - Generate appropriate responses
  - Manage conversational logic
  - Provide motivational content

### 3. **Data Layer (Knowledge Base)**
- **Components**: Knowledge maps in `ChatbotEngine.java`
- **Data Structure**: `Map<String, String[]>`
- **Contains**:
  - Academic topics
  - Study tips
  - Exam preparation
  - Motivational messages

### 4. **Utility Layer**
- **Components**:
  - `Message.java`: Message model
  - `AppConfig.java`: Configuration constants
  - `Logger.java`: Logging functionality

## Component Diagram

```
┌──────────────────────────────────────────────────────┐
│                    ChatbotGUI                        │
│  (Presentation Layer - JavaFX UI Components)         │
├──────────────────────────────────────────────────────┤
│                                                      │
│  ┌─────────────┐  ┌──────────────┐  ┌────────────┐  │
│  │  Header     │  │  Chat Area   │  │ Input Area │  │
│  │  Section    │  │  (TextArea)  │  │(TextField) │  │
│  └─────────────┘  └──────────────┘  └────────────┘  │
│                                                      │
│  ┌─────────────┐  ┌──────────────┐  ┌────────────┐  │
│  │ Send Button │  │  Tips Button │  │ Motivation │  │
│  │  [📤]       │  │   [💡]       │  │  [⭐]      │  │
│  └─────────────┘  └──────────────┘  └────────────┘  │
│                                                      │
└──────────────────────────────────────────────────────┘
                        │ Calls
                        ▼
┌──────────────────────────────────────────────────────┐
│              ChatbotEngine                           │
│  (Business Logic Layer - AI Processing)              │
├──────────────────────────────────────────────────────┤
│                                                      │
│  ┌──────────────┐  ┌──────────────┐                 │
│  │  Input       │  │  Pattern     │                 │
│  │  Processing  │  │  Matching    │                 │
│  └──────────────┘  └──────────────┘                 │
│         │                  │                        │
│         └──────────┬───────┘                        │
│                    │                                │
│            ┌───────▼──────────┐                     │
│            │  Response        │                     │
│            │  Generation      │                     │
│            └──────────────────┘                     │
│                    │                                │
└────────────────────┼────────────────────────────────┘
                     │ Uses
                     ▼
┌──────────────────────────────────────────────────────┐
│         Knowledge Base & Utilities                   │
├──────────────────────────────────────────────────────┤
│                                                      │
│  ┌──────────────┐  ┌──────────────┐                │
│  │ Knowledge    │  │ Study Tips   │                │
│  │ Base Map     │  │ Map          │                │
│  └──────────────┘  └──────────────┘                │
│                                                      │
│  ┌──────────────┐  ┌──────────────┐                │
│  │ Exam Prep    │  │ Motivational │                │
│  │ Map          │  │ Messages     │                │
│  └──────────────┘  └──────────────┘                │
│                                                      │
└──────────────────────────────────────────────────────┘
```

## Data Flow Diagram

```
User Input
    │
    ▼
┌─────────────────────┐
│  User Types Query   │
│  "What is algebra?" │
└─────────────────────┘
    │
    ▼
┌──────────────────────────────┐
│  TextField receives input    │
│  Send button clicked         │
└──────────────────────────────┘
    │
    ▼
┌──────────────────────────────┐
│  sendMessage() triggered     │
│  Input added to chat display │
└──────────────────────────────┘
    │
    ▼
┌──────────────────────────────┐
│  chatbot.getResponse()       │
│  called with user input      │
└──────────────────────────────┘
    │
    ▼
┌──────────────────────────────────────┐
│  ChatbotEngine:                      │
│  1. Convert to lowercase             │
│  2. Search knowledge base            │
│  3. Find matching key                │
│  4. Select random response           │
└──────────────────────────────────────┘
    │
    ▼
┌──────────────────────────┐
│  Return response string  │
│  "Algebra is a branch.." │
└──────────────────────────┘
    │
    ▼
┌──────────────────────────────┐
│  Display in chat area        │
│  Clear input field           │
│  Update status label         │
└──────────────────────────────┘
    │
    ▼
User sees response
```

## Class Diagram

```
ChatbotGUI (extends Application)
├── chatbot: ChatbotEngine
├── chatDisplay: TextArea
├── userInput: TextField
├── sendButton: Button
├── tipsButton: Button
├── motivateButton: Button
├── clearButton: Button
└── statusLabel: Label

ChatbotEngine
├── knowledgeBase: Map<String, String[]>
├── studyTips: Map<String, String[]>
├── examPreparation: Map<String, String[]>
├── random: Random
├── getResponse(String): String
├── getMotivationalMessage(): String
├── searchAndRespond(String, Map): String
└── initializeKnowledgeBase(): void

Message
├── sender: String
├── content: String
├── timestamp: LocalDateTime
├── type: MessageType
├── getSender(): String
├── getContent(): String
├── getTimestamp(): LocalDateTime
├── getFormattedMessage(): String
└── toString(): String

AppConfig
├── APP_NAME: String (static final)
├── APP_VERSION: String (static final)
├── WINDOW_WIDTH: int (static final)
├── PRIMARY_COLOR: String (static final)
└── [other static constants]

Logger
├── logs: List<String> (static)
├── log(LogLevel, String): void (static)
├── info(String): void (static)
├── warning(String): void (static)
└── [other logging methods]
```

## MVC Design Pattern

```
┌─────────────────────────────────────────────────────┐
│ VIEW: ChatbotGUI                                    │
│ ├─ Displays chat messages                          │
│ ├─ Renders GUI components                          │
│ ├─ Handles user interactions                       │
│ └─ Updates status information                      │
└─────────────────────────────────────────────────────┘
                   │ Uses
                   ▼
┌─────────────────────────────────────────────────────┐
│ CONTROLLER: Interaction Handlers                   │
│ ├─ sendMessage()                                   │
│ ├─ askAboutTips()                                  │
│ ├─ getMotivation()                                 │
│ └─ clearChat()                                     │
└─────────────────────────────────────────────────────┘
                   │ Calls
                   ▼
┌─────────────────────────────────────────────────────┐
│ MODEL: ChatbotEngine                               │
│ ├─ Knowledge Base (Data)                           │
│ ├─ Study Tips (Data)                               │
│ ├─ Exam Preparation (Data)                         │
│ ├─ getResponse() (Logic)                           │
│ └─ Pattern Matching (Logic)                        │
└─────────────────────────────────────────────────────┘
```

## Interaction Flow

### Scenario 1: User Asks Academic Question

```
1. User types: "What is photosynthesis?"
2. GUI: addMessageToChat("You", input)
3. GUI: String response = chatbot.getResponse(input)
4. ChatbotEngine:
   - input.toLowerCase() → "what is photosynthesis?"
   - Search knowledgeBase for matching key
   - Find: "photosynthesis"
   - Get response array
   - Return random response
5. GUI: addMessageToChat("Bot", response)
6. GUI: userInput.clear()
7. GUI: statusLabel.setText("✓ Message sent!")
```

### Scenario 2: User Clicks "Study Tips"

```
1. User clicks tipsButton
2. GUI: tipsButton.setOnAction() triggered
3. GUI: askAboutTips()
4. GUI: userInput.setText("Study tips")
5. GUI: sendMessage() called
6. ChatbotEngine:
   - Process "study tips"
   - Search studyTips Map
   - Find matching tips
   - Return random tip
7. GUI: Display study tip in chat
```

### Scenario 3: User Clicks "Motivate Me"

```
1. User clicks motivateButton
2. GUI: getMotivation()
3. ChatbotEngine: getMotivationalMessage()
4. Return random motivational message
5. GUI: addMessageToChat("Bot", message)
6. GUI: statusLabel.setText("💪 Stay motivated!")
```

## Database Structure (In-Memory)

### Knowledge Base Format
```
Key: String (lowercase topic)
Value: String[] (multiple response variations)

Example:
"photosynthesis" → [
    "Photosynthesis is the process...",
    "Plants convert light into...",
    "The equation is: 6CO₂ + 6H₂O..."
]
```

### Keys Organization
- Lowercase, without punctuation
- Single topic per key
- Related terms point to same entry

## Error Handling Strategy

```
User Input
    │
    ▼
Try: Process Input
    │
    ├─ Success → Generate Response
    │
    └─ Failure (Unknown Topic)
        │
        ▼
    Check Fallback Responses
        │
        ├─ Greeting? → Return greeting
        ├─ Help? → Return help message
        ├─ Study Tips? → Return tips
        └─ Unknown → Return default response
            │
            ▼
        User sees: "I don't have info..."
        and helpful suggestions
```

## Performance Considerations

### Current Implementation
- **Memory**: ~50MB (Java VM overhead)
- **Response Time**: <10ms (HashMap lookup)
- **Data Structure**: HashMap (O(1) lookup)
- **Scalability**: Linear with knowledge base size

### Optimization Opportunities
1. **Caching**: Cache frequent queries
2. **Indexing**: Pre-index keywords
3. **Lazy Loading**: Load knowledge on demand
4. **Async Processing**: Non-blocking responses

## Extension Points

### 1. Add New Topics
```java
knowledgeBase.put("new topic", new String[]{
    "Response 1",
    "Response 2",
    "Response 3"
});
```

### 2. Database Integration
```java
// Replace HashMap with DB queries
String response = queryDatabase(userInput);
```

### 3. ML Integration
```java
// Use ML for better matching
String response = mlModel.predict(userInput);
```

### 4. NLP Implementation
```java
// Use NLP library for understanding
SemanticMeaning meaning = nlpProcessor.analyze(userInput);
```

## Security Considerations

### Current Implementation
- No user authentication required
- No data persistence
- No network connectivity
- Isolated execution

### Future Enhancements
- User profiles (optional)
- Chat history encryption
- API authentication
- Rate limiting

## Testing Strategy

### Unit Tests
```java
@Test
public void testChatbotResponse() {
    ChatbotEngine chatbot = new ChatbotEngine();
    String response = chatbot.getResponse("what is algebra");
    assertNotNull(response);
    assertFalse(response.isEmpty());
}
```

### Integration Tests
```java
@Test
public void testGUIResponse() {
    // Test user interaction end-to-end
}
```

### Functional Tests
- Test knowledge base coverage
- Test error handling
- Test button functionality
- Test UI responsiveness

## Deployment Architecture

```
┌──────────────────────────────────┐
│   Developer Machine              │
│   (Development Environment)      │
└──────────────────────────────────┘
            │
            │ Build: mvn clean install
            │
            ▼
┌──────────────────────────────────┐
│   JAR File                       │
│   (Executable Package)           │
│   educhatbot-uber.jar            │
└──────────────────────────────────┘
            │
            │ Deploy
            │
            ▼
┌──────────────────────────────────┐
│   User Machine                   │
│   (Runtime Environment)          │
│   - Java 11+ installed           │
│   - Run: java -jar *.jar         │
└──────────────────────────────────┘
```

## Future Scalability

```
Current: Single-Tier
┌─────────────────┐
│  GUI            │
│  + Engine       │
│  + Knowledge    │
└─────────────────┘

Future: Multi-Tier
┌──────────────────┐
│  GUI (Client)    │
├──────────────────┤
│  Server (REST)   │
├──────────────────┤
│  AI Engine       │
├──────────────────┤
│  Database        │
└──────────────────┘
```

---

This architecture is designed to be:
- ✅ Modular and extensible
- ✅ Easy to maintain
- ✅ Scalable for future enhancements
- ✅ Performance-optimized
- ✅ User-friendly
