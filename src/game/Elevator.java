/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.Walker;
import city.cs.engine.World;

/**
 *
 * @author Paul Gansca
 */
public class Elevator extends Walker {
    
    private static final Shape elevator = new PolygonShape(
            -1.76f,0.84f, -1.78f,-0.14f, 1.45f,-0.13f, 1.47f,0.83f);
    SolidFixture elevatorFixture = new SolidFixture(this, elevator, 100);
    private static final BodyImage image =
        new BodyImage("data/elevator.png", 3f);

    public Elevator(World world) {
        super(world, elevator);
        addImage(image);
    }
}
