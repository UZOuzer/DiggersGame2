package com.diggers.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.diggers.game.model.Game;
import com.diggers.game.units.Platform;
import com.diggers.game.units.Pula;
import com.diggers.game.units.Unit;
import com.diggers.game.units.UnitPhysic;

import java.awt.Rectangle;
import java.util.ArrayList;

public class DiggersGame extends ApplicationAdapter {
	SpriteBatch batch;
	//Unit unit;
	ArrayList<Platform> platforms = new ArrayList<>();
	ArrayList<Pula> pulas = new ArrayList<>();

	float curFrameTimer = 0;
	float deltatimepul = 12f;
	float FRAME_DURATION = 0.2f;

	Unit bob11;

	Sprite stena;

	Sprite snarayd; // снаряд


	//Sprite play;

	int p = 0;

	@Override
	public void create () {
		batch = new SpriteBatch();

		bob11 = new Unit("bob11.png", 300, 300); // картинка для анимации
		stena = new Sprite(new Texture("stena.png"));
		snarayd = new Sprite(new Texture(("snarayd.bmp")));

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

		curFrameTimer += Gdx.graphics.getDeltaTime();

		if (Gdx.input.isTouched()){
			if(Gdx.input.isKeyPressed(Input.Keys.E)){
				if(curFrameTimer >= FRAME_DURATION){
					pulas.add(new Pula("pulka1.png", bob11.getX() + 70, bob11.getY() + 51, 1, 0));
					curFrameTimer = 0;
				}
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.Q)){
				if(curFrameTimer >= FRAME_DURATION){
					pulas.add(new Pula("pulka1.png", bob11.getX() + 10, bob11.getY() + 51, -1, 0));
					curFrameTimer = 0;
				}
			}
		}



		if (Gdx.input.isKeyPressed(Input.Keys.D)){
			bob11.goRight(Gdx.graphics.getDeltaTime());
		}else{
			if (Gdx.input.isKeyPressed(Input.Keys.A)){
				bob11.goLeft(Gdx.graphics.getDeltaTime());
			}else{
				if (Gdx.input.isKeyPressed(Input.Keys.S)){
					bob11.oMove(Gdx.graphics.getDeltaTime());
					bob11.sit(Gdx.graphics.getDeltaTime());
				}
				else {
					if(Gdx.input.isKeyPressed(Input.Keys.E)){
						bob11.oMove(Gdx.graphics.getDeltaTime());
						bob11.rightAim(Gdx.graphics.getDeltaTime());
					}

					else if (Gdx.input.isKeyPressed(Input.Keys.Q)){
						bob11.oMove(Gdx.graphics.getDeltaTime());
						bob11.leftAim(Gdx.graphics.getDeltaTime());
					}
					else {
						bob11.oMove(Gdx.graphics.getDeltaTime());
					}

				}

			}
		}



		//unit.update(Gdx.graphics.getDeltaTime(), platforms);
		bob11.update(Gdx.graphics.getDeltaTime(), platforms);

		for (Pula pula: pulas){
			pula.updata(Gdx.graphics.getDeltaTime());
		}

		snarayd.setX(200);
		snarayd.setY(200);

		batch.begin();
		//play.draw(batch);



		for (int i = 0; i < Gdx.graphics.getWidth() / stena.getWidth() + 1; i++){
			for(int j = 0; j < Gdx.graphics.getHeight() / stena.getHeight() + 1; j++){
				stena.setX(i * stena.getWidth());
				stena.setY(j * stena.getHeight());
				stena.draw(batch);
			}
		}

		//snarayd.draw(batch);


		//unit.draw(batch, Gdx.graphics.getDeltaTime());


		for (Platform platform: platforms){
			platform.draw(batch, Gdx.graphics.getDeltaTime());
		}

		bob11.draw(batch);
		for (Pula pula: pulas) pula.draw(batch, Gdx.graphics.getDeltaTime());
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
