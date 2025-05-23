package com.example.BulletHell;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class Enemy extends GameEntity {
    static float BASE_MOVEMENT = 30f;

    AssetManager manager;
    Texture currentFrame;
    Texture bulletTexture;
    Texture specialBulletTexture;

    private boolean isDefeated = false;

    private float timeAlive = 0;
    private float shootTimer = 0;
    private float patternTimer = 0;
    private int patternCounter = 0;

    // movimiento del enemigo en si:
    private float moveTimer = 0;
    private float moveSpeed = 50f;
    private float moveRangeX = 200f;
    private float moveRangeY = 100f;
    private float baseX = 180f;
    private float baseY = 600f;

    private float enemyCenterX, enemyCenterY;
    // Bullet management
    private Array<Bullet> bullets = new Array<>();
    private float fireRate = 0.1f;  // Much faster firing rate

    public Enemy(AssetManager manager) {
        this.manager = manager;
        currentFrame = manager.get("ship/enemy.png");
        enemyCenterX = (baseX + currentFrame.getWidth() / 2) - 12;
        enemyCenterY = (baseY + currentFrame.getHeight() / 2) - 12;
        bulletTexture = manager.get("bullets/alien_bullet.png");
        specialBulletTexture = manager.get("bullets/long_bullet.png");
        setInitialPosition();
    }

    public void setInitialPosition() {
        setPosition(baseX, baseY);
    }

    @Override
    public void act(float delta) {
        timeAlive += delta;
        shootTimer += delta;
        patternTimer += delta;
        moveTimer += delta;

        float moveX = MathUtils.sin(moveTimer * 0.8f) * moveRangeX;
        float moveY = MathUtils.cos(moveTimer * 0.5f) * moveRangeY;

        setPosition(baseX + moveX, baseY + moveY);

        enemyCenterX = getX() + 36;
        enemyCenterY = getY() + 36;

        enemyStages(timeAlive);

        // Update all bullets
        for (Bullet bullet : bullets) {
            bullet.update(delta);
        }

        // Remove expired bullets
        for (int i = bullets.size - 1; i >= 0; i--) {
            if (bullets.get(i).isExpired()) {
                bullets.removeIndex(i);
            }
        }

        currentFrame = manager.get("ship/enemy.png");
    }

    public void enemyStages(float timeSurvived) {
        if (timeSurvived < 12f) stage5();       // Gentle Rings
        else if (timeSurvived < 24f) stage6();  // Pulsing Sun
        else if (timeSurvived < 36f) stage1();  // Vortex Spiral
        else if (timeSurvived < 48f) stage7();  // Seeking Vines
        else if (timeSurvived < 60f) stage2();  // Photon Spiral
        else if (timeSurvived < 72f) stage8();  // Atomic Orbitals
        else if (timeSurvived < 84f) stage3();  // Celestial Orbit
        else if (timeSurvived < 96f) stage9();  // Dragonfire Barrage
        else if (timeSurvived < 108f) stage4(); // Angle Spam
        else if (timeSurvived < 120f) stage10(); // Ulitmo ataque
        else if (timeAlive >= 132f) {
            isDefeated = true;
            stop(); // elimina y detenemos todo
        }
    }

    public boolean isDefeated() {
        return isDefeated;
    }

    public void stop() {
        bullets.clear();
        moveSpeed = 0;
        BASE_MOVEMENT = 0;
    }

    // STAGE 1: VORTEX SPIRAL (Dancing Sunflowers)
    public void stage1() {
        if (shootTimer >= 0.15f) {
            shootTimer = 0;

            int flowers = 3;
            float baseAngle = timeAlive * 45f;

            for (int f = 0; f < flowers; f++) {
                float flowerAngle = baseAngle + (f * 120f);
                float distance = 50f + (30f * MathUtils.sin(timeAlive * 2f + f));

                for (int i = 0; i < 8; i++) {
                    float angle = flowerAngle + (i * 45f);
                    bullets.add(new Bullet(
                        (i%2==0) ? bulletTexture : specialBulletTexture,
                        enemyCenterX,
                        enemyCenterY,
                        angle,
                        120f,
                        0,
                        0,
                        5f
                    ));
                }

                bullets.add(new Bullet(
                    specialBulletTexture,
                    enemyCenterX,
                    enemyCenterY,
                    flowerAngle,
                    180f + (30f * MathUtils.sin(timeAlive * 3f)),
                    0,
                    0,
                    4f
                ));
            }
        }
    }

    // STAGE 2: PHOTON SPIRAL WAVE
    public void stage2() {
        if (shootTimer >= 0.1f) {
            shootTimer = 0;

            int arms = 4;
            float armRotation = timeAlive * 30f;

            for (int a = 0; a < arms; a++) {
                float angle = armRotation + (a * 90f);

                for (int i = 0; i < 3; i++) {
                    bullets.add(new Bullet(
                        bulletTexture,
                        enemyCenterX,
                        enemyCenterY,
                        angle + (i * 15f),
                        150f,
                        0,
                        5f,
                        4f
                    ));
                }

                bullets.add(new Bullet(
                    specialBulletTexture,
                    enemyCenterX + MathUtils.cosDeg(angle) * 40f, // Orbit around center
                    enemyCenterY + MathUtils.sinDeg(angle) * 40f,
                    angle + 180f,
                    80f,
                    20f,
                    0,
                    3f
                ));
            }
        }
    }

    // STAGE 3: CELESTIAL ORBIT
    public void stage3() {
        if (shootTimer >= 0.2f) {
            shootTimer = 0;

            for (int p = 0; p < 5; p++) {
                float angle = (p * 72f) + (timeAlive * 40f);
                float x = enemyCenterX + MathUtils.cosDeg(angle) * 100f;
                float y = enemyCenterY + MathUtils.sinDeg(angle) * 100f;

                bullets.add(new Bullet(
                    specialBulletTexture,
                    x,
                    y,
                    angle + 90f,
                    0,
                    0,
                    0,
                    10f
                ));

                for (int m = 0; m < 3; m++) {
                    float moonAngle = angle + (m * 120f) + (timeAlive * 120f);
                    bullets.add(new Bullet(
                        bulletTexture,
                        x + MathUtils.cosDeg(moonAngle) * 30f,
                        y + MathUtils.sinDeg(moonAngle) * 30f,
                        moonAngle + 90f,
                        60f,
                        0,
                        0,
                        8f
                    ));
                }
            }
            patternCounter++;
        }
    }

    // STAGE 4: spam angulos
    public void stage4() {
        int newAngle = 0;
        if (shootTimer >= 0.08f) {
            shootTimer = 0;

            if ((int)timeAlive % 5 == 0) {
                for (int i = 0; i < 36; i++) {
                    bullets.add(new Bullet(
                        specialBulletTexture,
                        enemyCenterX,
                        enemyCenterY,
                        i * 10f,
                        300f,
                        -50f,
                        newAngle+=1,
                        3f
                    ));
                }
            }
        }
    }

    // STAGE 5: Gentle Rings (Beginner)
    public void stage5() {
        if (shootTimer >= 0.4f) {
            shootTimer = 0;
            int rings = 3;
            for (int r = 0; r < rings; r++) {
                for (int i = 0; i < 12; i++) {
                    bullets.add(new Bullet(
                        bulletTexture,
                        enemyCenterX,
                        enemyCenterY,
                        i * 30f + (r * 10f),
                        80f + (r * 40f),
                        -5f,
                        0,
                        6f
                    ));
                }
            }
        }
    }

    // STAGE 6: Pulsing Sun (Beginner+)
    public void stage6() {
        if (shootTimer >= 0.3f) {
            shootTimer = 0;
            float pulse = MathUtils.sin(timeAlive * 3f) * 0.5f + 0.8f;
            for (int i = 0; i < 8; i++) {
                bullets.add(new Bullet(
                    (i%2==0) ? bulletTexture : specialBulletTexture,
                    enemyCenterX,
                    enemyCenterY,
                    i * 45f + timeAlive * 10f,
                    100f * pulse,
                    5f * pulse,
                    0,
                    5f
                ));
            }
        }
    }

    // STAGE 7: Seeking Vines (Intermediate)
    public void stage7() {
        if (shootTimer >= 0.25f) {
            shootTimer = 0;
            int vines = 5;
            float playerAngle = MathUtils.atan2(enemyCenterY, enemyCenterX) * MathUtils.radDeg;

            for (int v = 0; v < vines; v++) {
                float baseAngle = playerAngle + (v * 72f) - 144f;
                for (int s = 0; s < 3; s++) {
                    bullets.add(new Bullet(
                        specialBulletTexture,
                        enemyCenterX,
                        enemyCenterY,
                        baseAngle + (s * 5f),
                        120f + (s * 40f),
                        0,
                        2f,
                        4f
                    ));
                }
            }
        }
    }

    // STAGE 8: Atomic Orbitals (Intermediate+)
    public void stage8() {
        if (shootTimer >= 0.2f) {
            shootTimer = 0;
            int shells = 4;
            for (int s = 0; s < shells; s++) {
                float radius = 40f + (s * 30f);
                int electrons = 6 + (s * 2);

                for (int e = 0; e < electrons; e++) {
                    float angle = (e * 360f/electrons) + (timeAlive * (20f + s * 10f));
                    bullets.add(new Bullet(
                        (s%2==0) ? bulletTexture : specialBulletTexture,
                        enemyCenterX + MathUtils.cosDeg(angle) * radius,
                        enemyCenterY + MathUtils.sinDeg(angle) * radius,
                        angle + 90f,
                        60f,
                        0,
                        10f - (s * 2f),
                        8f
                    ));
                }
            }
        }
    }

    // STAGE 9: Dragonfire Barrage (Advanced)
    public void stage9() {
        if (shootTimer >= 0.15f) {
            shootTimer = 0;
            int dragons = 3;
            float playerAngle = MathUtils.atan2(enemyCenterY, enemyCenterX) * MathUtils.radDeg;

            for (int d = 0; d < dragons; d++) {
                float baseAngle = playerAngle + (d * 120f) - 120f;
                for (int f = 0; f < 5; f++) {
                    bullets.add(new Bullet(
                        specialBulletTexture,
                        enemyCenterX,
                        enemyCenterY,
                        baseAngle + (f * 8f),
                        200f + (f * 20f),
                        -10f,
                        0,
                        3f
                    ));
                }

                // Wave body
                for (int s = 0; s < 8; s++) {
                    float wave = MathUtils.sin(timeAlive * 3f + s) * 30f;
                    bullets.add(new Bullet(
                        bulletTexture,
                        enemyCenterX,
                        enemyCenterY,
                        270f + wave * 0.5f,
                        150f,
                        0,
                        (s%2==0) ? 3f : -3f,
                        5f
                    ));
                }
            }
        }
    }

    // STAGE 10: Final Judgement (Expert)
    public void stage10() {
        if (shootTimer >= 0.1f) {
            shootTimer = 0;

            // Spiral Core
            for (int i = 0; i < 3; i++) {
                bullets.add(new Bullet(
                    specialBulletTexture,
                    enemyCenterX,
                    enemyCenterY,
                    timeAlive * 100f + (i * 120f),
                    150f,
                    0,
                    15f,
                    6f
                ));
            }

            // Seeking Lasers
            if (patternCounter % 4 == 0) {
                for (int i = 0; i < 6; i++) {
                    float playerAngle = MathUtils.atan2(enemyCenterY, enemyCenterX) * MathUtils.radDeg;
                    bullets.add(new Bullet(
                        bulletTexture,
                        enemyCenterX,
                        enemyCenterY,
                        playerAngle + (i * 10f) - 25f,
                        400f,
                        -30f,
                        0,
                        2.5f
                    ));
                }
            }

            patternCounter++;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        for (Bullet bullet : bullets) {
            float scale = 0.8f + 0.2f * MathUtils.sin(timeAlive * 5f);
            batch.setColor(1, 1, 1, parentAlpha);
            batch.draw(bullet.getTexture(),
                bullet.getX(), bullet.getY(),
                bullet.getWidth()/2, bullet.getHeight()/2,
                bullet.getWidth(), bullet.getHeight(),
                scale, scale,
                bullet.getRotation(),
                0, 0,
                bullet.getTexture().getWidth(), bullet.getTexture().getHeight(),
                false, false);
        }

        batch.draw(currentFrame, getX(), getY());
    }

    public Array<Bullet> getBullets() {
        return bullets;
    }

    public float getTimeAlive() {
        return timeAlive;
    }
}
