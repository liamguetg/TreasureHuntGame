# Treasure Hunt (2D Java Swing)

A top-down, tile-based adventure game built in Java using Swing/AWT. The project includes a tile map loader, collision detection, animated player/NPC movement, an inventory system with item stacking, and a simple NPC-driven shop/trade loop.

<img width="766" height="566" alt="TreasureHunt_screenshot" src="https://github.com/user-attachments/assets/64abe708-7d43-4ff2-bdcd-95ba985e790b" />

## What it does

- Renders a scrolling 2D world using a tile map loaded from text files.
- Lets the player move around with WASD and updates a sprite-based animation frame.
- Detects collisions with:
  - Tile obstacles (walls/water/trees, etc.)
  - World objects (keys, doors, boots, chests, gear)
  - NPCs (interaction triggers dialogue/trade UI)
- Allows the player to pick up items and manage them in an inventory:
  - Items stack by logical item identity (tracked via item `name`)
  - Items can be removed/consumed during gameplay (e.g., opening doors, trading)
- Implements an NPC shop/trading flow driven by game UI state:
  - Dialogue -> Buy (master key)
  - Dialogue -> Trade (sell items for coins)
- Ends when the player reaches the win tile (a specific world grid location).

## Technology & tools used

- Java (core language)
- Swing/AWT:
  - `JFrame` + `JPanel`
  - `Graphics2D` rendering loop
  - `KeyListener` input
  - `BufferedImage` + `ImageIO` for sprites/tiles
- Game/data structures:
  - Tile map grid (2D int array)
  - Hitbox collision using `Rectangle.intersects`
  - `HashMap` for inventory stacking and counts
- Testing:
  - JUnit 5 test suite under `src/test`

## Frontend vs backend (code organization)

### Frontend (UI + input)

- `src/main/ui/Main.java`
  - Creates the main window and starts the game.
- `src/main/ui/GamePanel.java`
  - Rendering surface (`paintComponent`)
  - Game loop (`run()` -> `update()` + `repaint()`)
  - Holds references to the game systems (tile manager, collision, UI, etc.)
- `src/main/ui/KeyHandler.java`
  - Input controller
  - Routes keys based on `GamePanel.gameState`
  - Handles menu confirmations and menu navigation
- `src/main/ui/UI.java`
  - Draws all menu screens (pause, dialogue, buy, trade, inventory, confirmations)

### Backend (game model)

- `src/main/model/TileManager.java`
  - Loads maps from text (`/Maps/*.txt`)
  - Loads/scales tile images (`/BackgroundTiles/*.png`)
  - Draws the portion of the world near the player (simple culling)
- `src/main/model/Entities.java`, `src/main/model/Player.java`, `src/main/model/NpcOldMan.java`
  - Shared entity logic (world position, direction, sprite animation)
  - Player movement + pickup + win check integration
  - NPC movement via a timed random direction change
- `src/main/model/CollisionCheck.java`
  - Tile collisions (grid sampling)
  - Object/NPC collisions (AABB hitbox intersection)
  - Win condition check (`checkWon`)
- `src/main/model/Inventory.java`
  - Item collection with stacking behavior
  - Adding/removing items and tracking quantities
- `src/main/model/Store.java`
  - Shop/trade rules (buy master key with coins; sell items for coins)
- `src/main/model/objectPlacer.java`
  - Places fixed objects (door)
  - Spawns NPC(s)
  - Randomly places walkable items on valid tile types

## How it works (walkthrough)

1. Startup
   - `ui.Main` creates a `GamePanel`, adds it to a `JFrame`, and calls:
     - `gamePanel.setUpGame()` (initial object/NPC spawning)
     - `gamePanel.startGameThread()` (starts the update/render loop)
2. Update loop
   - `GamePanel.run()` targets ~60 FPS and calls `update()` + `repaint()`.
   - In `playState`, the player and NPC(s) update:
     - movement
     - collision checks
     - interactions (pickups and NPC dialogue)
3. Rendering
   - `TileManager.draw()` renders visible tiles near the player.
   - The player, world objects, random items, and NPCs are drawn using:
     - world-to-screen offset: `entityWorld - playerWorld + playerScreenCenter`
4. Interactions and state machine
   - `KeyHandler` reads key presses and changes `GamePanel.gameState`.
   - `UI` draws the corresponding menu screen for the current state.
   - Example flow: walking into the NPC triggers `dialogueState`, and dialogue keys move into `buyState` / `tradeState`.

## Gameplay controls

- Move: `W` `A` `S` `D`
- Open inventory: `I`
- Pause menu: `P`
- Dialogue choices (when in the NPC dialogue screen):
  - Buy: `B`
  - Trade: `T`
- Trade selling (when in trade screen, if you own the item):
  - Key: `K`
  - Boots: `B`
  - Sword: `S`
  - Shield: `H`
  - Chest: `C`
- Confirm / cancel in menus: `Y` / `N`
- Exit any menu: `Q` (returns to `playState`)

## Resources/Assets (important for running)

This project loads images and maps from the runtime classpath using absolute resource paths, including:

- Maps: `/Maps/world01.txt` (loaded by `TileManager`)
- Tile images: `/BackgroundTiles/*.png`
- Sprites:
  - Player: `/BbSprite/*.png`
  - NPC: `/npcSprites/*.png`
  - Items: `/Items/*.png`

If you clone the repo and run from a clean environment, you must ensure those resource folders are available on the classpath (otherwise sprite/map loads will fail).

## Running the game from terminal

### Option A: Run prebuilt artifacts (if present)

If your environment already has the prebuilt output, you can run:

```bash
cd TreasureHuntGameGit
java -cp "out/production/210-Project-Starter2" ui.Main
```

### Option B: Compile and run from source

```bash
cd TreasureHuntGameGit
mkdir -p out/cli
javac -d out/cli $(find src/main -name "*.java")

# IMPORTANT:
# Start the app with a classpath that includes BOTH:
# - compiled classes (out/cli)
# - resource folders (Maps/, Items/, BackgroundTiles/, BbSprite/, npcSprites/)
java -cp "out/cli:<resources-classpath>" ui.Main
```

## Testing

- Tests are located under `src/test`.
- The included JUnit 5 tests focus on inventory behavior:
  - adding items
  - stacking / counting amounts
  - removing items and updating totals

Note: game execution is interactive/graphics-based, so tests target core logic where possible.

## Experience gained

This project helped me practice:

- Designing a simple but complete game architecture (update loop + render + input routing).
- Implementing tile-based rendering and collision using hitboxes.
- Using a state machine (`gameState`) to coordinate UI screens with player actions.
- Building inventory and shop/trade logic that is approachable to extend (new item types and rules).


##

java -cp "out/production/210-Project-Starter2:lib/spec/json-20210307.jar" ui.Main

