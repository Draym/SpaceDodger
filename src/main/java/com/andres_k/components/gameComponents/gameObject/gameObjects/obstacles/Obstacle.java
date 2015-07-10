package com.andres_k.components.gameComponents.gameObject.gameObjects.obstacles;

import com.andres_k.components.gameComponents.animations.Animator;
import com.andres_k.components.gameComponents.gameObject.GameObject;
import com.andres_k.components.graphicComponents.input.EnumInput;
import com.andres_k.utils.tools.Debug;
import org.newdawn.slick.Graphics;

/**
 * Created by andres_k on 10/07/2015.
 */
public class Obstacle extends GameObject {

    public Obstacle(Animator animator, float posX, float posY, float life, float damage) {
        super(animator, posX, posY, life, damage);
        Debug.debug("add Obstacle: " + posX + ", " + posY);
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
    }

    @Override
    public void eventReleased(EnumInput input) {
    }
}
