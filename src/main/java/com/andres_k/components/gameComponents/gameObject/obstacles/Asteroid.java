package com.andres_k.components.gameComponents.gameObject.obstacles;

import com.andres_k.components.gameComponents.animations.Animator;
import com.andres_k.components.gameComponents.animations.EnumAnimation;
import com.andres_k.utils.configs.GlobalVariable;
import com.andres_k.utils.configs.WindowConfig;

/**
 * Created by andres_k on 11/07/2015.
 */
public class Asteroid extends Obstacle {
    public Asteroid(Animator animator, String id, float posX, float posY) {
        super(animator, id, posX, posY, 3, 5);
        this.move = true;
    }

    @Override
    public void update() {
        this.moveTo.setV2(this.calculateWithSpeed(3f));
        this.move();
        if (this.getPosY() > WindowConfig.w2_sY + 300){
            this.animator.setCurrent(EnumAnimation.EXPLODE);
        }
    }

    public float calculateWithSpeed(float number) {
        return number + (GlobalVariable.gameSpeed * 0.5f);
    }

}
