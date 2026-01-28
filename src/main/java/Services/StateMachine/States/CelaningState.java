package Services.StateMachine.States;

import Services.StateMachine.StateMachine;

public class CelaningState extends BaseState {

    public CelaningState(StateMachine stateMachine) {
        super(stateMachine);
    }

    @Override
    public void start() {
        System.out.println("Entering Cleaning State");
    }

    @Override
    public void update() {
        double cleanPerSecond = 5;
        double cleanPoints = cleanPerSecond * Services.Time.Time.deltaTime;

        stateMachine.pet.setCleaning(stateMachine.pet.getCleaning() + cleanPoints);

        if(stateMachine.pet.getCleaning() >= 100) {
            stateMachine.SwitchState(new IdleState(stateMachine));
        }
    }

    @Override
    public void exit() {
        System.out.println("Exiting Cleaning State");
    }
    
}
