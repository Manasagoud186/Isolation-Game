# 🚀 Quick Start Guide

## Installation & Running in 3 Easy Steps

### Step 1: Prerequisites Check
Make sure you have installed:
- **Java**: Version 11 or higher
  ```bash
  java -version
  ```
- **Maven**: Version 3.6.0 or higher
  ```bash
  mvn -version
  ```

### Step 2: Build the Project
Navigate to the project directory and run:
```bash
mvn clean install
```

### Step 3: Run the Application
Choose one of the following methods:

#### Method 1: Maven (Recommended)
```bash
mvn javafx:run
```

#### Method 2: Java Direct Execution
```bash
java -cp target/classes;target/dependency/* com.educhatbot.ui.ChatbotGUI
```

#### Method 3: Executable JAR
```bash
mvn package
java -jar target/educhatbot-uber.jar
```

## 🎮 Using the Chatbot

### Basic Usage
1. **Launch** the application (see above)
2. **Type** your academic question in the input field
3. **Press** Enter or click "📤 Send" button
4. **Receive** instant response from the bot

### Example Questions
```
"What is algebra?"
"How to study effectively?"
"Tell me about photosynthesis"
"Exam preparation tips"
"Study tips for mathematics"
```

### Quick Buttons
| Button | Use |
|--------|-----|
| 📤 Send | Submit question |
| 💡 Study Tips | Get study advice |
| ⭐ Motivate Me | Get motivation |
| 🗑️ Clear | Clear chat |

## 📚 Topics Covered

### Sciences
- Physics
- Chemistry
- Biology

### Mathematics
- Algebra
- Geometry
- Calculus

### Humanities
- History
- Geography
- Literature

### Study Skills
- How to study
- Exam preparation
- Memory techniques

## ❓ Troubleshooting

**Q: Application won't start**
A: Ensure Java 11+ and Maven 3.6+ are installed and in PATH

**Q: "JavaFX not found" error**
A: Use Maven command `mvn javafx:run` instead

**Q: Chatbot doesn't understand my question**
A: Try asking with different keywords or use the quick buttons

## 📝 Example Session

```
You: hello

Bot: Hello! I'm your educational chatbot. How can I help you 
learn today?

---

You: What is algebra?

Bot: Algebra is a branch of mathematics that uses letters and 
symbols to represent unknown numbers. It helps solve equations 
and understand relationships between quantities. Basic algebra 
topics include: variables, equations, expressions, and functions.

---

You: Study tips

Bot: 🎯 Tip 1: Create a dedicated study space free from distractions.
📚 Tip 2: Use active recall - test yourself instead of just re-reading.
⏰ Tip 3: Study in 25-30 minute blocks with short breaks (Pomodoro technique).
[... and more tips ...]
```

## 🔧 Next Steps

1. **Explore** different topics by asking questions
2. **Use** quick buttons for common requests
3. **Check** study tips regularly for learning strategies
4. **Get motivated** with daily messages

## 📖 Full Documentation

For detailed information, see [README.md](README.md)

---

**Happy Learning!** 🎓✨
