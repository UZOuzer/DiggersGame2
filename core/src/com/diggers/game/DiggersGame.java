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
	Unit unit;
	ArrayList<Platform> platforms = new ArrayList<>();

	AnimatedSprite h;

	Sprite stena;

	int p = 0;

	class AnimatedSprite{
		float FRAME_DURATION = 0.2f;

		Texture texture;
		Rectangle curFrameRect;
		int frameWidth = 100;
		int frameHeight = 100;
		float curFrameTimer = 0;

		public AnimatedSprite(Texture texture, Rectangle curFrameRect) {
			this.texture = texture;
			this.curFrameRect = curFrameRect;
		}

		void update(float deltaTime){
			curFrameTimer += deltaTime;
			if (curFrameTimer >= FRAME_DURATION){
				curFrameRect = new Rectangle((curFrameRect.x + frameWidth) % (5 * frameWidth), curFrameRect.y, curFrameRect.width, curFrameRect.height);
				curFrameTimer = 0;
			}
		}

		void draw(SpriteBatch batch){
			Sprite sprite = new Sprite(texture, (int) curFrameRect.x, (int) curFrameRect.y, (int) curFrameRect.width, (int) curFrameRect.height);
			batch.draw(sprite, 0, 0);
		}
	}

	@Override
	public void create () {
		batch = new SpriteBatch();

		h = new AnimatedSprite(new Texture("g.png"), new Rectangle(0, 0, 80, 100));
		stena = new Sprite(new Texture("stena.png"));
		unit = new Unit("geroipravo.png", 200, 300);


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
			unit.unitPhysic.jump();
		}

		if (Gdx.input.isKeyPressed(Input.Keys.D)){
			unit.unitPhysic.moveRight();
		}else{
			if (Gdx.input.isKeyPressed(Input.Keys.A)){
				unit.goLeft(Gdx.graphics.getDeltaTime());
			}else{
				unit.unitPhysic.noMove();
			}
		}

		unit.update(Gdx.graphics.getDeltaTime(), platforms);
		h.update(Gdx.graphics.getDeltaTime());
		batch.begin();
		h.draw(batch);

//		for (int i = 0; i < Gdx.graphics.getWidth() / stena.getWidth() + 1; i++){
//			for(int j = 0; j < Gdx.graphics.getHeight() / stena.getHeight() + 1; j++){
//				stena.setX(i * stena.getWidth());
//				stena.setY(j * stena.getHeight());
//				stena.draw(batch);
//			}
//		}


//		unit.draw(batch, Gdx.graphics.getDeltaTime());
//
//
//		for (Platform platform: platforms){
//			platform.draw(batch, Gdx.graphics.getDeltaTime());
//		}

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
