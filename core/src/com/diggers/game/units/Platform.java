package com.diggers.game.units;

public class Platform extends GameObject{
    public Platform(String textureName) {
        this(textureName, 0, 0);
    }

    public Platform(String textureName, float posX, float posY){
        super(textureName);
        this.setX(posX);
        this.setY(posY);
    }
}
