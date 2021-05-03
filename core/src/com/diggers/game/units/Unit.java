package com.diggers.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Unit extends GameObject{
    public UnitPhysic unitPhysic;
    private AnimatedSprite animatedSprite;

    public Unit(String textureName) {
        this(textureName, 0, 0);
    }

    public Unit(String textureName, float posX, float posY) {
        super(textureName);
        this.animatedSprite = new AnimatedSprite(this.texture, new Rectangle(0, 500, 100, 100));
        this.setX(posX);
        this.setY(posY);
        this.unitPhysic = new UnitPhysic(this);
    }

    public void update(float deltaTime, ArrayList<Platform> platforms) {
        //super.update(deltaTime);
        animatedSprite.update(deltaTime);
        unitPhysic.update(deltaTime, platforms);
        animatedSprite.posX = getX();
        animatedSprite.posY = getY();
    }
    public void goLeft(float deltaTime){
        animatedSprite.curFrameY = 3;
        unitPhysic.moveLeft();

    }
    public void draw(SpriteBatch batch){
        animatedSprite.draw(batch);
    }

    public void jump(float deltaTime) {
        animatedSprite.curFrameY = 0;
        unitPhysic.jump();
    }

    public void goRight(float deltaTime) {
        animatedSprite.curFrameY = 4;
        unitPhysic.moveRight();

    }

    public void oMove(float deltaTime) {
        animatedSprite.curFrameY = 0;
        unitPhysic.noMove();
    }

    public void sit(float deltaTime) {
        animatedSprite.curFrameY = 12;
    }
}
