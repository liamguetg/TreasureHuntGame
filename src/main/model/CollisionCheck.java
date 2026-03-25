package model;

import ui.GamePanel;

// Represents a collision checker to check if an entity will collide with an object, tile or another entity.
public class CollisionCheck {

    GamePanel gp;
    private int entityLeftWorldX;
    private int entityRightWorldX;
    private int entityTopWorldY;
    private int entityBottomWorldY;
    private int entityLeftCol;
    private int entityRightCol;
    private int entityTopRow;
    private int entityBottomRow;
    private int tileNum1;
    private int tileNum2;

    //EFFECTS: Constructor
    public CollisionCheck(GamePanel gp) {
        this.gp = gp;
    }

    //MODIFIES: this
    //EFFECTS: sets the coordinates for the entities solid Area (hit-box).
    public void setSolidArea(Entities entity) {
        entityLeftWorldX = entity.entityWorldX + entity.solidArea.x;
        entityRightWorldX = entity.entityWorldX + entity.solidArea.width + entity.solidArea.x;
        entityTopWorldY = entity.entityWorldY + entity.solidArea.y;
        entityBottomWorldY = entity.entityWorldY + entity.solidArea.height + entity.solidArea.y;

        entityLeftCol = entityLeftWorldX / gp.getTileSize();
        entityRightCol = entityRightWorldX / gp.getTileSize();
        entityTopRow = entityTopWorldY / gp.getTileSize();
        entityBottomRow = entityBottomWorldY / gp.getTileSize();
    }

    //EFFECTS: checks if entity will collide with a solid tile based on direction of movement.
    public void checkTile(Entities entity) {
        setSolidArea(entity);
        switch (entity.direction) {
            case "up":
                entityCollisionUp(entity);
                break;
            case "down":
                entityCollisionDown(entity);
                break;
            case "left":
                entityCollisionLeft(entity);
                break;
            case "right":
                entityCollisionRight(entity);
                break;
        }
    }

    //MODIFIES: entity.collisionOn,
    //EFFECTS: Checks if a player will collide into a solid tile when moving up.
    public void entityCollisionUp(Entities entity) {
        entityTopRow = (entityTopWorldY - entity.speed) / gp.getTileSize();
        tileNum1 = gp.getTileManaged().getMapTileNum()[entityLeftCol][entityTopRow];
        tileNum2 = gp.getTileManaged().getMapTileNum()[entityRightCol][entityTopRow];
        if (gp.getTileManaged().getTile()[tileNum1].collision || gp.getTileManaged().getTile()[tileNum2].collision) {
            entity.collisionOn = true;
        }
    }

    //MODIFIES: entity.collisionOn,
    //EFFECTS: Checks if a player will collide into a solid tile when moving right.
    public void entityCollisionRight(Entities entity) {
        entityRightCol = (entityRightWorldX + entity.speed) / gp.getTileSize();
        tileNum1 = gp.getTileManaged().getMapTileNum()[entityRightCol][entityTopRow];
        tileNum2 = gp.getTileManaged().getMapTileNum()[entityRightCol][entityBottomRow];
        if (gp.getTileManaged().getTile()[tileNum1].collision || gp.getTileManaged().getTile()[tileNum2].collision) {
            entity.collisionOn = true;
        }
    }

    //MODIFIES: entity.collisionOn,
    //EFFECTS: Checks if a player will collide into a solid tile when moving down.
    public void entityCollisionDown(Entities entity) {
        entityBottomRow = (entityBottomWorldY + entity.speed) / gp.getTileSize();
        tileNum1 = gp.getTileManaged().getMapTileNum()[entityLeftCol][entityBottomRow];
        tileNum2 = gp.getTileManaged().getMapTileNum()[entityRightCol][entityBottomRow];
        if (gp.getTileManaged().getTile()[tileNum1].collision || gp.getTileManaged().getTile()[tileNum2].collision) {
            entity.collisionOn = true;
        }
    }

    //MODIFIES: entity.collisionOn,
    //EFFECTS: Checks if a player will collide into a solid tile when moving down.
    public void entityCollisionLeft(Entities entity) {
        entityLeftCol = (entityLeftWorldX - entity.speed) / gp.getTileSize();
        tileNum1 = gp.getTileManaged().getMapTileNum()[entityLeftCol][entityTopRow];
        tileNum2 = gp.getTileManaged().getMapTileNum()[entityLeftCol][entityBottomRow];
        if (gp.getTileManaged().getTile()[tileNum1].collision || gp.getTileManaged().getTile()[tileNum2].collision) {
            entity.collisionOn = true;
        }
    }

    //EFFECTS: Checks if player has collided with the winning tile (wins game).
    public void checkWon(Entities player) {
        int playerX = player.entityWorldX / gp.getTileSize();
        int playerY = player.entityWorldY / gp.getTileSize();
        if (playerX == 10 && playerY == 9) {
            gp.setGameState(gp.wonState);
        }
    }

    //MODIFIES: entity.collisionOn,
    //EFFECTS: Checks if a player will collide with an object given direction of movement.
    //I TRIED SO HARD TO SPLIT THIS UP BUT IT WOULD NEVER WORK :(
    @SuppressWarnings("methodlength")
    public int checkObject(Entities entity, boolean player, ObjectSuper[] theList) {
        int index = 999;
        int len = theList.length;
        for (int i = 0; i < len; i++) {

            if (theList[i] != null) {

                //get entities (players) solidArea position
                entity.solidArea.x = entity.entityWorldX;
                entity.solidArea.y = entity.entityWorldY;

                //get objects solidArea
                theList[i].solidArea.x += theList[i].worldX;
                theList[i].solidArea.y += theList[i].worldY;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(theList[i].solidArea)) {
                            if (theList[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(theList[i].solidArea)) {
                            if (theList[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(theList[i].solidArea)) {
                            if (theList[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(theList[i].solidArea)) {
                            if (theList[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                }
                resetEntitySA(entity);
                theList[i].solidArea.x = theList[i].solidAreaDefaultX;
                theList[i].solidArea.y = theList[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    // EFFECTS: Checks if player will collide with NPC given its direction of movement.
    // If so index of the NPC is returned, 999 otherwise.
    @SuppressWarnings("methodlength")
    public int colPlayerEntCheck(Entities entity, Entities[] target) {
        int index = 999;
        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                //PLAYERS SA
                entity.solidArea.x = entity.entityWorldX + entity.solidArea.x;
                entity.solidArea.y = entity.entityWorldY + entity.solidArea.y;

                //NPCs SA
                target[i].solidArea.x = target[i].entityWorldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].entityWorldY + target[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        index = playerEntColDirection(entity, target, i);
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        index = playerEntColDirection(entity, target, i);
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        index = playerEntColDirection(entity, target, i);
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        index = playerEntColDirection(entity, target, i);
                        break;
                }
                resetEntitySA(entity);
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    //EFFECTS: Checks if player collides with other entity. Returns index of the NPC if so.
    public int playerEntColDirection(Entities entity, Entities[] target, int i) {
        int index = 999;
        if (entity.solidArea.intersects(target[i].solidArea)) {
            entity.collisionOn = true;
            index = i;
        }
        return index;
    }

    // EFFECTS: Checks if NPC will collide with player given its direction of movement.
    @SuppressWarnings("methodlength")
    public void colEntPlayerCheck(Entities entity) {
        //NPC SA:
        entity.solidArea.x = entity.entityWorldX + entity.solidArea.x;
        entity.solidArea.y = entity.entityWorldY + entity.solidArea.y;
        //PLAYER SA:
        gp.getPlayer().solidArea.x = gp.getPlayer().entityWorldX + gp.getPlayer().solidArea.x;
        gp.getPlayer().solidArea.y = gp.getPlayer().entityWorldX + gp.getPlayer().solidArea.y;

        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                npcPlayerCol(entity);
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                npcPlayerCol(entity);
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                npcPlayerCol(entity);
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                npcPlayerCol(entity);
                break;
        }
        resetEntitySA(entity);
        resetPlayerSA();
    }

    //EFFECTS: Checks if NPC will collide with player.
    public void npcPlayerCol(Entities entity) {
        if (entity.solidArea.intersects(gp.getPlayer().solidArea)) {
            entity.collisionOn = true;
        }
    }

    //MODIFIES: entities
    //EFFECTS: Resets players solid area to default after collision check was made.
    public void resetPlayerSA() {
        gp.getPlayer().solidArea.x = gp.getPlayer().solidAreaDefaultX;
        gp.getPlayer().solidArea.y = gp.getPlayer().solidAreaDefaultY;
    }

    //MODIFIES: entities
    //EFFECTS: Resets entities solid area to default after collision check was made.
    public void resetEntitySA(Entities entity) {
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
    }
}

