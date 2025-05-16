package com.example.BulletHell;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Player extends GameEntity {
    static final float BASE_MOVEMENT = 200f;

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
        }

        if (joypad.isPressed("Left")) {
            speed.x -= BASE_MOVEMENT;
        }

        if (joypad.isPressed("Up")) {
            speed.x += BASE_MOVEMENT;
        }

        if (joypad.isPressed("Down")) {
            speed.y -= BASE_MOVEMENT;
        }
    }
}
