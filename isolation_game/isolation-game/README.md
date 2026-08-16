# Isolation Game

A strategic two-player board game where you outmaneuver your opponent by cutting off their moves — until they have nowhere left to go.

## How to Play

Open `index.html` in any browser. No installation or server needed.

## Game Rules

1. Each player has one piece on the board, starting at opposite corners.
2. On your turn, move your piece **one square** — up, down, left, or right. No diagonals.
3. The tile you just left is **permanently removed** from the board.
4. The player who **cannot move** loses the round.
5. Win the **majority of rounds** to win the match.

## Features

- **Player vs Player** — two players on the same device
- **Player vs AI** — the AI minimizes your available moves each turn
- **Growing board** — each round the grid gets bigger (Round 1: 5×5, Round 2: 7×7, Round 3: 9×9 ...)
- **Shrinking timer** — each round you get less time per turn (30s → 25s → 20s → 15s → 10s min)
- **Smooth animations** — pieces slide across the board, removed tiles flash, danger warning when nearly trapped
- **Streak tracking** — consecutive round wins shown in results
- **Color selection** — each player picks their own color before the game

## Files

```
isolation-game/
├── index.html   — game UI and screens
├── game.js      — all game logic, AI, animations
├── style.css    — styling and animations
└── README.md    — this file
```

## AI — How It Works

The AI uses a **greedy mobility heuristic**: on each turn it picks the move that leaves the opponent with the fewest available moves. This is a one-step lookahead — fast and effective for smaller boards.

### Alpha-Beta Pruning

Alpha-beta pruning is the standard algorithm used to make game-tree search practical for two-player zero-sum games like Isolation.

**How it works:**

A minimax search explores every possible sequence of moves to a given depth, then picks the move with the best outcome assuming both players play optimally. The problem is the tree grows exponentially — on a 7×7 board with 4-directional movement, the branching factor makes deep search very slow.

Alpha-beta pruning cuts branches that can never affect the final decision:

- **Alpha** — the best score the maximizing player (AI) is guaranteed so far
- **Beta** — the best score the minimizing player (opponent) is guaranteed so far
- If at any node `beta ≤ alpha`, the rest of that branch is pruned — it won't change the outcome

**Example:**
```
Maximizer looking at move A (score 6) then move B:
  Move B's subtree shows the minimizer can force score 4
  Since 4 < 6 (alpha), the maximizer will never pick B
  → prune the rest of B's subtree entirely
```

**Why it matters for Isolation:**

Isolation is a perfect fit for alpha-beta because:
- It's a two-player, zero-sum, perfect-information game
- The game tree terminates (board tiles only get removed, never added)
- The evaluation function is simple: `my_moves - opponent_moves`

With alpha-beta pruning at depth 4–6, the AI can look several moves ahead and play near-optimally, especially on later rounds where the board is more constrained and the branching factor drops quickly.

The current implementation uses depth-1 greedy search. Upgrading to full alpha-beta would make the AI significantly stronger on larger boards.

- Corner and edge positions are dangerous — you run out of moves faster there.
- Try to stay in open space while pushing your opponent toward removed tiles.
- Watch the danger glow — if your piece pulses red, you only have 1–2 moves left.
- On later rounds the board is bigger but the timer is shorter, so plan fast.

## Tips

- Corner and edge positions are dangerous — you run out of moves faster there.
- Try to stay in open space while pushing your opponent toward removed tiles.
- Watch the danger glow — if your piece pulses red, you only have 1–2 moves left.
- On later rounds the board is bigger but the timer is shorter, so plan fast.
