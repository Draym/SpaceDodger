package com.andres_k.components.gameComponents.gameObject.obstacles;

import com.andres_k.components.gameComponents.animations.Animator;
import com.andres_k.components.gameComponents.animations.EnumAnimation;
import com.andres_k.components.gameComponents.collisions.BodyAnimation;
import com.andres_k.components.gameComponents.gameObject.EnumGameObject;
import com.andres_k.components.gameComponents.gameObject.GameObject;
import com.andres_k.components.graphicComponents.input.EnumInput;
import com.andres_k.components.taskComponent.EnumTask;
import com.andres_k.utils.stockage.Pair;
import org.newdawn.slick.Graphics;

/**
 * Created by andres_k on 10/07/2015.
 */
public class SpaceShip extends GameObject {
    private EnumInput current;
    private long score;

    public SpaceShip(Animator animator, String id, float x, float y) {
        super(animator, id, EnumGameObject.SPACESHIP, new Pair<>(x, y), 1, 0, 3);
    }

    @Override
    public void clear() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawAnimation(this.animator.currentAnimation(), this.graphicalX(), this.graphicalY());
        BodyAnimation bodyAnimation = this.animator.currentBodyAnimation();

        if (bodyAnimation != null) {
            bodyAnimation.draw(g, this.animator.currentFrame(), this.getPosX(), this.getPosY());
        }
    }

    @Override
    public void update() {
        this.score += 1;
    }

    public void move() {
        if (this.move && !this.isNeedDelete()) {
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

        if (this.isAlive()) {
            if (input.isIn(EnumInput.MOVE_LEFT)) {
                this.animator.setCurrent(EnumAnimation.MOVE_LEFT);
                this.moveTo.setV1(-this.calculateWithSpeed());
                this.moveTo.setV2(0f);
                this.move = true;
            } else if (input.isIn(EnumInput.MOVE_RIGHT)) {
                this.animator.setCurrent(EnumAnimation.MOVE_RIGHT);
                this.moveTo.setV1(this.calculateWithSpeed());
                this.moveTo.setV2(0f);
                this.move = true;
            } else if (input.isIn(EnumInput.MOVE_UP)) {
                this.animator.setCurrent(EnumAnimation.BASIC);
                this.moveTo.setV1(0f);
                this.moveTo.setV2(-this.calculateWithSpeed());
                this.move = true;
            } else if (input.isIn(EnumInput.MOVE_DOWN)) {
                this.animator.setCurrent(EnumAnimation.BASIC);
                this.moveTo.setV1(0f);
                this.moveTo.setV2(this.calculateWithSpeed());
                this.move = true;
            }
            if (this.move) {
                this.current = input;
            }
        }
    }

    @Override
    public void eventReleased(EnumInput input) {
        if (this.isAlive()) {
            if (input == this.current) {
                this.animator.setCurrent(EnumAnimation.BASIC);
                this.moveTo.setV1(0f);
                this.moveTo.setV2(0f);
                this.move = false;
            }
        }
    }

    @Override
    public Object doTask(Object task) {
        if (task instanceof Pair){
            Pair<EnumTask, Object> received = (Pair<EnumTask, Object>) task;

            if (received.getV1() == EnumTask.UPGRADE_SCORE && received.getV2() instanceof Integer){
                this.score += (int)received.getV2();
            }
        }
        return null;
    }

    // GETTERS

    public long getScore(){
        return this.score / 10;
    }

}
