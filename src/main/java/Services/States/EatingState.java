package Services.States;

import Models.Pet;
import Services.AssetManager;
import Services.Time.Time;

public class EatingState extends BaseState {
    
    private final double duration = 2.0; // Dura 2 segundos
    private double timer = 0;

    public EatingState(Pet pet) {
        super(pet);
        this.name = "Eating";
    }
    
    @Override
    public void start() {
        System.out.println("Entering Eating State - Nhac nhac!");
        timer = 0;
        pet.setCurrentImage(AssetManager.loadPetImage(StateEnum.NORMAL));
    }

    @Override
    public void update() {
        pet.setCurrentImage(AssetManager.loadPetImage(StateEnum.NORMAL));
        timer += Time.deltaTime;

        // Recupera MUITA fome rapidamente
        pet.setHungry(pet.getHungry() - (30 * Time.deltaTime));

        // Se cheiinho, para de comer
        if (timer >= duration || pet.getHungry() <= 0) {
            pet.SetState(new IdleState(pet));
        }
    }

    @Override
    public void exit() {
        System.out.println("Exiting Eating State");
    }

    // Bloqueios: Enquanto come, não faz mais nada
    @Override public boolean canSleep() { return false; }
    @Override public boolean canClean() { return false; }
    @Override public boolean canPlay() { return false; }
    @Override public boolean canEat() { return false; }
}
