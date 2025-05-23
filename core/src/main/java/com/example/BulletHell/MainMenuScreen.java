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
    ButtonLayout mainMenu;

    public MainMenuScreen(BulletHell game) {
        this.game = game;
        mainMenu = new ButtonLayout(game.camera, game.manager, game.bf);
        mainMenu.loadFromJson("mainmenu.json");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        // Update the camera
        game.camera.update();
        game.spriteBatch.setProjectionMatrix(game.camera.combined);

        // dibuja el fondo
        game.spriteBatch.begin();
        game.spriteBatch.draw(game.manager.get("first_screen/bullet_hell.png", Texture.class), 0, 0, 480, 800, 0,0, 1024, 1536, false, false);
        game.spriteBatch.end();

        mainMenu.render(game.spriteBatch, game.textBatch);

        if(mainMenu.consumeRelease("Start")) {
            game.setScreen(new GameScreen(game));
            this.dispose();
        }
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
    }
}
