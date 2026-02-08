package Services.States;

import Models.Pet;


public class CelaningState extends BaseState {

    public CelaningState(Pet pet) {
        super(pet);
        this.name = "Cleaning";
    }

    @Override
    public void start() {
    
    }

    @Override
    public void update() {
        
    }

    @Override
    public void exit() {
        System.out.println("Exiting Cleaning State");
    }

    @Override
    public String getImagemState() {
        return "/imagens/sujo.png";
    }
}
