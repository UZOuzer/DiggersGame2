package com.diggers.game.model;

public class Transport extends Unit{
    public static final int MAX_RESOURCE_COUNT = 10;

    private int resourceCount;

    public Transport(Field field, Player player) {
        super(TYPE_TRANSPORT, field, SPEED_TRANSPORT, player);
        resourceCount = 0;
    }

    @Override
    public boolean isCanMove(Field field) {
        return super.isCanMove(field) &&
                field.getType() == Field.TYPE_TUNNEL &&
                field.getType() == Field.TYPE_BASE &&
                field.getType() == Field.TYPE_DIAMOND;
    }

    @Override
    public void updateField() {
        if (position.getType() == Field.TYPE_DIAMOND){
            resourceCount = Math.min(MAX_RESOURCE_COUNT - resourceCount, position.getHealth());
            position.setHealth(position.getHealth() - resourceCount);
        }
    }
}
