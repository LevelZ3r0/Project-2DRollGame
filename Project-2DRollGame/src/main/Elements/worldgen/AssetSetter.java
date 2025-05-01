package main.Elements.worldgen;

import main.Elements.Objects.obj.Chest;
import main.GamePanel;

public class AssetSetter {

    public GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObjects(){
        gp.objects[0] = new Chest(96, 96);

    }
}
