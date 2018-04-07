/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import city.cs.engine.Body;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Paul Gansca
 */
public class SpawnPlatform implements ActionListener {
    public GameLevel demo;
    
    public SpawnPlatform(GameLevel demo){
        this.demo = demo;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        demo.makePlatform();
        demo.makePlatform();
        demo.makePlatform();
    }
    
}
