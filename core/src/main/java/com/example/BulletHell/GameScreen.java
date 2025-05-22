package com.example.BulletHell;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {
    BulletHell game;
    ButtonLayout joypad;

    Stage stage;

    Player player;

    public GameScreen(BulletHell game) {
        this.game = game;

        // joypad
        joypad = new ButtonLayout(game.camera, game.manager, game.bf);
        joypad.loadFromJson("joypad.json");

        // stg
        stage = new Stage();
        player = new Player(game.manager);
        player.setJoypad(joypad);
        stage.addActor(player);

        Viewport vp = new ScreenViewport(game.camera);
        stage.setViewport(vp);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(Color.BLACK);

        game.camera.update();
        game.spriteBatch.setProjectionMatrix(game.camera.combined);
        game.shapeRenderer.setProjectionMatrix(game.camera.combined);

        stage.draw();

        joypad.render(game.spriteBatch, game.textBatch);
        game.textBatch.begin();
        game.textBatch.end();

        gameLogic(delta);
    }

    // gamelogic
    void gameLogic(float delta) {
        stage.act(delta);
        //System.out.println("render");
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
