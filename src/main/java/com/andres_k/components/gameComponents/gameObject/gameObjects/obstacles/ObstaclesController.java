package com.andres_k.components.gameComponents.gameObject.gameObjects.obstacles;

import com.andres_k.components.gameComponents.animations.AnimatorGameData;
import com.andres_k.components.gameComponents.gameObject.EnumGameObject;
import com.andres_k.components.gameComponents.gameObject.GameObject;
import com.andres_k.utils.configs.WindowConfig;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andres_k on 10/07/2015.
 */
public class ObstaclesController {
    private List<GameObject> obstacles;
    private AnimatorGameData animatorGameData;

    public ObstaclesController(){
        this.obstacles = new ArrayList<>();
        animatorGameData = null;
    }

    // INIT
    public void init(AnimatorGameData animatorGameData){
        this.animatorGameData = animatorGameData;
    }

    public void initWorld(){
        this.obstacles.add(new Obstacle(this.animatorGameData.getAnimator(EnumGameObject.BARRIER), 14, 450, 1, 1));
        this.obstacles.add(new Obstacle(this.animatorGameData.getAnimator(EnumGameObject.BARRIER), WindowConfig.w2_sX - 14, 450, 1, 1));
    }

    // FUNCTIONS

    public void leave(){
        for (GameObject object : this.obstacles){
            object.clear();
        }
        this.obstacles.clear();
    }

    public void draw(Graphics g){
        for (GameObject object : this.obstacles){
            object.draw(g);
        }
    }

    public void update(){
        for (GameObject object : this.obstacles){
            object.update();
        }
    }
}
