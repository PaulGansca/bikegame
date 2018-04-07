/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import city.cs.engine.Body;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.UserView;
import city.cs.engine.World;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Paul Gansca
 */
public class MyView extends UserView {
    
    
    private Image life;
    private Image coin;
    private Image bomb;
    private Image fuel;
    private Bike bike;
    public int count;
    private Game game;
    
    public MyView(World world, int width, int height, Bike bike, Game game) {
        super(world, width, height);
               
        life = new ImageIcon("data/heart.png").getImage();
        coin = new ImageIcon("data/coin.png").getImage();
        bomb = new ImageIcon("data/bomb.gif").getImage();
        this.bike = bike;
        this.game = game; 
        
    }
//TODO fix parallax scrolling
    @Override
    protected void paintBackground(Graphics2D g) {
        //System.out.println("paintBackground");
        String back = ".png";
        String number = String.valueOf(game.getLevel());
        back = number+back;
        Image background = new ImageIcon("data/desert"+back).getImage();
        g.drawImage(background, 0, 0, this);
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }
    
    
    
    
        @Override
    protected void paintForeground(Graphics2D g) {
        //System.out.println("paintBackground");
        g.drawString("Life", 0, 20);
        int adjust = 5;
            for (int i=0; i<bike.getLifeCount(); i++){
                g.drawImage(life, 15+adjust, 0, this);
                adjust = adjust+35;
            }
        // consider looop and count variable 
        g.drawString("Coins: " + bike.getCoinCount(), 0, 50);
        g.drawImage(coin, 50, 33, this);
        g.drawString("Bombs: " + bike.getBombCount(), 0, 80);
        g.drawImage(bomb, 58, 60, this);
        if (game.getLevel() < 3){
            for (int i=0; i<game.getPlayer().getFuelTank(); i++){
                count = game.getPlayer().getFuelTank();
                fuel = new ImageIcon("data/fuel"+ count + ".png").getImage();
                g.drawImage(fuel, 470, 320, this);
            }
        }
    }
}
