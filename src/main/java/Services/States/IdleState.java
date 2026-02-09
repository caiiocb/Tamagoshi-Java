package Services.States;

import Models.Pet;
import Services.AssetManager;
import Services.PetStatusConfig;

public class IdleState extends BaseState {
    
    public IdleState(Pet pet) {
        super(pet);
        this.name = "Idle";
    }
    
    @Override
    public void start() {
        System.out.println("Entering Idle State");
        this.pet.setCurrentImage(AssetManager.loadPetImage(this.pet));
    }

    @Override
    public void update() {
        PetStatusConfig.updateDecay(this.pet);
        checkDeath();
        this.pet.setCurrentImage(AssetManager.loadPetImage(this.pet));
    }

    @Override
    public void exit() {
        System.out.println("Exiting Baby State");
    }

    private void checkDeath() {
        if (pet.getLife() <= 0) {
            pet.SetState(new DeadState(pet));
        }
    }
}
