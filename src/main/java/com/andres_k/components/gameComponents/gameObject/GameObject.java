package com.andres_k.components.gameComponents.gameObject;

import com.andres_k.components.gameComponents.animations.Animator;
import com.andres_k.components.graphicComponents.input.EnumInput;
import com.andres_k.utils.stockage.Pair;
import org.newdawn.slick.Graphics;

/**
 * Created by andres_k on 10/07/2015.
 */
public abstract class GameObject {
    protected Animator animator;
    protected Pair<Float, Float> positions;
    protected Pair<Float, Float> moveTo;

    protected boolean move;
    protected float maxLife;
    protected float currentLife;
    protected float damage;

    protected GameObject(Animator animator, float posX, float posY, float life, float damage) {
        this.positions = new Pair<>(posX, posY);
        this.moveTo = new Pair<>(0f, 0f);
        this.move = false;

        this.animator = animator;
        this.maxLife = life;
        this.currentLife = life;
        this.damage = damage;
    }

    public abstract void clear();

    public abstract void draw(Graphics g);

    public abstract void update();

    public abstract void eventPressed(EnumInput input);

    public abstract void eventReleased(EnumInput input);

    public void move() {
        if (this.move) {
            this.positions.setV1(this.positions.getV1() + this.moveTo.getV1());
            this.positions.setV2(this.positions.getV2() + this.moveTo.getV2());
        }
    }

    // GETTERS

    public boolean isMove() {
        return this.move;
    }

    public float getMaxLife() {
        return this.maxLife;
    }

    public float getCurrentLife() {
        return this.currentLife;
    }

    public float getDamage() {
        return this.damage;
    }

    public float graphicalX() {
        return this.positions.getV1() - (this.animator.currentAnimation().getWidth() / 2);
    }

    public float graphicalY() {
        return this.positions.getV2() - (this.animator.currentAnimation().getHeight() / 2);
    }

    public float getPosX() {
        return this.positions.getV1();
    }

    public float getPosY() {
        return this.positions.getV2();
    }

    // SETTERS

    public void setMove(boolean value) {
        this.move = value;
    }
}
