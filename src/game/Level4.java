package game;

import city.cs.engine.*;
import java.awt.Color;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Timer;
import org.jbox2d.common.Vec2;

/**
 * Level 2 of the game
 */
public class Level4 extends GameLevel {

    private static final int NUM_ORANGES = 11;
   

    /**
     * Populate the world.
     */
    @Override
    public void populate(Game game) {
        super.populate(game);    


        // make the ground
        Shape groundShape = new BoxShape(999, 0.5f);
        Body ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(0, -11.5f));
        // make border
        Body sky = new StaticBody(this, groundShape);
        sky.setPosition(new Vec2(0, 15));


        // make some platforms
        float spacing = -78.5f;
        Shape boxShape = new BoxShape(4, 0.5f);
        while (spacing<80) {
            Body platform2 = new StaticBody(this, boxShape);
            platform2.setPosition(new Vec2(spacing, -2.5f));
            platform2.addCollisionListener(new Pickup(getPlayer()));
            spacing= spacing + 20;
            platform2.setFillColor(Color.WHITE);
        }

        for (float i = -50; i < 50; i= i+5) {
            Body coin = new Coin(this);
            coin.setPosition(new Vec2(i * 1.5f, 10));
            coin.addCollisionListener(new Pickup(getPlayer()));
        }
    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(-75, -10);
    }

    @Override
    public Vec2 goalPosition() {
        return new Vec2(0f, -9.6f);
    }

    @Override
    public boolean isCompleted() {
        return getPlayer().getCoinCount() > 0;
    }
     
}
