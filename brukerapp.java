
import java.awt.TextArea;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class brukerapp extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	double seier = 1;
	double remis = 0.5;
	double tap = 0;

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane pane = new BorderPane();
		VBox meny = new VBox();
		meny.setPadding(new Insets(20, 20, 0, 20));
		pane.setLeft(meny);
		Scene scene = new Scene(pane, 850, 750);

		primaryStage.setTitle("Brukerapp");
		primaryStage.setScene( scene);
		primaryStage.show();
		primaryStage.setResizable(false);
		
	  

	        // Knapper
		Button openScore = new Button("Vis rangering");
		openScore.setMinWidth(100);
		openScore.setMaxWidth(150);

		Button søkPartier = new Button("Søk etter parti");
		søkPartier.setMinWidth(100);
		søkPartier.setMaxWidth(150);
		
		Button visBrett = new Button("Åpne brettet");
		søkPartier.setMinWidth(100);
		søkPartier.setMaxWidth(150);

	
		meny.getChildren().addAll(openScore, søkPartier, visBrett);

		openScore.setOnMouseClicked(e -> {
			scoreBoard();

		});

		søkPartier.setOnMouseClicked(e -> {
			searchParti();
		});
		
		
		/*
		visBrett.setOnMouseClicked(e -> {
			visBrett();
		});
		
		*/
	}
	
	// Testa litt, funker ikke veldig bra
/*
	void visBrett() {
		 GridPane root = new GridPane();
		    final int size = 8;
	
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col ++) {
                StackPane square = new StackPane();
                String color ;
                if ((row + col) % 2 == 0) {
                    color = "white";
                } else {
                    color = "black";
                }
                square.setStyle("-fx-background-color: "+color+";");
                root.add(square, col, row);
            }
        }
        for (int i = 0; i < size; i++) {
            root.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
            root.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }
   
	}
	*/
	void scoreBoard() {
		Stage subStage = new Stage();
		subStage.setTitle("Scorboard");

		TextArea navnFelt = new TextArea("scores");

		VBox layout = new VBox(10);

		Scene scene = new Scene(layout, 300, 200);
		
		subStage.setScene(scene);
		subStage.show();
	}

	Button søk;

	void searchParti() {
		

	}
}
