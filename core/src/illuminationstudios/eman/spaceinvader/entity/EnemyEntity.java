package illuminationstudios.eman.spaceinvader.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

/**
 * Created by lavacake on 6/1/2015.
 */
public class EnemyEntity extends Sprite {

    private float maxScreenX;
    private float maxScreenY;

    public ArrayList<EnemyEntity> enemies;

    public EnemyEntity(String path) {
        super(new Texture(path));
        maxScreenY = Gdx.graphics.getHeight();
        maxScreenX = Gdx.graphics.getWidth();
        setPosition(0, maxScreenY);
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
    }

    public void update(float delta) {
        moveEnemy();
    }

    public void moveEnemy() {
        setY(getY() - 5);
    }
}
