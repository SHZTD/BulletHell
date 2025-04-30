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
    private Texture image;

    public MainMenuScreen(BulletHell game) {
        this.game = game;
        this.image = game.manager.get("first_screen/bullet_hell.png", Texture.class);
    }

    @Override
    public void show() {
        // Set the camera's projection mode
        game.camera.setToOrtho(false, 480, 800); // This could be any resolution you want
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        // Update the camera
        game.camera.update();

        game.spriteBatch.setProjectionMatrix(game.camera.combined);

        float scaleWidth = game.camera.viewportWidth / image.getWidth();
        float scaleHeight = game.camera.viewportHeight / image.getHeight();

        float scale = Math.min(scaleWidth, scaleHeight);

        float scaledWidth = image.getWidth() * scale;
        float scaledHeight = image.getHeight() * scale;

        game.spriteBatch.begin();
        game.spriteBatch.draw(image, 0, 0, scaledWidth, scaledHeight);
        game.spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        game.camera.viewportWidth = width;
        game.camera.viewportHeight = height;
        game.camera.update();
    }

    @Override
    public void hide() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        image.dispose();
    }
}
