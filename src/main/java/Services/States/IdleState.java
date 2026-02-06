package Services.States;

import Models.Pet;

public class IdleState extends BaseState {
    
    public IdleState(Pet pet) {
        super(pet);
        this.name = "Idle";
    }
    
    @Override
    public void start() {
        
    }

    @Override
    public void update() {

    }

    @Override
    public void exit() {
        System.out.println("Exiting Baby State");
    }
    
}
