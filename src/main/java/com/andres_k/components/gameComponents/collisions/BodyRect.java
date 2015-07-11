package com.andres_k.components.gameComponents.collisions;

import com.andres_k.components.gameComponents.gameObject.EnumGameObject;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;


/**
 * Created by andres_k on 09/07/2015.
 */
public class BodyRect {
    private Rectangle body;
    private EnumGameObject type;


    public BodyRect(JSONObject object, Float decalX, Float decalY) throws JSONException {
        this.type = EnumGameObject.getEnumByValue(object.getString("type"));
        this.body = new Rectangle((float) object.getDouble("posX") - decalX, (float) object.getDouble("posY") - decalY, (float) object.getDouble("sizeX"), (float) object.getDouble("sizeY"));
    }

    public void draw(Graphics g, float posX, float posY) {
        if (this.type == EnumGameObject.DEFENSE_BODY) {
            g.setColor(Color.cyan);
        } else if (this.type == EnumGameObject.ATTACK_BODY) {
            g.setColor(Color.orange);
        } else if (this.type == EnumGameObject.BLOCK_BODY) {
            g.setColor(Color.green);
        }
        g.drawRect(posX + this.body.getMinX(), posY + this.body.getMinY(), this.body.getWidth(), this.body.getHeight());
    }


    // GETTERS

    public Rectangle getBody() {
        return this.body;
    }

    public EnumGameObject getType() {
        return this.type;
    }

    // SETTERS
    public void setType(EnumGameObject type) {
        this.type = type;
    }

    @Override
    public String toString() {
        JSONObject object = new JSONObject();

        try {
            object.put("type", this.type.getValue());
            object.put("posX", this.body.getMinX());
            object.put("posY", this.body.getMinY());
            object.put("sizeX", this.body.getWidth());
            object.put("sizeY", this.body.getHeight());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object.toString();
    }
}
