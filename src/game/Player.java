/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.ArrayList;

/**
 *
 * @author Paul Gansca
 */
public class Player {

    
    String name;
    int score;
    
    public Player (String name, int score){
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return name + ", " + score;
    }

}
