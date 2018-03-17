package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * A level of the game.
 */
public abstract class GameLevel extends World {
    private Bike player;
    
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
    
}
