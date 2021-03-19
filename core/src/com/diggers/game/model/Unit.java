package com.diggers.game.model;

public class Unit {
    public static final int TYPE_WORKER = 0;
    public static final int TYPE_TRANSPORT = 1;
    public static final int TYPE_KAMIKAZE = 2;
    public static final int TYPE_GUARDIAN = 3;

    public static final int SPEED_WORKER = 3;
    public static final int SPEED_TRANSPORT = 3;
    public static final int SPEED_KAMIKAZE = 3;
    public static final int SPEED_GUARDIAN = 3;

    public static final int COST_WORKER = 20;
    public static final int COST_TRANSPORT = 30;
    public static final int COST_KAMIKAZE = 40;
    public static final int COST_GUARDIAN = 40;

    protected int type, speed;
    protected Field position;
    protected Player player;

    public Unit(int type, Field field, int speed) {
        this(type, field, speed, new Player("Компьютер Вася"));
    }

    public Unit(int type, Field field, int speed, Player player){
        this.type = type;
        this.position = field;
        this.speed = speed;
        this.player = player;
    }

    public boolean isCanMove(Field field){
        return Math.abs(position.getPositionX() - field.getPositionX()) +
                Math.abs(position.getPositionY() - field.getPositionY()) == 1 &&
                speed > 0 && !field.isBusy();
    }

    public void move(Field field) throws Exception {
        if (isCanMove(field)){
            field.addUnit(this);
            position.removeUnit(this);
            speed -= 1;
        }
    }

    public static int getCost(int unitType){
        if (unitType == TYPE_TRANSPORT) return COST_TRANSPORT;
        if (unitType == TYPE_GUARDIAN) return COST_GUARDIAN;
        if (unitType == TYPE_KAMIKAZE) return COST_KAMIKAZE;
        return COST_WORKER;
    }

    public void updateField() {
        //
    }
}
