package Services.States;

import Models.Pet;


public class SleepingState extends BaseState {

    public SleepingState(Pet pet) {
        super(pet);
        this.name = "Sleeping";
    }
    
    @Override
    public void start() {
        System.out.println("Entering Sleeping State");
    }

    @Override
    public void update() {

    }

    @Override
    public void exit() {
        System.out.println("Exiting Sleeping State");
    }
    
}
