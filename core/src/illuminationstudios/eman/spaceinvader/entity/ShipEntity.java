package illuminationstudios.eman.spaceinvader.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

/**
 * Created by lavacake on 5/29/2015.
 */
public class ShipEntity extends Sprite implements InputProcessor {

    public ArrayList<LaserEntity> lasers;
    public int life;
    public double recoveringTime = 1;

    private float time;
    private int targetX;
    private int targetY;

    private int maxScreenX;
    private int maxScreenY;

    private int boundsX;


    public ShipEntity(String path) {
        super(new Texture(path));
        maxScreenX = Gdx.graphics.getWidth();
        maxScreenY = Gdx.graphics.getHeight();
        Gdx.input.setInputProcessor(this);
        life = 3;
        boundsX = (int) (getX() * getWidth());

        System.out.println("Life remaining: " + life);

    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
    }

    public void update(float delta) {
        delta = Gdx.graphics.getDeltaTime();
        moveRight();
        moveLeft();
        moveUp();
        moveDown();

        time += delta;
        if (time > 1) {
            shootLaser();
            time = 0;
        }
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        targetX = screenX - (int) (getWidth() / 2);
        targetY = Gdx.app.getGraphics().getHeight() - screenY
                - (int) (getHeight() / 2);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public void setLasers(ArrayList<LaserEntity> lasers) {
        this.lasers = lasers;
    }

    private void shootLaser() {
        LaserEntity laser = new LaserEntity("Laser.png");
        laser.setPosition(getX() + (getWidth() / 2) - (laser.getWidth() / 2), getY() + getHeight());
        lasers.add(laser);
        System.out.println("Number of laser " + lasers.size());
    }

    public void loseLife() {
        float delta = Gdx.graphics.getDeltaTime();
        time += delta;
        if (time > recoveringTime) {
            life--;
            System.out.println("Life remaining: " + life);
            time = 0;
        }
    }

    private void moveDown() {
        if (getY() > targetY && heightOfTheShip() > downEdgeOfTheScreen()) {
            setY(getY() - 10);
            if (getY() <= targetY)
                setY(targetY);
        }
    }

    private void moveUp() {
        if (getY() < targetY && heightOfTheShip() < (maxScreenY)) {
            setY(getY() + 10);
            if (getY() >= targetY)
                setY(targetY);
        }
    }

    private void moveLeft() {
        if (getX() > targetX && widthOfTheShip() > leftEdgeOfTheScreen()) {
            setX(getX() - 10);
            if (getX() <= targetX)
                setX(targetX);
        }
    }

    private void moveRight() {
        if (getX() < targetX && widthOfTheShip() < (maxScreenX)) {
            setX(getX() + 10);
            if (getX() >= targetX) {
                setX(targetX);
            }
        }
    }

    private float widthOfTheShip() {
        return getX() + getWidth();
    }

    private float heightOfTheShip() {
        return getY() + getHeight();
    }

    private float downEdgeOfTheScreen() {
        return maxScreenY / getY() + getHeight() - 15;
    }

    private float leftEdgeOfTheScreen() {
        return maxScreenX / getX() + getWidth() - 15;
    }

}
