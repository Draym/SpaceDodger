package com.andres_k.components.gameComponents.gameObject.objects;

import com.andres_k.components.gameComponents.animations.Animator;
import com.andres_k.components.gameComponents.gameObject.EnumGameObject;
import com.andres_k.utils.configs.GlobalVariable;

/**
 * Created by andres_k on 11/07/2015.
 */
public class Stone extends Obstacle {
    public Stone(Animator animator, String id, float posX, float posY) {
        super(animator, id, EnumGameObject.STONE, posX, posY, 1, 2, 3);
        this.move = true;
    }

    @Override
    public float calculateWithSpeed() {
        return this.speed + (GlobalVariable.gameSpeed * 0.5f);
    }

}
