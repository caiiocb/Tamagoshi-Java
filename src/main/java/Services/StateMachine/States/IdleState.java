package Services.StateMachine.States;

import Services.PetStatusConfig;
import Services.StateMachine.StateMachine;

public class IdleState extends BaseState {
    
    public IdleState(StateMachine stateMachine) {
        super(stateMachine);
    }
    
    @Override
    public void start() {
        
    }

    @Override
    public void update() {
        PetStatusConfig.updatePetStatus(stateMachine.pet);

        if (stateMachine.pet.getLife() <= 0){
            stateMachine.SwitchState(new DeadState(stateMachine));
        }
    }

    @Override
    public void exit() {
        System.out.println("Exiting Baby State");
    }
    
}
