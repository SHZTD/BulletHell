package com.example.BulletHell;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

public class Player extends GameEntity {
    static final float BASE_MOVEMENT = 5f;

    AssetManager manager;
    ButtonLayout joypad;

    Texture currentFrame;

    // vida
    private int maxHealth = 10;
    private int currentHealth;
    private float invulnerabilityTimer = 0;
    private final float invulnerabilityDuration = 1.5f;
    private boolean isInvulnerable = false;

    private boolean isPlayerDead = false;

    private Circle hitbox;
    private float hitboxRadius = 20f;

    private final float startX = 240f, startY = 50f;

    public void setJoypad(ButtonLayout joypad) {
        this.joypad = joypad;
    }

    public Player(AssetManager manager) {
        this.manager = manager;
        this.currentFrame = manager.get("ship/1.png");
        this.currentHealth = maxHealth;
        this.hitbox = new Circle();
        setInitialPosition();
    }

    private void setInitialPosition() {
        this.setPosition(startX, startY);
        updateHitboxPosition();
    }

    private void updateHitboxPosition() {
        hitbox.set(speed.x, speed.y, hitboxRadius);
    }

    @Override
    public void act(float delta) {
        if (isInvulnerable) {
            invulnerabilityTimer -= delta;
            if (invulnerabilityTimer <= 0) {
                isInvulnerable = false;
            }
        }

        if (joypad.isPressed("Right")) {
            speed.x += BASE_MOVEMENT;
            currentFrame = manager.get("ship/1.png", Texture.class);
            if (isOutOfBounds(-startX, 480 - (startX + currentFrame.getWidth()), -startY, 800 - (startY + currentFrame.getWidth()))) speed.x -= BASE_MOVEMENT;
            System.out.println("moved right");
        }

        if (joypad.isPressed("Left")) {
            speed.x -= BASE_MOVEMENT;
            currentFrame = manager.get("ship/2.png", Texture.class);
            if (isOutOfBounds(-startX, 480 - (startX + currentFrame.getWidth()), -startY, 800 - (startY + currentFrame.getWidth()))) speed.x += BASE_MOVEMENT;
            System.out.println("moved left");
        }

        if (joypad.isPressed("Up")) {
            speed.y += BASE_MOVEMENT;
            currentFrame = manager.get("ship/3.png", Texture.class);
            if (isOutOfBounds(-startX, 480, -startY, 800 - (startY + currentFrame.getWidth()))) speed.y -= BASE_MOVEMENT;
            System.out.println("moved up");
        }

        if (joypad.isPressed("Down")) {
            speed.y -= BASE_MOVEMENT;
            currentFrame = manager.get("ship/4.png", Texture.class);
            if (isOutOfBounds(-startX, 480, -startY, 800 - (startY + currentFrame.getWidth()))) speed.y += BASE_MOVEMENT;
            System.out.println("moved dwn");
        }

        hitbox.set(
            getX() + speed.x + getWidth()/2f,
            getY() + speed.y + getHeight()/2f,
            hitboxRadius
        );
    }

    public boolean checkCollision(Bullet bullet) {
        if (isInvulnerable) return false;

        if (Intersector.overlaps(hitbox, bullet.getCollisionCircle())) {
            //System.out.println("COLLISION DETECTED!");
            takeDamage(1);
            return true;
        }
        return false;
    }


    public void takeDamage(int damage) {
        if (!isInvulnerable) {
            currentHealth -= damage;
            isInvulnerable = true;
            invulnerabilityTimer = invulnerabilityDuration;

            if (currentHealth <= 0) {
                die();
            }
        }
    }

    private boolean die() {
        return isPlayerDead = true;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isInvulnerable) {
            float flash = (int)(invulnerabilityTimer * 10) % 2; // Blink effect
            if (flash == 1) {
                batch.setColor(1, 0, 0, 0.5f); // Semi-transparent
            }
        }

        super.draw(batch, parentAlpha);
        batch.draw(currentFrame, getX() + speed.x, getY() + speed.y);
    }

    public int getHealth() { return currentHealth; }
    public int getMaxHealth() { return maxHealth; }
    public boolean isInvulnerable() { return isInvulnerable; }
}
