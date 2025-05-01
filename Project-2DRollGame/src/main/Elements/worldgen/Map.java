package main.Elements.worldgen;

import main.GamePanel;
import main.graphics.tiles.TileManager;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Map {

    public int[][] map;

    public String mapPath;
    public TileManager tm;
    public GamePanel gp;
    public Rectangle mapBox;

    public int mapCol;
    public int mapRow;
    public int mapWidth;
    public int mapHeight;

    public Map(GamePanel gp,TileManager tm){
        this.mapPath = "/maps/map.txt";
        this.gp=gp;
        this.tm=tm;

        mapCol = 0;
        mapRow = 0;

        getMapData(mapPath);
        mapBox = new Rectangle(0,0,mapWidth,mapHeight);

    }

    //la funcion se encarga de leer el archivo
    private void getMapData(String path){
        try(InputStream input = getClass().getResourceAsStream(mapPath)) {
            assert input != null;
            try(BufferedReader br = new BufferedReader(new InputStreamReader(input))){

                this.mapCol = getMapCol(br);
                this.mapRow = getMapRow(br);
                System.out.println("Col: " + this.mapCol + " Row: " + this.mapRow);

                map = new int[mapCol][mapRow];

                mapWidth = gp.tileSize*mapCol;
                mapHeight = gp.tileSize*mapRow;

                loadMap(br);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private int getMapCol(BufferedReader br) throws IOException {
        String line;
        if((line = br.readLine()) != null && line.startsWith("width:")){
            return Integer.parseInt(line.split(":")[1].replace(";", "").trim());

        }
        return 0;
    }

    private int getMapRow(BufferedReader br) throws IOException {
        String line;
        if((line = br.readLine()) != null && line.startsWith("height:")){
            return Integer.parseInt(line.split(":")[1].replace(";", "").trim());

        }
        return 0;
    }

    private void loadMap(BufferedReader br) throws IOException {
        String line;
        int row = 0;
        while ((line = br.readLine()) != null){
            if(line.startsWith("map:{")) continue;
            if(line.startsWith("}")) break;
            String[] tiles = line.trim().split(" ");
            for(int col=0;col < tiles.length;col++){
                this.map[col][row] = Integer.parseInt(tiles[col]);
            }
            row++;
        }
    }

    public void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < mapCol && worldRow < mapRow){

            int tileNum = map[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.posX + gp.screenMarginX;
            int screenY = worldY - gp.player.posY + gp.screenMarginY;

            if(
                    worldX + gp.tileSize > gp.player.posX - gp.screenMarginX &&
                    worldX - gp.tileSize < gp.player.posX + gp.screenMarginX &&
                    worldY + gp.tileSize > gp.player.posY - gp.screenMarginY &&
                    worldY - gp.tileSize < gp.player.posY + gp.screenMarginY
            ){
                g2.drawImage(tm.tiles[tileNum].sprite,screenX,screenY,gp.tileSize,gp.tileSize,null);
            }
            worldCol++;

            if(worldCol == mapCol){
                worldCol = 0;
                worldRow++;
            }
        }
        g2.drawRect(mapBox.x, mapBox.y, mapBox.width,mapBox.height);
    }

    public Rectangle getMapLimits(final int posx,final int posy){

        //formula media rara pero de momento funciona
        int x = -posx;
        int y = -posy;

        mapBox.x = x;
        mapBox.y = y;

        return new Rectangle(x,y,mapBox.width,mapBox.height);
    }
}
