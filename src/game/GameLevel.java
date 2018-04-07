package game;

import city.cs.engine.*;
import java.awt.Color;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.jbox2d.common.Vec2;

/**
 * A level of the game.
 */
public abstract class GameLevel extends World {
    private Bike player;

    private Game game;
    public Bike getPlayer() {
        return player;
    }
    
    /**
     * Populate the world of this level.
     * Child classes should this method with additional bodies.
     * @param game
     */
    public void populate(Game game) {
        player = new Bike(this);
        this.game = game;
        //System.out.println("Bike");
        player.setPosition(startPosition());
        player.addCollisionListener(new Pickup(getPlayer()));
        Goal goal = new Goal(this);
        goal.setPosition(goalPosition());
        goal.addCollisionListener(new GoalListener(game));
    }
    
    /** The initial position of the player. */
    public abstract Vec2 startPosition();
    
    /** The position of the exit door. */
    public abstract Vec2 goalPosition();
    
    /** Is this level complete? */
    public abstract boolean isCompleted();
    Shape groundShape = new BoxShape(5, 0.5f);
    public void makePlatform(){
            
            Body ground = new DynamicBody(game.getWorld(), groundShape);
            ground.setPosition(new Vec2(-36.5f, 9f));
            ground.setFillColor(Color.RED);
            SolidFixture groundFixture = new SolidFixture(ground, groundShape, 100);

    }
    
    public void spawnFuel(){
        for (float i = -60; i <76; i= i+45){
            Body fuel = new Fuel(game.getWorld());
            fuel.setPosition(new Vec2(i, 3f));
            fuel.addCollisionListener(new Pickup(getPlayer()));
        }
    }


    
    //public void stopMusic();
    
}
