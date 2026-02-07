package Services.States;

import Models.Pet;

public class JoyState extends BaseState {

    public JoyState(Pet pet) {
        super(pet);
        this.name = "Joy";
    }

    @Override
    public void start() {
        this.toSleep = false;
        this.toClean = true;
        this.toPlay = false;
        this.toEat = false;
    }

    @Override
    public void update() {
        
    }

    @Override
    public void exit() {
        System.out.println("Exiting Joy State");
    }
    
}
