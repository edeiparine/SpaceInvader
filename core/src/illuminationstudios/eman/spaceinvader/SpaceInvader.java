package illuminationstudios.eman.spaceinvader;

import com.badlogic.gdx.Game;
import illuminationstudios.eman.spaceinvader.screen.GameScreen;

public class SpaceInvader extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen(this));
    }
}
