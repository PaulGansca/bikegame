package game;

import city.cs.engine.*;

/**
 * A bowling ball.
 */
public class Bomb extends DynamicBody {

    private static final Shape ballShape = new PolygonShape(-0.382f,
            0.885f, -0.942f,0.313f, -0.942f,-0.365f, -0.372f,-0.93f,
            0.308f,-0.935f, 0.873f,-0.36f, 0.878f,0.31f, 0.875f,0.88f);
    private static final BodyImage ballImage =
        new BodyImage("data/bomb.gif", 2);

    /**
     * Construct a bowling ball.
     * @param world the world in which this body exists.
     */
    public Bomb(World world) {
        super(world, ballShape);
        addImage(ballImage);
        
    }
}
