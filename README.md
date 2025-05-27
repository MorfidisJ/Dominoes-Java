# Dominoes Game

A Java implementation of the classic Dominoes game, featuring a human player against a computer opponent.

## Game Overview

This is a two-player Dominoes game where one player is human and the other is computer-controlled. The game uses a standard set of 28 domino tiles (double-six set) where each tile has two values ranging from 0 to 6.

## Game Rules

1. Each player starts with 7 tiles drawn from the stock
2. Players take turns placing tiles on the board
3. A tile can be placed if at least one of its values matches the exposed end values of the chain
4. If a player cannot play, they must draw tiles from the stock until they can play or the stock is empty
5. The game ends when:
   - A player empties their hand (they win)
   - Both players cannot play and the stock is empty (player with fewer points in hand wins)
6. Points are awarded to the winner based on the opponent's remaining tiles

## Game Structure

The game is implemented using several key classes:

- `DominoesGame`: Main game class that handles the game loop
- `DominoesRound`: Manages individual rounds
- `Player`: Represents both human and computer players
- `Board`: Manages the game board and tile placement
- `Stock`: Handles the tile stock and drawing
- `Hand`: Manages player's tiles
- `Tile`: Represents individual domino tiles
- `Chain`: Implements the chain of played tiles
- `ChainElement`: Represents elements in the chain

## Game Flow

### Main Game Flow
```mermaid
graph TD
    A[Start Game] --> B[Initialize Players]
    B --> C[Start New Round]
    C --> D[Initialize Stock & Board]
    D --> E[Deal Initial Tiles]
    E --> F[Game Loop]
    F --> G{Player Turn}
    G --> H[Human Turn]
    G --> I[Computer Turn]
    H --> J{Can Play?}
    I --> K{Can Play?}
    J -->|Yes| L[Place Tile]
    J -->|No| M[Draw Tiles]
    K -->|Yes| N[Place Tile]
    K -->|No| O[Draw Tiles]
    L --> P{Game Over?}
    M --> P
    N --> P
    O --> P
    P -->|No| F
    P -->|Yes| Q[End Round]
    Q --> R{Play Again?}
    R -->|Yes| C
    R -->|No| S[End Game]
```

### Tile Placement Logic
```mermaid
graph TD
    A[Select Tile] --> B{Matches Left?}
    B -->|Yes| C{Matches Right?}
    B -->|No| D{Matches Right?}
    C -->|Yes| E[Choose Side]
    C -->|No| F[Place Left]
    D -->|Yes| G[Place Right]
    D -->|No| H[Draw New Tile]
    E --> I[Place on Chosen Side]
    F --> J[Update Board]
    G --> J
    I --> J
    H --> K{Stock Empty?}
    K -->|No| A
    K -->|Yes| L[End Turn]
```

### Computer Player Logic
```mermaid
graph TD
    A[Computer Turn] --> B[Find Best Tile]
    B --> C{Found Match?}
    C -->|Yes| D[Place Best Tile]
    C -->|No| E[Draw Tile]
    D --> F[Update Board]
    E --> G{Stock Empty?}
    G -->|No| B
    G -->|Yes| H[End Turn]
```

## How to Play

1. Run the game using `java DominoesGame`
2. Enter your name when prompted
3. During your turn:
   - View your hand and the current board state
   - Select a tile by its position number (0-6)
   - Choose placement side if the tile matches both ends
   - Enter -1 to draw a new tile if you can't play
4. The computer will automatically play its turn
5. After each round, choose whether to play again

## Scoring System

- Each tile's value is the sum of its two numbers
- When a player wins by emptying their hand, they receive points equal to the opponent's remaining tiles
- In case of a blocked game, the player with fewer points in their hand wins and receives the opponent's points

## Implementation Details

The game uses a doubly-linked list implementation (Chain) to maintain the board state, allowing efficient tile placement at both ends. The computer player uses a simple strategy of always playing the highest-value matching tile available.

## Author

John Morfidis