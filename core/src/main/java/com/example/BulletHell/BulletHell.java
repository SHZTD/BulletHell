package com.example.BulletHell;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BulletHell extends Game {
    public OrthographicCamera camera;
    public SpriteBatch spriteBatch;
    public AssetManager manager;
    public BitmapFont bf;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 800);
        manager = new AssetManager();
        bf = new BitmapFont(); // fuente
        setScreen(new AssetLoader(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        spriteBatch.dispose();
        manager.dispose();
    }
}
