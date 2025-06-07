package gestclub;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GestClubApp extends Application {
    
    private BorderPane rootPane;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        
        this.primaryStage = primaryStage;
        this.rootPane = new BorderPane();

        Scene scene = new Scene(rootPane);

        scene.getStylesheets().add(GestClubApp.class.getResource("style.css").toExternalForm());
    

        primaryStage.setTitle("GestClub App");
        primaryStage.setScene(scene);

        loadListeMembre();
        showSaisieMembre(); // Affichage temporaire pour validation

        primaryStage.show();

    }

    public void loadListeMembre() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GestClubApp.class.getResource("view/ListeMembre.fxml"));
        
            BorderPane vueListe = loader.load();
            this.rootPane.setCenter(vueListe);
        }   catch (IOException e) {
            System.out.println("Ressource FXML non disponible : ListeMembres");
            System.exit(1);
        }
    }

    public void showSaisieMembre() {

        try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GestClubApp.class.getResource("view/SaisieMembre.fxml"));

        BorderPane vueSaisie = loader.load();
        Scene scene = new Scene(vueSaisie);

        scene.getStylesheets().setAll(primaryStage.getScene().getStylesheets());
        
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edition membre");
        dialogStage.initOwner(this.primaryStage);
        dialogStage.setScene(scene);
        
        dialogStage.show();

        } catch (IOException e) {
            System.out.println("Ressource FXMML ou CSS non disponible : SaisieMembres");
            System.exit(1);
        }
    }

    public static void main2(String[] args) {
        Application.launch(args);
    }
}
