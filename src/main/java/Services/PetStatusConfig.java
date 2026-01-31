package Services;

import java.util.Random;

import Models.Pet;
import Services.Time.Time;

public class PetStatusConfig {

    private static final Random random = new Random();
    
    public static void updatePetStatus(Pet pet) {
        pet.setHungry(pet.getHungry() + 2 * Time.deltaTime);
        pet.setCleaning(pet.getCleaning() - 1.5 * Time.deltaTime);
        pet.setFun(pet.getFun() - 1 * Time.deltaTime);
        
        double newHappiness = (100 - pet.getHungry()) * 0.4 + pet.getCleaning() * 0.3 + pet.getFun() * 0.3;
        pet.setHappiness(newHappiness);
    }

    public static void petInitialize(String name, Pet.PetType[] type, Pet pet) {
        pet.setName(name);
        pet.setType(type);

        for (Pet.PetType t : type) {
            switch (t) {
                case FIRE -> {
                    pet.setAttack(pet.getAttack() + random.nextInt(7) + 3);
                    pet.setDefense(pet.getDefense() + random.nextInt(5) + 2);
                }
                case WATER -> {
                    pet.setDefense(pet.getDefense() + random.nextInt(7) + 3);
                    pet.setSpeed(pet.getSpeed() + random.nextInt(5) + 2);
                }
                case EARTH -> {
                    pet.setLife(pet.getLife() + random.nextInt(25) + 15);
                    pet.setDefense(pet.getDefense() + random.nextInt(5) + 2);
                }
                case SKY -> {
                    pet.setSpeed(pet.getSpeed() + random.nextInt(7) + 3);
                    pet.setAttack(pet.getAttack() + random.nextInt(5) + 2);
                }
                case NONE -> {
                }
            }
        }
    }

    public static void loadStatus(Pet pet, double deltaTime) {
        for (double i = 0; i < deltaTime; i++) {
            updatePetStatus(pet);
        }
    }
}
