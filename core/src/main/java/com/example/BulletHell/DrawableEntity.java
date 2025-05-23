package com.example.BulletHell;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

class DrawableEntity {
    Texture texture;
    float x, y;
    float width, height;
    float originX, originY; // For rotation center
    float rotation; // For bullet rotation

    // overload
    public DrawableEntity(Texture texture, float x, float y) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
    }

    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture,
            x - originX, y - originY,
            originX, originY,
            width, height,
            1, 1,
            rotation,
            0, 0,
            texture.getWidth(), texture.getHeight(),
            false, false
        );
    }

    public DrawableEntity(Texture texture) {
        this.texture = texture;
    }

    public void move(float dx, float dy) {
        this.x += dx;
        this.y += dy;
    }

    public float getX() { return x; }
    public float getY() { return y; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public Texture getTexture() { return texture; }
    public float getRotation() { return rotation; }
    public void setRotation(float rotation) { this.rotation = rotation; }
}
