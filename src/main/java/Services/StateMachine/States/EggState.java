package Services.StateMachine.States;

import Services.StateMachine.StateMachine;

public class EggState extends BaseState {
    public double eggTime;
    
    public EggState(StateMachine stateMachine) {
        super(stateMachine);
    }
    
    @Override
    public void start() {
        eggTime = 0;
    }

    @Override
    public void update() {
        eggTime += 1 * Services.Time.Time.deltaTime;

        if (eggTime >= 60 * 3 ) { // 3 minutos
            stateMachine.SwitchState(new IdleState(stateMachine));
        }
    }

    @Override
    public void exit() {
        System.out.println("Exiting Egg State");
    }
    
}
