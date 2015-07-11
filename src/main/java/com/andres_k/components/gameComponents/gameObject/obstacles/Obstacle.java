package com.andres_k.components.gameComponents.gameObject.obstacles;

import com.andres_k.components.gameComponents.animations.Animator;
import com.andres_k.components.gameComponents.gameObject.GameObject;
import com.andres_k.components.graphicComponents.input.EnumInput;
import org.newdawn.slick.Graphics;

/**
 * Created by andres_k on 10/07/2015.
 */
public class Obstacle extends GameObject {

    public Obstacle(Animator animator, String id, float posX, float posY, float life, float damage) {
        super(animator, id, posX, posY, life, damage);
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
        this.move();
    }

    @Override
    public void eventPressed(EnumInput input) {
    }

    @Override
    public void eventReleased(EnumInput input) {
    }
}
