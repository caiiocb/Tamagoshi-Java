package App;

import Controllers.GameController;
import Controllers.MenuController;
import Controllers.NamingController;
import Models.Pet;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        window = stage;
        window.setTitle("Tamagotchi");
        window.setResizable(false);

        // Inicia exibindo o Menu
        showMenu();
        
        window.show();
    }

    // --- Métodos de Roteamento (Router) ---

    public static void showMenu() {
        MenuController menu = new MenuController(); // Instancia o objeto da tela
        window.setScene(menu.getScene());
    }

    public  static void showNamingScreen(){
        NamingController namingController = new NamingController();
        window.setScene(namingController.getScene());
    }
    public static void startGame(Pet pet) {
        GameController game = new GameController(pet); // Passa o Pet para o controlador do jogo
        window.setScene(game.getScene());
    }
    
    public static void closeGame() {
        window.close();
        System.exit(0);
    }
}