package Services.States;

import Models.Pet;

public class EatingState extends BaseState {
    
    public EatingState(Pet pet) {
        super(pet);
        this.name = "Eating";
    }
    
    @Override
    public void start() {
        System.out.println("Entering Eating State");
    }

    @Override
    public void update() {
        
    }

    @Override
    public void exit() {
        System.out.println("Exiting Eating State");
    }
    
}
