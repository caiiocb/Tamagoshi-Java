package Controllers;

import App.Main;
import Models.Pet;
import Services.DataSaveSystem;
import Services.GameLoop;
import Services.States.CelaningState;
import Services.States.EatingState;
import Services.States.IdleState;
import Services.States.JoyState;
import Services.States.SleepingState;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.image.ImageView;

public class GameController {

    private Scene scene;
    private Pet pet;
    private GameLoop gameLoop;

    // Componentes da UI para atualizar depois
    private Label statusLabel;
    private ProgressBar cleaningBar;
    private ProgressBar funBar;
    private ProgressBar hungerBar;
    private ProgressBar energyBar;
    private Label nameLabel;
    private Label coinsLabel;
    private ImageView petImageView;

    public GameController(Pet pet) {
        this.pet = pet;
        createView();
        startGameLoop();
    }

    //Método que cria as barras de necessidades, define o tamanho e aplica a cor via código
    private ProgressBar createStyledBar(double value, String colorStyle){
        ProgressBar bar = new ProgressBar(value / 100.0);
        bar.setPrefWidth(300);
        bar.setStyle(colorStyle);
        return bar;
    }
    
    private void createView() {
        BorderPane layout = new BorderPane();
        layout.setPrefSize(400, 600);
        layout.setStyle("-fx-background-color: #f4f4f4;"); //fundo cinza claro

        // --- HUD (Topo) ---
        VBox hud = new VBox(10);
        hud.setPadding(new javafx.geometry.Insets(20));
        hud.setAlignment(Pos.CENTER);
        //Label de moedas
        coinsLabel = new Label("Moedas: " + pet.getCoins());
        coinsLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #2ecc71; -fx-font-weight: bold;");

        nameLabel = new Label(pet.getName());
        nameLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        statusLabel = new Label("Status: Iniciando...");
        statusLabel.setStyle("-fx-font-style: italic ");

        //Criando as barras de nessecidades
        hungerBar = createStyledBar(pet.getHungry(), "-fx-accent: #e74c3c;"); // Vermelho
        funBar = createStyledBar(pet.getFun(), "-fx-accent: #3498db;");       // Azul
        cleaningBar = createStyledBar(pet.getCleaning(), "-fx-accent: #2ecc71;");// Verde
        energyBar = createStyledBar(pet.getDrowsiness(), "-fx-accent: #f1c40f;"); //amarelo


        hud.getChildren().addAll(
                nameLabel, statusLabel,
                new Label("Fome"), hungerBar,
                new Label("Diversão"), funBar,
                new Label("Limpeza"), cleaningBar,
                new Label("Energia"), energyBar);
        layout.setTop(hud);

        // --- AÇÕES (Base) ---
        VBox centerArea = new VBox();
        centerArea.setAlignment(Pos.CENTER);
        // cria o componente (o porta-retrato)
        petImageView = new ImageView();
        petImageView.setFitHeight(200);
        petImageView.setPreserveRatio(true);

        // carrega e definir a imagem
        try {
            var stream = getClass().getResourceAsStream("/imagens/pet_1.png");
            if (stream != null) {
                Image img = new Image(stream);
                petImageView.setImage(img);
            } else {
                System.out.println("Erro: Arquivo não encontrado em resources/imagens/pet_1.png");
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar imagem: " + e.getMessage());
        }
        centerArea.getChildren().add(petImageView);

        layout.setCenter(centerArea);

        //Botões de interação
        HBox actions = new HBox(15); // Espaço de 15px entre os botôes
        actions.setAlignment(Pos.CENTER);
        actions.setPadding(new javafx.geometry.Insets(20));

        // Botão de Alimentar
        Button btnFeed = new Button("Alimentar");
        btnFeed.setPrefSize(100, 40);
        btnFeed.setOnAction(e -> {
            pet.SetState(new EatingState(pet));
        });
        // Botão de Brincar
        Button btnPlay = new Button("Brincar");
        btnPlay.setPrefSize(100, 40);
        btnPlay.setOnAction(e ->{
            pet.SetState(new JoyState(pet));
        });
        // Botão de Limpar
        Button btnClean = new Button("Limpar");
        btnClean.setPrefSize(100, 40);
        btnClean.setOnAction(e -> {
            pet.SetState(new CelaningState(pet));
        });
        // Botão de Dormir
        Button btnSleep = new Button("Dormir");
        btnSleep.setPrefSize(100, 40);
        btnSleep.setOnAction(e ->{
            if (pet.getCurrentState() == null || !(pet.getCurrentState() instanceof SleepingState)) {
                pet.SetState(new SleepingState(pet));
            }
        });
        //Botão de Salvar e Sair
        Button btnSave = new Button("Salvar e Sair");
        btnSave.setPrefSize(100, 40);
        btnSave.setStyle("-fx-base: #f39c12"); // alaranjado para destacar
        btnSave.setOnAction(e -> {
            stopGameLoop(); //Para thread do jogo
            DataSaveSystem.save(pet); //Salvar o estado atual
            Main.showMenu(); //Voltar para o menu
        });

        actions.getChildren().addAll(btnFeed, btnPlay, btnClean,btnSleep, btnSave);
        layout.setBottom(actions);

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
        funBar.setProgress(pet.getFun() / 100.0);
        cleaningBar.setProgress(pet.getCleaning() / 100.0);
        energyBar.setProgress(pet.getDrowsiness() / 100.0);

        // Atualiza o texto de status baseado no State atual do Pet
        if (pet.getCurrentState() != null) {
            statusLabel.setText("Status: " + pet.getCurrentState().name);
        } else {
            statusLabel.setText("Status: Desconecido");
        }
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