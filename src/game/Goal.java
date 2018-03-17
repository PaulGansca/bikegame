/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.GhostlyFixture;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;

/**
 *
 * @author Paul Gansca
 */
public class Goal extends StaticBody {
    private static final Shape goal = new BoxShape(1.5f, 1.5f);
    
        public Goal(World world) {
        super(world, goal);
        GhostlyFixture goalGhostly = new GhostlyFixture(this, goal, 5);
        addImage(new BodyImage("data/goal.png", 2.8f));
    }
}
