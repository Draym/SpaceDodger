package com.andres_k.components.gameComponents.gameObject.obstacles;

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
    private EnumInput current;

    public SpaceShip(Animator animator, String id, float x, float y) {
        super(animator, id, x, y, 1, 0);
    }

    @Override
    public void clear() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawAnimation(this.animator.currentAnimation(), this.graphicalX(), this.graphicalY());
        this.animator.currentBodyAnimation().draw(g, this.animator.currentFrame(), this.getPosX(), this.getPosY());
    }

    @Override
    public void update() {

    }

    public void move() {
        if (this.move) {
            if (this.inTheMapAfterMove()) {
                this.positions.setV1(this.positions.getV1() + this.moveTo.getV1());
                this.positions.setV2(this.positions.getV2() + this.moveTo.getV2());
            } else {
                this.move = false;
                this.moveTo.setV1(0f);
                this.moveTo.setV2(0f);
            }
        }
    }

    @Override
    public void eventPressed(EnumInput input) {

        if (input.isIn(EnumInput.MOVE_LEFT)) {
            this.animator.setCurrent(EnumAnimation.MOVE_LEFT);
            this.moveTo.setV1(-this.calculateWithSpeed(5));
            this.moveTo.setV2(0f);
            this.move = true;
        } else if (input.isIn(EnumInput.MOVE_RIGHT)) {
            this.animator.setCurrent(EnumAnimation.MOVE_RIGHT);
            this.moveTo.setV1(this.calculateWithSpeed(5));
            this.moveTo.setV2(0f);
            this.move = true;
        } else if (input.isIn(EnumInput.MOVE_UP)) {
            this.animator.setCurrent(EnumAnimation.BASIC);
            this.moveTo.setV1(0f);
            this.moveTo.setV2(-this.calculateWithSpeed(5));
            this.move = true;
        } else if (input.isIn(EnumInput.MOVE_DOWN)) {
            this.animator.setCurrent(EnumAnimation.BASIC);
            this.moveTo.setV1(0f);
            this.moveTo.setV2(this.calculateWithSpeed(5));
            this.move = true;
        }
        if (this.move) {
            this.current = input;
        }
    }

    @Override
    public void eventReleased(EnumInput input) {
        if (input == this.current) {
            this.animator.setCurrent(EnumAnimation.BASIC);
            this.moveTo.setV1(0f);
            this.moveTo.setV2(0f);
            this.move = false;
        }
    }

}
