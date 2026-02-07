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

    
    public boolean toSleep = true;
    public boolean toClean = true;
    public boolean toPlay = true;
    public boolean toEat = true;
}
