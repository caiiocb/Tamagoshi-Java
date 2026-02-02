package Controllers;

import App.Main;
import Models.Pet;
import Services.DataSaveSystem;
import Services.GameLoop;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class GameController {

    private Scene scene;
    private Pet pet;
    private GameLoop gameLoop;

    // Componentes da UI para atualizar depois
    private Label statusLabel;
    private ProgressBar cleaningBar;
    private ProgressBar funBar;
    private ProgressBar hungerBar;

    public GameController(Pet pet) {
        this.pet = pet;
        createView();
        startGameLoop();
    }

    private void createView() {
        BorderPane layout = new BorderPane();
        layout.setPrefSize(400, 500);

        // --- HUD (Topo) ---
        VBox hud = new VBox(10);
        hud.setAlignment(Pos.CENTER);
        
        Label nameLabel = new Label("Pet: " + pet.getName());
        statusLabel = new Label("Status: Iniciando...");
        hungerBar = new ProgressBar(pet.getHungry() / 100.0);
        hungerBar.setPrefWidth(300);

        hud.getChildren().addAll(nameLabel, statusLabel, new Label("Fome"), hungerBar);
        layout.setTop(hud);

        // --- AÇÕES (Base) ---
        VBox controls = new VBox(10);
        controls.setAlignment(Pos.CENTER);
        
        Button btnSave = new Button("Salvar e Sair");
        btnSave.setOnAction(e -> {
            stopGameLoop();
            DataSaveSystem.save(pet);
            Main.showMenu();
        });

        controls.getChildren().add(btnSave);
        layout.setBottom(controls);

        this.scene = new Scene(layout);
    }

    private void startGameLoop() {
        // Instancia o GameLoop passando o pet
        this.gameLoop = new GameLoop(pet); 
        
        // Inicia a Thread do loop
        gameLoop.start(); 

        // Cria um timer visual separado apenas para atualizar a UI do JavaFX
        // Isso evita conflito de Threads entre o GameLoop e a UI
        Thread uiUpdater = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100); // 10fps para UI está ótimo
                    Platform.runLater(this::updateUI);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        uiUpdater.setDaemon(true);
        uiUpdater.start();
    }

    private void updateUI() {
        // Atualiza as barras com os dados reais do Pet
        hungerBar.setProgress(pet.getHungry() / 100.0);
        // statusLabel.setText(...)
    }

    public void stopGameLoop() {
        if (gameLoop != null) {
            gameLoop.stop();
        }
    }

    public Scene getScene() {
        return scene;
    }
}