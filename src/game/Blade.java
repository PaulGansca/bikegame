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
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.Walker;
import city.cs.engine.World;
import fsm.FSM;
/**
 *
 * @author Paul Gansca
 */
//TODO more FSM uses 
public class Blade extends Walker implements StepListener{
    private static final Shape blade = new PolygonShape(-0.25f
            ,1.49f, -1.23f,0.89f, -1.5f,-0.25f, -0.89f,-1.21f,
            0.23f,-1.41f, 1.2f,-0.85f, 1.45f,0.28f, 0.89f,1.22f);
    
    private static final BodyImage image =
        new BodyImage("data/blade.png", 3f);
    
    public static final float RANGE = 16;
    private Game game;
    private FSM<Blade> fsm;
    //different constructor from code
    public Blade(Game game) {
        super(game.getWorld(), blade);
        this.game = game;
        addImage(image);
        fsm = new FSM<Blade>(this, new StandStillState());
        getWorld().addStepListener(this);
    }
    
    public boolean inRangeLeft() {
        Bike a = game.getPlayer();
        float gap = getPosition().x - a.getPosition().x;
        return gap < RANGE && gap > 0;
    }
    
    public boolean inRangeRight() {
        Bike a = game.getPlayer();
        float gap = getPosition().x - a.getPosition().x;
        return gap > -RANGE && gap < 0;
    }
    
    public boolean inRange() {
        return inRangeLeft() || inRangeRight();
    }
        
    public void preStep(StepEvent e) {
        fsm.update();
    }
    
    public void postStep(StepEvent e) {}
    
}
