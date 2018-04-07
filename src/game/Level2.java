package game;

import city.cs.engine.*;
import java.awt.Color;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.jbox2d.common.Vec2;

/**
 * Level 2 of the game
 */
public class Level2 extends GameLevel {

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
        
        // walls
        /*
        Shape leftWallShape = new BoxShape(0.5f, 6, new Vec2(-11.5f, 5.5f));
        Fixture leftWall = new SolidFixture(ground, leftWallShape);
        Shape rightWallShape = new BoxShape(0.5f, 6, new Vec2(11.5f, 5.5f));
        Fixture rightWall = new SolidFixture(ground, rightWallShape);
        

        // make some platforms

        Shape platformShape = new BoxShape(4, 0.5f);
        Body platform1 = new StaticBody(this, platformShape);
        platform1.setPosition(new Vec2(-7, 5.5f));
        Body platform2 = new StaticBody(this, platformShape);
        platform2.setPosition(new Vec2(5, -2.5f)); */
        int spacing = -65;
        float difficulty = 0.2f;
        while (spacing<80) {
            Spikes spike = new Spikes(this);
            spike.setPosition(new Vec2(spacing, 8));
            spike.setGravityScale(3+difficulty);
            spike.spikeFixture.setRestitution(1);
            spike.addCollisionListener(new Pickup(getPlayer()));
            spacing= spacing + 15;
            difficulty=difficulty+0.2f;
        }


        for (int i = -70; i < 70; i=i+15) {
            Body coin = new Coin(this);
            coin.setPosition(new Vec2(i, 10));
            coin.addCollisionListener(new Pickup(getPlayer()));
        }
    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(-80, -10);
    }

    @Override
    public Vec2 goalPosition() {
        return new Vec2(82, -9.6f);
    }

    @Override
    public boolean isCompleted() {
        return getPlayer().getCoinCount() > 15;
    }
    
    
}
