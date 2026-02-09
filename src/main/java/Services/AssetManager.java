package Services;

import java.io.InputStream;

import Models.Pet;
import Services.States.StateEnum;
import javafx.scene.image.Image;

public class AssetManager {

    public static Image loadPetImage(Pet pet) {
        if (pet.getLife() <= 0) return loadPetImage(StateEnum.MORTO);

        // Lógica baseada em status
        boolean isDirty = pet.getCleaning() <= 20;
        boolean isHungry = pet.getHungry() >= 80;

        // Se estiver sujo e com fome -> triste_sujo
        if (isDirty && isHungry) return loadPetImage(StateEnum.TRISTE_SUJO);
        
        // Se estiver sujo apenas -> sujo
        if (isDirty) return loadPetImage(StateEnum.SUJO);
        
        // Se estiver com fome, mas n estiver sujo -> triste
        if (isHungry) return loadPetImage(StateEnum.TRISTE);

        // Se estiver com os status ok ele fica em idle
        return loadPetImage(StateEnum.NORMAL);
    }

    public static Image loadPetImage(StateEnum state) {
        String path = null;

        path = switch (state) {
            case SUJO -> "/images/Catow/sujo.png";
            case TRISTE_SUJO -> "/images/Catow/triste_sujo.png";
            case LIMPO -> "/images/Catow/Idle.png";
            case COMENDO -> "/images/Catow/comendo.png";
            case NORMAL -> "/images/Catow/Idle.png";
            case TRISTE -> "/images/Catow/triste.png";
            case DORMINDO -> "/images/Catow/dormindo.png";
            case DORMINDO_SUJO -> "/images/Catow/dormindo_sujo.png";
            case FOME -> "/images/Catow/fome.png";
            case MORTO -> "/images/Catow/triste.png";
            default -> "/images/Catow/Idle.png";
        };

        InputStream is = AssetManager.class.getResourceAsStream(path);
        
        if (is == null) {
            System.err.println("Não foi possível encontrar a imagem no caminho: " + path);
            return null;
        }

        return new Image(is);
    }
}
