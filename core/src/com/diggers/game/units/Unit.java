package com.diggers.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class Unit extends GameObject{
    public UnitPhysic unitPhysic;

    public Unit(String textureName) {
        this(textureName, 0, 0);
    }

    public Unit(String textureName, float posX, float posY) {
        super(textureName);
        this.setX(posX);
        this.setY(posY);
        this.unitPhysic = new UnitPhysic(this);
    }

    public void update(float deltaTime, ArrayList<Platform> platforms) {
        //super.update(deltaTime);
        unitPhysic.update(deltaTime, platforms);
    }
}
