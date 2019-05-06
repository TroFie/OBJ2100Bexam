import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class admapp extends Application{

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane pane = new BorderPane();
		VBox meny = new VBox();
		meny.setPadding(new Insets(20,20,0,20));
		pane.setLeft(meny);
		Scene scene = new Scene(pane, 850, 750);
		
		primaryStage.setTitle("Admapp");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
		
		Button regNavn = new Button("Registrer deltaker");
		regNavn.setMinWidth(100);
		regNavn.setMaxWidth(150);
		Button regPartier = new Button("Registrer parti");
		regPartier.setMinWidth(100);
		regPartier.setMaxWidth(150);
		Button regResultat = new Button("Registrer resultat");
		regResultat.setMinWidth(100);
		regResultat.setMaxWidth(150);
		meny.getChildren().addAll(regNavn, regPartier, regResultat);
		
		regNavn.setOnMouseClicked(e -> {
			NewStage();
			
		});
		
		regPartier.setOnMouseClicked(e -> {
			nyttParti();
			
		});
		
		regResultat.setOnMouseClicked(e -> {
			
			
		});

	}
	Button registrer;
	Button avbryt;
	void NewStage()  {
	    Stage subStage = new Stage();
	    subStage.setTitle("Registrer deltaker");
	            
	    TextField navnFelt = new TextField();
	  
	    Text deltakerNavn = new Text("Skriv fullt navn");
	    registrer = new Button("     Lagre deltaker     ");
	    avbryt = new Button("           Avbryt           ");
	    
	    VBox layout = new VBox(10);
	    layout.setPadding(new Insets(20,20,20,20));
	    layout.getChildren().addAll(deltakerNavn, navnFelt, registrer, avbryt);
	    
	    Scene scene = new Scene(layout, 300, 200);
	    subStage.setScene(scene);
	    subStage.show();
	    
	    
	    registrer.setOnMouseClicked(e -> {});
	    
	    avbryt.setOnMouseClicked(e -> {
	    	subStage.close();
	    });
	    
}
	
	void nyttParti()  {
	    Stage subStage = new Stage();
	    subStage.setTitle("Registrer sjakk-parti");
	            
	    TextField navnFelt = new TextField();
	    TextField navnFelt2 = new TextField();
	    TextField datoTid = new TextField();
	  
	    Text deltakerNavn1 = new Text("1. deltaker");
	    Text deltakerNavn2 = new Text("2. deltaker");
	    Text dato = new Text("Dato og tid");
	    registrer = new Button("     Lagre parti     ");
	    avbryt = new Button("           Avbryt           ");
	    
	    VBox layout = new VBox(10);
	    layout.setPadding(new Insets(20,20,20,20));
	    layout.getChildren().addAll(deltakerNavn1, navnFelt, deltakerNavn2, navnFelt2, dato, datoTid, registrer, avbryt);
	    
	    Scene scene = new Scene(layout, 300, 300);
	    subStage.setScene(scene);
	    subStage.show();
	    
	    
	    registrer.setOnMouseClicked(e -> {});
	    
	    avbryt.setOnMouseClicked(e -> {
	    	subStage.close();
	    });
	    
}

}
