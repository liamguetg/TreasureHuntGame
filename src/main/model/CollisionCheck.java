package model;

import ui.GamePanel;

import static java.lang.Math.floor;


// Represents a collision checker to check if an entity will collide with an object, tile or another entity.
public class CollisionCheck {

    GamePanel gp;
    int entityLeftWorldX;
    int entityRightWorldX;
    int entityTopWorldY;
    int entityBottomWorldY;
    int entityLeftCollisionCol;
    int entityRightCollisionCol;
    int entityTopCollisionRow;
    int entityBottomCollisionRow;

    public CollisionCheck(GamePanel gp) {
        this.gp = gp;
    }

    public void playerSolidArea(Entities entity) {
        //NOTE: add entity.entityWorld(S), ((S) = X or Y) to the respective places to deal with the visual
        // effect of the player running over things going down and not touching them going up.
        entityLeftWorldX = entity.entityWorldX + entity.solidArea.x;
        entityRightWorldX = entity.entityWorldX + entity.solidArea.width + entity.solidArea.x;
        entityTopWorldY = entity.entityWorldY + entity.solidArea.y;
        entityBottomWorldY = entity.entityWorldY + entity.solidArea.height + entity.solidArea.y;

        entityLeftCollisionCol = entityLeftWorldX / gp.tileSize;
        entityRightCollisionCol = entityRightWorldX / gp.tileSize;
        entityTopCollisionRow = entityTopWorldY / gp.tileSize;
        entityBottomCollisionRow = entityBottomWorldY / gp.tileSize;
    }

    public void checkTile(Entities entity) {
        playerSolidArea(entity);

        int tileNum1;
        int tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopCollisionRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCollisionCol][entityTopCollisionRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCollisionCol][entityTopCollisionRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomCollisionRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCollisionCol][entityBottomCollisionRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCollisionCol][entityBottomCollisionRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCollisionCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCollisionCol][entityTopCollisionRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCollisionCol][entityBottomCollisionRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCollisionCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCollisionCol][entityTopCollisionRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCollisionCol][entityBottomCollisionRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public void checkWon(Entities player) {
        int playerX = (int) floor(player.entityWorldX / gp.tileSize);
        int playerY = (int) floor(player.entityWorldY / gp.tileSize);
        if (playerX == 10 && playerY == 9) {
            gp.gameState = gp.wonState;
        }
    }


    public int checkObject(Entities entity, boolean player, ObjectSuper theList[]) {
        int index = 999;
        for (int i = 0; i < gp.objList.length; i++) {

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
                            if (theList[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(theList[i].solidArea)) {
                            if (theList[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(theList[i].solidArea)) {
                            if (theList[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(theList[i].solidArea)) {
                            if (theList[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
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

    // EFFECTS: Checks if player is colliding with another entity.
    // If collision does NOT occur, the index returned is 999.
    public int colPlayerEntCheck(Entities entity, Entities[] target) {
        int index = 999;
        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                //get entities (players) solidArea position
                entity.solidArea.x = entity.entityWorldX + entity.solidArea.x;
                entity.solidArea.y = entity.entityWorldY + entity.solidArea.y;

                //get other entities solidArea
                target[i].solidArea.x = target[i].entityWorldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].entityWorldY + target[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                            break;
                        }

                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                            break;
                        }

                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                            break;
                        }
                }
                resetEntitySA(entity);
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public void colEntPlayerCheck(Entities entity) {
        entity.solidArea.x = entity.entityWorldX + entity.solidArea.x;
        entity.solidArea.y = entity.entityWorldY + entity.solidArea.y;

        gp.player.solidArea.x = gp.player.entityWorldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.entityWorldX + gp.player.solidArea.y;

        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                }
                break;

            case "left":
                entity.solidArea.x -= entity.speed;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                }
                break;

            case "right":
                entity.solidArea.x += entity.speed;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                }
                break;
        }
        resetEntitySA(entity);
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
    }

    public void resetEntitySA(Entities entity) {
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
    }
}

