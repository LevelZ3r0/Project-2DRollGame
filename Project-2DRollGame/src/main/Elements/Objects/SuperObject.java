package main.Elements.Objects;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/*
 los objetos seran elementos que pueden ser dibujados en el ambiente del juego y que pueden ser interactuables.
* */

public class SuperObject {

    public String name;
    public BufferedImage sprite;
    public int posX,posY;
    public int defaultX,defaultY;
    public boolean collision = false;
    public Rectangle collisionBox;

    public SuperObject(){}

    public void draw(Graphics2D g2, GamePanel gp){
        int screenX = posX - gp.player.posX + gp.screenMarginX;
        int screenY = posY - gp.player.posY + gp.screenMarginY;

        if(
                posX + gp.tileSize > gp.player.posX - gp.screenMarginX &&
                        posX - gp.tileSize < gp.player.posX + gp.screenMarginX &&
                        posY + gp.tileSize > gp.player.posY - gp.screenMarginY &&
                        posY - gp.tileSize < gp.player.posY + gp.screenMarginY
        ){
            g2.drawImage(sprite,screenX,screenY,gp.tileSize,gp.tileSize,null);
        }
        g2.setColor(Color.BLUE);
        g2.drawRect(collisionBox.x,collisionBox.y,collisionBox.width,collisionBox.height);
    }

}
