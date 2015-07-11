package com.andres_k.components.gameComponents.gameObject.obstacles;

import com.andres_k.components.gameComponents.animations.Animator;
import com.andres_k.utils.configs.GlobalVariable;

/**
 * Created by andres_k on 11/07/2015.
 */
public class Asteroid extends Obstacle {
    public Asteroid(Animator animator, String id, float posX, float posY) {
        super(animator, id, posX, posY, 3, 10, 2);
        this.move = true;
    }


    @Override
    public float calculateWithSpeed() {
        return this.speed + (GlobalVariable.gameSpeed * 0.4f);
    }

}
