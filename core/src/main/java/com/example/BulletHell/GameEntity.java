package com.example.BulletHell;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameEntity extends Actor
{

    public Vector2 speed;

    // x & y struct
    public GameEntity()
    {
        speed = new Vector2(0,0);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        moveBy(delta*speed.x, delta* speed.y);
    }

    public boolean isOutOfBounds(float minX, float maxX, float minY, float maxY) {
        return speed.x < minX || speed.x > maxX || speed.y < minY || speed.y > maxY;
    }

    public Vector2 getSpeed() {
        return speed;
    }

}
