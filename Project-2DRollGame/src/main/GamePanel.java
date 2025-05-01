package main;

import main.Elements.Objects.SuperObject;
import main.Elements.worldgen.AssetSetter;
import main.Elements.worldgen.Map;
import main.control.KeyHandler;
import main.entities.Player;
import main.graphics.CollisionManager;
import main.graphics.tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //atributos (configuracion del panel)
    public static final int originalTileSize = 32;
    public static final int scale = 3;
    public final int tileSize = originalTileSize * scale;

    //screen settings
    public final int maxScreenCol = 12;
    public final int maxScreenRow = 8;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public final int screenMarginX = screenWidth/2 -(tileSize/2);
    public final int screenMarginY = screenHeight/2 -(tileSize/2);

    /*
      el tamaño del la lista no define cuantos elementos pueden existir en el juego
    * sino cuantos pueden mostrarse en pantalla al mismo tiempo.
    * */
    public SuperObject[] objects = new SuperObject[96];

    //fps
    int fps = 60;

    //elements
    public TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Map map = new Map(this,tileM);
    public AssetSetter as = new AssetSetter(this);
    public Player player = new Player(this,map,keyH);
    public final UI ui = new UI(this);
    public CollisionManager cm = new CollisionManager(this);

    //constructores del panel de juego
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        //startGameThread();
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setUpGame(){
        this.as.setObjects();
    }

    @Override
    public void run() {
        //aqui se crea o define el game loop o bucle principal del juego
        //creacion del bucle principal mediante metodo delta
        double interval = (double) 1000000000 /fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null){

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / interval;
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //map
        map.draw(g2);

        //objects
        for(int i=0;i< objects.length;i++){
            if(objects[i] != null){
                objects[i].draw(g2,this);
            }
            else break;
        }

        //player
        player.draw(g2);

        //GUI
        ui.draw(g2);

        g2.dispose();
    }
}
