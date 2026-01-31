package Services.StateMachine.States;

import Services.StateMachine.StateMachine;

public class SleepingState extends BaseState {
    
    public SleepingState(StateMachine stateMachine) {
        super(stateMachine);
    }
    
    @Override
    public void start() {
        System.out.println("Entering Sleeping State");
    }

    @Override
    public void update() {
        double energyPerSecond = 2;
        double energyPoints = energyPerSecond * Services.Time.Time.deltaTime;

        stateMachine.pet.setDrowsiness(stateMachine.pet.getDrowsiness() + energyPoints);

        if(stateMachine.pet.getDrowsiness() >= 100) {
            stateMachine.SwitchState(new IdleState(stateMachine));
        }
    }

    @Override
    public void exit() {
        System.out.println("Exiting Sleeping State");
    }
    
}
