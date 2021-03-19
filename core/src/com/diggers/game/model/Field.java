package com.diggers.game.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Field {
    public static final int TYPE_SOIL = 0;
    public static final int TYPE_WATER = 1;
    public static final int TYPE_BASE = 2;
    public static final int TYPE_TUNNEL = 3;
    public static final int TYPE_DIAMOND = 4;
    public static final int TYPE_GOLD = 5;
    public static final int TYPE_OIL = 6;
    public static final int TYPE_STONE = 7;

    public static final int MAX_UNIT_COUNT_ON_FIELD = 3;

    private int type;
    private ArrayList<Unit> units;
    public boolean visible;
    private int health;

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    private int positionX, positionY;

    public Field(int type, int positionX, int positionY) {
        this.health = 100;
        this.visible = false;
        this.type = type;
        this.positionX = positionX;
        this.positionY = positionY;
        this.units = new ArrayList<>();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) throws Exception {
        this.units = new ArrayList<>();
        for (Unit unit: units){
            this.addUnit(unit);
        }
    }

    public boolean isBusy(){
        return this.units.size() >= MAX_UNIT_COUNT_ON_FIELD;
    }

    public void addUnit(Unit unit) throws Exception{
        if (isBusy()){
            throw new Exception("error!");
        }else{
            this.units.add(unit);
        }
    }

    public static int getRandomType(){
        Random random = new Random();
        return random.nextInt(Field.TYPE_STONE + 1);
    }

    public void removeUnit(Unit unit) {
        units.remove(unit);
    }

    public void update(){
        for (Unit unit: units){
            unit.updateField();
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health <= 0){
            type = TYPE_TUNNEL;
            health = 0;
        }
        this.health = health;
    }
}
