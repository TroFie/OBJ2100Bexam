import java.awt.TextArea;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Collectors;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class spillerapp extends Application {
	
	int index = -1;

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
		Scene scene = new Scene(pane, 200, 100);

		primaryStage.setTitle("Brukerapp");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);

		// Knapper
		Button openScore = new Button("Vis rangering");
		openScore.setMinWidth(100);
		openScore.setMaxWidth(150);

		Button finnPartier = new Button("Søk etter parti");
		finnPartier.setMinWidth(100);
		finnPartier.setMaxWidth(150);

		Button visBrett = new Button("Åpne brettet");
		visBrett.setMinWidth(100);
		visBrett.setMaxWidth(150);

		meny.getChildren().addAll(openScore, finnPartier, visBrett);

		openScore.setOnMouseClicked(e -> {
			scoreBoard();

		});

		finnPartier.setOnMouseClicked(e -> {
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

		String Black_Rook = "ikoner\\Black_Rook.png";
		String Black_Knight = "ikoner\\Black_Knight.png";
		String Black_Bishop = "ikoner\\Black_Bishop.png";
		String Black_Queen = "ikoner\\Black_Queen.png";
		String Black_King = "ikoner\\Black_King.png";
		String Black_Pawn = "ikoner\\Black_Pawn.png";
		// black pieces
		String White_Rook = "ikoner\\White_Rook.png";
		String White_Knight = "ikoner\\White_Knight.png";
		String White_Bishop = "ikoner\\White_Bishop.png";
		String White_Queen = "ikoner\\White_Queen.png";
		String White_King = "ikoner\\White_King.png";
		String White_Pawn = "ikoner\\White_Pawn.png";

		Stage subStage = new Stage(); 
		GridPane root = new GridPane(); 
		final int size = 8;
		final int windowSize = 500;
		
		Button[][] squares = new Button[size][size];
		
		for (int rad = 0; rad < size; rad++) { 
            for (int kol = 0; kol < size; kol ++) { 
            	Button square = new Button();
            	square.setPrefSize(windowSize/size, windowSize/size);
                String color ; 
                if ((rad + kol) % 2 == 0) { 
                    color = "beige"; 
                } else { 
                    color = "tan"; 
                } 
                square.setStyle("-fx-background-color: "+color+";"); 
                root.add(square, kol, rad); 
                squares[rad][kol] = square;
            } 
        } 
		
		for (int i = 0; i < size; i++) { 
            root.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true)); 
            root.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true)); 
        } 
		// Svarte brikker
		// Black rook 2
		ImageView imgViewBRook = new ImageView();
		Image imageBRook = new Image(spillerapp.class.getResourceAsStream(Black_Rook));
		imgViewBRook.setImage(imageBRook);
		imgViewBRook.setFitHeight(windowSize/11);
		imgViewBRook.setFitWidth(windowSize/11);
		squares[0][0].setGraphic(imgViewBRook);
		
		// Black rook 2
		ImageView imgViewBRook2 = new ImageView();
		Image imageBRook2 = new Image(spillerapp.class.getResourceAsStream(Black_Rook));
		imgViewBRook2.setImage(imageBRook2);
		imgViewBRook2.setFitHeight(windowSize/11);
		imgViewBRook2.setFitWidth(windowSize/11);
		squares[0][7].setGraphic(imgViewBRook2);
		
		// Black knight 2
		ImageView imgViewBKnight = new ImageView();
		Image imageBKnight = new Image(spillerapp.class.getResourceAsStream(Black_Knight));
		imgViewBKnight.setImage(imageBKnight);
		imgViewBKnight.setFitHeight(windowSize/11);
		imgViewBKnight.setFitWidth(windowSize/11);
		squares[0][1].setGraphic(imgViewBKnight);
		
		// Black knight 2
		ImageView imgViewBKnight2 = new ImageView();
		Image imageBKnight2 = new Image(spillerapp.class.getResourceAsStream(Black_Knight));
		imgViewBKnight2.setImage(imageBKnight2);
		imgViewBKnight2.setFitHeight(windowSize/11);
		imgViewBKnight2.setFitWidth(windowSize/11);
		squares[0][6].setGraphic(imgViewBKnight2);
		
		// Black bishop 2
		ImageView imgViewBBishop = new ImageView();
		Image imageBBishop = new Image(spillerapp.class.getResourceAsStream(Black_Bishop));
		imgViewBBishop.setImage(imageBBishop);
		imgViewBBishop.setFitHeight(windowSize/11);
		imgViewBBishop.setFitWidth(windowSize/11);
		squares[0][2].setGraphic(imgViewBBishop);
		
		// Black bishop 2
		ImageView imgViewBBishop2 = new ImageView();
		Image imageBBishop2 = new Image(spillerapp.class.getResourceAsStream(Black_Bishop));
		imgViewBBishop2.setImage(imageBBishop2);
		imgViewBBishop2.setFitHeight(windowSize/11);
		imgViewBBishop2.setFitWidth(windowSize/11);
		squares[0][5].setGraphic(imgViewBBishop2);
		
		// Black queen
		ImageView imgViewBQueen = new ImageView();
		Image imageBQueen = new Image(spillerapp.class.getResourceAsStream(Black_Queen));
		imgViewBQueen.setImage(imageBQueen);
		imgViewBQueen.setFitHeight(windowSize/11);
		imgViewBQueen.setFitWidth(windowSize/11);
		squares[0][3].setGraphic(imgViewBQueen);
		
		// Black king
		ImageView imgViewBKing = new ImageView();
		Image imageBKing = new Image(spillerapp.class.getResourceAsStream(Black_King));
		imgViewBKing.setImage(imageBKing);
		imgViewBKing.setFitHeight(windowSize/11);
		imgViewBKing.setFitWidth(windowSize/11);
		squares[0][4].setGraphic(imgViewBKing);
		
		for(int i = 0; i < 8; i++) {
			ImageView imgViewBPawn = new ImageView();
			Image imageBPawn = new Image(spillerapp.class.getResourceAsStream(Black_Pawn));
			imgViewBPawn.setImage(imageBPawn);
			imgViewBPawn.setFitHeight(windowSize/11);
			imgViewBPawn.setFitWidth(windowSize/11);
			squares[1][i].setGraphic(imgViewBPawn);
		}
		
		// Hvite brikker
		// White rook
		ImageView imgViewWRook = new ImageView();
		Image imageWRook = new Image(spillerapp.class.getResourceAsStream(White_Rook));
		imgViewWRook.setImage(imageWRook);
		imgViewWRook.setFitHeight(windowSize/11);
		imgViewWRook.setFitWidth(windowSize/11);
		squares[7][0].setGraphic(imgViewWRook);
		
		// White rook 2
		ImageView imgViewWRook2 = new ImageView();
		Image imageWRook2 = new Image(spillerapp.class.getResourceAsStream(White_Rook));
		imgViewWRook2.setImage(imageWRook2);
		imgViewWRook2.setFitHeight(windowSize/11);
		imgViewWRook2.setFitWidth(windowSize/11);
		squares[7][7].setGraphic(imgViewWRook2);
		
		// White knight
		ImageView imgViewWKnight = new ImageView();
		Image imageWKnight = new Image(spillerapp.class.getResourceAsStream(White_Knight));
		imgViewWKnight.setImage(imageWKnight);
		imgViewWKnight.setFitHeight(windowSize/11);
		imgViewWKnight.setFitWidth(windowSize/11);
		squares[7][1].setGraphic(imgViewWKnight);
		
		// White knight 2
		ImageView imgViewWKnight2 = new ImageView();
		Image imageWKnight2 = new Image(spillerapp.class.getResourceAsStream(White_Knight));
		imgViewWKnight2.setImage(imageWKnight2);
		imgViewWKnight2.setFitHeight(windowSize/11);
		imgViewWKnight2.setFitWidth(windowSize/11);
		squares[7][6].setGraphic(imgViewWKnight2);
		
		// White bishop
		ImageView imgViewWBishop = new ImageView();
		Image imageWBishop = new Image(spillerapp.class.getResourceAsStream(White_Bishop));
		imgViewWBishop.setImage(imageWBishop);
		imgViewWBishop.setFitHeight(windowSize/11);
		imgViewWBishop.setFitWidth(windowSize/11);
		squares[7][2].setGraphic(imgViewWBishop);
		
		// White bishop 2
		ImageView imgViewWBishop2 = new ImageView();
		Image imageWBishop2 = new Image(spillerapp.class.getResourceAsStream(White_Bishop));
		imgViewWBishop2.setImage(imageWBishop2);
		imgViewWBishop2.setFitHeight(windowSize/11);
		imgViewWBishop2.setFitWidth(windowSize/11);
		squares[7][5].setGraphic(imgViewWBishop2);
		
		// White queen
		ImageView imgViewWQueen = new ImageView();
		Image imageWQueen = new Image(spillerapp.class.getResourceAsStream(White_Queen));
		imgViewWQueen.setImage(imageWQueen);
		imgViewWQueen.setFitHeight(windowSize/11);
		imgViewWQueen.setFitWidth(windowSize/11);
		squares[7][3].setGraphic(imgViewWQueen);
		
		// White king
		ImageView imgViewWKing = new ImageView();
		Image imageWKing = new Image(spillerapp.class.getResourceAsStream(White_King));
		imgViewWKing.setImage(imageWKing);
		imgViewWKing.setFitHeight(windowSize/11);
		imgViewWKing.setFitWidth(windowSize/11);
		squares[7][4].setGraphic(imgViewWKing);
		
		for(int i = 0; i < 8; i++) {
			ImageView imgViewWPawn = new ImageView();
			Image imageWPawn = new Image(spillerapp.class.getResourceAsStream(White_Pawn));
			imgViewWPawn.setImage(imageWPawn);
			imgViewWPawn.setFitHeight(windowSize/11);
			imgViewWPawn.setFitWidth(windowSize/11);
			squares[6][i].setGraphic(imgViewWPawn);
		
		}
		
		
		subStage.setScene(new Scene(root, windowSize, windowSize)); 
		subStage.show();
		subStage.setResizable(false);
	
		
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



	@SuppressWarnings({ "rawtypes", "unchecked" })
	void searchParti() throws Exception {
		Stage subStage = new Stage();
		Scene scene = new Scene(new Group());
		subStage.setTitle("Parti-liste");
		subStage.setWidth(400);
		subStage.setHeight(550);

		final Label label = new Label("Parti-liste");
		label.setFont(new Font("Arial", 19));

		Collection<SpillerData> list = Files.readAllLines(new File("forSearching.txt").toPath()).stream().map(line -> {
			String[] details = line.split(",|\\  |\\\n");
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

		tableView.setItems(details);
		tableView.getColumns().addAll(navnSpiller, navnSpiller2, datoSpiller);

		navnSpiller.setCellValueFactory(data -> data.getValue().navnProperty());
		navnSpiller2.setCellValueFactory(data -> data.getValue().navn2Property());
		datoSpiller.setCellValueFactory(data -> data.getValue().datoProperty());

		tableView.getSelectionModel().setCellSelectionEnabled(true);
		ObservableList selectedCells = tableView.getSelectionModel().getSelectedCells();
		
		// Sjekker hvilken rad som er markert
		selectedCells.addListener((ListChangeListener) c -> {
			try {
		    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
		    int val = tablePosition.getRow();
		    index = val;
			}catch (IndexOutOfBoundsException e) {
				System.out.println("Kan ikke søke etter klikk på parti..");
				subStage.close();
			}
		});
		
		Button velgKnapp = new Button("Se parti");
		velgKnapp.setOnMouseClicked(e -> {
			navnSpiller.getCellData(index);
			navnSpiller2.getCellData(index);
			datoSpiller.getCellData(index);
			String fil = new String("resultat" + navnSpiller.getCellData(index) + navnSpiller2.getCellData(index) + datoSpiller.getCellData(index) + ".dat");
			try(
					ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fil))); 
					) {
						ArrayList<Deltaker> t1 = (ArrayList<Deltaker>)(input.readObject());
						System.out.println(t1.toString());
						ArrayList<Deltaker> t = (ArrayList<Deltaker>)(input.readObject());
						System.out.println(t.toString());
						ArrayList<Date> t2 = (ArrayList<Date>)(input.readObject());
						System.out.println(t2.toString());
						ArrayList<Resultat> t3 = (ArrayList<Resultat>)(input.readObject());
						System.out.println(t3.toString());
						ArrayList<Trekk> t4 = (ArrayList<Trekk>)(input.readObject());
						System.out.println(t4.toString());
						
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						System.out.println("End of file");
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
			//visBrett();
			
		});
	
		TextField textField = new TextField();
		textField.setPromptText("Søk her");
		textField.setOnKeyReleased(keyEvent -> {

			if (textField.textProperty().get().isEmpty()) {
				tableView.setItems(details);
				return;
			}

			ObservableList<SpillerData> tableItems = FXCollections.observableArrayList();
			ObservableList<TableColumn<SpillerData, ?>> cols = tableView.getColumns();
			for (int i = 0; i < details.size(); i++) {

				for (int j = 0; j < cols.size(); j++) {
					TableColumn col = cols.get(j);
					String cellValue = col.getCellData(details.get(i)).toString();
					cellValue = cellValue.toLowerCase();
					if (cellValue.contains(textField.textProperty().get().toLowerCase())) {
						tableItems.add(details.get(i));
						break;
					}
				}
			}
			tableView.setItems(tableItems);

		});

		HBox hBox = new HBox(textField, velgKnapp);
		hBox.setAlignment(Pos.CENTER);
		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, tableView, hBox);

		((Group) scene.getRoot()).getChildren().addAll(vbox);
		tableView.setItems(details);
		subStage.setScene(scene);
		subStage.setResizable(false);
		subStage.show();

	}

	

}
