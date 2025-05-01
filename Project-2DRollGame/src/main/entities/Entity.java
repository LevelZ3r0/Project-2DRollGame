package main.entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int posX,posY;
    public int collisionDefaultX,collisionDefaultY;
    public int speed;
    public String direcction = "down";
    public boolean isMoving = false;

    public int spriteWidth;
    public int spriteHeight;
    public BufferedImage movementSheet;

    public Rectangle collisionArea;
    public boolean inCollision = false;

    public BufferedImage getSpriteFromSheet(BufferedImage sheet,int col, int row) {
        int sheetWidth = sheet.getWidth();
        int sheetHeight = sheet.getHeight();

        int x = col * this.spriteWidth;
        int y = row * this.spriteHeight;

        if(x < sheetWidth && y < sheetHeight){
            return sheet.getSubimage(x, y, this.spriteWidth, this.spriteHeight);
        }
        return null;

    }

    public BufferedImage getSpriteFromSheet(BufferedImage sheet,int col, int row,int spriteWidth,int spriteHeight) {
        int sheetWidth = sheet.getWidth();
        int sheetHeight = sheet.getHeight();

        int x = col * spriteWidth;
        int y = row * spriteHeight;

        if(x < sheetWidth && y < sheetHeight){
            return sheet.getSubimage(x, y, spriteWidth, spriteHeight);
        }
        return null;
    }
}
