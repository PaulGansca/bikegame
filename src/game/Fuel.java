/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.Walker;
import city.cs.engine.World;

/**
 *
 * @author Paul Gansca
 */
public class Fuel extends Walker{
    private static final Shape shape = new PolygonShape(-0.26f,0.84f, -0.6f,
            0.44f, -0.58f,-1.23f, -0.01f,-1.45f, 0.95f,-0.97f, 0.97f,
            0.66f, -0.13f,0.93f);
    private static final BodyImage fuel =
        new BodyImage("data/fuel.png", 3);
    
    public Fuel(World world){
        super(world, shape);
        addImage(fuel);
    }
}
