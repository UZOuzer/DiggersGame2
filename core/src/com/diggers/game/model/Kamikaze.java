package com.diggers.game.model;

public class Kamikaze extends Unit{
    public Kamikaze(Field field, Player player) {
        super(TYPE_KAMIKAZE, field, SPEED_KAMIKAZE, player);
    }

    @Override
    public boolean isCanMove(Field field) {
        return super.isCanMove(field) &&
                field.getType() == Field.TYPE_TUNNEL &&
                field.getType() == Field.TYPE_BASE;
    }
}
