package com.diggers.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class Unit extends GameObject{
    public UnitPhysic unitPhysic;
    private float currentFrameTime = 0;
    private int currentFrameNumberX = 0;
    private int currentFrameNumberY = 0;
    private float maxTimePerFrame = 0.5f;
    private Sprite[][] frames;

    public Unit(String textureName) {
        this(textureName, 0, 0);
    }

    public Unit(String textureName, float posX, float posY) {
        super(textureName);
        this.setX(posX);
        this.setY(posY);
        this.unitPhysic = new UnitPhysic(this);
        frames = new Sprite[2][2];
//        for(int i = 0; i < frames.length; i++){
//            for (int j = 0; j < frames[i].length; j++){
//                Texture texture = new Texture(textureName);
//                int w = texture.getWidth() / frames[i].length;
//                int h = texture.getHeight() / frames.length;
//                System.out.println(j * w + " " +  i * h + " " +  w + " " + h);
//                frames[i][j] =
//                        new Sprite(texture, j * w, i * h, w, h);
//            }
//        }
        frame = new Sprite(new Texture(textureName), (int) frame.getX(), (int) frame.getY());
    }

    public void update(float deltaTime, ArrayList<Platform> platforms) {
        //super.update(deltaTime);
        //unitPhysic.update(deltaTime, platforms);
//        currentFrameTime += deltaTime;
//        if (currentFrameTime >= maxTimePerFrame){
//            currentFrameTime = 0;
//            currentFrameNumberX = (currentFrameNumberX + 1) % frames[currentFrameNumberY].length;
//            frame = frames[currentFrameNumberY][currentFrameNumberX];
//        }
    }
    public void goLeft(float deltaTime){
        unitPhysic.moveLeft();
        currentFrameNumberY = 1;
    }
}
