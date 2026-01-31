package Services.StateMachine.States;

import Services.StateMachine.StateMachine;

public class DeadState extends BaseState {
    
    public DeadState(StateMachine stateMachine) {
        super(stateMachine);
    }
    
    @Override
    public void start() {
        System.out.println("Pet has died.");
        exit();
    }

    @Override
    public void update() {
        // No updates needed for dead state
    }

    @Override
    public void exit() {
        // No exit actions needed for dead state
    }
    
}
