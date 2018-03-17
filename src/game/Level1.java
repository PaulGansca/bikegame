/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import city.cs.engine.Body;
import city.cs.engine.BoxShape;
import city.cs.engine.Fixture;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.StaticBody;
import city.cs.engine.UserView;
import java.awt.Graphics2D;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Paul Gansca
 */
public class Level1 extends GameLevel {
    
    private static final int NUM_ORANGES = 11;
    
    public void populate (Game game) {
        super.populate(game);
        
        // make the ground
        
        Shape groundShape = new BoxShape(999, 0.5f);
        Body ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(0, -11.5f));
        ground.addCollisionListener(new Pickup(getPlayer()));

        // walls
        Shape leftWallShape = new BoxShape(0.5f, 6, new Vec2(-11.5f, 5.5f));
        Fixture leftWall = new SolidFixture(ground, leftWallShape);

        

        // make platforms
        /*
        Shape boxShape = new BoxShape(4, 0.5f);
        Body platform1 = new StaticBody(this, boxShape);
        platform1.setPosition(new Vec2(-7, 5.5f));
        Body platform2 = new StaticBody(this, boxShape);
        platform2.setPosition(new Vec2(5, -2.5f));
*/

        
        
        for (int i = 0; i < NUM_ORANGES; i++) {
            Body coin = new Coin(this);
            coin.setPosition(new Vec2(i*2-10, 10));
            coin.addCollisionListener(new Pickup(getPlayer()));
          
        }
        
        Body fuel = new Fuel(this);
        fuel.setPosition(new Vec2(0.5f, 3f));
        fuel.addCollisionListener(new Pickup(getPlayer()));        
        
    }
    

    @Override
    public Vec2 startPosition() {
        return new Vec2(2, -5);
    }

    @Override
    public Vec2 goalPosition() {
        return new Vec2(-4f, -4f);
    }

    @Override
    public boolean isCompleted() {
        return getPlayer().getCoinCount() >= NUM_ORANGES;
    }
       
    
}
