package game;

import city.cs.engine.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple character
 */
public class Bike extends Walker {

    // Remember:  using the keyword static below means the fields shape and image belong to the class, rather than any instance. 
    // That means there is a single shape and image for all instances of the class.
    private static final Shape bike = new PolygonShape(
            -2.7f,0.36f, 1.43f,0.5f, 2.35f,
                -0.95f, 2.3f,-1.85f, 1.72f,-2.23f, -2.75f,-2.23f);
    //adding to shapes to one variable for adding the 2 wheels
    SolidFixture bikeFixture = new SolidFixture(this, bike, 55);
    private static final Shape rider = new PolygonShape(-0.01f,2.12f, -0.43f,1.8f, -1.07f,1.3f,
            -1.45f,0.03f, -0.21f,0.21f, 0.95f,0.38f, 0.44f,1.9f);
    SolidFixture riderFixture = new SolidFixture(this, rider, 20);

    private static final BodyImage image =
        new BodyImage("data/bike.png", 5);

    private int coinCount;
    private int fuelTank;
    private int lifeCount;
    private int bombCount;

    public Bike(World world) {
        super(world, bike);
        addImage(image);
        coinCount = 0;
        fuelTank = 4;
        lifeCount = 3;
        bombCount = 3;
    }

    public int getCoinCount() {
        return coinCount;
    }

    public int getFuelTank(){
        return fuelTank;
    }
    
    public int getLifeCount(){
        return lifeCount;
    }
    
    public int getBombCount(){
        return bombCount;
    }
    
    public void incrementCoinCount() {
        coinCount++;
        System.out.println("Rich!  Coin count = " + coinCount);
    }
    
    public void bombUsed(){
        bombCount--;
        System.out.println(bombCount+" bombs left");
    }
    
    public SolidFixture getRiderFixture(){
        return riderFixture;
    }
    
    public void fillTank(){
        if (fuelTank < 4){
            fuelTank++;
        }
        System.out.println("Fuel is now at " + fuelTank + "/4");
    }
    public void fuelConsumption() {
        if (fuelTank < 1){
            System.out.println("GAME OVER!");
            //System.exit(0);
        }
        fuelTank--;
        System.out.println("Fuel " + fuelTank);
        
    }
        

    public void decreaseLife(){
        if (lifeCount > 1){
            lifeCount--;
            System.out.println("Lost 1 life, current lifes" + lifeCount);
        } else{
            System.out.println("GAME OVER!");
            System.exit(0);
        }
    }
    
    
    public void setCoinCount(int cnt){
        coinCount = cnt;
    }
    
    public void setLifeCount(int cnt){
        lifeCount = cnt;
    }
    
    public void setBombCount(int cnt){
        bombCount = cnt;
    }

    public void setFuelTank(int cnt) {
        fuelTank = cnt;
        System.out.println(fuelTank + " fuel");
    }
    
    
    
}
