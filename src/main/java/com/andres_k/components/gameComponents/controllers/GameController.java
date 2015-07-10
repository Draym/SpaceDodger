package com.andres_k.components.gameComponents.controllers;

import com.andres_k.components.gameComponents.animations.AnimatorGameData;
import com.andres_k.components.gameComponents.gameObject.EnumGameObject;
import com.andres_k.components.gameComponents.gameObject.GameObject;
import com.andres_k.components.gameComponents.gameObject.gameObjects.SpaceShip;
import com.andres_k.components.graphicComponents.graphic.EnumWindow;
import com.andres_k.components.graphicComponents.input.EnumInput;
import com.andres_k.components.graphicComponents.input.InputData;
import com.andres_k.components.graphicComponents.input.InputGame;
import com.andres_k.components.graphicComponents.userInterface.overlay.EnumOverlayElement;
import com.andres_k.components.taskComponent.EnumTargetTask;
import com.andres_k.utils.configs.Config;
import com.andres_k.utils.configs.WindowConfig;
import com.andres_k.utils.stockage.Tuple;
import org.codehaus.jettison.json.JSONException;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.Observable;

/**
 * Created by andres_k on 08/07/2015.
 */
public class GameController extends WindowController {
    private AnimatorGameData animatorGameData;
    private GameObject player;
    private InputGame inputGame;

    public GameController() throws JSONException {
        this.animatorGameData = new AnimatorGameData();

        this.inputGame = new InputGame(new InputData(Config.input));
    }

    @Override
    public void enter() {
    }

    @Override
    public void leave() {
    }

    @Override
    public void init() throws SlickException {
        this.animatorGameData.init();

        this.player = new SpaceShip(this.animatorGameData.getItemAnimator(EnumGameObject.SPACESHIP), WindowConfig.getSizeX() / 2, 800);
    }

    @Override
    public void renderWindow(Graphics g) {
        this.player.draw(g);
    }

    @Override
    public void updateWindow(GameContainer gameContainer) {
        this.player.update();
    }

    @Override
    public void keyPressed(int key, char c) {
        int result = this.inputGame.checkInput(key, EnumInput.PRESSED);
        if (result >= EnumInput.MOVE_LEFT.getIndex() && result <= EnumInput.MOVE_RIGHT.getIndex()){
            this.player.eventPressed(EnumInput.getEnumByIndex(result));
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        int result = this.inputGame.checkInput(key, EnumInput.RELEASED);
        if (result >= EnumInput.MOVE_LEFT.getIndex() && result <= EnumInput.MOVE_RIGHT.getIndex()){
            this.player.eventReleased(EnumInput.getEnumByIndex(result));
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
                }
            }
        }
    }
}
