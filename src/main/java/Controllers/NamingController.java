package Controllers;

import App.Main;
import Models.Pet;
import Models.PetType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
        typeSelector.setValue(PetType.EARTH); // Valor padrão

        Button btnStart = new Button("Começar Jogo!!");
        btnStart.setOnAction(e ->{
            String name = nameField.getText();
            PetType selectedType = typeSelector.getValue();
            
            if (name == null || name.trim().isEmpty()) {
                showAlert("Erro", "O nome do pet não pode ser vazio!");
                return;
            }
            
            if (selectedType == null || selectedType == PetType.NONE) {
                showAlert("Erro", "Selecione um tipo válido para o pet!");
                return;
            }

            //Cria um novo pet e inicia jogo
            Pet newPet = new Pet(name, new PetType[]{selectedType});
            Main.startGame(newPet);
        });
        layout.getChildren().addAll(title, new Label("Nome: "), nameField, new Label("Tipo: "), typeSelector, btnStart);
        this.scene = new Scene(layout);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public Scene getScene(){return scene; }
}
