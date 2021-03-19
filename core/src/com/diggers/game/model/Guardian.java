package com.diggers.game.model;

public class Guardian extends Unit{
    public Guardian(Field field, Player player) {
        super(TYPE_GUARDIAN, field, SPEED_GUARDIAN, player);
    }

    @Override
    public boolean isCanMove(Field field) {
        return super.isCanMove(field) &&
                field.getType() == Field.TYPE_TUNNEL &&
                field.getType() == Field.TYPE_BASE;
    }
}
