# 🎮 Isolation Game

> **Outmaneuver. Outlast. Isolate.**

Isolation Game is a strategic browser-based board game where players compete by moving their pieces and permanently removing the tiles they leave behind. The goal is to restrict the opponent's movement until they have no valid moves remaining.

The game supports both **Player vs Player** and **Player vs AI** modes and includes multiple rounds, dynamic board sizes, turn timers, animations, color customization, and an AI opponent powered by **Minimax with Alpha-Beta Pruning**.

---

## 📌 Project Overview

Isolation is a two-player strategy game based on movement, positioning, and blocking.

Each player starts from an opposite corner of the board. During a turn, a player moves one square horizontally or vertically. The tile they leave becomes permanently unavailable.

As more tiles are removed, the available space becomes smaller. The player who cannot make a valid move loses the round.

The match is decided by the player who wins the majority of the rounds.

---

## ✨ Features

### 👥 Player vs Player

Two players can play against each other on the same device by taking turns.

### 🤖 Player vs AI

Play against a computer-controlled opponent. The AI analyzes possible moves using a game-tree search algorithm.

### 🧠 Minimax with Alpha-Beta Pruning

The AI uses Minimax with Alpha-Beta Pruning to search possible future game states and select a strategically strong move.

The current implementation uses a search depth of **5**.

### 📈 Growing Board

The board size increases as the rounds progress:

```text
Round 1 → 5 × 5
Round 2 → 7 × 7
Round 3 → 9 × 9
Round 4 → 11 × 11
...
```

### ⏱️ Dynamic Turn Timer

The available time decreases as the rounds progress:

```text
Round 1 → 30 seconds
Round 2 → 25 seconds
Round 3 → 20 seconds
Round 4 → 15 seconds
Round 5+ → 10 seconds minimum
```

### 🎨 Player Color Selection

Players can select different colors before starting the game.

### 🎬 Smooth Animations

The game includes:

* Piece movement animations
* Removed-tile effects
* Turn transitions
* Winning animations
* Danger warnings
* Active-player indicators

### 🔥 Streak Tracking

Consecutive round victories are tracked and displayed in the results screen.

### 📱 Responsive Interface

The interface adapts to smaller screens using responsive CSS.

---

## 🎯 Game Rules

1. Each player starts with one piece.
2. Players start from opposite corners of the board.
3. A player can move **one square** per turn.
4. Movement is allowed:

   * Up
   * Down
   * Left
   * Right
5. Diagonal movement is not allowed.
6. The tile that the player leaves is permanently removed.
7. Removed tiles cannot be used again.
8. If a player has no valid moves, they lose the round.
9. The player who wins the majority of rounds wins the match.
10. If a player's timer reaches zero, they lose the round.

---

## 🕹️ How to Play

### Step 1 — Start the Game

Open `index.html` in a web browser.

### Step 2 — Select Game Mode

Choose one of:

* **Player vs Player**
* **Player vs AI**

### Step 3 — Configure the Game

Choose:

* Number of rounds
* Player colors

The number of rounds is restricted to odd numbers so that a match can have a clear majority winner.

### Step 4 — Make Your Move

Select your piece.

The game will highlight the valid cells where you can move.

Click one of the highlighted cells to move.

### Step 5 — Isolate Your Opponent

Use your movement strategically to remove important tiles and reduce the opponent's available moves.

---

## 🧠 AI Algorithm

The AI uses **Minimax with Alpha-Beta Pruning**.

### Minimax

Minimax is a decision-making algorithm commonly used in two-player strategy games.

The AI considers possible future moves and tries to choose the move that produces the best outcome for itself while assuming that the opponent will also make good decisions.

### Alpha-Beta Pruning

Alpha-Beta Pruning improves Minimax by eliminating branches of the game tree that cannot influence the final decision.

Two values are maintained:

* **Alpha** — the best score currently guaranteed for the maximizing player.
* **Beta** — the best score currently guaranteed for the minimizing player.

When:

```text
Beta ≤ Alpha
```

the remaining branches can be skipped because they cannot improve the decision.

### Evaluation Function

The AI evaluates a position using:

```text
AI available moves - Opponent available moves
```

The number of available moves is given greater importance, while board-center positioning is also considered.

This encourages the AI to maintain mobility while restricting the opponent.

---

## 🏗️ Project Structure

```text
Isolation-Game/
│
├── index.html
├── game.js
├── style.css
└── README.md
```

### `index.html`

Contains the structure of the game interface, including:

* Main menu
* Instructions
* Game mode selection
* Game setup
* Game board
* Player information
* Timers
* Results screen

### `game.js`

Contains the main game functionality:

* Game state management
* Board creation
* Player movement
* Valid move calculation
* Round management
* Timer management
* Score tracking
* Animations
* AI decision-making
* Minimax
* Alpha-Beta Pruning

### `style.css`

Contains:

* Game layout
* Dark theme
* Buttons
* Player cards
* Board styling
* Animations
* Responsive design
* Winning and danger effects

### `README.md`

Contains the project documentation, game rules, features, AI explanation, and instructions.

---

## 🛠️ Technologies Used

| Technology         | Purpose                                           |
| ------------------ | ------------------------------------------------- |
| HTML5              | Game structure and interface                      |
| CSS3               | Styling, layout, animations and responsive design |
| JavaScript         | Game logic and interaction                        |
| Minimax            | AI decision-making                                |
| Alpha-Beta Pruning | Optimization of AI game-tree search               |
| Git                | Version control                                   |
| GitHub             | Source code hosting                               |

---

## ▶️ How to Run

No installation or backend server is required.

### Option 1 — Open Directly

1. Download or clone the repository.
2. Open the project folder.
3. Double-click:

```text
index.html
```

4. The game will open in your browser.

### Option 2 — Using VS Code

Open the project folder in Visual Studio Code and open `index.html` using a browser.

---

## 🎮 Game Flow

```text
Start Game
     ↓
Select Game Mode
     ↓
Configure Rounds & Colors
     ↓
Start Round
     ↓
Player Selects Piece
     ↓
Valid Moves Highlighted
     ↓
Player Moves
     ↓
Previous Tile Removed
     ↓
Opponent's Turn
     ↓
Check Available Moves
     ↓
 ┌───────────────┐
 │ Moves Available│
 └───────┬───────┘
         ↓
    Continue Game
         │
         ↓
 ┌────────────────┐
 │ No Moves / Time│
 │     Expired    │
 └───────┬────────┘
         ↓
      Round Ends
         ↓
  More Rounds?
    ↙        ↘
  Yes         No
   ↓           ↓
Next Round   Final Result
```

---

## 🔍 Key Technical Concepts

This project demonstrates practical implementation of:

* Two-player game logic
* Game-state management
* Board representation using arrays
* Valid-move generation
* Turn-based programming
* Timers
* Event handling
* DOM manipulation
* CSS animations
* Responsive web design
* Artificial intelligence
* Minimax search
* Alpha-Beta Pruning
* Heuristic evaluation
* Game-tree optimization

---

## 🚀 Future Enhancements

Possible future improvements include:

* Online multiplayer
* Multiplayer using WebSockets
* Difficulty levels for AI
* AI move explanation
* Player name customization
* Sound effects and background music
* Leaderboard system
* Game history storage
* Undo/replay functionality
* Dark/light theme selection
* Mobile touch optimization
* Improved AI with deeper search
* Persistent player statistics

---

## 📸 Screenshots

Add screenshots of the following sections here:
<img width="1919" height="905" alt="image" src="https://github.com/user-attachments/assets/f0c853c3-97be-42f5-b196-c53aaa847da5" />


```text
Main Menu
Game Mode Selection
Game Setup
Gameplay
AI Gameplay
Final Results
```

Example:

```markdown
![Main Menu](screenshots/main-menu.png)
```

Create a `screenshots` folder when you add the images:

```text
Isolation-Game/
│
├── screenshots/
│   ├── main-menu.png
│   ├── game-setup.png
│   ├── gameplay.png
│   └── results.png
│
├── index.html
├── game.js
├── style.css
└── README.md
```

---

## 📚 Learning Outcomes

Through this project, the following concepts are demonstrated:

* Understanding game development using JavaScript
* Applying data structures to represent a game board
* Implementing turn-based game mechanics
* Understanding search algorithms
* Implementing Minimax and Alpha-Beta Pruning
* Designing heuristic evaluation functions
* Managing game states
* Creating interactive browser interfaces
* Using Git and GitHub for version control

---

## 👩‍💻 Project Information

**Project:** Isolation Game
**Type:** Browser-Based Strategy Game
**Platform:** Web Browser
**Languages:** HTML, CSS, JavaScript
**AI:** Minimax with Alpha-Beta Pruning
**Version:** 2.0

---

## ⭐ Conclusion

Isolation Game combines **web development, game logic, and artificial intelligence** into an interactive strategy game.

The project demonstrates how JavaScript can be used to create a complete browser-based game while applying algorithmic concepts such as **Minimax, Alpha-Beta Pruning, heuristic evaluation, and game-state search**.

The main challenge is not simply making legal moves, but controlling the available space and strategically isolating the opponent before they isolate you.

---

## 📄 License

This project is developed for educational and academic purposes.
