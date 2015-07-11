package com.andres_k.components.networkComponents.messages;

import com.andres_k.components.graphicComponents.userInterface.overlay.EnumOverlayElement;
import com.andres_k.components.networkComponents.MessageModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andres_k on 10/07/2015.
 */
public class MessageGameNew extends MessageModel {
    EnumOverlayElement from;
    List<Object> objects;

    public MessageGameNew(String pseudo, String id, EnumOverlayElement from){
        super(pseudo, id);

        this.from = from;
        this.objects = new ArrayList<>();
    }

    public void addObject(Object object){
        this.objects.add(object);
    }

    // GETTERS
    public EnumOverlayElement getFrom(){
        return this.from;
    }

    public List<Object> getObjects(){
        return this.objects;
    }
}
