package com.diggers.game.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    public static final int RESOURCE_TYPE_GOLD = 0;
    public static final int RESOURCE_TYPE_DIAMOND = 1;
    public static final int RESOURCE_TYPE_OIL = 2;

    public static final int RESOURCE_START_COUNT_GOLD = 100;
    public static final int RESOURCE_START_COUNT_DIAMOND = 0;
    public static final int RESOURCE_START_COUNT_OIL = 0;

    protected ArrayList<Player> players;
    protected Board board;
    protected Player currentPlayer;

    private int roundNumber;

    public Game(ArrayList<Player> players, int boardSize) {
        this.players = new ArrayList<Player>();
        this.board = Board.getRandomBoard(boardSize, boardSize);
        for (Player player: players){
            this.addPlayer(player);
        }
        this.currentPlayer = players.get(0);
        this.roundNumber = 0;
    }

    public void nextRound(){
        roundNumber++;
        currentPlayer = players.get(roundNumber % players.size());
        for (Field[] fields: board.fields){
            for(Field field: fields){
                field.update();
            }
        }
    }

    private boolean isTheirTurn(Player player){
        return player.equals(currentPlayer);
    }

    public void addUnit(int unitType, Field field, Player player) throws Exception {
        if (!isTheirTurn(player)) throw new Exception("Чужой ход");
        Unit unit = UnitFactory.createByType(unitType, field, player);
        field.addUnit(unit);
    }

    public void buyUnitAndAdd(int unitType, Field field, Player player) throws Exception {
        if (!isTheirTurn(player)) throw new Exception("Чужой ход");
        player.calculateResource(RESOURCE_TYPE_GOLD, Unit.getCost(unitType));
        addUnit(unitType, field, player);
    }

    public void moveUnit(Unit unit, Field targetField) throws Exception {
        if (!isTheirTurn(unit.player)) throw new Exception("Чужой ход");
        unit.move(targetField);
    }

    private void addPlayer(Player player){
        HashMap<Integer, Integer> resources = new HashMap<>();

        resources.put(RESOURCE_TYPE_GOLD, RESOURCE_START_COUNT_GOLD);
        resources.put(RESOURCE_TYPE_DIAMOND, RESOURCE_START_COUNT_DIAMOND);
        resources.put(RESOURCE_TYPE_OIL, RESOURCE_START_COUNT_OIL);

        player.resources = resources;
        this.players.add(player);
    }


}
