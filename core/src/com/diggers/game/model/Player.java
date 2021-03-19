package com.diggers.game.model;

import java.util.HashMap;

public class Player {
    public String name;
    public HashMap<Integer, Integer> resources;

    public Player(String name, HashMap<Integer, Integer> resources) {
        this.name = name;
        this.resources = resources;
    }

    public Player(String name){
        this(name, new HashMap<Integer, Integer>());
    }

    public void calculateResource(int resource, int value) throws Exception {
        if (resources.containsKey(resource)){
            if (resources.get(resource) + value >= 0){
                resources.put(resource, resources.get(resource) + value);
            }else{
                throw new Exception("Недостаточно");
            }
        }
    }


}
