package game;

import fsm.FSMState;
import org.jbox2d.common.Vec2;

public class StandStillState extends FSMState<Blade> {

    protected void update() {
        Blade blade = getContext();
        if (blade.inRangeLeft()) {
            gotoState(new RollLeftState());
        } else if (blade.inRangeRight()) {
            gotoState(new RollRightState());
        }
    }

    protected void enter() {
        Blade blade = getContext();
        blade.setAngularVelocity(0);
        blade.setLinearVelocity(new Vec2());
    }

    protected void exit() {
    }
}
