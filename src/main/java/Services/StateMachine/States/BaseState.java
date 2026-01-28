package Services.StateMachine.States;

import Services.StateMachine.StateMachine;

public abstract class BaseState {
    protected StateMachine stateMachine;

    public BaseState(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    public abstract void start();
    public abstract void update();
    public abstract void exit();

}
