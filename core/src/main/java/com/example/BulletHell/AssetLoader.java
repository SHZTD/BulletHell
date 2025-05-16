package com.example.BulletHell;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class AssetLoader implements Screen {

    private BulletHell game;
    private float loadProgress;

    public AssetLoader(BulletHell game){
        this.game = game;

        AssetManager mgr = game.manager;
        // first screen
        mgr.load("first_screen/bullet_hell.png", Texture.class);
        mgr.load("first_screen/planet.png", Texture.class);
        mgr.load("first_screen/play.png", Texture.class);

        // joystick
        mgr.load("joystick/d.png", Texture.class);
        mgr.load("joystick/u.png", Texture.class);
        mgr.load("joystick/l.png", Texture.class);
        mgr.load("joystick/r.png", Texture.class);
        mgr.load("joystick/shoot.png", Texture.class);

        for (int i = 1; i <= 4; i++) {
            mgr.load("ship/" + i + ".png", Texture.class);
        }

        loadProgress = 0;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        if(game.manager.update()) {
            game.setScreen(new MainMenuScreen(game));
            this.dispose();
        }

        game.spriteBatch.begin();
        game.bf.getData().setScale(4f);
        game.bf.draw(
            game.spriteBatch,
            "Loading: " + (int)(loadProgress * 100) + "%",
            50,
            50
        );

        game.spriteBatch.end();
        if (game.manager.update()) {
            game.setScreen(new MainMenuScreen(game));
            this.dispose();
        }
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
