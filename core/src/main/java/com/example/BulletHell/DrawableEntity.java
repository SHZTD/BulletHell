package com.example.BulletHell;

import com.badlogic.gdx.graphics.Texture;

class DrawableEntity {
    Texture texture;
    float x, y;
    int layer;

    public DrawableEntity(Texture texture, float x, float y, int layer) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.layer = layer;
    }

    public void move(float dx, float dy) {
        this.x += dx;
        this.y += dy;
    }
}
