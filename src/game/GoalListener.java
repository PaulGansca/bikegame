/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import city.cs.engine.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paul Gansca
 */
public class GoalListener implements CollisionListener {
        private Game game;
    
    public GoalListener(Game game) {
        this.game = game;
    }

    @Override
    public void collide(CollisionEvent e) {
        Bike player = game.getPlayer();
        if (e.getOtherBody() == player && game.isCurrentLevelCompleted()) {
            System.out.println("Going to next level...");
            try {
                game.goNextLevel();
            } catch (IOException ex) {
                Logger.getLogger(GoalListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
