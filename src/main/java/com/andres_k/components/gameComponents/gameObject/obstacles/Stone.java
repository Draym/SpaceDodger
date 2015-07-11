package com.andres_k.components.gameComponents.gameObject.obstacles;

import com.andres_k.components.gameComponents.animations.Animator;
import com.andres_k.utils.configs.GlobalVariable;

/**
 * Created by andres_k on 11/07/2015.
 */
public class Stone extends Obstacle {
    public Stone(Animator animator, String id, float posX, float posY) {
        super(animator, id, posX, posY, 1, 1, 3);
        this.move = true;
    }

    @Override
    public float calculateWithSpeed() {
        return this.speed + (GlobalVariable.gameSpeed * 0.5f);
    }

}