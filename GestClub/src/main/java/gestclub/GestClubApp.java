package gestclub;
	
import java.io.IOException;
import java.time.LocalDate;

import gestclub.model.EtatMembre;
import gestclub.model.Membre;
import gestclub.view.ListeMembresController;
import gestclub.view.SaisieMembreController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class GestClubApp extends Application {
	
	private BorderPane rootPane;
	private Stage      primaryStage;
	
	private final ObservableList<Membre> listeMembres = FXCollections.observableArrayList();
	
	public GestClubApp() {
		// Création de quelques membres de départ dans liste Membre
		// code temporaire en attendant d'avoir les méthodes le sauvegarde/restauration en base ou dans des fichiers.
		this.listeMembres.add(new Membre("Floraville", "Rose", EtatMembre.Membre, "Toulouse", LocalDate.of(1985, 11, 13), "Amis de Tom" ));
        this.listeMembres.add(new Membre("Brown", "Emet", EtatMembre.Ancien, "Paris", LocalDate.of(1855, 9, 12), "Chercheur fou" ));
		this.listeMembres.add(new Membre("D'Idrila", "Argenti", EtatMembre.Ancien, "Espace", LocalDate.of(1310, 2, 14), "Chevalier de la beauté" ));
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
		this.rootPane     = new BorderPane();
		
		Scene scene = new Scene(rootPane);
		scene.getStylesheets().add(GestClubApp.class.getResource("style.css").toExternalForm());
		primaryStage.setTitle("GestClub App");
		primaryStage.setScene(scene);

		loadListeMembre();
		//showSaisieMembre();

		primaryStage.show();		
		
	}
	
	public void loadListeMembre() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation( GestClubApp.class.getResource("view/ListeMembres.fxml"));
			
			BorderPane vueListe = loader.load();
			
			ListeMembresController ctrl = loader.getController();
			ctrl.setListeMembre( this.listeMembres );
			ctrl.setGetClubApp(this);
			ctrl.initialisation();
			
			this.rootPane.setCenter( vueListe );
						
		} catch (IOException e) {
			System.out.println("Ressource FXML non disponible : ListeMembres");
			System.exit(1);
		}	
	}
	
	public Membre showSaisieMembre() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation( GestClubApp.class.getResource("view/SaisieMembre.fxml"));
			
			BorderPane vueSaisie = loader.load();
			
			Scene scene = new Scene(vueSaisie);
			scene.getStylesheets().setAll( primaryStage.getScene().getStylesheets() );
			
			Stage dialogStage =new Stage();
			dialogStage.setTitle("Edition membre");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(this.primaryStage);
			dialogStage.setScene(scene);
			
			SaisieMembreController ctrl = loader.getController();
			ctrl.setDialogStage(dialogStage);
			
			// dialogStage.show();
			dialogStage.showAndWait();
			if(ctrl.getMembre()!=null)this.listeMembres.add(ctrl.getMembre());
			return ctrl.getMembre();
						
		} catch (IOException e) {
			System.out.println("Ressource FXML non disponible : SaisieMembres");
			System.exit(1);
			return null;
		}	
		
	}

	public Membre editSaisieMembre(Membre membre) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation( GestClubApp.class.getResource("view/SaisieMembre.fxml"));
			
			BorderPane vueSaisie = loader.load();
			
			Scene scene = new Scene(vueSaisie);
			scene.getStylesheets().setAll( primaryStage.getScene().getStylesheets() );
			
			Stage dialogStage =new Stage();
			dialogStage.setTitle("Edition membre");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(this.primaryStage);
			dialogStage.setScene(scene);
			
			SaisieMembreController ctrl = loader.getController();
			ctrl.setDialogStage(dialogStage);
			// dialogStage.show();
			ctrl.setMembre(membre);
			dialogStage.showAndWait();

			return ctrl.getMembre();
						
		} catch (IOException e) {
			System.out.println("Ressource FXML non disponible : SaisieMembres");
			System.exit(1);
			return null;
		}	
	}
	
	
	@Override
	public void stop() throws Exception {
		this.primaryStage.close();
	}

	public void remove(int index){
		this.listeMembres.remove(index);
	}

	public static void main2(String[] args) {
		launch(args);
	}

	
}
