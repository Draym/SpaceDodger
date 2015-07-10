package com.andres_k.components.gameComponents.gameObject.gameObjects;

import com.andres_k.components.gameComponents.animations.Animator;
import com.andres_k.components.gameComponents.animations.EnumAnimation;
import com.andres_k.components.gameComponents.gameObject.GameObject;
import com.andres_k.components.graphicComponents.input.EnumInput;
import com.andres_k.utils.configs.GlobalVariable;
import com.andres_k.utils.stockage.Pair;
import com.andres_k.utils.tools.Debug;
import org.newdawn.slick.Graphics;

/**
 * Created by andres_k on 10/07/2015.
 */
public class SpaceShip extends GameObject {

    public SpaceShip(Animator animator, float x, float y) {
        this.animator = animator;
        this.positions = new Pair<>(x, y);
        Debug.debug("player created in " + x + ", " + y);
        this.moveTo = new Pair<>(0f, 0f);
        this.move = false;
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
        if (this.move) {
            this.positions.setV1(this.positions.getV1() + this.moveTo.getV1());
            this.positions.setV2(this.positions.getV2() + this.moveTo.getV2());
        }
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

    public float graphicalX() {
        return this.positions.getV1() - (this.animator.currentAnimation().getWidth() / 2);
    }

    public float graphicalY() {
        return this.positions.getV2() - (this.animator.currentAnimation().getHeight() / 2);
    }
}
