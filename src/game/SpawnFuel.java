/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Paul Gansca
 */
public class SpawnFuel implements ActionListener{
    
    public GameLevel demo;
    
    public SpawnFuel(GameLevel demo){
        this.demo = demo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        demo.spawnFuel();
    }
}
