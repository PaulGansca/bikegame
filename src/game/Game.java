package game;

import city.cs.engine.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.io.IOException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


import javax.swing.JFrame;
import static javax.swing.JOptionPane.showInputDialog;
import javax.swing.Timer;
import org.jbox2d.common.Vec2;


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
    private Timer spawner;
    private Timer spawnFuel;

    private DropBomb dropBomb;
    private HighScoreReader demo;
    private HighScoreWriter hsWriter;
    private SoundClip gameMusic;
    private static SoundClip warp;
    private String username;
    private double volume;
    
    
    /** Initialise a new Game. */
    public Game() {
        username = "";
        while (username.isEmpty()){
            username = showInputDialog(null, "Player name:");
        }
        // make the world
        volume = 0.2d;     
        level = 1;
        world = new Level1();
        world.populate(this);
        timer = new Timer(7000, new MyTimerTask(world.getPlayer()));
        
        spawner = new Timer(2000, new SpawnPlatform(world));
        spawnFuel = new Timer(10000, new SpawnFuel(world));
        view = new MyView(world, 500, 500, getPlayer(), this);
        
        // make a view
        // uncomment this to draw a 1-metre grid over the view
        // view.setGridResolution(1);
        demo = new HighScoreReader("highscore.txt");
        hsWriter = new HighScoreWriter("highscore.txt");
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
        timer.start();
        backgroundMusic();
        
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
        
    public GameLevel getWorld() {
        return world;
    }
    
    public void pause(){
        world.stop();
        timer.stop();
    }
    
    public void restart(){
        world.start();
        if (level < 3){
            timer.start();
        }
    }
    
    public boolean isCurrentLevelCompleted(){
        return world.isCompleted();
    }
    
    static {
        try {
           warp = new SoundClip("data/warp.wav");
         } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
           System.out.println(e);
         }        
    }
    
    public void backgroundMusic(){
        try {
            gameMusic = new SoundClip("data/Track" + getLevel() + ".mp3");   // Open an audio input stream
            gameMusic.play();  // Set it to continous playback (looping)
            gameMusic.setVolume(0.2d);
        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
            System.out.println(e);
        }  
    }

    public SoundClip getGameMusic() {
        return gameMusic;
    }
    
    
    
    //advance to next level
    public void goNextLevel() throws IOException{
        world.stop();
        timer.stop();
        spawner.stop();
        spawnFuel.stop();
        demo.readScores();
        //System.out.println(demo.getPlayerScores().size());
        Bike oldBike = world.getPlayer();
        gameMusic.stop();
        warp.play();
        switch (level) {
            case 4:
                hsWriter.writeHighScore(username, world.getPlayer().getCoinCount());
                System.exit(0);             
            case 0:
                level++;
                backgroundMusic();

                // get a new world
                world = new Level1();
                // fill it with bodies
                world.populate(this);
                // switch the keyboard control to the new player
                controller.setBody(world.getPlayer());
                world.getPlayer().setFuelTank(4);                
                timer = new Timer(5000, new MyTimerTask(world.getPlayer()));
                timer.start();
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
            case 1:
                level++;
                timer.stop();
                
                spawnFuel.start();
                // get a new world
                world = new Level2();
                backgroundMusic();
                JFrame debugView = new DebugViewer(world, 500, 500);
                // fill it with bodies
                world.populate(this);
                // switch the keyboard control to the new player
                controller.setBody(world.getPlayer());
                world.getPlayer().setFuelTank(4);
                timer = new Timer(5000, new MyTimerTask(world.getPlayer()));
                timer.start();               
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
            case 2:
                
                spawner.start();
                timer.stop();
                level++;
                backgroundMusic();
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
            case 3:
                
                timer.stop();
                level++;
                backgroundMusic();
                // get a new world
                world = new Level4();
                
                // fill it with bodies
                world.populate(this);
                // switch the keyboard control to the new player
                controller.setBody(world.getPlayer());
                //world.getPlayer().setGravityScale(0.1f);
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
        //TODO when game starts prompt dialog for name
    }
}
