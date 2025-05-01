package main.entities;

import main.Elements.worldgen.Map;
import main.GamePanel;
import main.control.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;
    Map map;


    // Variables para manejar la animación
    private int spriteCounter = 0;
    private int spriteNum = 1;

    private final Rectangle contornoSprite;
    public Rectangle test;

    public Player(GamePanel gp, Map map, KeyHandler kh){

        this.gp = gp;
        this.keyH = kh;
        this.map = map;

        this.spriteWidth = 32;
        this.spriteHeight = 32;


        this.collisionArea = new Rectangle(30,36,36,60);

        collisionDefaultX = collisionArea.x;
        collisionDefaultY = collisionArea.y;

        try{
            this.movementSheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spritesheet_pj_v1.png")));
        }
        catch(IOException e){
            this.movementSheet = null;
        }

        setDefaultValues();
        contornoSprite = new Rectangle(0,0,spriteWidth*3,spriteHeight*3);
    }

    private void setDefaultValues(){
        posX=1*gp.tileSize;
        posY=1*gp.tileSize;
        speed=2;
    }

    //funcion que controla la direccion y movimiento del jugador
    private void move() {

        checkDirection();
        checkCollision();
        if (!isMoving || inCollision) return;

        /*if(direcction.equals("left") && withinOfMapBounds(posX-speed,posY)){
            posX -= speed;
        } else if (direcction.equals("right") && withinOfMapBounds(posX+speed,posY)) {
            posX += speed;
        } else if (direcction.equals("up") && withinOfMapBounds(posX,posY-speed)) {
            posY -= speed;
        } else if (direcction.equals("down") && withinOfMapBounds(posX,posY+speed)) {
            posY += speed;
        }*/
        if(direcction.equals("left")){
            posX -= speed;
        } else if (direcction.equals("right")) {
            posX += speed;
        } else if (direcction.equals("up")) {
            posY -= speed;
        } else if (direcction.equals("down")) {
            posY += speed;
        }
        movementAnimation();

    }

    /*
        ahora si solucionado, el error parecia ser que la caja de colision del jugador
        no estaba en la posicion correcta.
    */
    public boolean withinOfMapBounds(final int futurePosX,final int futurePosY){
        test = gp.map.getMapLimits(futurePosX,futurePosY);
        return  test.contains(collisionArea);
    }

    //comprueba la direccion del jugador
    private void checkDirection(){
        isMoving = false;
        if(keyH.up){direcction="up";isMoving = true;}
        else if (keyH.right) {direcction="right";isMoving = true;}
        else if (keyH.down) {direcction="down";isMoving = true;}
        else if (keyH.left) {direcction="left";isMoving = true;}
    }

    //comprueba si hay colision con elementos del entorno (no incluye el borde del mapa)
    private void checkCollision(){
        inCollision = false;
        //gp.cm.checkTileCollision(this);
        gp.cm.checkObjectCollision(this,true);
    }

    public void update(){
        move();
    }

    //controla la animacion de caminar del jugador
    private void movementAnimation(){
        if(isMoving){
            spriteCounter++;
            if(spriteCounter > 10){
                spriteNum = (spriteNum % 4) + 1; // Ciclo entre 1 y 4
                spriteCounter = 0;
            }
        } else {
            spriteNum = 1;
        }
    }

    public void draw(Graphics2D g2){
        if(!isMoving){
            switch (direcction){
                case "down": g2.drawImage(getSpriteFromSheet(movementSheet,0,0), gp.screenMarginX, gp.screenMarginY,spriteWidth* GamePanel.scale,spriteHeight* GamePanel.scale,null);break;
                case "right": g2.drawImage(getSpriteFromSheet(movementSheet,1,0),gp.screenMarginX, gp.screenMarginY,spriteWidth* GamePanel.scale,spriteHeight* GamePanel.scale,null);break;
                case "up": g2.drawImage(getSpriteFromSheet(movementSheet,2,0), gp.screenMarginX, gp.screenMarginY,spriteWidth* GamePanel.scale,spriteHeight* GamePanel.scale,null);break;
                case "left": g2.drawImage(getSpriteFromSheet(movementSheet,3,0), gp.screenMarginX, gp.screenMarginY,spriteWidth* GamePanel.scale,spriteHeight* GamePanel.scale,null);break;
            }
        }
        else{
            switch (direcction){
                case "down": g2.drawImage(getSpriteFromSheet(movementSheet,0,spriteNum-1), gp.screenMarginX, gp.screenMarginY,spriteWidth* GamePanel.scale,spriteHeight* GamePanel.scale,null);break;
                case "right": g2.drawImage(getSpriteFromSheet(movementSheet,1,spriteNum-1), gp.screenMarginX, gp.screenMarginY,spriteWidth* GamePanel.scale,spriteHeight* GamePanel.scale,null);break;
                case "up": g2.drawImage(getSpriteFromSheet(movementSheet,2,spriteNum-1), gp.screenMarginX, gp.screenMarginY,spriteWidth* GamePanel.scale,spriteHeight* GamePanel.scale,null);break;
                case "left": g2.drawImage(getSpriteFromSheet(movementSheet,3,spriteNum-1), gp.screenMarginX, gp.screenMarginY,spriteWidth* GamePanel.scale,spriteHeight* GamePanel.scale,null);break;
            }
        }
        g2.setColor(Color.CYAN);
        g2.drawRect(contornoSprite.x,contornoSprite.y, contornoSprite.width,contornoSprite.height);
        g2.setColor(Color.red);
        g2.drawRect(collisionArea.x, collisionArea.y,collisionArea.width,collisionArea.height);
        if(test != null){
            g2.setColor(Color.MAGENTA);
            g2.drawRect(test.x,test.y,test.width,test.height);
        }
    }
}
