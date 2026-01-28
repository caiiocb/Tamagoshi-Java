package Services.StateMachine.States;

public class BabyState extends BaseState {
    @Override
    public void start() {
        
    }

    @Override
    public void update() {
        System.out.println("Updating Baby State");
    }

    @Override
    public void exit() {
        System.out.println("Exiting Baby State");
    }
    
}
