package com.andres_k.components.gameComponents.gameObject;

import com.andres_k.components.gameComponents.animations.Animator;
import com.andres_k.components.gameComponents.animations.EnumAnimation;
import com.andres_k.components.gameComponents.collisions.BodyRect;
import com.andres_k.components.gameComponents.collisions.BodySprite;
import com.andres_k.components.graphicComponents.input.EnumInput;
import com.andres_k.utils.configs.GlobalVariable;
import com.andres_k.utils.configs.WindowConfig;
import com.andres_k.utils.stockage.Pair;
import org.newdawn.slick.Graphics;

import java.util.List;

/**
 * Created by andres_k on 10/07/2015.
 */
public abstract class GameObject {
    protected Animator animator;
    protected Pair<Float, Float> positions;
    protected Pair<Float, Float> moveTo;
    protected String id;

    protected boolean move;
    protected float maxLife;
    protected float currentLife;
    protected float damage;

    protected GameObject(Animator animator, String id, float posX, float posY, float life, float damage) {
        this.positions = new Pair<>(posX, posY);
        this.moveTo = new Pair<>(0f, 0f);
        this.move = false;

        this.id = id;
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

    public Pair<Float, Float> predictMove() {
        if (this.move) {
            return new Pair<>(this.positions.getV1() + this.moveTo.getV1(), this.positions.getV2() + this.moveTo.getV2());
        } else {
            return new Pair<>(this.positions.getV1(), this.positions.getV2());
        }
    }

    public boolean inTheMapAfterMove(){
        Pair<Float, Float> pos = this.predictMove();
        if (pos.getV1() > 0 && pos.getV1() < WindowConfig.w2_sX && pos.getV2() > 0 && pos.getV2() < WindowConfig.w2_sY){
            return true;
        }
        return false;
    }

    public void checkCollisionWith(GameObject enemy) {
        Pair<Float, Float> tmpPos = predictMove();

        BodySprite enemyBody = enemy.getBody();
        BodySprite myBody = this.getBody();

        if (myBody.getBody(tmpPos.getV1(), tmpPos.getV2()).intersects(enemyBody.getBody(enemy.getPosX(), enemy.getPosY()))) {
            List<BodyRect> enemyBodies = enemyBody.getBodies();
            List<BodyRect> myBodies = myBody.getBodies();

            for (BodyRect mine : myBodies) {
                for (BodyRect his : enemyBodies) {
                    if (mine.getBody(tmpPos.getV1(), tmpPos.getV2()).intersects(his.getBody(enemy.getPosX(), enemy.getPosY()))) {
                        if (mine.getType() == EnumGameObject.ATTACK_BODY && his.getType() == EnumGameObject.DEFENSE_BODY) {
                            enemy.getHit(this);
                        } else if (mine.getType() == EnumGameObject.DEFENSE_BODY && his.getType() == EnumGameObject.ATTACK_BODY) {
                            this.getHit(enemy);
                        } else if (mine.getType() != EnumGameObject.ATTACK_BODY && his.getType() != EnumGameObject.ATTACK_BODY) {
                            this.move = false;
                        }
                    }
                }
            }
        }
    }

    public void getHit(GameObject enemy){
        this.currentLife -= enemy.getDamage();
        if (this.currentLife <= 0){
            this.animator.setCurrent(EnumAnimation.EXPLODE);
        } else {
            this.animator.nextCurrentIndex();
        }
    }

    public float calculateWithSpeed(float number) {
        return number + (GlobalVariable.gameSpeed * 0.8f);
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

    public BodySprite getBody() {
        return this.animator.currentBodySprite();
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

    public boolean isNeedDelete() {
        return this.animator.isDeleted();
    }

    public String getId() {
        return this.id;
    }

    // SETTERS

    public void setMove(boolean value) {
        this.move = value;
    }
}
