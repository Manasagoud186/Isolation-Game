# Isolation Game AI Algorithm

## Minimax with Alpha-Beta Pruning

The Isolation Game includes a Player vs AI mode. The AI uses **Minimax with Alpha-Beta Pruning** to evaluate possible moves and select a strategic move.

The current implementation uses a search depth of **5**.

## Minimax

Minimax is a decision-making algorithm used for two-player games.

The AI considers possible future game states and tries to select a move that gives it the best possible outcome while considering the opponent's response.

## Alpha-Beta Pruning

Alpha-Beta Pruning improves the Minimax algorithm by removing branches of the game tree that do not need to be evaluated.

### Alpha

Alpha represents the best score currently available to the maximizing player.

### Beta

Beta represents the best score currently available to the minimizing player.

When:

```text
beta <= alpha
```

the remaining branches can be skipped because they cannot improve the final decision.

## Evaluation Function

The AI evaluates the current game position using:

```text
AI available moves - Opponent available moves
```

The difference in available moves is given greater importance.

The evaluation also considers the position of each player relative to the center of the board.

## Why Alpha-Beta Pruning Is Useful

The Isolation game can produce many possible future moves. Searching every possible branch can become expensive as the board becomes larger.

Alpha-Beta Pruning reduces unnecessary searching and allows the AI to examine promising moves more efficiently.

## AI Process

```text
AI Turn
   ↓
Find Valid Moves
   ↓
Evaluate Possible Moves
   ↓
Apply Minimax Search
   ↓
Apply Alpha-Beta Pruning
   ↓
Evaluate Game States
   ↓
Select Best Move
   ↓
Move AI Piece
```

## Implementation

The AI implementation is located in `game.js`.

The project uses a search depth of 5 and evaluates positions based mainly on player mobility and board positioning.
