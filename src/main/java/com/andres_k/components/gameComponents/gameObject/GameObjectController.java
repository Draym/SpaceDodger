package com.andres_k.components.gameComponents.gameObject;

import com.andres_k.components.gameComponents.animations.AnimatorGameData;
import com.andres_k.components.gameComponents.controllers.ScoreData;
import com.andres_k.components.gameComponents.gameObject.objects.Asteroid;
import com.andres_k.components.gameComponents.gameObject.objects.Barrier;
import com.andres_k.components.gameComponents.gameObject.objects.SpaceShip;
import com.andres_k.components.gameComponents.gameObject.objects.Stone;
import com.andres_k.components.graphicComponents.input.EnumInput;
import com.andres_k.components.graphicComponents.userInterface.overlay.EnumOverlayElement;
import com.andres_k.components.taskComponent.EnumTargetTask;
import com.andres_k.components.taskComponent.EnumTask;
import com.andres_k.utils.configs.GlobalVariable;
import com.andres_k.utils.configs.WindowConfig;
import com.andres_k.utils.stockage.Pair;
import com.andres_k.utils.stockage.Tuple;
import com.andres_k.utils.tools.ConsoleWrite;
import com.andres_k.utils.tools.RandomTools;
import com.andres_k.utils.tools.FilesTools;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.UUID;

/**
 * Created by andres_k on 10/07/2015.
 */
public class GameObjectController extends Observable {
    private List<GameObject> obstacles;
    private List<GameObject> players;

    private AnimatorGameData animatorGameData;

    private long updateIncrement;
    private long objectiveIncrement;

    public GameObjectController() {
        this.obstacles = new ArrayList<>();
        this.players = new ArrayList<>();
        this.animatorGameData = null;
    }

    // INIT
    public void init(AnimatorGameData animatorGameData) {
        this.animatorGameData = animatorGameData;
    }

    public void initWorld() throws SlickException {
        this.obstacles.add(new Barrier(this.animatorGameData.getAnimator(EnumGameObject.BARRIER), UUID.randomUUID().toString(), 14, 450));
        this.obstacles.add(new Barrier(this.animatorGameData.getAnimator(EnumGameObject.BARRIER), UUID.randomUUID().toString(), WindowConfig.w2_sX - 14, 450));
        this.obstacles.add(new Barrier(this.animatorGameData.getAnimator(EnumGameObject.BARRIER), UUID.randomUUID().toString(), 14, -450));
        this.obstacles.add(new Barrier(this.animatorGameData.getAnimator(EnumGameObject.BARRIER), UUID.randomUUID().toString(), WindowConfig.w2_sX - 14, -450));
    }

    // FUNCTIONS

    public void enter() throws SlickException {
        this.initWorld();
        this.updateIncrement = 0;
        this.objectiveIncrement = 20;
    }

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

    public void update(boolean running) throws SlickException {

        if (this.updateIncrement == this.objectiveIncrement) {
            this.updateIncrement = 0;
            for (float i = 0; i < GlobalVariable.currentSpeed; ++i) {
                if (RandomTools.getBoolean()) {
                    this.popAnObstacle(EnumGameObject.ASTEROID);
                } else {
                    this.popAnObstacle(EnumGameObject.STONE);
                }
            }
            ConsoleWrite.debug("objects: " + this.obstacles.size());
            this.objectiveIncrement = (long) (10 + RandomTools.getInt((int) (50 / GlobalVariable.currentSpeed)) + (20 / GlobalVariable.currentSpeed));
        }
        for (int i = 0; i < this.players.size(); ++i) {
            this.players.get(i).update();
            if (this.players.get(i).isNeedDelete()) {
                this.thisPlayerIsDead((SpaceShip) this.players.get(i));
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
        if (running) {
            ++this.updateIncrement;
        }
    }

    // EVENTS
    public void event(EnumInput event, EnumInput input) {
        if (event == EnumInput.KEY_RELEASED) {
            if (input.getIndex() >= 0 && input.getIndex() < this.players.size()) {
                GameObject player = this.getPlayerById("player" + String.valueOf(input.getIndex()));
                if (player != null) {
                    player.eventReleased(input);
                }
            }
        } else if (event == EnumInput.KEY_PRESSED) {
            GameObject player = this.getPlayerById("player" + String.valueOf(input.getIndex()));
            if (player != null) {
                player.eventPressed(input);
            }
        }
    }

    public void thisPlayerIsDead(SpaceShip player) {
        String score = String.valueOf(player.getScore());

        ScoreData.setAvailableScore(player.getPseudo(), score);
        score = FilesTools.addCharacterEach(score, " ", 3);
        Pair task = new Pair<>(EnumOverlayElement.SCORE.getValue() + player.getIdIndex(), new Tuple<>(EnumTask.SETTER, "value", player.getPseudo() + " - " + score));

        this.setChanged();
        this.notifyObservers(new Pair<>(EnumTargetTask.GAME_OVERLAY, new Pair<>(EnumOverlayElement.TABLE_ROUND_END, task)));
        ConsoleWrite.write("\n" + player.getPseudo() + " : '" + score + "' pts.");
    }

    public void changeGameState(boolean running){
        for (GameObject object : this.players){
            object.getAnimator().currentAnimation().setAutoUpdate(running);
        }
        for (GameObject object : this.obstacles){
            object.getAnimator().currentAnimation().setAutoUpdate(running);
        }
    }

    // ADD

    public void createPlayers(List<String> playerNames, AnimatorGameData animatorGameData) throws SlickException {
        for (int i = 0; i < playerNames.size(); ++i) {
            SpaceShip player = null;
            while (player == null || this.checkCollision(player)) {
                int randomX = RandomTools.getInt(WindowConfig.getW2SizeX() - 200) + 100;
                player = new SpaceShip(animatorGameData.getAnimator(EnumGameObject.SPACESHIP), "player" + String.valueOf(i) + ":" + playerNames.get(i), randomX, WindowConfig.w2_sY - 100);
            }
            this.players.add(player);
        }
    }

    public void popAnObstacle(EnumGameObject type) throws SlickException {

        float x = RandomTools.getInt(700);
        float y = -(RandomTools.getInt(200) + 105);

        if (type == EnumGameObject.ASTEROID) {
            x = (x < 95 ? 95 : x);
            x = (x > 605 ? 605 : x);
            this.obstacles.add(new Asteroid(this.animatorGameData.getAnimator(EnumGameObject.ASTEROID), UUID.randomUUID().toString(), x, y));
        } else if (type == EnumGameObject.STONE) {
            x = (x < 70 ? 70 : x);
            x = (x > 630 ? 630 : x);
            this.obstacles.add(new Stone(this.animatorGameData.getAnimator(EnumGameObject.STONE), UUID.randomUUID().toString(), x, y));
        }
    }

    // COLLISION

    public boolean checkCollision(GameObject current) {
        boolean collision = false;
        if (current != null) {
            List<GameObject> items = this.getAllExpectHim(current.getId());

            for (GameObject item : items) {
                if (current.checkCollisionWith(item)){
                    collision = true;
                }
            }
        }
        return collision;
    }

    // GETTERS

    public List<GameObject> getAllExpectHim(String id) {
        List<GameObject> items = new ArrayList<>();

        for (GameObject object : this.players) {
            if (!object.getId().equals(id)) {
                items.add(object);
            }
        }
        for (GameObject object : this.obstacles) {
            if (!object.getId().equals(id)) {
                items.add(object);
            }
        }
        return items;
    }

    public GameObject getPlayerById(String id) {
        for (GameObject player : this.players) {
            if (player.getId().contains(id)) {
                return player;
            }
        }
        return null;
    }

    public int getNumberPlayers() {
        return this.players.size();
    }
}
