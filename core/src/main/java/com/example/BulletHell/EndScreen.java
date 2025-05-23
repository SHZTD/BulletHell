package com.example.BulletHell;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class EndScreen implements Screen {
    BulletHell game;
    ButtonLayout endMenu;

    public EndScreen(BulletHell game) {
        this.game = game;
        endMenu = new ButtonLayout(game.camera, game.manager, game.bf);
        endMenu.loadFromJson("endmenu.json");
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
        game.spriteBatch.draw(game.manager.get("first_screen/game_over.png", Texture.class), 0, 0, 480, 800, 0,0, 1024, 1536, false, false);
        game.spriteBatch.end();

        endMenu.render(game.spriteBatch, game.textBatch);

        if(endMenu.consumeRelease("Retry")) {
            game.setScreen(new GameScreen(game));
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
