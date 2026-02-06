package Services;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Models.Pet;

public class DataSaveSystem {
    private static final String SAVE_FILE = "tamagochi_save.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();    

    public static void save(Pet pet) {
        pet.setTimestamp(System.currentTimeMillis());

        try {
            try (Writer writer = new FileWriter(SAVE_FILE)) {
                gson.toJson(pet, writer);
            }
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Pet load(){
        if (!Files.exists(Paths.get(SAVE_FILE))){
            return null;
        }

        try (Reader reader = new FileReader(SAVE_FILE)) {
            Pet loadedPet = gson.fromJson(reader, Pet.class);

            if (loadedPet != null){
                loadedPet.reinitializeAfterLoad();
            }

            return loadedPet;
        } 
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
