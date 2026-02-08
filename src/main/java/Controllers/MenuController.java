package Controllers;

import App.Main;
import Models.Pet;
import Models.PetType;
import Services.DataSaveSystem; // Sua classe de save
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MenuController {

    private Scene scene;

    public MenuController() {
        createView();
    }

    private void createView() {
        // 1. Configuração do Layout (View)
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPrefSize(400, 500);
        layout.setStyle("-fx-background-color: #f0f4f8;");

        Label title = new Label("Tamagotchi Java");
        title.setFont(new Font("Arial Bold", 24));

        // 2. Criação dos Botões
        Button btnPlay = new Button("Novo Jogo");
        Button btnLoad = new Button("Carregar Jogo");
        Button btnExit = new Button("Sair");
        
        styleButton(btnPlay);
        styleButton(btnLoad);
        styleButton(btnExit);

        // 3. Lógica dos Botões (Controller Logic)
        
        // JOGAR: Cria novo pet e troca para tela de jogo
        btnPlay.setOnAction(e -> {
            // Main é nossa classe principal, chamamos o método estático
            Main.showNamingScreen();
        });

        // CARREGAR: Busca do disco e troca para tela de jogo
        btnLoad.setOnAction(e -> {
            Pet loadedPet = DataSaveSystem.load();
            if (loadedPet != null) {
                Main.startGame(loadedPet);
            } else {
                System.out.println("Nenhum save encontrado!");
                // Aqui você poderia adicionar um Label de erro na interface
            }
        });

        // SAIR
        btnExit.setOnAction(e -> Main.closeGame());

        // Montagem
        layout.getChildren().addAll(title, btnPlay, btnLoad, btnExit);
        this.scene = new Scene(layout);
    }

    public Scene getScene() {
        return scene;
    }

    private void styleButton(Button btn) {
        btn.setPrefWidth(200);
        btn.setPrefHeight(40);
        btn.setFont(new Font(14));
    }
}