package com.example.BulletHell;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
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

    }

    // variables generales
    DrawableEntity player, button1, playButton;
    List<DrawableEntity> entities = new ArrayList<>();

    @Override
    public void show() {
        player = new DrawableEntity(game.manager.get("first_screen/planet.png"));
        button1 = new DrawableEntity(game.manager.get("joystick/d.png"));
        playButton = new DrawableEntity(game.manager.get("first_screen/play.png"));
        entities.add(button1);
        entities.add(player);
        entities.add(playButton);
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        ScreenUtils.clear(Color.BLACK);

        // Update the camera
        game.camera.update();

        // para el render + variables
        float camViewportW = game.camera.viewportWidth;
        float camViewportH = game.camera.viewportHeight;

        float scaleWidth = camViewportW / image.getWidth();
        float scaleHeight = camViewportH / image.getHeight();

        float scale = Math.min(scaleWidth, scaleHeight);

        float scaledWidth = image.getWidth() * scale;
        float scaledHeight = image.getHeight() * scale;

        game.spriteBatch.setProjectionMatrix(game.camera.combined);

        playButton.setPos(-117, 32);
        player.move(delta * -20, 0);


        game.spriteBatch.begin();
        game.spriteBatch.draw(image, 0, camViewportH / 32, scaledWidth, scaledHeight);
        // dibuja todos los assets
        for (DrawableEntity e : entities) {
            game.spriteBatch.draw(e.texture, e.x, e.y);
        }
        game.spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {}

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
