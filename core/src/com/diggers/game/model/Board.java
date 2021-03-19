package com.diggers.game.model;

public class Board {
    public Field[][] fields;

    public Board(int sizeX, int sizeY) {
        fields = new Field[sizeY][sizeX];
        for (int i = 0; i < sizeY; i++){
            for(int j = 0; j < sizeX; j++){
                fields[i][j] = new Field(Field.TYPE_SOIL , j, i);
            }
        }
    }

    public static Board getRandomBoard(int sizeX, int sizeY){
        Board board = new Board(sizeX, sizeY);
        for (int i = 0; i < sizeY; i++){
            for(int j = 0; j < sizeX; j++){
                board.fields[i][j].setType(Field.getRandomType());
            }
        }
        return board;
    }
}
