package com.example.BulletHell;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;

public class AssetLoader implements Screen {

    private final BulletHell game;
    private final BitmapFont font;

    public AssetLoader(BulletHell game) {
        this.game = game;
        this.font = new BitmapFont();
        font.getData().setScale(4f);

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

        // enemy
        mgr.load("ship/enemy.png", Texture.class);

        for (int i = 1; i <= 4; i++) {
            mgr.load("ship/" + i + ".png", Texture.class);
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        float progress = game.manager.getProgress();

        game.spriteBatch.begin();
        font.draw(
            game.spriteBatch,
            "Loading: " + (int)(progress * 100) + "%",
            50,
            60
        );
        game.spriteBatch.end();

        if (game.manager.update()) {
            game.setScreen(new MainMenuScreen(game));
        }
    }

    @Override
    public void dispose() {
        font.dispose();
    }

    @Override public void show() {}
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
}
