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
public class Level3 extends GameLevel {

    private static final int NUM_ORANGES = 11;

   

    /**
     * Populate the world.
     */
    @Override
    public void populate(Game game) {
        super.populate(game);    

        //TODO redesign level 3 to make better use of FSM
        // make the ground
        Shape groundShape = new BoxShape(40, 0.5f);
        Body ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(12, -11.5f));
        
        Body ground2 = new StaticBody(this, groundShape);
        ground2.setPosition(new Vec2(-99, -11.5f));

        // walls
        Shape climbWallShape = new BoxShape(3.5f, 6, new Vec2(-46f, -6));
        Body climbWall = new StaticBody(this, climbWallShape);
        climbWall.addCollisionListener(new Pickup(getPlayer()));
        climbWall.setFillColor(Color.GREEN);
        Shape rightWallShape = new BoxShape(0.5f, 8, new Vec2(11.5f, 5.5f));
        Body rightWall = new StaticBody(this, rightWallShape);
        rightWall.setFillColor(Color.GREEN);
        rightWall.setPosition(new Vec2(4.5f, -12));
        

        // make some platforms
        Shape platformShape = new BoxShape(4, 0.5f);
        Body platform1 = new StaticBody(this, platformShape);
        platform1.setPosition(new Vec2(9, 0.5f));
        platform1.setFillColor(Color.GREEN);
        
        Blade blade1 = new Blade(game);
        blade1.setPosition(new Vec2(0, 0));
        
        Blade blade2 = new Blade(game);
        blade2.setPosition(new Vec2(-90, 0));
        
        float spacing  = -90;

        for (float i = 2f; i < 10; i++) {
            Body coin = new Coin(this);
            coin.setPosition(new Vec2(i * 6.5f, 10));
            coin.addCollisionListener(new Pickup(getPlayer()));
        }
    }
    
    
    

    @Override
    public Vec2 startPosition() {
        return new Vec2(-75, -10);
    }

    @Override
    public Vec2 goalPosition() {
        return new Vec2(28.4f, -9.6f);
    }

    @Override
    public boolean isCompleted() {
        return getPlayer().getCoinCount() > 20;
    }

        
}
