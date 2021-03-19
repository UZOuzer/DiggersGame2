package com.diggers.game.model;

public class Worker extends Unit{

    public static final int POWER = 30;

    public Worker(Field field, Player player) {
        super(TYPE_WORKER, field, SPEED_WORKER, player);
    }

    @Override
    public boolean isCanMove(Field field) {
        return super.isCanMove(field);
    }

    @Override
    public void move(Field field) throws Exception {
        if (isCanMove(field)){
            field.addUnit(this);
            position.removeUnit(this);
            if (field.getType() != Field.TYPE_TUNNEL && field.getType() != Field.TYPE_BASE){
                speed = 0;
            }else{
                speed -= 1;
            }
        }
    }

    @Override
    public void updateField() {
        position.setHealth(position.getHealth() - POWER);
        try {
            if (Field.TYPE_GOLD == position.getType()){
                player.calculateResource(Game.RESOURCE_TYPE_GOLD, POWER);
            }
            if (Field.TYPE_OIL == position.getType()){
                player.calculateResource(Game.RESOURCE_TYPE_OIL, POWER);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
