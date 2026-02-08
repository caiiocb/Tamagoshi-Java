package Services.States;

import Models.Pet;


public class DeadState extends BaseState {
    
    public DeadState(Pet pet) {
        super(pet);
        this.name = "Dead";
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

    @Override
    public String getImagemState() {
        return "";
    }
}
