package com.andres_k.components.gameComponents.animations;

import com.andres_k.components.gameComponents.gameObject.EnumGameObject;
import org.codehaus.jettison.json.JSONException;
import org.newdawn.slick.SlickException;

import java.util.HashMap;

/**
 * Created by andres_k on 13/03/2015.
 */
public class AnimatorGameData {
    private AnimatorFactory animatorFactory;
    private HashMap<EnumGameObject, Animator> itemAnimator;


    public AnimatorGameData() {
        this.animatorFactory = new AnimatorGameFactory();
        this.itemAnimator = new HashMap<>();
    }

    public void init() throws SlickException, JSONException {
        this.initItem();
    }

    public void initItem() throws SlickException, JSONException {
        this.addItemAnimator(this.animatorFactory.getAnimator(EnumSprites.SPACESHIP), EnumGameObject.SPACESHIP);
        this.addItemAnimator(this.animatorFactory.getAnimator(EnumSprites.ASTEROID), EnumGameObject.ASTEROID);
        this.addItemAnimator(this.animatorFactory.getAnimator(EnumSprites.BARRIER), EnumGameObject.BARRIER);
        this.addItemAnimator(this.animatorFactory.getAnimator(EnumSprites.STONE), EnumGameObject.STONE);
    }

    public void addItemAnimator(Animator roundAnimator, EnumGameObject type) {
        this.itemAnimator.put(type, roundAnimator);
    }


    // GETTERS
    public Animator getAnimator(EnumGameObject index) {
        if (this.itemAnimator.containsKey(index)) {
            return new Animator(this.itemAnimator.get(index));
        }
        return null;
    }

}

