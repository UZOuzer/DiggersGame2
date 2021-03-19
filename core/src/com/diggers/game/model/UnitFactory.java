package com.diggers.game.model;

public class UnitFactory {
    public static Unit createByType(int type, Field field, Player player){
        if (type == Unit.TYPE_WORKER){
            return new Worker(field, player);
        }
        if (type == Unit.TYPE_GUARDIAN){
            return new Guardian(field, player);
        }
        if (type == Unit.TYPE_KAMIKAZE){
            return new Kamikaze(field, player);
        }
        return new Transport(field, player);
    }
}
