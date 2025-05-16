package com.example.BulletHell;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class BulletHell extends Game {
    public OrthographicCamera camera;
    public SpriteBatch spriteBatch, textBatch;
    public ShapeRenderer shapeRenderer; // ???
    public AssetManager manager;
    public BitmapFont bf;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        textBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 800);
        manager = new AssetManager();
        bf = new BitmapFont(); // fuente

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        setScreen(new AssetLoader(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        spriteBatch.dispose();
        manager.dispose();
    }
}
