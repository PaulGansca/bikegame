/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.Comparator;

/**
 *
 * @author Paul Gansca
 */
public class ScoreComparator implements Comparator<Player>{

    @Override
    public int compare(Player p1, Player p2) {
        return p2.score-p1.score;
    }
        
}
