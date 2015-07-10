package com.andres_k.components.graphicComponents.sounds;

import com.andres_k.components.gameComponents.gameObject.EnumGameObject;

/**
 * Created by andres_k on 07/07/2015.
 */
public enum EnumSound {
    NOTHING(""),
    //music
    BACKGROUND("music/background.ogg"), SELECT("");

    private String path;
    private EnumGameObject object;

    EnumSound(String path){
        this.path = path;
        this.object = EnumGameObject.NULL;
    }

    EnumSound(EnumGameObject object, String path){
        this.path = path;
        this.object = object;
    }

    public String getPath(){
        return this.path;
    }

    public EnumGameObject getObject(){
        return this.object;
    }

    public static EnumSound getSound(EnumGameObject object){
        EnumSound[] sounds = EnumSound.values();

        for (int i = 0; i < sounds.length; ++i){
            if (sounds[i].getObject() == object){
                return sounds[i];
            }
        }
        return NOTHING;
    }
}
