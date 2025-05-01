package main;

import java.awt.*;

public class UI {

    public GamePanel gp;
    private Font font;

    public UI(GamePanel gp){
        this.gp = gp;
        this.font = new Font("Arial", Font.PLAIN,20);
    }

    public void update(){

    }

    public void draw(Graphics2D g2){
        g2.setFont(font);
        g2.setColor(Color.WHITE);
        g2.drawString("posX=" + (gp.player.collisionArea.x + gp.player.posX),30,50);
        g2.drawString("posY=" + (gp.player.collisionArea.y + gp.player.posY),30,80);
        g2.drawString("coorX=" + (gp.player.posX+gp.tileSize)/gp.tileSize,30,110);
        g2.drawString("coorY=" + (gp.player.posY+gp.tileSize)/gp.tileSize,30,140);
        g2.drawString("mapWidth=" + gp.map.mapBox.width,30,170);
        g2.drawString("mapHeight=" + gp.map.mapBox.height,30,200);

    }
}
