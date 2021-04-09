package com.diggers.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.diggers.game.units.Platform;
import com.diggers.game.units.Unit;
import com.diggers.game.units.UnitPhysic;

import java.util.ArrayList;

public class DiggersGame extends ApplicationAdapter {
	SpriteBatch batch;
	Unit unit;
	ArrayList<Platform> platforms = new ArrayList<>();

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		unit = new Unit("hero.png", 200, 300);
		platforms.add(new Platform("water.jpg", 100, 100));
		platforms.add(new Platform("water.jpg", 300, 50));
		platforms.add(new Platform("water.jpg", 500, 200));
		platforms.add(new Platform("water.jpg", 100, 450));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (Gdx.input.isKeyPressed(Input.Keys.W)){
			unit.unitPhysic.jump();
		}

		if (Gdx.input.isKeyPressed(Input.Keys.D)){
			unit.unitPhysic.moveRight();
		}else{
			if (Gdx.input.isKeyPressed(Input.Keys.A)){
				unit.unitPhysic.moveLeft();
			}else{
				unit.unitPhysic.noMove();
			}
		}

		unit.update(Gdx.graphics.getDeltaTime(), platforms);

		batch.begin();
		unit.draw(batch, Gdx.graphics.getDeltaTime());
		for (Platform platform: platforms){
			platform.draw(batch, Gdx.graphics.getDeltaTime());
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
