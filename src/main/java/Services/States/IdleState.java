package Services.States;

import Models.Pet;
import Services.PetStatusConfig;

public class IdleState extends BaseState {
    
    public IdleState(Pet pet) {
        super(pet);
        this.name = "Idle";
    }
    
    @Override
    public void start() {
        System.out.println("Entering Idle State");

    }

    @Override
    public void update() {
        PetStatusConfig.updateDecay(this.pet);
        checkDeath();
    }

    @Override
    public void exit() {
        System.out.println("Exiting Baby State");
    }

    @Override
    public String getImagemState() {
        return "/imagens/idle.png";
    }

    private void checkDeath() {
        if (pet.getLife() <= 0) {
            pet.SetState(new DeadState(pet));
        }
    }
}
