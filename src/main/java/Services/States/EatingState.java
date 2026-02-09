package Services.States;

import Models.Pet;
import Services.AssetManager;
import Services.Time.Time;

public class EatingState extends BaseState {
    
    private final double duration = 5.0; // Dura 5 segundos
    private double timer = 0;

    public EatingState(Pet pet) {
        super(pet);
        this.name = "Eating";
    }
    
    @Override
    public void start() {
        System.out.println("Entering Eating State - Nhac nhac!");
        timer = 0;
        pet.setCurrentImage(AssetManager.loadPetImage(StateEnum.COMENDO));
    }

    @Override
    public void update() {
        pet.setCurrentImage(AssetManager.loadPetImage(StateEnum.COMENDO));
        timer += Time.deltaTime;

        // Recupera fome progressivamente ao longo de 5 segundos
        // Total esperado: 30 pontos. Taxa = 30 / 5 = 6 pontos por segundo
        pet.setHungry(pet.getHungry() - (6 * Time.deltaTime));

        // Se cheiinho ou acabou o tempo, volta para idle
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
