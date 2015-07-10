package com.andres_k.components.gameComponents.controllers;

import com.andres_k.components.gameComponents.animations.AnimatorGameData;
import com.andres_k.components.gameComponents.gameObject.EnumGameObject;
import com.andres_k.components.gameComponents.gameObject.GameObject;
import com.andres_k.components.gameComponents.gameObject.gameObjects.obstacles.ObstaclesController;
import com.andres_k.components.gameComponents.gameObject.gameObjects.player.SpaceShip;
import com.andres_k.components.graphicComponents.graphic.EnumWindow;
import com.andres_k.components.graphicComponents.input.EnumInput;
import com.andres_k.components.graphicComponents.input.InputGame;
import com.andres_k.components.graphicComponents.userInterface.overlay.EnumOverlayElement;
import com.andres_k.components.networkComponents.messages.MessageGameNew;
import com.andres_k.components.taskComponent.EnumTargetTask;
import com.andres_k.utils.configs.GlobalVariable;
import com.andres_k.utils.configs.WindowConfig;
import com.andres_k.utils.stockage.Tuple;
import com.andres_k.utils.tools.RandomTools;
import org.codehaus.jettison.json.JSONException;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by andres_k on 08/07/2015.
 */
public class GameController extends WindowController {
    private AnimatorGameData animatorGameData;
    private List<GameObject> players;
    private ObstaclesController obstacles;
    private InputGame inputGame;

    public GameController() throws JSONException {
        this.animatorGameData = new AnimatorGameData();

        this.players = new ArrayList<>();
        this.inputGame = new InputGame();
        this.obstacles = new ObstaclesController();
    }

    @Override
    public void enter() {
    }

    @Override
    public void leave() {
        for (GameObject player : this.players){
            player.clear();
        }
        this.players.clear();
        this.obstacles.leave();
    }

    @Override
    public void init() throws SlickException {
        this.animatorGameData.init();
        this.obstacles.init(this.animatorGameData);
    }

    @Override
    public void renderWindow(Graphics g) {
        for (GameObject player : this.players) {
            player.draw(g);
        }
        this.obstacles.draw(g);
    }

    @Override
    public void updateWindow(GameContainer gameContainer) {
        for (GameObject player : this.players) {
            player.update();
        }
        this.obstacles.update();
    }

    @Override
    public void keyPressed(int key, char c) {
        EnumInput result = this.inputGame.checkInput(key, EnumInput.PRESSED);

        if (result.getIndex() >= 0 && result.getIndex() < this.players.size()){
            this.players.get(result.getIndex()).eventPressed(result);
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        EnumInput result = this.inputGame.checkInput(key, EnumInput.RELEASED);

        if (result.getIndex() >= 0 && result.getIndex() < this.players.size()){
            this.players.get(result.getIndex()).eventReleased(result);
        }
    }

    @Override
    public void mousePressed(int button, int x, int y) {
    }

    @Override
    public void mouseReleased(int button, int x, int y) {
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Tuple) {
            Tuple<EnumTargetTask, EnumTargetTask, Object> received = (Tuple<EnumTargetTask, EnumTargetTask, Object>) arg;
            if (received.getV1().equals(EnumTargetTask.WINDOWS) && received.getV2().equals(EnumTargetTask.GAME)) {
                if (received.getV3() instanceof EnumWindow) {
                    this.stateWindow.enterState(((EnumWindow) received.getV3()).getValue());
                } else if (received.getV3() instanceof EnumOverlayElement) {
                    if (received.getV3() == EnumOverlayElement.EXIT) {
                        this.window.quit();
                    }
                } else if (received.getV3() instanceof MessageGameNew){
                    int nbr = Integer.valueOf((String) ((MessageGameNew) received.getV3()).getObjects().get(0));
                    float newSpeed = Float.valueOf((String)((MessageGameNew) received.getV3()).getObjects().get(1));

                    for (int i = 0; i < nbr && i < 2; ++i){
                        int randomX = RandomTools.getInt(WindowConfig.getW2SizeX() - 200) + 100;
                        this.players.add(new SpaceShip(this.animatorGameData.getAnimator(EnumGameObject.SPACESHIP), randomX, WindowConfig.w2_sY - 100));
                    }
                    if (newSpeed >= 1) {
                        GlobalVariable.gameSpeed = newSpeed;
                    }
                    this.stateWindow.enterState(EnumWindow.GAME.getValue());
                }
            }
        }
    }
}
