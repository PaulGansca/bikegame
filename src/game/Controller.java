package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Key handler to control an Walker.
 */
public class Controller extends KeyAdapter {
    private static final float JUMPING_SPEED = 6;
    private float WALKING_SPEED = 0;
    private static final float ROTATION = 5.5f;
    private static SoundClip engine;
    
    private Walker body;
    
    public Controller(Walker body) {
        this.body = body;
        
    }
    /**
     * Handle key press events for walking and jumping.
     * @param e description of the key event
     */
    static {
        try {
           engine = new SoundClip("data/engine.mp3");
         } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
           System.out.println(e);
         }        
        }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Q) { // Q = quit
            System.exit(0);
        } else if (code == KeyEvent.VK_J) { // J = jump
            Vec2 v = body.getLinearVelocity();
            // only jump if body is not already jumping
            if (Math.abs(v.y) < 0.01f) {
                body.jump(JUMPING_SPEED);
            }
        } else if (code == KeyEvent.VK_S && WALKING_SPEED>-5) { //when body at rest reset speed or else won't enter loop
            //body.startWalking(-WALKING_SPEED); // S = walk left
            body.startWalking(WALKING_SPEED--);
            System.out.println(WALKING_SPEED);
        } else if (code == KeyEvent.VK_W && WALKING_SPEED<10) {
            //body.startWalking(WALKING_SPEED); // W = walk right
            body.startWalking(WALKING_SPEED++);
            System.out.println(WALKING_SPEED);
        } else if (code == KeyEvent.VK_A){
            body.rotateDegrees(ROTATION);
        } else if (code == KeyEvent.VK_D){
            body.rotateDegrees(-ROTATION);
        }
    }
    
    /**
     * Handle key release events (stop walking).
     * @param e description of the key event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        //engine.stop();
        if (code == KeyEvent.VK_S) {
            body.stopWalking();
            WALKING_SPEED=0; // allows for proper direction when the body is at rest
            engine.play();
            engine.setVolume(0.2d);
        } else if (code == KeyEvent.VK_W) {
            body.stopWalking();
            WALKING_SPEED=0;
            engine.play();
            engine.setVolume(0.2d);
        }
    }
    public void setBody(Walker body) {
        this.body = body;
    }
}
