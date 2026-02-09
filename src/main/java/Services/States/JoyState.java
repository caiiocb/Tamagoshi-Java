package Services.States;

import Models.Pet;
import Services.AssetManager;
import Services.Time.Time;

public class JoyState extends BaseState {

    private final double duration = 3.0; // Brinca por 3 segundos
    private double timer = 0;

    public JoyState(Pet pet) {
        super(pet);
        this.name = "Joy";
    }

    @Override
    public void start() {
        System.out.println("Entering Joy State - Yahoo!");
        timer = 0;
        pet.setCurrentImage(AssetManager.loadPetImage(StateEnum.NORMAL));
    }

    @Override
    public void update() {
        pet.setCurrentImage(AssetManager.loadPetImage(StateEnum.NORMAL));
        timer += Time.deltaTime;

        pet.setFun(pet.getFun() + (20 * Time.deltaTime));
        pet.setDrowsiness(pet.getDrowsiness() + (5 * Time.deltaTime));
        pet.setHungry(pet.getHungry() + (5 * Time.deltaTime));

        if (timer >= duration) {
            pet.SetState(new IdleState(pet));
        }
    }

    @Override
    public void exit() {
        System.out.println("Exiting Joy State");
    }

    // Bloqueios
    @Override public boolean canSleep() { return false; }
    @Override public boolean canClean() { return false; }
    @Override public boolean canPlay() { return false; }
    @Override public boolean canEat() { return false; } 
}
