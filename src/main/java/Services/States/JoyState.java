package Services.States;

import Models.Pet;

public class JoyState extends BaseState {

    public JoyState(Pet pet) {
        super(pet);
        this.name = "Joy";
    }

    @Override
    public void start() {
        System.out.println("Entering Joy State");
<<<<<<< HEAD
        pet.setFun(Math.min(100, pet.getFun() + 25));
        pet.setHungry(pet.getHungry() + 10);
        pet.setDrowsiness(pet.getDrowsiness() + 5);
        System.out.println("Diversão: " + pet.getFun() + " | Fome: " + pet.getHungry() + " | Energia: " + pet.getDrowsiness());
=======
        this.toSleep = false;
        this.toClean = true;
        this.toPlay = false;
        this.toEat = false;
>>>>>>> upstream/main
    }

    @Override
    public void update() {
        
    }

    @Override
    public void exit() {
        System.out.println("Exiting Joy State");
    }
    
}
