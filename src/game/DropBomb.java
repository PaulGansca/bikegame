
package game;

import city.cs.engine.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A MouseListener that drops a bowling ball on each mouse press.
 */
public class DropBomb extends MouseAdapter {

    private WorldView view;
    private Bike bike;

    /**
     * Construct a listener.
     * @param view the view the mouse will be clicked on
     */
    public DropBomb(WorldView view, Bike bike) {
        this.view = view;
        this.bike = bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }
    

    /**
     * Create a bowling ball at the current mouse position.
     * @param e event object containing the mouse position
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if(bike.getBombCount() > 0){
            DynamicBody ball = new Bomb(view.getWorld());
            ball.setPosition(view.viewToWorld(e.getPoint()));
            ball.addCollisionListener(new Pickup(bike));
            bike.bombUsed();
        } else{
            System.out.println("No more ammo.");
        }
    }

    
           
 
}
