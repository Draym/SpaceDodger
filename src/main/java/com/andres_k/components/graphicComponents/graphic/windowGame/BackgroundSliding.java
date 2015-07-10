package com.andres_k.components.graphicComponents.graphic.windowGame;

import com.andres_k.utils.configs.GlobalVariable;
import com.andres_k.utils.configs.WindowConfig;
import com.andres_k.utils.stockage.Pair;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andres_k on 10/07/2015.
 */
public class BackgroundSliding {

    private List<Image> images;
    private List<Pair<Float, Float>> positions;
    private float backgroundSizeY;

    public BackgroundSliding(String path) throws SlickException {

        this.images = new ArrayList<>();
        this.positions = new ArrayList<>();
        Image background = new Image(path);

        this.backgroundSizeY = background.getHeight();
        int number = (int) (WindowConfig.w2_sY / this.backgroundSizeY) + 2;

        float x = 0;
        float y = WindowConfig.w2_sY - this.backgroundSizeY;

        y = (y < 0 ? 0 : y);
         for (int i = 0; i < number; ++i) {
            this.images.add(background.copy());
            this.positions.add(new Pair<>(x, y));
            y -= this.backgroundSizeY - 1;
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < this.images.size(); ++i) {
            g.drawImage(this.images.get(i), this.positions.get(i).getV1(), this.positions.get(i).getV2());
        }
    }

    public void update() {
        for (Pair<Float, Float> pos : this.positions){
            pos.setV2(pos.getV2() + (GlobalVariable.gameSpeed / 2));
        }
        for (int i = 0; i < this.positions.size(); ++i){
            if (this.positions.get(i).getV2() > WindowConfig.w2_sY){
                int posPrev = i - 1;
                posPrev = (posPrev < 0 ? this.positions.size() - 1 : posPrev);

                this.positions.get(i).setV2(this.positions.get(posPrev).getV2() - this.backgroundSizeY);
            }
        }
    }
}
