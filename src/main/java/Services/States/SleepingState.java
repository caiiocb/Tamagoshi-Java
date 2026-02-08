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

    @Override
    public String getImagemState() {
        if (pet.getCleaning() < 30) {
            return "/imagens/dormindo_sujo.png";
        }
        return "/imagens/dormindo.png";
    }

    public boolean toSleep = false;
    public boolean toEat = false;
    public boolean toClean = false;
    public boolean toPlay = false;
}
