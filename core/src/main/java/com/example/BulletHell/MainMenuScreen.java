package com.example.BulletHell;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainMenuScreen implements Screen {
    BulletHell game;
    private Texture image;

    public MainMenuScreen(BulletHell game) {
        this.game = game;
        this.image = game.manager.get("first_screen/bullet_hell.png", Texture.class);
        //this.image = game.manager.get("first_screen/planet.png", Texture.class);
    }

    DrawableEntity player;
    List<DrawableEntity> entities = new ArrayList<>();
    @Override
    public void show() {
        player = new DrawableEntity(game.manager.get("first_screen/planet.png", Texture.class), 0, 0, 2);
        entities.add(player);
    }



    @Override
    public void render(float delta) {
        // Clear the screen
        ScreenUtils.clear(Color.BLACK);

        // Update the camera
        game.camera.update();

        game.spriteBatch.setProjectionMatrix(game.camera.combined);

        float scaleWidth = game.camera.viewportWidth / image.getWidth();
        float scaleHeight = game.camera.viewportHeight / image.getHeight();

        float scale = Math.min(scaleWidth, scaleHeight);

        float scaledWidth = image.getWidth() * scale;
        float scaledHeight = image.getHeight() * scale;

        player.move(delta * -20, 0);

        game.spriteBatch.begin();
        game.spriteBatch.draw(image, 0, 0, scaledWidth, scaledHeight);
        for (DrawableEntity e : entities) {
            game.spriteBatch.draw(e.texture, e.x, e.y);
        }
        game.spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        /*
        game.camera.viewportWidth = width;
        game.camera.viewportHeight = height;
        game.camera.update();
         */
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
