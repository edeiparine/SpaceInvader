package illuminationstudios.eman.spaceinvader.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by lavacake on 5/29/2015.
 */
public class LaserEntity extends Sprite {

    public float maxScreenY;
    public boolean disposeLaser;

    public LaserEntity(String path) {
        super(new Texture(path));
        maxScreenY = Gdx.graphics.getHeight();
        disposeLaser = false;
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
    }

    public void update(float delta) {
        moveLaser();
        disposeLaser();
    }

    public void moveLaser() {
        setY(getY() + 15);
    }

    private void disposeLaser() {
        if(getY() > maxScreenY) {
            disposeLaser = true;
        }
    }
}
