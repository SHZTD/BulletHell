package com.example.BulletHell;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainMenuScreen implements Screen {
    BulletHell game;
    private SpriteBatch batch;
    private Texture image;

    public MainMenuScreen(BulletHell game) {
        this.game = game;
        this.batch = game.spriteBatch;
        this.image = game.manager.get("first_screen/bullet_hell.png", Texture.class);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        TextureRegion tx = new TextureRegion();
        tx.flip(false, true);
        batch.draw(image, 0, 0, 480, 800);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
