package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.Color;

/**
 *
 * @author greg
 */


/**
 * Coins.
 */
public class Coin extends DynamicBody {
    private static final Shape shape = new PolygonShape(-0.176f,0.476f,
            -0.452f,0.185f, -0.47f,-0.127f, -0.236f,-0.445f, 0.176f,
            -0.436f, 0.455f,-0.145f, 0.458f,0.106f, 0.152f,0.482f);
    private static final BodyImage image =
        new BodyImage("data/coins.gif", 1);
    
    public Coin(World world) {
        super(world, shape);
        addImage(image);
    }
}
