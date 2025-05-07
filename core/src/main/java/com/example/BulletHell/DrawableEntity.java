package com.example.BulletHell;

import com.badlogic.gdx.graphics.Texture;

class DrawableEntity {
    Texture texture;
    float x, y;

    // overload
    public DrawableEntity(Texture texture, float x, float y) {
        this.texture = texture;
        this.x = x;
        this.y = y;
    }

    public DrawableEntity(Texture texture) {
        this.texture = texture;
    }

    public void move(float dx, float dy) {
        this.x += dx;
        this.y += dy;
    }

    public void setPos(float dx, float dy) {
        this.x = dx;
        this.y = dy;
    }
}
