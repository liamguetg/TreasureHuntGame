# Treasure Hunt (2D Java Swing)

Please checkout the main branch for the full readMe. This is a secondary branch with no data persistent Implemented, in order to create JAR file that can be paired with Webswinger to create a dockerImage that is runnable on the browser. 

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