package com.diggers.game.units;

import com.badlogic.gdx.math.Rectangle;

import org.w3c.dom.css.Rect;

import java.util.ArrayList;

public class UnitPhysic {

    public Unit unit;

    protected float dx = 0, dy = 0;
    protected float maxSpeed = 200; // max pixels per second
    protected float maxJumpPower = 800; // max pixels per second
    public boolean isOnAGround = false;

    public UnitPhysic(Unit unit){
        this.unit = unit;
    }

    protected Rectangle getUpRect(){
        Rectangle unitRect = getRect();
        return new Rectangle(unitRect.x, unitRect.y + unitRect.height, unitRect.width, 1);
    }

    protected Rectangle getDownRect(){
        Rectangle unitRect = getRect();
        return new Rectangle(unitRect.x, unitRect.y - 1, unitRect.width, 1);
    }

    protected Rectangle getRightRect(){
        Rectangle unitRect = getRect();
        return new Rectangle(unitRect.x + unitRect.width, unitRect.y, 1, unitRect.height);
    }

    protected Rectangle getLeftRect(){
        Rectangle unitRect = getRect();
        return new Rectangle(unitRect.x - 1, unitRect.y, 1, unitRect.height);
    }

    protected Rectangle getRect(){
        return unit.getRect();
    }

    public void jump(){
        if (isOnAGround) dy = maxJumpPower;
    }

    public void moveLeft(){
        dx = -maxSpeed;
    }

    public void moveRight(){
        dx = maxSpeed;
    }

    public void noMove(){
        dx = 0;
    }



    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getMaxJumpPower() {
        return maxJumpPower;
    }

    public void setMaxJumpPower(float maxJumpPower) {
        this.maxJumpPower = maxJumpPower;
    }

    public void update(float deltaTime, ArrayList<Platform> platforms){
        dy = Math.max(-maxJumpPower, dy - 30);

        float deltaX = dx * deltaTime;
        float deltaY = dy * deltaTime;

        float newX = getRect().x + deltaX;
        float newY = getRect().y + deltaY;

        float oldX = getRect().x;
        float oldY = getRect().y;

        this.unit.setX(newX);
        this.unit.setY(newY);

        isOnAGround = false;

        float corY = 0;
        for (Platform platform: platforms){
            if (platform.getRect().overlaps(getRect())){
                float oldDistY = Math.abs(platform.getY() + platform.getRect().height - oldY);
                if (oldDistY <= Math.abs(deltaY)){
                    unit.setY(platform.getY() + platform.getRect().height);
                    isOnAGround = true;
                }
            }
            //left
            if (platform.getRect().overlaps(getLeftRect())){
                float oldDistX = Math.abs(platform.getX() + platform.getRect().width - oldX);
                if (oldDistX <= Math.abs(deltaX)){
                    unit.setX(platform.getX() + platform.getRect().width);
                }
            }
            //right
            if (platform.getRect().overlaps(getRightRect())){
                float oldDistX = Math.abs(platform.getX() - oldX - getRect().width);
                if (oldDistX <= Math.abs(deltaX)){
                    unit.setX(platform.getX() - getRect().width);
                }
            }
            //down
            if (platform.getRect().overlaps(getUpRect())){
                float oldDistY = Math.abs(platform.getY() - oldY - getRect().height);
                if (oldDistY <= Math.abs(deltaY)){
                    unit.setY(platform.getY() - getRect().height - (deltaY - oldDistY));
                    dy = -30;
                }
            }
        }


    }
}
