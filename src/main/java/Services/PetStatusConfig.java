package Services;

import java.util.Random;

import Models.Pet;
import Services.Time.Time;
public class PetStatusConfig {

    private static final Random random = new Random();

    private static final double HUNGER_RATE = 2.0;      // Ganha 2 de fome por segundo
    private static final double HYGIENE_RATE = 1.0;     // Perde 1 de limpeza por segundo
    private static final double FUN_RATE = 1.5;         // Perde 1.5 de diversão por segundo
    private static final double DROWSINESS_RATE = 0.8;  // Fica 0.8 mais cansado por segundo
    private static final double HEALTH_DECAY = 5.0;     // Se estiver crítico, perde 5 de vida
    
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

    public static void updateDecay(Pet pet) {
        // 1. Fome aumenta (0 = cheio, 100 = morrendo de fome)
        pet.setHungry(pet.getHungry() + (HUNGER_RATE * Time.deltaTime));

        // 2. Limpeza diminui (100 = limpo, 0 = sujo)
        pet.setCleaning(pet.getCleaning() - (HYGIENE_RATE * Time.deltaTime));

        // 3. Diversão diminui (100 = feliz, 0 = entediado)
        pet.setFun(pet.getFun() - (FUN_RATE * Time.deltaTime));

        // 4. Cansaço aumenta (0 = acordado, 100 = exausto)
        pet.setDrowsiness(pet.getDrowsiness() + (DROWSINESS_RATE * Time.deltaTime));

        double hungerPenalty = 100 - pet.getHungry();
        double sleepPenalty = 100 - pet.getDrowsiness();
        
        double currentHappiness = (hungerPenalty + sleepPenalty + pet.getCleaning() + pet.getFun()) / 4.0;
        pet.setHappiness(currentHappiness);

        checkCriticalCondition(pet);
    }

    private static void checkCriticalCondition(Pet pet) {
        boolean isStarving = pet.getHungry() >= 90;
        boolean isDirty = pet.getCleaning() <= 10;
        
        if (isStarving || isDirty) {
            System.out.println("Pet está sofrendo dano por negligência!");
            pet.setLife((int) (pet.getLife() - (HEALTH_DECAY * Time.deltaTime)));
        }
    }

}
