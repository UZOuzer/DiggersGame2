package com.diggers.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.diggers.game.model.Game;

import java.util.ArrayList;

public class DiggersGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Hero hero;
	ArrayList<Platform> platforms = new ArrayList<>();

	class Platform{
		float posX, posY;

		Texture texture;
		Sprite sprite;

		public Platform(Texture texture, float posX, float posY) {
			sprite = new Sprite(texture);
			this.setPosX(posX);
			this.setPosY(posY);
			this.texture = texture;
		}

		public void setPosX(float posX) {
			this.posX = posX;
			this.sprite.setX(posX);
		}

		public void setPosY(float posY) {
			this.posY = posY;
			this.sprite.setY(posY);
		}

		public void draw(SpriteBatch batch){
			batch.draw(sprite, posX, posY);
		}

		public boolean isIntersect(Sprite sprite){
			return this.sprite.getBoundingRectangle().overlaps(sprite.getBoundingRectangle());
		}
	}

	class Hero{

		boolean isOnGround = true;

		int tileSize = 100;
		int currentFrame = 0;
		int currentCondition = 0;
		float currentFrameTime = 0;

		int condition = 0;

		float dx = 0;
		float dy = 0;

		float posX, posY;

		Texture texture;
		Sprite sprite;


		public Hero(Texture texture, float posX, float posY) {
			this.texture = texture;
			this.sprite = new Sprite(
					this.texture,
					currentFrame * tileSize, currentCondition * tileSize,
					tileSize, tileSize);

			this.setPosX(posX);
			this.setPosY(posY);
		}

		public void draw(SpriteBatch batch, float deltaTime){
			currentFrameTime += deltaTime;
			batch.draw(sprite, posX, posY);
			nextFrame(deltaTime);
		}

		public void update(float deltaTime){
			dy = Math.max(-500, dy - 500 * deltaTime);
			setPosY(Math.max(0, posY + dy * deltaTime));
			setPosX(posX + dx * deltaTime);
		}

		public void nextFrame(float deltaTime){
			if (currentFrameTime < 0.1) return;
			currentFrameTime = 0;
			currentFrame = (currentFrame + 1) % getFramesCount();
			sprite = new Sprite(
					this.texture,
					currentFrame * tileSize, currentCondition * tileSize,
					tileSize, tileSize);
		}

		protected int getFramesCount(){
			return this.texture.getWidth() / tileSize;
		}

		public void jump(){
			if (isOnGround){
				dy = 500;
				currentFrame = 0;
				currentCondition = 2;
				isOnGround = false;
			}
		}

		public void goLeft(){
			if (dx != -200){
				currentFrame = 0;
				currentCondition = 1;
			}
			dx = -200;
		}

		public void goRight(){
			if (dx != 200){
				currentFrame = 0;
				currentCondition = 0;
			}
			dx = 200;
		}

		public void stay(){
			dx = 0;
			//isOnGround = true;
			//dy = 0;
		}


		public void setPosX(float posX) {
			this.sprite.setX(posX);
			this.posX = posX;
		}

		public void setPosY(float posY) {
			this.sprite.setY(posY);
			this.posY = posY;
		}
	}
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		hero = new Hero(new Texture("Hero.png"), 0, 0);
		platforms.add(new Platform(new Texture("platform.png"), 300, 100));
		platforms.add(new Platform(new Texture("platform.png"), 400, 100));
		platforms.add(new Platform(new Texture("platform.png"), 400, 200));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			hero.goRight();
		}else{
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
				hero.goLeft();
			}else{
				hero.stay();
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)){
			hero.jump();
		}

		for (Platform platform: platforms){
			if (platform.isIntersect(hero.sprite)){
				hero.dy = 0;
			}
		}

		hero.update(Gdx.graphics.getDeltaTime());

		batch.begin();
		hero.draw(batch, Gdx.graphics.getDeltaTime());
		for (Platform platform: platforms){
			platform.draw(batch);
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
