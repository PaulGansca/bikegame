/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import city.cs.engine.DynamicBody;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.Walker;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Paul Gansca
 */
public class Spikes extends Walker {
    
    private static final Shape spikes = new PolygonShape(
            -2.9f,1.5f, -2.9f,-1.5f, 2.9f,-1.5f, 2.9f,1.5f);
    
    SolidFixture spikeFixture = new SolidFixture(this, spikes, 100);
    
    private static final BodyImage image =
        new BodyImage("data/spikes.png", 3f);
    
    public Spikes(World w) {
        super(w, spikes);
        addImage(image);
    }

    
}
