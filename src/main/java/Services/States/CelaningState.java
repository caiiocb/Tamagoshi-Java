package Services.States;

import Models.Pet;
import Services.AssetManager;
import Services.Time.Time;

public class CelaningState extends BaseState {

    private double duration = 5.0; // Banho dura 5 segundos
    private double timer = 0;

    public CelaningState(Pet pet) {
        super(pet);
        this.name = "Cleaning";
    }

    @Override
    public void start() {
        System.out.println("Entering Cleaning State - Scrub Scrub!");
        timer = 0;
        pet.setCurrentImage(AssetManager.loadPetImage(StateEnum.NORMAL)); // Poderia ser SUJO ou específico
    }

    @Override
    public void update() {
        pet.setCurrentImage(AssetManager.loadPetImage(StateEnum.NORMAL));
        timer += Time.deltaTime;
        
        // Limpeza em 5s
        // Total: 30 -> 6/s
        pet.setCleaning(pet.getCleaning() + (6 * Time.deltaTime));

        if (timer >= duration || pet.getCleaning() >= 100) {
            pet.SetState(new IdleState(pet));
        }
    }

    @Override
    public void exit() {
        System.out.println("Exiting Cleaning State");
    }
    
    // Bloqueios
    @Override public boolean canSleep() { return false; }
    @Override public boolean canClean() { return false; }
    @Override public boolean canPlay() { return false; }
    @Override public boolean canEat() { return false; }
}
