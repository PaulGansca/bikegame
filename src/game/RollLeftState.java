package game;

import fsm.FSMState;

public class RollLeftState extends FSMState<Blade> {

    protected void update() {
        Blade blade = getContext();
        if (blade.inRangeRight()) {
            gotoState(new RollRightState());
        } else if (!blade.inRange()) {
            gotoState(new StandStillState());
        } else {
            blade.setAngularVelocity(3);
        }
    }

    protected void enter() {
        Blade blade = getContext();
        blade.setAngularVelocity(1);
    }
    
    protected void exit() {}
}