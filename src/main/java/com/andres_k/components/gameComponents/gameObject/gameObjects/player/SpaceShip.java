package com.andres_k.components.gameComponents.gameObject.gameObjects.player;

import com.andres_k.components.gameComponents.animations.Animator;
import com.andres_k.components.gameComponents.animations.EnumAnimation;
import com.andres_k.components.gameComponents.gameObject.GameObject;
import com.andres_k.components.graphicComponents.input.EnumInput;
import com.andres_k.utils.configs.GlobalVariable;
import org.newdawn.slick.Graphics;

/**
 * Created by andres_k on 10/07/2015.
 */
public class SpaceShip extends GameObject {

    public SpaceShip(Animator animator, float x, float y) {
        super(animator, x, y, 1, 0);
    }

    @Override
    public void clear() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawAnimation(this.animator.currentAnimation(), this.graphicalX(), this.graphicalY());
    }

    @Override
    public void update() {
        this.move();
    }

    @Override
    public void eventPressed(EnumInput input) {

        if (input.isIn(EnumInput.MOVE_LEFT)) {
            this.animator.setCurrent(EnumAnimation.MOVE_LEFT);
            this.moveTo.setV1(-5f * GlobalVariable.gameSpeed);
            this.moveTo.setV2(0f);
            this.move = true;
        } else if (input.isIn(EnumInput.MOVE_RIGHT)) {
            this.animator.setCurrent(EnumAnimation.MOVE_RIGHT);
            this.moveTo.setV1(5f * GlobalVariable.gameSpeed);
            this.moveTo.setV2(0f);
            this.move = true;
        } else if (input.isIn(EnumInput.MOVE_UP)) {
            this.animator.setCurrent(EnumAnimation.BASIC);
            this.moveTo.setV1(0f);
            this.moveTo.setV2(-5f * GlobalVariable.gameSpeed);
            this.move = true;
        } else if (input.isIn(EnumInput.MOVE_DOWN)) {
            this.animator.setCurrent(EnumAnimation.BASIC);
            this.moveTo.setV1(0f);
            this.moveTo.setV2(5f * GlobalVariable.gameSpeed);
            this.move = true;
        }
    }

    @Override
    public void eventReleased(EnumInput input) {
        if (input.isIn(EnumInput.MOVE_UP) || input.isIn(EnumInput.MOVE_DOWN) ||
                input.isIn(EnumInput.MOVE_RIGHT) || input.isIn(EnumInput.MOVE_LEFT)) {
            this.animator.setCurrent(EnumAnimation.BASIC);
            this.moveTo.setV1(0f);
            this.moveTo.setV2(0f);
            this.move = false;
        }
    }

}
