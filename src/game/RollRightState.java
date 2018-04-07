package game;

import fsm.FSMState;

public class RollRightState extends FSMState<Blade> {

    protected void update() {
        Blade blade = getContext();
        if (blade.inRangeLeft()) {
            gotoState(new RollLeftState());
        } else if (!blade.inRange()) {
            gotoState(new StandStillState());
        } else {
            blade.setAngularVelocity(-3);
        }
    }

    protected void enter() {
        Blade blade = getContext();
        blade.setAngularVelocity(-1);
    }
    
    protected void exit() {}
}

