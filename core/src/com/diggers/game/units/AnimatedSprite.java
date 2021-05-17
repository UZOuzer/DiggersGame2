package com.diggers.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.Rectangle;
import java.util.ArrayList;

public 	class AnimatedSprite{
    float FRAME_DURATION = 0.1f;

    Texture texture;
    Rectangle curFrameRect;
    int frameWidth = 100;
    int frameHeight = 100;
    int curFrameX = 0, curFrameY = 4; // curFrameY - номер линейки
    float curFrameTimer = 0;

    float posX, posY;

    ArrayList<ArrayList<Rectangle>> frames = new ArrayList<>();

    public AnimatedSprite(Texture texture, Rectangle curFrameRect) {
        this.texture = texture;
        this.curFrameRect = curFrameRect;

//			for (int y = 0; y < 11; y++){
//				frames.add(new ArrayList<Rectangle>());
//				for (int x = 0; x < 8; x++){
//					frames.get(y).add(new Rectangle(x * frameWidth, y * frameHeight, frameWidth, frameHeight));
//				}
//			}

        // 0 - стоять на месте
        frames.add(new ArrayList<Rectangle>());
        for (int i = 0; i < 5; i++){
            frames.get(0).add(new Rectangle(i * frameWidth, 0, frameWidth, frameHeight));
        }
        // 1 - стрельба влево
        frames.add(new ArrayList<Rectangle>());
        for (int i = 3; i < 5; i++){
            frames.get(1).add(new Rectangle(i * frameWidth, frameHeight, frameWidth, frameHeight));
        }
        // 2 - стрельба вправо
        frames.add(new ArrayList<Rectangle>());
        for (int i = 3; i < 5; i++){
            frames.get(2).add(new Rectangle(i * frameWidth, frameHeight * 2, frameWidth, frameHeight));
        }
        // 3 - бег влево
        frames.add(new ArrayList<Rectangle>());
        for (int i = 0; i < 10; i++){
            frames.get(3).add(new Rectangle(i * frameWidth, frameHeight * 3, frameWidth, frameHeight));
        }
        // 4 - бег вправо
        frames.add(new ArrayList<Rectangle>());
        for (int i = 0; i < 10; i++){
            frames.get(4).add(new Rectangle(i * frameWidth, frameHeight * 4, frameWidth, frameHeight));
        }
        // 5 - вверх влево
        frames.add(new ArrayList<Rectangle>());
        for (int i = 0; i < 6; i++){
            frames.get(5).add(new Rectangle(i * frameWidth, frameHeight * 5, frameWidth, frameHeight));
        }
        // 6 - вверх вправо
        frames.add(new ArrayList<Rectangle>());
        for (int i = 0; i < 4; i++){
            frames.get(6).add(new Rectangle(i * frameWidth, frameHeight * 6, frameWidth, frameHeight));
        }
        // 7 - стойка влево
        frames.add(new ArrayList<Rectangle>());
        for (int i = 0; i < 6; i++){
            frames.get(7).add(new Rectangle(i * frameWidth, frameHeight * 7, frameWidth, frameHeight));
        }
        // 8 - стойка влево
        frames.add(new ArrayList<Rectangle>());
        for (int i = 0; i < 6; i++){
            frames.get(8).add(new Rectangle(i * frameWidth, frameHeight * 8, frameWidth, frameHeight));
        }
        // 9 - стойка вправо
        frames.add(new ArrayList<Rectangle>());
        for (int i = 0; i < 6; i++){
            frames.get(9).add(new Rectangle(i * frameWidth, frameHeight * 9, frameWidth, frameHeight));
        }
        // 10 - стойка вниз влево
        frames.add(new ArrayList<Rectangle>());
        for (int i = 0; i < 6; i++){
            frames.get(10).add(new Rectangle(i * frameWidth, frameHeight * 10, frameWidth, frameHeight));
        }
        // 11 - стойка вниз вправо
        frames.add(new ArrayList<Rectangle>());
        for (int i = 0; i < 6; i++){
            frames.get(11).add(new Rectangle(i * frameWidth, frameHeight * 11, frameWidth, frameHeight));
        }
        // 12 - сидя влево
        frames.add(new ArrayList<Rectangle>());
        for (int i = 0; i < 1; i++){
            frames.get(12).add(new Rectangle(i * frameWidth, frameHeight * 12, frameWidth, frameHeight));
        }



    }

    void update(float deltaTime){
        curFrameTimer += deltaTime;
        if (curFrameTimer >= FRAME_DURATION) {
            curFrameX = (curFrameX + 1) % frames.get(curFrameY).size();
            curFrameRect = frames.get(curFrameY).get(curFrameX);
            curFrameTimer = 0;
        }
    }

    Sprite getCurFrame(){
        return new Sprite(texture, (int) curFrameRect.x,
                (int) curFrameRect.y, (int) curFrameRect.width, (int) curFrameRect.height);
    }

    void draw(SpriteBatch batch){
        batch.draw(getCurFrame(), posX, posY);
    }
}
