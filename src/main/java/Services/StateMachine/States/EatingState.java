package Services.StateMachine.States;

import Services.StateMachine.StateMachine;
import Services.Time.Time;

public class EatingState extends BaseState {
    
    public EatingState(StateMachine stateMachine) {
        super(stateMachine);
    }
    
    @Override
    public void start() {
        System.out.println("Entering Eating State");
    }

    @Override
    public void update() {
        double initialTime = 0 + Time.deltaTime;
        double eatingPerSecond = 1;
        double eatingPoints = eatingPerSecond * Time.deltaTime;

        stateMachine.pet.setFood(stateMachine.pet.getFood() - 1);
        
        if(stateMachine.pet.getHungry() > 100) return;
        
        stateMachine.pet.setHungry(stateMachine.pet.getHungry() - eatingPoints);

        if(stateMachine.pet.getHungry() <= 0 || initialTime >= 5) {
            stateMachine.SwitchState(new IdleState(stateMachine));
        }
    }

    @Override
    public void exit() {
        System.out.println("Exiting Eating State");
    }
    
}
