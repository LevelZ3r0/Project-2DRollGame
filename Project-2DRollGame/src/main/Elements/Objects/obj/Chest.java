package main.Elements.Objects.obj;

import main.Elements.Objects.SuperObject;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Chest extends SuperObject {

    public Chest(int x,int y){

        this.posX = x;
        this.posY = y;

        this.defaultX = x;
        this.defaultY = y;

        this.collisionBox = new Rectangle(7,13,18*3,19*3);

        this.name = "Chest";
        try{
            this.sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/elements/objects/chest.png")));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.collision = true;
    }
}
