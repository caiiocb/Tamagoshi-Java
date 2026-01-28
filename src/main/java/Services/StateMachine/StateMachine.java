package Services.StateMachine;
import Models.Pet;
import Services.StateMachine.States.BaseState;

public class StateMachine {
    public Pet pet;
    public BaseState currentState;

    public StateMachine(Pet pet) {
        this.pet = pet;
    }

    
    public void SwitchState(BaseState newState) {
        if (currentState != null) {
            currentState.exit();
        }
        currentState = newState;
        currentState.start();
    }
}
