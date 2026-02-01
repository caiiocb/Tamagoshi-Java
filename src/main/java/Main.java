import Models.Pet;
import Services.SaveManager;

public class Main {
    public static void main (String[] args){
        Pet meuPet = SaveManager.load();
        meuPet = new Pet("Lady");
        if(meuPet == null ){
            meuPet = new Pet("Lala");
        }else{
            System.out.println("Bem vindo de volta, " + meuPet.getName());
        }
        SaveManager.save(meuPet);
    }
}
