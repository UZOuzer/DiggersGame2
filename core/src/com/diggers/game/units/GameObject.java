package com.diggers.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;


public class GameObject {
    Texture texture;
    Sprite frame;

    public GameObject(String textureName){
        this.frame = new Sprite(new Texture(textureName));
    }

    public void draw(SpriteBatch spriteBatch, float deltaTime){
        spriteBatch.draw(frame, frame.getX(), frame.getY());
    }

    public float getX(){
        return frame.getX();
    }

    public float getY(){
        return frame.getY();
    }

    public void setX(float x){
        frame.setX(x);
    }

    public void setY(float y){
        frame.setY(y);
        System.out.println(y + " " + frame.getY());
    }

    public Rectangle getRect(){
        return frame.getBoundingRectangle();
    }

    public boolean isIntersect(Rectangle rectangle){
        return getRect().overlaps(rectangle);
    }

}
