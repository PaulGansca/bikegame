/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Paul Gansca
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
class MyTimerTask implements ActionListener {
    private Bike bike;

    public MyTimerTask(Bike bike) {
        this.bike = bike;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae){
        bike.fuelConsumption();
    }

}
