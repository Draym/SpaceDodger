package com.andres_k.components.gameComponents.controllers;

import com.andres_k.components.graphicComponents.graphic.EnumWindow;
import com.andres_k.components.graphicComponents.userInterface.overlay.EnumOverlayElement;
import com.andres_k.components.networkComponents.messages.MessageGameNew;
import com.andres_k.components.taskComponent.EnumTargetTask;
import com.andres_k.components.taskComponent.TaskFactory;
import com.andres_k.utils.configs.GlobalVariable;
import com.andres_k.utils.stockage.Pair;
import com.andres_k.utils.stockage.Tuple;
import org.codehaus.jettison.json.JSONException;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.Observable;

/**
 * Created by andres_k on 08/07/2015.
 */
public class InterfaceController extends WindowController {

    public InterfaceController() throws JSONException, SlickException {
    }

    @Override
    public void enter() {
        //init overlay value
        this.setChanged();
        this.notifyObservers(TaskFactory.createTask(EnumTargetTask.INTERFACE, EnumTargetTask.INTERFACE_OVERLAY, new Pair<EnumOverlayElement, Pair>(EnumOverlayElement.TABLE_MENU_NEWGAME, new Pair<>(EnumOverlayElement.SELECT_FIELD.getValue() + EnumOverlayElement.NEW.getValue() + "nbPlayer",  new Pair<>("setCurrent", "1")))));
        this.setChanged();
        this.notifyObservers(TaskFactory.createTask(EnumTargetTask.INTERFACE, EnumTargetTask.INTERFACE_OVERLAY, new Pair<EnumOverlayElement, Pair>(EnumOverlayElement.TABLE_MENU_NEWGAME, new Pair<>(EnumOverlayElement.SELECT_FIELD.getValue() + EnumOverlayElement.NEW.getValue() + "speedGame", new Pair<>("setCurrent", String.valueOf(GlobalVariable.gameSpeed))))));

    }

    @Override
    public void leave() {
    }

    @Override
    public void init() throws SlickException {
    }

    @Override
    public void renderWindow(Graphics g) {
    }

    @Override
    public void updateWindow(GameContainer gameContainer) {

    }

    @Override
    public void keyPressed(int key, char c) {

    }

    @Override
    public void keyReleased(int key, char c) {
    }

    @Override
    public void mouseReleased(int button, int x, int y) {
    }

    @Override
    public void mousePressed(int button, int x, int y) {

    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Tuple) {
            Tuple<EnumTargetTask, EnumTargetTask, Object> received = (Tuple<EnumTargetTask, EnumTargetTask, Object>) arg;

            if (received.getV1().equals(EnumTargetTask.WINDOWS) && received.getV2().equals(EnumTargetTask.INTERFACE)) {
                if (received.getV3() instanceof EnumWindow) {
                    if (this.stateWindow != null) {
                        this.stateWindow.enterState(((EnumWindow) received.getV3()).getValue());
                    }
                } else if (received.getV3() instanceof EnumOverlayElement) {
                    if (received.getV3() == EnumOverlayElement.EXIT) {
                        this.window.quit();
                    }
                } else if (received.getV3() instanceof MessageGameNew) {
                    this.setChanged();
                    this.notifyObservers(TaskFactory.createTask(EnumTargetTask.INTERFACE, EnumTargetTask.GAME, received.getV3()));
                }
            }
        }
    }
}
