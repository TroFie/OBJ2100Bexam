

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ChessGUI extends Application {

	private static final int WIDTH = 80;
	private static final int HEIGHT = 80;
	private static final int BOARD_SIZE = 640;

	@Override
	public void start(Stage primaryStage) {

		Pane root = new Pane();
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

		Scene scene = new Scene(root, BOARD_SIZE, BOARD_SIZE);

		primaryStage.setTitle("Sjakkbrett");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}


}