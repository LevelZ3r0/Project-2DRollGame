package main.graphics.tiles;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    public Tile[] tiles;

    public TileManager(GamePanel gp){
        this.gp = gp;
        this.tiles = new Tile[5];
        getTileSprite();
    }

    public void getTileSprite(){
        try{
            this.tiles[0] = new Tile();
            this.tiles[0].sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water.png")));
            this.tiles[0].collision = true;
            this.tiles[1] = new Tile();
            this.tiles[1].sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sand.png")));
            this.tiles[2] = new Tile();
            this.tiles[2].sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/dirt.png")));
            this.tiles[3] = new Tile();
            this.tiles[3].sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));
            this.tiles[4] = new Tile();
            this.tiles[4].sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree.png")));
            this.tiles[4].collision = true;
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
