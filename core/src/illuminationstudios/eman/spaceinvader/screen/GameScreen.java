package illuminationstudios.eman.spaceinvader.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import illuminationstudios.eman.spaceinvader.entity.EnemyEntity;
import illuminationstudios.eman.spaceinvader.entity.LaserEntity;
import illuminationstudios.eman.spaceinvader.entity.ShipEntity;

/**
 * Created by lavacake on 5/29/2015.
 */
public class GameScreen extends SpaceInvaderScreen {

    private float maxScreenX;
    private float maxScreenY;

    Texture bg;
    Texture life;
    EnemyEntity enemy;
    SpriteBatch batch;
    ShipEntity ship;

    private ArrayList<LaserEntity> lasers;
    private ArrayList<EnemyEntity> enemies;

    private float time;
    private float delta = Gdx.graphics.getDeltaTime();

    public GameScreen(Game game) {
        super(game);
        maxScreenX = Gdx.graphics.getWidth();
        maxScreenY = Gdx.graphics.getHeight();

        lasers = new ArrayList<LaserEntity>();
        enemies = new ArrayList<EnemyEntity>();
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        bg = new Texture("bg.png");
        ship = new ShipEntity("Ship.png");
        ship.setLasers(lasers);
        enemy = new EnemyEntity("enemy.png");
        life = new Texture("life.png");
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(bg, 0, 0);
        ship.draw(batch);
        update();
        render();
        renderEnemies(delta);
        batch.end();
    }

    private void update() {
        updateShip();
        updateLaser();
        disposeLaser();
        updateEnemy();
        disposeEnemies();
        collisionCheck();
    }

    private void render() {
        renderLife();
    }


    private void updateEnemy() {
        for (int i = 0; i < enemies.size(); i++) {
            EnemyEntity enemy = enemies.get(i);
            enemy.draw(batch);
            enemy.update(delta);
        }
    }

    private void updateShip() {
        ship.update(delta);
    }

    private void updateLaser() {
        for (int i = 0; i < lasers.size(); i++) {
            LaserEntity laser = lasers.get(i);
            laser.draw(batch);
            laser.update(delta);
        }
    }

    private void disposeLaser() {
        for (int i = 0; i < lasers.size(); i++) {
            LaserEntity laser = lasers.get(i);
            if (laser.disposeLaser) {
                lasers.remove(i);
            }
        }
    }

    private void renderLife() {
        for (int i = 0; i < ship.life; i++) {
            batch.draw(life, 15 + (i * 50), 685);
        }
    }

    private void renderEnemies(float delta) {
        time += delta;
        if (time > 1) {
            enemy = new EnemyEntity("enemy.png");
            enemies.add(enemy);
            System.out.println(enemies.size());
            time = 0;
        }
    }

    private void disposeEnemies() {
        for (int i = 0; i < enemies.size(); i++) {
            EnemyEntity enemy = enemies.get(i);
            if (enemy.getY() < (maxScreenY / enemy.getY()) / (enemy.getHeight())) {
                enemies.remove(i);
            }
        }
    }

    private void collisionCheck() {
        for (int i = 0; i < enemies.size(); i++) {
            EnemyEntity enemy = enemies.get(i);
            if (enemy.getBoundingRectangle().overlaps(ship.getBoundingRectangle())) {
                ship.loseLife();
            }
        }
    }
}
