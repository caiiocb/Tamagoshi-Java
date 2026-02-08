package Controllers;

import App.Main;
import Models.Pet;
import Models.PetType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
public class NamingController {
    private Scene scene;

    public NamingController(){
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #f4f4f4; -fx-padding: 20;");
        layout.setPrefSize(400, 500);

        Label title = new Label("Configure seu novo pet");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField nameField = new TextField();
        nameField.setPromptText("Digite o nome do pet...");
        nameField.setMaxWidth(200);

        ComboBox<PetType> typeSelector = new ComboBox<>();
        typeSelector.getItems().addAll(PetType.values());
        typeSelector.setValue(PetType.NONE);

        Button btnStart = new Button("Começar Jogo!!");
        btnStart.setOnAction(e ->{
            String name = nameField.getText();
            if (!name.isEmpty()){
                //Cria um novo pet e inicia jogo
                Pet newPet = new Pet(name, new PetType[]{typeSelector.getValue()});
                Main.startGame(newPet);
            }
        });
        layout.getChildren().addAll(title, new Label("Nome: "), nameField, new Label("Tipo: "), typeSelector, btnStart);
        this.scene = new Scene(layout);
    }

    public Scene getScene(){return scene; }
}
