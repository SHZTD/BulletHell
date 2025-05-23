package com.example.BulletHell;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class WinScreen implements Screen {
    BulletHell game;
    ButtonLayout winMenu;

    public WinScreen(BulletHell game) {
        this.game = game;
        winMenu = new ButtonLayout(game.camera, game.manager, game.bf);
        winMenu.loadFromJson("endmenu.json");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        game.camera.update();
        game.spriteBatch.setProjectionMatrix(game.camera.combined);

        // dibuja el fondo
        game.spriteBatch.begin();
        game.spriteBatch.draw(game.manager.get("first_screen/you_win.png", Texture.class), 0, 0, 480, 800, 0,0, 1024, 1536, false, false);
        game.spriteBatch.end();

        winMenu.render(game.spriteBatch, game.textBatch);

        if(winMenu.consumeRelease("Retry")) {
            game.setScreen(new MainMenuScreen(game));
            this.dispose();
        }
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

    }
}
