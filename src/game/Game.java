package game;

import city.cs.engine.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.swing.JFrame;
import javax.swing.Timer;


/**
 * The computer game.
 */
public class Game {

    /** The World in which the bodies move and interact. */
    private GameLevel world;

    /** A graphical display of the world (a specialised JPanel). */
    private UserView view;
    private UserView wideView;
    
    private int level;
    private Controller controller;
    private Timer timer;
    private Game game;

    private DropBomb dropBomb;
    /** Initialise a new Game. */
    public Game() {

        // make the world
        level = 1;
        this.game = game;
        world = new Level1();
        world.populate(this);
        timer = new Timer(5000, new MyTimerTask(world.getPlayer()));
        timer.start();
        view = new MyView(world, 500, 500, getPlayer(), game);

        // make a view
        // uncomment this to draw a 1-metre grid over the view
        // view.setGridResolution(1);

        // display the view in a frame
        JFrame frame = new JFrame("Multi-level game");
        Container buttons = new ControlPanel(this);
        frame.add(buttons, BorderLayout.EAST);
        wideView = new UserView(world, 500, 100);
        wideView.setZoom(4);
        frame.add(wideView, BorderLayout.SOUTH);
        // quit the application when the game window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // display the world in the window
        frame.add(view);
        // don't let the game window be resized
        frame.setResizable(false);
        // size the game window to fit the world view
        frame.pack();
        // make the window visible
        frame.setVisible(true);
        // get keyboard focus
        frame.requestFocus();
        // give keyboard focus to the frame whenever the mouse enters the view
        view.addMouseListener(new GiveFocus(frame));
        view.addMouseListener(new MouseTest());
        dropBomb = new DropBomb(view, world.getPlayer());
        view.addMouseListener(dropBomb);
        
        

        controller = new Controller(world.getPlayer());
        frame.addKeyListener(controller);
        
        // uncomment to make the view track the bird
        world.addStepListener(new Tracker(view, world.getPlayer()));

        // uncomment this to make a debugging view
        JFrame debugView = new DebugViewer(world, 500, 500);

        // start!
        world.start();
    }
    
    public void setLevel(int lvl){
        this.level = lvl;
    }
    
    public int getLevel(){
        return level;
    }
    
    public Bike getPlayer(){
        return world.getPlayer();
    }
    
    
    public void pause(){
        world.stop();
    }
    
    public void restart(){
        world.start();
    }
    
    public boolean isCurrentLevelCompleted(){
        return world.isCompleted();
    }
    
    
    //advance to next level
    public void goNextLevel(){
        world.stop();
        Bike oldBike = world.getPlayer();
        
        switch (level) {
            case 4:
                System.exit(0);
            case 0:
                level++;
                timer.start();
                // get a new world
                world = new Level1();
                // fill it with bodies
                world.populate(this);
                // switch the keyboard control to the new player
                controller.setBody(world.getPlayer());
                world.getPlayer().setCoinCount(oldBike.getCoinCount());
                world.getPlayer().setLifeCount(oldBike.getLifeCount());
                world.getPlayer().setBombCount(oldBike.getBombCount());
                // show the new world in the view
                view.setWorld(world);
                wideView.setZoom(4);
                wideView.setWorld(world);
                world.addStepListener(new Tracker(view, world.getPlayer()));
                world.start();
                break;
            case 1:
                level++;
                timer.stop();
                // get a new world
                world = new Level2();
                // fill it with bodies
                world.populate(this);
                // switch the keyboard control to the new player
                controller.setBody(world.getPlayer());
                world.getPlayer().setCoinCount(oldBike.getCoinCount());
                world.getPlayer().setLifeCount(oldBike.getLifeCount());
                world.getPlayer().setBombCount(oldBike.getBombCount());
                // show the new world in the view
                view.setWorld(world);
                wideView.setZoom(4);
                wideView.setWorld(world);
                world.addStepListener(new Tracker(view, world.getPlayer()));
                world.start();
                break;
            case 2:
                
                timer.stop();
                level++;
                // get a new world
                world = new Level3();
                // fill it with bodies
                world.populate(this);
                // switch the keyboard control to the new player
                controller.setBody(world.getPlayer());
                
                world.getPlayer().setCoinCount(oldBike.getCoinCount());
                world.getPlayer().setLifeCount(oldBike.getLifeCount());
                world.getPlayer().setBombCount(oldBike.getBombCount());
                // show the new world in the view
                view.setWorld(world);
                
                wideView.setWorld(world);
                wideView.setZoom(4);
                world.addStepListener(new Tracker(view, world.getPlayer()));
                world.start();
                break;
            default:
                break;            
        }
        ((MyView)view).setBike(world.getPlayer());
        dropBomb.setBike(world.getPlayer());
    }

    /** Run the game. */
    public static void main(String[] args) {
        new Game();
    }
}
