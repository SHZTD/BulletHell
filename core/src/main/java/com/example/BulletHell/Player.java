package com.example.BulletHell;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Player extends GameEntity {
    static final float BASE_MOVEMENT = 20f;

    AssetManager manager;
    ButtonLayout joypad;

    Texture currentFrame;

    public void setJoypad(ButtonLayout joypad) {
        this.joypad = joypad;
    }

    public Player(AssetManager manager) {
        this.manager = manager;
        currentFrame = manager.get("ship/2.png");
    }

    @Override
    public void act(float delta) {
        if (joypad.isPressed("Right")) {
            speed.x += BASE_MOVEMENT;
            currentFrame = manager.get("ship/1.png", Texture.class);
            if (isOutOfBounds(0, 480, 0, 800)) speed.x = 0;
            System.out.println("moved right");
        }

        if (joypad.isPressed("Left")) {
            speed.x -= BASE_MOVEMENT;
            currentFrame = manager.get("ship/2.png", Texture.class);
            if (isOutOfBounds(0, 480 - currentFrame.getWidth(), 0, 800)) speed.x = 0;
            System.out.println("moved left");
        }

        if (joypad.isPressed("Up")) {
            speed.y += BASE_MOVEMENT;
            currentFrame = manager.get("ship/3.png", Texture.class);
            if (isOutOfBounds(0, 480 - currentFrame.getHeight(), 0, 800)) speed.y = 0;
            System.out.println("moved up");
        }

        if (joypad.isPressed("Down")) {
            speed.y -= BASE_MOVEMENT;
            currentFrame = manager.get("ship/4.png", Texture.class);
            if (isOutOfBounds(0, 480, 0, 800)) speed.y = 0;
            System.out.println("moved dwn");
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(currentFrame, getX() + speed.x, getY() + speed.y);
    }
}
