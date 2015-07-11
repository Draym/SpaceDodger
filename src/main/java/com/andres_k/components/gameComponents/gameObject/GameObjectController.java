package com.andres_k.components.gameComponents.gameObject;

import com.andres_k.components.gameComponents.animations.AnimatorGameData;
import com.andres_k.components.gameComponents.gameObject.obstacles.Obstacle;
import com.andres_k.components.graphicComponents.input.EnumInput;
import com.andres_k.utils.configs.WindowConfig;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by andres_k on 10/07/2015.
 */
public class GameObjectController {
    private List<GameObject> obstacles;
    private List<GameObject> players;

    private AnimatorGameData animatorGameData;

    public GameObjectController() {
        this.obstacles = new ArrayList<>();
        this.players = new ArrayList<>();
        this.animatorGameData = null;
    }

    // INIT
    public void init(AnimatorGameData animatorGameData) {
        this.animatorGameData = animatorGameData;
    }

    public void initWorld() {
        this.obstacles.add(new Obstacle(this.animatorGameData.getAnimator(EnumGameObject.BARRIER), UUID.randomUUID().toString(), 14, 450, 1, 1));
        this.obstacles.add(new Obstacle(this.animatorGameData.getAnimator(EnumGameObject.BARRIER), UUID.randomUUID().toString(), WindowConfig.w2_sX - 14, 450, 1, 1));
    }

    // FUNCTIONS

    public void leave() {
        for (GameObject player : this.players) {
            player.clear();
        }
        this.players.clear();
        for (GameObject object : this.obstacles) {
            object.clear();
        }
        this.obstacles.clear();
    }

    public void draw(Graphics g) {
        for (GameObject player : this.players) {
            player.draw(g);
        }
        for (GameObject object : this.obstacles) {
            object.draw(g);
        }
    }

    public void update() {
        for (int i = 0; i < this.players.size(); ++i) {
            this.players.get(i).update();
            if (this.players.get(i).isNeedDelete()) {
                this.players.remove(i);
                --i;
            } else {
                this.checkCollision(this.players.get(i));
                this.players.get(i).move();
            }
        }
        for (int i = 0; i < this.obstacles.size(); ++i) {
            obstacles.get(i).update();
            if (this.obstacles.get(i).isNeedDelete()) {
                this.obstacles.remove(i);
                --i;
            } else {
                this.checkCollision(this.obstacles.get(i));
                this.obstacles.get(i).move();
            }
        }
    }

    public void event(EnumInput event, EnumInput input) {
        if (event == EnumInput.KEY_RELEASED) {
            if (input.getIndex() >= 0 && input.getIndex() < this.players.size()) {
                GameObject player = this.getPlayer("player" + String.valueOf(input.getIndex()));
                if (player != null) {
                    player.eventReleased(input);
                }
            }
        } else if (event == EnumInput.KEY_PRESSED) {
            GameObject player = this.getPlayer("player" + String.valueOf(input.getIndex()));
            if (player != null) {
                player.eventPressed(input);
            }
        }
    }

    // ADD

    public void addPlayer(GameObject player) {
        this.players.add(player);
    }

    // COLLISION

    public void checkCollision(GameObject current){
        List<GameObject> items = this.getAllExpectHim(current.getId());

        for (GameObject item : items){
            current.checkCollisionWith(item);
        }
    }

    // GETTERS

    public List<GameObject> getAllExpectHim(String id){
        List<GameObject> items = new ArrayList<>();

        for (GameObject object : this.players){
            if (!object.getId().equals(id)){
                items.add(object);
            }
        }
        for (GameObject object : this.obstacles){
            if (!object.getId().equals(id)){
                items.add(object);
            }
        }
        return items;
    }

    public GameObject getPlayer(String id){
        for (GameObject player : this.players){
            if (player.getId().equals(id)){
                return player;
            }
        }
        return null;
    }

    public int getNumberPlayers(){
        return this.players.size();
    }
}
