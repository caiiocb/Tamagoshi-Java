package Services;

import Services.StateMachine.StateMachine;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import Models.Pet;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaveManager {
    private static final String SAVE_FILE = "pet_save.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    //Método para salvar Pet no arquivo
    public static void save(Pet pet){
        pet.setTimestamp(System.currentTimeMillis());
        try(FileWriter writer = new FileWriter(SAVE_FILE)) {
            gson.toJson(pet, writer);
            System.out.println("Jogo salvo com sucesso");
        } catch (IOException e){
            System.err.println("Erro ao salvar o jogo: " + e.getMessage());
        }
    }
    //Método para carregar o Pet do arquivo
    public static Pet load(){
        try(FileReader reader = new FileReader(SAVE_FILE)){
            Pet pet = gson.fromJson(reader, Pet.class);
            if (pet != null) {
                pet.stateMachine = new StateMachine(pet);
            }
            return pet;
        } catch(IOException e){
            System.out.println("Nenhum save encontrado. Iniciando novo jogo.");
            return null;
        }
    }
}
