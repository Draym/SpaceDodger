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

    public abstract void draw(Graphics g);

    public abstract void update();

    public abstract void eventPressed(EnumInput input);

    public abstract void eventReleased(EnumInput input);
}
