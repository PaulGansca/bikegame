/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import city.cs.engine.*;

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
            game.goNextLevel();
        }
    }
}
