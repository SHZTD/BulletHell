package com.example.BulletHell;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class Bullet extends DrawableEntity {
    private float direccion; // se posiciona en grados
    private float velocidad;
    private float acceleracion;
    private float curva;
    private float dirX;
    private float dirY;
    private float entityLifeTime; // cuanto tiempo la entidad puede estar presente
    private float currentTime;

    public Bullet(Texture texture, float x, float y,
                  float direccion, float velocidad, float acceleracion,
                  float curva, float entityLifeTime) {
        super(texture, x, y);
        this.direccion = direccion;
        this.velocidad = velocidad;
        this.acceleracion = acceleracion;
        this.curva = curva;
        this.entityLifeTime = entityLifeTime;
        this.currentTime = 0;
        updateDirectionVectors();
    }

    @Override
    public void move(float dx, float dy) {
        super.move(dx, dy);
    }

    public void update(float deltaTime) {
        currentTime += deltaTime;
        direccion += curva * deltaTime;
        velocidad += acceleracion * deltaTime;

        updateDirectionVectors();

        x += dirX * velocidad * deltaTime;
        y += dirY * velocidad * deltaTime;
    }

    public boolean isExpired() {
        return currentTime >= entityLifeTime;
    }

    public boolean isOutOfBounds(float minX, float maxX, float minY, float maxY) {
        return x < minX || x > maxX || y < minY || y > maxY;
    }

    private void updateDirectionVectors() {
        float radians = direccion * MathUtils.degreesToRadians;
        dirX = MathUtils.cos(radians);
        dirY = -MathUtils.sin(radians);
    }

    // auto generados
    public float getDireccion() {
        return direccion;
    }

    public void setDireccion(float direccion) {
        this.direccion = direccion;
    }

    public float getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }

    public float getAcceleracion() {
        return acceleracion;
    }

    public void setAcceleracion(float acceleracion) {
        this.acceleracion = acceleracion;
    }

    public float getCurva() {
        return curva;
    }

    public void setCurva(float curva) {
        this.curva = curva;
    }

    public float getDirX() {
        return dirX;
    }

    public void setDirX(float dirX) {
        this.dirX = dirX;
    }

    public float getDirY() {
        return dirY;
    }

    public void setDirY(float dirY) {
        this.dirY = dirY;
    }

    public float getEntityLifeTime() {
        return entityLifeTime;
    }

    public void setEntityLifeTime(float entityLifeTime) {
        this.entityLifeTime = entityLifeTime;
    }

    public float getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(float currentTime) {
        this.currentTime = currentTime;
    }
}
