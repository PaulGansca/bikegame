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
import city.cs.engine.SoundClip;
import city.cs.engine.StaticBody;
import city.cs.engine.UserView;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Paul Gansca
 */
public class Level1 extends GameLevel {
    
    private static final int NUM_ORANGES = 11;
    private static final int NUM_COINS = 5;
    
    public void populate (Game game) {
        super.populate(game);
        
        // make the ground
        
        Shape groundShape = new BoxShape(999, 0.5f);
        Body ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(0, -12f));
        ground.addCollisionListener(new Pickup(getPlayer()));

        // walls
        Shape leftWallShape = new BoxShape(0.5f, 6, new Vec2(-69.5f, -5.5f));
        Body leftWall = new StaticBody(this, leftWallShape);
        leftWall.addCollisionListener(new Pickup(getPlayer()));
        
        Shape climbWallShape = new BoxShape(0.5f, 6, new Vec2(-58.5f, -5.5f));
        Body climbWall = new StaticBody(this, climbWallShape);
        climbWall.addCollisionListener(new Pickup(getPlayer()));
        

        // make platforms
        float spacing = -78.5f;
        float coinSpacing = -78.5f;
        int invariant = 0;
        Shape ramp = new BoxShape(8, 0.5f);
        Body platform1 = new StaticBody(this, ramp);
        platform1.setAngleDegrees(20);
        platform1.setPosition(new Vec2(-58.5f, 7.2f));
        platform1.addCollisionListener(new Pickup(getPlayer()));
        Shape boxShape = new BoxShape(4, 0.5f);
        while (spacing<80) {
            Body platform2 = new StaticBody(this, boxShape);
            platform2.setPosition(new Vec2(spacing, -2.5f));
            platform2.addCollisionListener(new Pickup(getPlayer()));
            spacing= spacing + 15;
            platform2.setFillColor(Color.DARK_GRAY);
        }


        
        while (invariant< NUM_ORANGES) {
            Body coin = new Coin(this);
            coin.setPosition(new Vec2(coinSpacing, 10));
            coin.addCollisionListener(new Pickup(getPlayer()));
            coinSpacing = coinSpacing + 15;
            invariant++;          
        }
        
        for (float i = -80; i <80; i= i+30){
            Body fuel = new Fuel(this);
            fuel.setPosition(new Vec2(i, 3f));
            fuel.addCollisionListener(new Pickup(getPlayer()));
        }        
        
    }
    

    @Override
    public Vec2 startPosition() {
        return new Vec2(-80, -8);
    }

    @Override
    public Vec2 goalPosition() {
        return new Vec2(70f, -4f);
    }

    @Override
    public boolean isCompleted() {
        return getPlayer().getCoinCount() >= NUM_COINS;
    }
    
    
}
