
import java.awt.TextArea;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


import javafx.application.Application;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class brukerapp extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	double seier = 1;
	double remis = 0.5;
	double tap = 0;
	private TableView<Parti> table = new TableView<Parti>();
	

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
		
		
		
		visBrett.setOnMouseClicked(e -> {
			visBrett();
		});
		
		
	}
	
	// Testa litt, funker ikke veldig bra

	void visBrett() {
		
		int WIDTH = 80;
		int HEIGHT = 80;
		int BOARD_SIZE = 640;
	
		Stage subStage = new Stage();
		Group root = new Group();
		Scene scene = new Scene(root, BOARD_SIZE, BOARD_SIZE);
		subStage.setScene(scene);
		for (int x = 0, c = 0; x < BOARD_SIZE; x = x + WIDTH, c++) {
			for (int y = 0; y < BOARD_SIZE; y = y + HEIGHT) {
				if (c % 2 == 0) {
					Rectangle r = new Rectangle();
					r.setX(x);
					r.setY(y);
					r.setWidth(WIDTH);
					r.setHeight(HEIGHT);

					root.getChildren().add(r);
				}
				c++;
			}
		}
		
		subStage.show();
	}

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
	Stage stage = new Stage();
		 Scene scene = new Scene(new Group());
	        stage.setTitle("Parti-liste");
	        stage.setWidth(450);
	        stage.setHeight(550);

	        final Label label = new Label("Parti-liste");
	        label.setFont(new Font("Arial", 19));
	        
	        table.setEditable(true);

	        TableColumn navnSpiller = new TableColumn("Navn");
	        navnSpiller.setMinWidth(100);
	        
	        TableColumn resultatSpiller = new TableColumn("Resultat");
	        resultatSpiller.setMinWidth(100);
	        
	        TableColumn datoSpiller = new TableColumn("Dato");
	        datoSpiller.setMinWidth(200);
	        
	       //  FilteredList<Parti> flPerson = new FilteredList(data, p -> true);
	        // table.setItems(flPerson);
	        table.getColumns().addAll(navnSpiller, resultatSpiller, datoSpiller);
	        
	        ChoiceBox<String> choiceBox = new ChoiceBox();
	        choiceBox.getItems().addAll("Navn", "Resultat");
	        choiceBox.setValue("Navn");

	        TextField textField = new TextField();
	        textField.setPromptText("Søk her");
	        textField.setOnKeyReleased(keyEvent ->
	        {
	            switch (choiceBox.getValue())
	            {
	                case "Navn":
	               //     flPerson.setPredicate(p -> p.getFirstName().toLowerCase().contains(textField.getText().toLowerCase().trim()));
	                    break;
	                case "Resultat":
	                //    flPerson.setPredicate(p -> p.getLastName().toLowerCase().contains(textField.getText().toLowerCase().trim()));
	                    break;
	            }
	        });
	        
	        HBox hBox = new HBox(choiceBox, textField);
	        hBox.setAlignment(Pos.CENTER);
	        final VBox vbox = new VBox();
	        vbox.setSpacing(5);
	        vbox.setPadding(new Insets(10, 0, 0, 10));
	        vbox.getChildren().addAll(label, table, hBox);

	        ((Group) scene.getRoot()).getChildren().addAll(vbox);
	        
	        
	        stage.setScene(scene);
	        stage.show();
	}
	
}
