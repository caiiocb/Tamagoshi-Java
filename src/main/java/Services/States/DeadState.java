package Services.States;

import Models.Pet;
import Services.AssetManager;

public class DeadState extends BaseState {
    
    public DeadState(Pet pet) {
        super(pet);
        this.name = "Dead";
    }
    
    @Override
    public void start() {
        System.out.println("Pet Morreu... :(");
        pet.setCurrentImage(AssetManager.loadPetImage(StateEnum.MORTO));
    }

    @Override
    public void update() {
        // Nada acontece :(
        pet.setCurrentImage(AssetManager.loadPetImage(StateEnum.MORTO));
    }

    @Override
    public void exit() {
        // Não sai desse estado
    }
    
    // Bloqueio Total
    @Override public boolean canSleep() { return false; }
    @Override public boolean canClean() { return false; }
    @Override public boolean canPlay() { return false; }
    @Override public boolean canEat() { return false; }
}
