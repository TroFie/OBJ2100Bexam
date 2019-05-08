import java.awt.TextArea;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.nio.file.Files;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

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
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);

		// Knapper
		Button openScore = new Button("Vis rangering");
		openScore.setMinWidth(100);
		openScore.setMaxWidth(150);

		Button s�kPartier = new Button("S�k etter parti");
		s�kPartier.setMinWidth(100);
		s�kPartier.setMaxWidth(150);

		Button visBrett = new Button("�pne brettet");
		s�kPartier.setMinWidth(100);
		s�kPartier.setMaxWidth(150);

		meny.getChildren().addAll(openScore, s�kPartier, visBrett);

		openScore.setOnMouseClicked(e -> {
			scoreBoard();

		});

		s�kPartier.setOnMouseClicked(e -> {
			try {
				searchParti();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		visBrett.setOnMouseClicked(e -> {
			visBrett();
		});

	}

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
*/
	void visBrett() {
		
		String S_T�rn = "ikoner\\S_T�rn.png";
	    String S_Hest = "ikoner\\S_Hest.png";
	    String S_L�per = "ikoner\\S_L�per.png";
	    String S_Dronning = "ikoner\\S_Dronning.png";
	    String S_Konge = "ikoner\\S_Konge.png";
	    String S_Bonde = "ikoner\\S_Bonde.png";
	    //black pieces
	    String H_T�rn = "ikoner\\H_T�rn.png";
	    String H_Hest = "ikoner\\H_Hest.png";
	    String H_L�per = "ikoner\\H_L�per.png";
	    String H_Dronning = "ikoner\\H_Dronning.png";
	    String H_Konge = "ikoner\\H_Konge.png";
	    String H_Bonde = "ikoner\\H_Bonde.png";
	    
	    Button[][] tiles;
	    int Size;
	
		Stage subStage = new Stage();
		GridPane root = new GridPane();
        final int st�rrelse = 8 ;
        
        for (int rad = 0; rad < st�rrelse; rad++) {
            for (int kol = 0; kol < st�rrelse; kol ++) {
                StackPane square = new StackPane();
                String color ;
                if ((rad + kol) % 2 == 0) {
                    color = "beige";
                } else {
                    color = "tan";
                }
                square.setStyle("-fx-background-color: "+color+";");
                root.add(square, kol, rad);
            }
        }
        
        for (int i = 0; i < st�rrelse; i++) {
            root.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
            root.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }
        
        subStage.setScene(new Scene(root, 400, 400));
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

	Button s�k;

	void searchParti() throws Exception {
		Stage subStage = new Stage();
		subStage.setTitle("Parti-liste");
		subStage.setWidth(400);
		subStage.setHeight(550);
		
		final Label label = new Label("Parti-liste");
		label.setFont(new Font("Arial", 19));

		Collection<SpillerData> list = Files.readAllLines(new File("resultat.dat").toPath()).stream().map(line -> {
			String[] details = line.split("-|\\  |\\Dato:");
			SpillerData cd = new SpillerData();
			cd.setNavn(details[0]);
			cd.setNavn2(details[1]);
			cd.setDato(details[2]);
			// Hvis vi skal ha med resultat cd.setResultat(details[3]);
			return cd;
		}).collect(Collectors.toList());

		ObservableList<SpillerData> details = FXCollections.observableArrayList(list);

		TableView<SpillerData> tableView = new TableView<>();
		TableColumn<SpillerData, String> navnSpiller = new TableColumn("Navn");
		TableColumn<SpillerData, String> navnSpiller2 = new TableColumn("Navn2");
		TableColumn<SpillerData, String> datoSpiller = new TableColumn("Dato");

		navnSpiller.setMinWidth(100);
		navnSpiller2.setMinWidth(100);
		datoSpiller.setMinWidth(200);

		tableView.getColumns().addAll(navnSpiller, navnSpiller2, datoSpiller);

		navnSpiller.setCellValueFactory(data -> data.getValue().navnProperty());
		navnSpiller2.setCellValueFactory(data -> data.getValue().navn2Property());
		datoSpiller.setCellValueFactory(data -> data.getValue().datoProperty());
		
		tableView.setItems(details);
		StackPane sp = new StackPane(tableView);
		Scene scene = new Scene(sp);
		subStage.setScene(scene);
		subStage.setResizable(false);
		subStage.show();
		
		
		/*
		  // Legger til s�ke funksjonen ChoiceBox<String> choiceBox = new ChoiceBox();
		  choiceBox.getItems().addAll("Navn", "Resultat"); choiceBox.setValue("Navn");
		  
		  TextField textField = new TextField(); textField.setPromptText("S�k her");
		  textField.setOnKeyReleased(keyEvent -> { switch (choiceBox.getValue()) { case
		  "Navn": // flPerson.setPredicate(p -> //
		  p.getNavn().toLowerCase().contains(textField.getText().toLowerCase().trim()))
		  ; break; case "Resultat": // flPerson.setPredicate(p -> //
		  p.getNavn1().toLowerCase().contains(textField.getText().toLowerCase().trim())
		  ); break; } });
		  
		  choiceBox.getSelectionModel().selectedItemProperty().addListener((obs,
		  oldVal, newVal) -> { if (newVal != null) { textField.setText(""); //
		  flPerson.setPredicate(null); } });
		*/
		
		
		/*
		 * HBox hBox = new HBox(choiceBox, textField); hBox.setAlignment(Pos.CENTER);
		 * final VBox vbox = new VBox(); vbox.setSpacing(5); vbox.setPadding(new
		 * Insets(10, 0, 0, 10)); vbox.getChildren().addAll(label, table, hBox);
		 * 
		 * ((Group) scene.getRoot()).getChildren().addAll(vbox);
		 * 
		 * 
		 * stage.setScene(scene); stage.show();
		 */
	}

	private class SpillerData {
		StringProperty navn = new SimpleStringProperty();
		StringProperty navn2 = new SimpleStringProperty();
		StringProperty dato = new SimpleStringProperty();
		StringProperty resultat = new SimpleStringProperty();

		public final StringProperty navnProperty() {
			return this.navn;
		}

		public final java.lang.String getNavn() {
			return this.navnProperty().get();
		}

		public final void setNavn(final java.lang.String navn) {
			this.navnProperty().set(navn);
		}

		public final StringProperty navn2Property() {
			return this.navn2;
		}

		public final java.lang.String getNavn2() {
			return this.navn2Property().get();
		}

		public final void setNavn2(final java.lang.String navn2) {
			this.navn2Property().set(navn2);
		}

		public final StringProperty datoProperty() {
			return this.dato;
		}

		public final java.lang.String getDato() {
			return this.datoProperty().get();
		}

		public final void setDato(final java.lang.String dato) {
			this.datoProperty().set(dato);
		}

		/*
		 * public final StringProperty resultatProperty() { return this.resultat; }
		 * 
		 * public final java.lang.String getResultat() { return
		 * this.resultatProperty().get(); }
		 * 
		 * public final void setResultat(final java.lang.String resultat) {
		 * this.resultatProperty().set(resultat); }
		 * 
		 */

	}

}
