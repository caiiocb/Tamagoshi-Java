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
        pet.setHungry(Math.max(0, pet.getHungry() - 30));
        pet.setHappiness(pet.getHappiness() + 10);
        System.out.println("Fome: " + pet.getHungry() + " | Felicidade: " + pet.getHappiness());
    }

    @Override
    public void update() {
        
    }

    @Override
    public void exit() {
        System.out.println("Exiting Eating State");
    }
    
}
