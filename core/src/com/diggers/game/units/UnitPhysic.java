package com.diggers.game.units;

import com.badlogic.gdx.math.Rectangle;

import org.w3c.dom.css.Rect;

import java.util.ArrayList;

public class UnitPhysic {

    public Unit unit;

    protected float dx = 0, dy = 0;
    protected float maxSpeed = 200; // max pixels per second
    protected float maxJumpPower = 400; // max pixels per second

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
        dy = maxJumpPower;
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

        //Rectangle newRect = new Rectangle(newX, newY, getRect().width, getRect().height);

        float corY = 0;
        for (Platform platform: platforms){
            boolean isDownClear = !platform.getRect().overlaps(getDownRect());
            boolean isUpClear = !platform.getRect().overlaps(getUpRect());
            boolean isLeftClear = !platform.getRect().overlaps(getLeftRect());
            boolean isRightClear = !platform.getRect().overlaps(getRightRect());

            if (!isDownClear){
                newX = oldX;
                newY = oldY;
            }
        }

        unit.setX(newX);
        unit.setY(newY);
    }
}
