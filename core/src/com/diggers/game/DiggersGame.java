package com.diggers.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.diggers.game.units.Platform;
import com.diggers.game.units.Unit;
import com.diggers.game.units.UnitPhysic;

import java.awt.Rectangle;
import java.util.ArrayList;

public class DiggersGame extends ApplicationAdapter {
	SpriteBatch batch;
	//Unit unit;
	ArrayList<Platform> platforms = new ArrayList<>();

	Unit bob11;

	Sprite stena;
	//Sprite play;

	int p = 0;

	@Override
	public void create () {
		batch = new SpriteBatch();

		bob11 = new Unit("bob11.png", 300, 300); // картинка для анимации
		stena = new Sprite(new Texture("stena.png"));

		//play = new Sprite(new Texture("play.png"));

		//unit = new Unit("bob11.png", 200, 300);


		for(int i = 0; i < 20; i++){
			platforms.add(new Platform("platforma.png", p, 0));
			p += 50;
		}

		//platforms.add(new Platform("platforma.png", 0, 0));

		//platforms.add(new Platform("water.jpg", 300, 50));
		//platforms.add(new Platform("water.jpg", 500, 200));
		//platforms.add(new Platform("water.jpg", 100, 450));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



		if (Gdx.input.isKeyPressed(Input.Keys.W)){
			bob11.jump(Gdx.graphics.getDeltaTime());
		}



		if (Gdx.input.isKeyPressed(Input.Keys.D)){
			bob11.goRight(Gdx.graphics.getDeltaTime());
		}else{
			if (Gdx.input.isKeyPressed(Input.Keys.A)){
				bob11.goLeft(Gdx.graphics.getDeltaTime());
			}else{
				if (Gdx.input.isKeyPressed(Input.Keys.S)){
					bob11.sit(Gdx.graphics.getDeltaTime());
				}else {
					bob11.oMove(Gdx.graphics.getDeltaTime());
				}

			}
		}

		//unit.update(Gdx.graphics.getDeltaTime(), platforms);
		bob11.update(Gdx.graphics.getDeltaTime(), platforms);

		batch.begin();
		//play.draw(batch);



		for (int i = 0; i < Gdx.graphics.getWidth() / stena.getWidth() + 1; i++){
			for(int j = 0; j < Gdx.graphics.getHeight() / stena.getHeight() + 1; j++){
				stena.setX(i * stena.getWidth());
				stena.setY(j * stena.getHeight());
				stena.draw(batch);
			}
		}


		//unit.draw(batch, Gdx.graphics.getDeltaTime());


		for (Platform platform: platforms){
			platform.draw(batch, Gdx.graphics.getDeltaTime());
		}

		bob11.draw(batch);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
