package model;

import ui.GamePanel;

public class CollisionCheck {

    GamePanel gp;

    public CollisionCheck(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entities entity) {

        int entityLeftWorldX = entity.entityWorldX + entity.solidArea.x;
        int entityRightWorldX = entity.entityWorldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.entityWorldY + entity.solidArea.y;
        int entityBottomWorldY = entity.entityWorldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCollisionCol = entityLeftWorldX/gp.tileSize;
        int entityRightCollisionCol = entityRightWorldX/gp.tileSize;
        int entityTopCollisionRow = entityTopWorldY/gp.tileSize;
        int entityBottomCollisionRow = entityBottomWorldY/gp.tileSize;

        int tileNum1;
        int tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopCollisionRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCollisionCol][entityTopCollisionRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCollisionCol][entityTopCollisionRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomCollisionRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCollisionCol][entityBottomCollisionRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCollisionCol][entityBottomCollisionRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCollisionCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCollisionCol][entityTopCollisionRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCollisionCol][entityBottomCollisionRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCollisionCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCollisionCol][entityTopCollisionRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCollisionCol][entityBottomCollisionRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Entities entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gp.objList.length; i++) {

            if (gp.objList[i] != null) {

                //get entities (players) solidArea position
                entity.solidArea.x = entity.entityWorldX + entity.solidArea.x;
                entity.solidArea.y = entity.entityWorldY + entity.solidArea.y;

                //get objects solidArea
                gp.objList[i].solidArea.x = gp.objList[i].worldX + gp.objList[i].solidArea.x;
                gp.objList[i].solidArea.y = gp.objList[i].worldY + gp.objList[i].solidArea.y;

                switch(entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.objList[i].solidArea)) {
                            if(gp.objList[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.objList[i].solidArea)) {
                            if(gp.objList[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.objList[i].solidArea)) {
                            if(gp.objList[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right": entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.objList[i].solidArea)) {
                            if(gp.objList[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.objList[i].solidArea.x = gp.objList[i].solidAreaDefaultX;
                gp.objList[i].solidArea.y = gp.objList[i].solidAreaDefaultY;
            }
        }
        return index;
    }
}
