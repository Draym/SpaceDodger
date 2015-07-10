package com.andres_k.components.graphicComponents.graphic.windowGame;

import com.andres_k.utils.configs.GlobalVariable;
import com.andres_k.utils.configs.WindowConfig;
import com.andres_k.utils.stockage.Pair;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by andres_k on 10/07/2015.
 */
public class BackgroundSliding {
    private Image img1;
    private Image img2;
    private Pair<Float, Float> pos1;
    private Pair<Float, Float> pos2;

    public BackgroundSliding(String path) throws SlickException {
        this.img1 = new Image(path);
        this.img2 = new Image(path);
        this.pos1 = new Pair<>(0f, 0f);
        this.pos2 = new Pair<>(0f, -WindowConfig.getSizeY());
    }

    public void draw(Graphics g){
        g.drawImage(this.img1, this.pos1.getV1(), this.pos1.getV2());
        g.drawImage(this.img2, this.pos2.getV1(), this.pos2.getV2());
    }

    public void update(){
        if (this.pos1.getV2() == WindowConfig.getSizeY()){
            this.pos1 = new Pair<>(0f, -WindowConfig.getSizeY());
        }
        if (this.pos2.getV2() == WindowConfig.getSizeY()){
            this.pos2 = new Pair<>(0f, -WindowConfig.getSizeY());
        }
        this.pos1.setV2(this.pos1.getV2() + GlobalVariable.gameSpeed);
        this.pos2.setV2(this.pos2.getV2() + GlobalVariable.gameSpeed);
    }
}
