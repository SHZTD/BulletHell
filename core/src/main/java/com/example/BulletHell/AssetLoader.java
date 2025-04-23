package com.example.BulletHell;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class AssetLoader implements Screen {

    BulletHell game;
    float loadProgress;

    AssetLoader(BulletHell game){
        this.game = game;
        AssetManager manager = game.manager;

        // carga todo;
        manager.load("first_screen/bullet_hell.png", Texture.class);
        manager.load("first_screen/planet.png", Texture.class);

        loadProgress = 0.f;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        if(game.manager.update()) {
            game.setScreen(new MainMenuScreen(game));
            this.dispose();
        }

        game.spriteBatch.begin();
        game.spriteBatch.end();
    }

    @Override
    public void show() {

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
