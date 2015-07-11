package com.andres_k.components.gameComponents.gameObject.obstacles;

import com.andres_k.components.gameComponents.animations.Animator;
import com.andres_k.components.gameComponents.gameObject.EnumGameObject;
import com.andres_k.utils.configs.GlobalVariable;
import com.andres_k.utils.configs.WindowConfig;

/**
 * Created by andres_k on 11/07/2015.
 */
public class Barrier extends Obstacle {
    public Barrier(Animator animator, String id, float posX, float posY) {
        super(animator, id, EnumGameObject.BARRIER, posX, posY, 1, 100, 0);
        this.move = true;
    }

    @Override
    public void update() {
        this.moveTo.setV2(this.calculateWithSpeed());
        this.move();
        if (this.getPosY() - 450 > WindowConfig.w2_sY) {
            this.positions.setV2(-450f);
        }
    }

    @Override
    public float calculateWithSpeed() {
        return GlobalVariable.gameSpeed / 3;
    }

}
