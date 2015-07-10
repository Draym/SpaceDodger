package com.andres_k.components.gameComponents.animations;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by andres_k on 13/03/2015.
 */
public class AnimatorGameFactory extends AnimatorFactory {
    public Animator getAnimator(EnumSprites index) throws SlickException {
        if (index.getIndex() == EnumSprites.ITEM.getIndex()) {
            return this.getItemAnimator(index);
        }
        return null;
    }

    public Animator getItemAnimator(EnumSprites index) throws SlickException {
        Animator animator = new Animator();
        if (index == EnumSprites.ASTEROID) {
            SpriteSheet spriteSheet = new SpriteSheet("image/game/asteroid.png", 101, 105);
            animator.addAnimation(EnumAnimation.BASIC, this.loadAnimation(spriteSheet, true, 0, 3, 0, 1, 100));
        } else if (index == EnumSprites.SPACESHIP) {

            SpriteSheet spriteSheetL = new SpriteSheet("image/game/redShipLEFT.png", 52, 57);
            SpriteSheet spriteSheetR = new SpriteSheet("image/game/redShipRIGHT.png", 52, 57);

            animator.addAnimation(EnumAnimation.BASIC, this.loadAnimation(new SpriteSheet("image/game/redShip.png", 52, 57), false, 0, 1, 0, 1, 100));
            animator.addAnimation(EnumAnimation.MOVE_LEFT, this.loadAnimation(spriteSheetL, false, 0, 3, 0, 1, 400));
            animator.addAnimation(EnumAnimation.MOVE_RIGHT, this.loadAnimation(spriteSheetR, false, 0, 3, 0, 1, 400));

            Animation animation = new Animation();
            animation.addFrame(spriteSheetL.getSprite(0, 0).copy(), 300);
            animation.addFrame(spriteSheetL.getSprite(1, 0).copy(), 300);
            animation.addFrame(spriteSheetR.getSprite(0, 0).copy(), 300);
            animation.addFrame(spriteSheetR.getSprite(1, 0).copy(), 300);
            animation.setLooping(true);

            animator.addAnimation(EnumAnimation.IDDLE, animation);
        }
        return animator;
    }

}
