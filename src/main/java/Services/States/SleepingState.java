package Services.States;

import Models.Pet;
import Services.Time.Time;


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
        double recoveryRate = 10;
        pet.setDrowsiness(pet.getDrowsiness() - (recoveryRate * Time.deltaTime));

        pet.setHungry(pet.getHungry() + 0.5 * Time.deltaTime);
        
        if (pet.getDrowsiness() <= 0){
            pet.SetState(new IdleState(pet));
        }
    }

    @Override
    public void exit() {
        System.out.println("Exiting Sleeping State");
    }
    
    public boolean toSleep = false;
    public boolean toEat = false;
    public boolean toClean = false;
    public boolean toPlay = false;
}
