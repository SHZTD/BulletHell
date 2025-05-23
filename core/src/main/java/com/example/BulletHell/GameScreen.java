package com.example.BulletHell;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {
    BulletHell game;
    ButtonLayout joypad;

    private Music backgroundMusic;
    private boolean musicPlaying = false;

    private Sound sound;

    Stage stage;
    Player player;
    Enemy enemy;

    // debug
    private ShapeRenderer debugRenderer;

    public GameScreen(BulletHell game) {
        this.game = game;
        this.debugRenderer = new ShapeRenderer();

        // joypad
        joypad = new ButtonLayout(game.camera, game.manager, game.bf);
        joypad.loadFromJson("joypad.json");

        // stg
        stage = new Stage();
        player = new Player(game.manager);
        enemy = new Enemy(game.manager);
        player.setJoypad(joypad);
        stage.addActor(player);
        stage.addActor(enemy); // instancia el enemigo

        Viewport vp = new ScreenViewport(game.camera);
        stage.setViewport(vp);
    }

    @Override
    public void show() {
        backgroundMusic = game.manager.get("audio/loop.mp3", Music.class);

        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.5f); // 50% volume

        backgroundMusic.play();
        musicPlaying = true;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        game.camera.update();
        game.spriteBatch.setProjectionMatrix(game.camera.combined);
        game.shapeRenderer.setProjectionMatrix(game.camera.combined);

        // Draw background
        game.spriteBatch.begin();
        game.spriteBatch.draw(game.manager.get("first_screen/main_arena.png", Texture.class),
            0, 0, 480, 800, 0, 0, 1080, 2340, false, false);
        game.spriteBatch.end();

        gameLogic(delta);

        stage.draw();
        joypad.render(game.spriteBatch, game.textBatch);

        // Draw UI elements
        drawGameUI();
    }

    ////
    private void drawGameUI() {
        game.textBatch.begin();

        drawHealthDisplay();

        if (!enemy.isDefeated()) {
            float timeLeft = Math.max(0, 132 - enemy.getTimeAlive());
            game.bf.draw(game.textBatch,
                String.format("Time to survive: %.1f", timeLeft),
                20, 750);
        }

        game.textBatch.end();
    }

    private void drawHealthDisplay() {
        game.bf.draw(game.textBatch, "Lives: ", 20, 780);

        Texture lifeTexture = game.manager.get("ship/1.png", Texture.class);
        float iconSize = 30f;
        float startX = 80f;

        for (int i = 0; i < player.getHealth(); i++) {
            game.spriteBatch.begin();
            game.spriteBatch.draw(lifeTexture,
                startX + (i * (iconSize + 5f)),
                760,
                iconSize, iconSize);
            game.spriteBatch.end();
        }

    }

    // gamelogic
    void gameLogic(float delta) {
        stage.act(delta);

        if (!enemy.isDefeated()) {
            enemy.enemyStages(delta);

            Array<Bullet> bullets = enemy.getBullets();
            for (int i = bullets.size - 1; i >= 0; i--) {
                if (player.checkCollision(bullets.get(i))) {
                    bullets.removeIndex(i);
                    sound = game.manager.get("audio/bullet.mp3", Sound.class);
                    sound.play(0.5f);
                }
            }
        }

        if (player.getHealth() <= 0) {
            pauseMusic();
            enemy.stop(); // se acabo
            game.setScreen(new EndScreen(game));
        } else if (enemy.isDefeated()) {
            pauseMusic();
            enemy.stop(); // se acabo
            game.setScreen(new WinScreen(game));
        }
    }

    void pauseMusic() {
        if (musicPlaying && backgroundMusic != null) {
            backgroundMusic.pause();
            musicPlaying = false;
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
