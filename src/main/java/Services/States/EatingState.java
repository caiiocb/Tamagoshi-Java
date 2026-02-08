package Services.States;

import Models.Pet;
import Services.Time.Time;

public class EatingState extends BaseState {
    
    public EatingState(Pet pet) {
        super(pet);
        this.name = "Eating";
    }
    
    @Override
    public void start() {

    }

    @Override
    public void update() {
    }

    @Override
    public void exit() {
        System.out.println("Exiting Eating State");
    }

    @Override
    public String getImagemState() {
        return "/imagens/fome.png";
    }
}
