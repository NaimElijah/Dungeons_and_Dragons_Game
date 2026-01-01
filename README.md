# Dungeons & Dragons – Console Game (Refactored)

A turn-based, console-based Dungeons & Dragons–style game written in Java.  
The project was originally implemented earlier when I was younger and has since been **refactored to follow clean software engineering principles**, including better separation of concerns, extensibility, and maintainability — while preserving all original gameplay behavior.

---

## 🎮 Game Overview

- The player selects a hero and progresses through multiple dungeon levels
- Each level is loaded from a text file
- The board consists of walls, empty tiles, traps, enemies, and the player
- Turn-based mechanics:
  - Player moves or uses abilities
  - Enemies act automatically
- Combat, abilities, cooldowns, health, and mana are fully implemented

---

## 🧱 Project Structure

```
DandDGame-REMASTERED/
├── src/
│   ├── Business_Layer/
│   │   ├── factories/
│   │   │   ├── TileFactory.java
│   │   │   ├── DefaultTileFactory.java
│   │   │   └── DefaultPlayerFactory.java
│   │   ├── Game_Board.java
│   │   ├── Player.java
│   │   ├── Enemy.java
│   │   ├── Tile.java
│   │   ├── Coordinate.java
│   │   ├── Health.java
│   │   ├── Mana.java
│   │   └── (other business/domain classes)
│   │
│   └── Presentation_Layer/
│       ├── CLI.java
│       └── InputReader.java
│
├── data/
│   └── levels_dir/
│       ├── level1.txt
│       ├── level2.txt
│       ├── level3.txt
│       └── level4.txt
│
└── README.md
````

---

## 🧠 Design & Refactoring Highlights

### 1️⃣ Factory Pattern (Major Refactor)

#### Tile Creation
Tile creation was refactored into a **Factory Pattern**:

- `TileFactory` (interface)
- `DefaultTileFactory` (implementation)

This removes large conditional blocks and allows new tile types to be added without modifying input logic.

```java
Tile tile = tileFactory.create(symbol, x, y);
````

#### Player Creation

Player selection logic was centralized into `DefaultPlayerFactory`, removing past duplicated `if/else` logic and improving extensibility.

---

### 2️⃣ Separation of Concerns

| Layer              | Responsibility                |
| ------------------ | ----------------------------- |
| Presentation Layer | User input, output, game flow |
| Business Layer     | Game logic, rules, entities   |
| Factories          | Object creation               |
| Data               | Levels and assets             |

---

### 3️⃣ Portability Improvements

* Removed hardcoded absolute paths
* All level files are loaded via **relative paths**
* The project now runs on any OS (Windows / Linux / macOS)

---

## ▶️ How to Run

### Requirements

* Java 8+
* IntelliJ IDEA (recommended)

### Run Instructions

1. Open the project in IntelliJ
2. Ensure `src` is marked as *Sources Root*
3. Ensure `Tests` is marked as *Test Sources Root*
4. Run:

   ```
   Presentation_Layer.CLI
   ```

---

## 🧩 Level Files

Levels are stored as text files under:

```
data/levels_dir/
```

Each character represents a tile type (wall, enemy, trap, etc.).
Levels are loaded dynamically at runtime.

---

## 🚀 Future Improvements

Possible extensions:

* GUI (JavaFX / Swing)
* Multiple players
* Save / Load system
* Smarter enemy AI
* Maven / Gradle build
* Continuous Integration (CI)

---

This project demonstrates:

* Object-Oriented Design
* Design Patterns (Factory)
* Clean Architecture
* Maintainable Java codebase

---

## **📜 License**

This project is for portfolio and learning purposes.
