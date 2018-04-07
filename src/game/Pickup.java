package game;

import city.cs.engine.*;

/**
 * Collision listener that allows the bird to collect things.
 */
public class Pickup implements CollisionListener {

    private Bike bike;

    public Pickup(Bike bike) {
        this.bike = bike;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getReportingBody() == bike) {
            if (e.getOtherBody() instanceof Coin) {
                bike.incrementCoinCount();
                e.getOtherBody().destroy();

            } else if (e.getOtherBody() instanceof Fuel) {
                bike.fillTank();
                e.getOtherBody().destroy();

            } else if (e.getOtherBody() instanceof Spikes || e.getOtherBody() instanceof Blade) {
                System.out.println("Game OVER!");
            } 
            if (e.getReportingFixture().equals(bike.getRiderFixture())
                    && e.getOtherBody().getClass()!=Coin.class
                    && e.getOtherBody().getClass()!= Fuel.class
                    && e.getOtherBody().getClass()!=Goal.class) {
                bike.decreaseLife();
            }

        }
        
        else if (e.getOtherBody() instanceof Bomb) {
                e.getOtherBody().destroy();
                e.getReportingBody().destroy();
                System.out.println("boom");

        } 

    }
}
