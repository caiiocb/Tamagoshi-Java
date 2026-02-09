package Services;

import java.io.InputStream;

import Models.Pet;
import Services.States.StateEnum;
import javafx.scene.image.Image;

public class AssetManager {

    public static Image loadPetImage(Pet pet) {
        if (pet.getLife() <= 0) return loadPetImage(StateEnum.MORTO);

        // Lógica baseada em status (Alterado para <50% ou >50% conforme solicitação)
        // Limpeza cai, então < 50 é ruim
        boolean isDirty = pet.getCleaning() < 50; 
        
        // Fome sobe, então > 50 é ruim
        boolean isHungry = pet.getHungry() > 50;  
        
        // Energia sobe (0=acordado, 100=sono), então > 50 é sono
        boolean isSleepy = pet.getDrowsiness() > 50;

        // Se estiver com muito sono (Prioridade visual alta)
        if (isSleepy && isDirty) return loadPetImage(StateEnum.DORMINDO_SUJO);
        if (isSleepy) return loadPetImage(StateEnum.DORMINDO);

        // Se estiver sujo e com fome -> triste_sujo
        if (isDirty && isHungry) return loadPetImage(StateEnum.TRISTE_SUJO);
        
        // Se estiver sujo apenas -> sujo
        if (isDirty) return loadPetImage(StateEnum.SUJO);
        
        // Se estiver com fome, mas n estiver sujo -> fome/triste
        if (isHungry) return loadPetImage(StateEnum.FOME); // Ou TRISTE se não tiver img de fome separada

        // Se estiver com os status ok ele fica em idle
        return loadPetImage(StateEnum.NORMAL);
    }

    public static Image loadPetImage(StateEnum state) {
        String path = null;

        path = switch (state) {
            case SUJO -> "/images/Catow/sujo.png";
            case TRISTE_SUJO -> "/images/Catow/triste_sujo.png";
            case LIMPO -> "/images/Catow/Idle.png";
            case COMENDO -> "/images/Catow/Idle.png";
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
