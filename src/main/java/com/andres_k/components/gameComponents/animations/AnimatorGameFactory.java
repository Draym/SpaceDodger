package com.andres_k.components.gameComponents.animations;

import com.andres_k.utils.tools.StringTools;
import org.codehaus.jettison.json.JSONException;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by andres_k on 13/03/2015.
 */
public class AnimatorGameFactory extends AnimatorFactory {
    public Animator getAnimator(EnumSprites index) throws SlickException, JSONException {
        if (index.getIndex() == EnumSprites.ITEM.getIndex()) {
            return this.getItemAnimator(index);
        }
        return null;
    }

    public Animator getItemAnimator(EnumSprites index) throws SlickException, JSONException {
        Animator animator = new Animator();
        if (index == EnumSprites.ASTEROID) {
            animator.addAnimation(EnumAnimation.BASIC, this.loadAnimation(new SpriteSheet("image/game/asteroid.png", 101, 105), true, 0, 3, 0, 1, 400));
            animator.addCollision(EnumAnimation.BASIC, StringTools.readFile("json/asteroid.json"));
        } else if (index == EnumSprites.SPACESHIP) {

            animator.addAnimation(EnumAnimation.MOVE_LEFT, this.loadAnimation(new SpriteSheet("image/game/redShipLEFT.png", 52, 57), false, 0, 3, 0, 1, 400));
            animator.addAnimation(EnumAnimation.MOVE_RIGHT, this.loadAnimation(new SpriteSheet("image/game/redShipRIGHT.png", 52, 57), false, 0, 3, 0, 1, 400));
            animator.addAnimation(EnumAnimation.BASIC, this.loadAnimation(new SpriteSheet("image/game/redShipIDLE.png", 52, 57), true, 0, 4, 0, 1, 300));
            animator.addCollision(EnumAnimation.MOVE_LEFT, StringTools.readFile("json/redShipLEFT.json"));
            animator.addCollision(EnumAnimation.MOVE_RIGHT, StringTools.readFile("json/redShipRIGHT.json"));
            animator.addCollision(EnumAnimation.BASIC, StringTools.readFile("json/redShipIDLE.json"));

        } else if (index == EnumSprites.BARRIER){
            animator.addAnimation(EnumAnimation.BASIC, this.loadAnimation(new SpriteSheet("image/game/barrier.png", 28, 900), true, 0, 3, 0, 1, 200));
            animator.addCollision(EnumAnimation.BASIC, StringTools.readFile("json/barrier.json"));
        }
        return animator;
    }

}
