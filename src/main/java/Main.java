import Models.Pet;
import Services.SaveManager;

public class Main {
    public static void main (String[] args){
        Pet meuPet = SaveManager.load();
        if(meuPet == null ){
            meuPet = new Pet("Lady");
            System.out.println("Novo pet criado: " + meuPet.getName());
        }else{
            System.out.println("Bem vindo de volta, " + meuPet.getName());
        }

    }
}