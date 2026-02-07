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
        pet.setDrowsiness(Math.min(100, pet.getDrowsiness() + 40));
        pet.setFun(Math.max(0, pet.getFun() - 5));
        System.out.println("Energia: " + pet.getDrowsiness() + " | Diversão: " + pet.getFun());
    }

    @Override
    public void update() {

    }

    @Override
    public void exit() {
        System.out.println("Exiting Sleeping State");
    }
    
}
