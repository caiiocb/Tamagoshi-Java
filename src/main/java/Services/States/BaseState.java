package Services.States;

import Models.Pet;


public abstract class BaseState {
    public  String name;
    protected Pet pet;
    
    public BaseState(Pet pet) {
        this.pet = pet;
    }

    public abstract void start();
    public abstract void update();
    public abstract void exit();

    // Hooks: Padrão é permitir tudo (return true)
    // Os estados específicos sobrescrevem para bloquear (return false)
    public boolean canSleep() { return true; }
    public boolean canClean() { return true; }
    public boolean canPlay() { return true; }
    public boolean canEat() { return true; }
}
