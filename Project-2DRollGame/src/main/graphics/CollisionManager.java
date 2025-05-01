package main.graphics;

import main.Elements.worldgen.Map;
import main.GamePanel;
import main.entities.Entity;

import java.awt.*;

public class CollisionManager {

    public GamePanel gp;

    public CollisionManager(GamePanel gp){
        this.gp = gp;
    }

    public void checkTileCollision(Entity entity){
        int entityLeftWorldX = entity.posX + entity.collisionArea.x;
        int entityRightWorldX = entity.posX + entity.collisionArea.x + entity.collisionArea.width;
        int entityTopWorldY = entity.posY + entity.collisionArea.y;
        int entityBottonWorldY = entity.posY + entity.collisionArea.y + entity.collisionArea.height;

        //calculamos las columnas y filas left,right,top y botton
        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottonRow = entityBottonWorldY/gp.tileSize;

        int tileNum1,tileNum2;

        switch (entity.direcction){
            case "down":
                entityBottonRow = (entityBottonWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.map.map[entityLeftCol][entityBottonRow];
                tileNum2 = gp.map.map[entityRightCol][entityBottonRow];
                if(gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision){
                    entity.inCollision = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.map.map[entityRightCol][entityTopRow];
                tileNum2 = gp.map.map[entityRightCol][entityBottonRow];
                if(gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision){
                    entity.inCollision = true;
                }
                break;
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.map.map[entityLeftCol][entityTopRow];
                tileNum2 = gp.map.map[entityRightCol][entityTopRow];
                if(gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision){
                    entity.inCollision = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.map.map[entityLeftCol][entityTopRow];
                tileNum2 = gp.map.map[entityLeftCol][entityBottonRow];
                if(gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision){
                    entity.inCollision = true;
                }
                break;
        }

    }

    public int checkObjectCollision(Entity entity,boolean player){

        int index = 999;
        for (int i=0; i<gp.objects.length;i++){
            if(gp.objects[i] != null){
                //get entity solid area position
                entity.collisionArea.x = entity.posX + entity.collisionArea.x;
                entity.collisionArea.y = entity.posY + entity.collisionArea.y;

                //get object area position
                gp.objects[i].collisionBox.x = gp.objects[i].posX + gp.objects[i].collisionBox.x;
                gp.objects[i].collisionBox.y = gp.objects[i].posY + gp.objects[i].collisionBox.y;

                switch (entity.direcction){
                    case "up":
                        entity.collisionArea.y -= entity.speed;
                        if(entity.collisionArea.intersects(gp.objects[i].collisionBox)){
                            System.out.println("arriba");
                        }
                        break;
                    case "down":
                        entity.collisionArea.y += entity.speed;
                        if(entity.collisionArea.intersects(gp.objects[i].collisionBox)){
                            System.out.println("abajo");
                        }
                        break;
                    case "left":
                        entity.collisionArea.x -= entity.speed;
                        if(entity.collisionArea.intersects(gp.objects[i].collisionBox)){
                            System.out.println("izquierda");
                        }
                        break;
                    case "right":
                        entity.collisionArea.x += entity.speed;
                        if(entity.collisionArea.intersects(gp.objects[i].collisionBox)){
                            System.out.println("derecha");
                        }
                        break;
                }
                entity.collisionArea.x = entity.collisionDefaultX;
                entity.collisionArea.y = entity.collisionDefaultY;
                gp.objects[i].collisionBox.x = gp.objects[i].defaultX;
                gp.objects[i].collisionBox.y = gp.objects[i].defaultY;
            }
        }
        return index;
    }

}
