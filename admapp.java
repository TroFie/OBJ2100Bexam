import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class admapp extends Application{

	public ArrayList<Deltaker> deltakerListe = new ArrayList<Deltaker>();
	
	public ArrayList<Parti> partiListe = new ArrayList<Parti>();
	
	public static void main(String[] args)throws Exception {
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Fyller opp liste med eksisterende deltakere
		File file = new File("deltaker.txt");
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
          
            ArrayList<Deltaker> deserializeBruker = (ArrayList<Deltaker>)ois.readObject();
            ois.close();
            
            Iterator<Deltaker> iter = deserializeBruker.iterator();
            while(iter.hasNext()){
                Deltaker s = iter.next();
                deltakerListe.add(new Deltaker(s.getName()));
            }
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        
        File partiFile = new File("partier.txt");
        try {
            FileInputStream fis = new FileInputStream(partiFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
          
            ArrayList<Parti> deserializeParti = (ArrayList<Parti>)ois.readObject();
            ois.close();
            
            Iterator<Parti> iter = deserializeParti.iterator();
            while(iter.hasNext()){
                Parti s = iter.next();
                partiListe.add(new Parti(s.getName1(), s.getName2(), s.getDato()));
            }
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        
		// Pane        
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
			registrerDeltaker();
			
		});
		
		regPartier.setOnMouseClicked(e -> {
			registrerParti();
			
		});
		
		regResultat.setOnMouseClicked(e -> {
			registrerResultat();
			
		});

	}
	void registrerDeltaker()  {
	    Stage subStage = new Stage();
	    subStage.setHeight(500);
		subStage.setResizable(false);
	    subStage.setTitle("Registrer deltaker");
	            
	    TextField navnFelt = new TextField();
	  
	    Text deltakerNavn = new Text("Skriv fullt navn");
	    Button lagreDeltaker = new Button("Lagre deltaker");
	    lagreDeltaker.setMinWidth(100);
	    lagreDeltaker.setMaxWidth(150);
	    Button seListe = new Button("Se liste");
	    seListe.setMinWidth(100);
	    seListe.setMaxWidth(150);
	    Button avbryt = new Button("Avbryt");
	    avbryt.setMinWidth(100);
	    avbryt.setMaxWidth(150);
	    
	    VBox layout = new VBox(10);
	    layout.setPadding(new Insets(20,20,20,20));
	    layout.getChildren().addAll(deltakerNavn, navnFelt, lagreDeltaker, seListe, avbryt);
	    
	    Scene scene = new Scene(layout, 300, 200);
	    subStage.setScene(scene);
	    subStage.show();
	    
	    
	    lagreDeltaker.setOnMouseClicked(e -> {
	    	deltakerListe.add(new Deltaker(navnFelt.getText()));
	    	
	        File file = new File("deltaker.txt");
	        try {
	            FileOutputStream fos = new FileOutputStream(file);
	            ObjectOutputStream oos = new ObjectOutputStream(fos);
	            oos.writeObject(deltakerListe);
	            oos.close();
	            
	        } catch (FileNotFoundException ex) {
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    
	    	// Kopi på txt format
	    	File file2 = new File("deltakerKopi.txt");
			try(
				FileWriter fileWriter = new FileWriter(file2, true);
				PrintWriter output	= new PrintWriter(fileWriter);
				) {
				output.write(navnFelt.getText().toUpperCase() + "\n");
				}
		         catch (FileNotFoundException ex) {
		            ex.printStackTrace();
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        } catch (NoSuchElementException e2) {
					
				}
	        navnFelt.setText("");
	        System.out.println("Deltaker lagret");
	    });
	    
	    seListe.setOnMouseClicked(e -> {
	    	File file = new File("deltakerKopi.txt");
	    	Scanner input;
			try {
				input = new Scanner(file);
				
				while (input.hasNextLine()) {
					String navn = input.nextLine();
					System.out.println(navn + " " );
				}
				input.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
	    });
	    
	    avbryt.setOnMouseClicked(e -> {
	    	subStage.close();
	    });
	    
}
	void registrerParti()  {
	    Stage subStage = new Stage();
	    subStage.setHeight(500);
		subStage.setResizable(false);
	    subStage.setTitle("Registrer parti");
	            
	    TextField navnFelt1 = new TextField();
	    TextField navnFelt2 = new TextField();
	    TextField datoTid   = new TextField();
	  
	    Text deltakerNavn1 = new Text("Navn på deltaker 1");
	    Text deltakerNavn2 = new Text("Navn på deltaker 2");
	    Text dato 		   = new Text("Dato og tid");
	    
	    Button lagreParti = new Button("Lagre sjakkparti");
	    lagreParti.setMinWidth(100);
	    lagreParti.setMaxWidth(150);
	    
	    Button seListe = new Button("Se liste");
	    seListe.setMinWidth(100);
	    seListe.setMaxWidth(150);
	    
	    Button avbryt = new Button("Avbryt");
	    avbryt.setMinWidth(100);
	    avbryt.setMaxWidth(150);
	    
	    VBox layout = new VBox(10);
	    layout.setPadding(new Insets(20,20,20,20));
	    layout.getChildren().addAll(deltakerNavn1, navnFelt1, deltakerNavn2, navnFelt2, dato, datoTid, lagreParti, seListe, avbryt);
	    
	    Scene scene = new Scene(layout, 300, 200);
	    subStage.setScene(scene);
	    subStage.show();
	    
	    
	    lagreParti.setOnMouseClicked(e -> {
	    	partiListe.add(new Parti(navnFelt1.getText(), navnFelt2.getText(), datoTid.getText()));

	    	File file = new File("partier.txt");
	    	try {
	    		FileOutputStream fos = new FileOutputStream(file);
	    		ObjectOutputStream oos = new ObjectOutputStream(fos);
	    		oos.writeObject(partiListe);
	    		oos.close();
	            
	        } catch (FileNotFoundException ex) {
	           ex.printStackTrace();
	        } catch (IOException ex) {
	           ex.printStackTrace();
	       	}
	    	
	    	
	    	// Kopi på txt format
	    	File file2 = new File("partiKopi.txt");
			try(
				FileWriter fileWriter = new FileWriter(file2, true);
				PrintWriter output	= new PrintWriter(fileWriter);
				) {
				output.write(navnFelt1.getText().toUpperCase() + " - "); 
				output.write(navnFelt2.getText().toUpperCase() + " Dato: "); 
				output.write(datoTid.getText().toUpperCase() + "\n" + "\n"); 
				} 
				  catch (FileNotFoundException ex) {
		            ex.printStackTrace();
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        } catch (NoSuchElementException e2) {
					
				}
	        navnFelt1.setText("");
	        navnFelt2.setText("");
	        datoTid.setText("");
	        System.out.println("Sjakkparti lagret");
	    });
	    
	    
	    seListe.setOnMouseClicked(e -> {
	    	File file = new File("partiKopi.txt");
	    	Scanner input;
			try {
				input = new Scanner(file);
				
				while (input.hasNextLine()) {
					String navn = input.nextLine();
					System.out.println(navn + " " );
				}
				input.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
	    });
	    
	    avbryt.setOnMouseClicked(e -> {
	    	subStage.close();
	    });
	    
}
	
	void registrerResultat() {
		Stage subStage = new Stage();
		subStage.setHeight(500);
		subStage.setResizable(false);
	    subStage.setTitle("Registrer resultat");
	            
	    TextField navnFelt1 = new TextField();
	    TextField navnFelt2 = new TextField();
	    MenuButton meny = new MenuButton("Parti");
	    
	    Text deltakerNavn1 = new Text("Navn på deltaker 1");
	    Text deltakerNavn2 = new Text("Navn på deltaker 2");
	    Button lagreResultat = new Button("Lagre resultat");
	    lagreResultat.setMinWidth(100);
	    lagreResultat.setMaxWidth(150);
	    Button seResultatListe = new Button("Se liste");
	    seResultatListe.setMinWidth(100);
	    seResultatListe.setMaxWidth(150);
	    Button avbryt = new Button("Avbryt");
	    avbryt.setMinWidth(100);
	    avbryt.setMaxWidth(150);
	    
	    VBox layout = new VBox(10);
	    layout.setPadding(new Insets(20,20,20,20));
	    layout.getChildren().addAll(deltakerNavn1, navnFelt1, deltakerNavn2, navnFelt2, meny,  lagreResultat, seResultatListe, avbryt);
	    
	    Scene scene = new Scene(layout, 300, 200);
	    subStage.setScene(scene);
	    subStage.show();
	    
	    for(int i=0; i<partiListe.size(); i++) {
	    	String navn1 = partiListe.get(i).getName1();
	    	String navn2 = partiListe.get(i).getName2();
	    	String dato = partiListe.get(i).getDato();
	    	MenuItem a = new MenuItem(navn1 + " - " + navn2 + " - " + dato);
	    	meny.getItems().add(a);
	    }
	    
	    lagreResultat.setOnMouseClicked(e -> {
	    	
	    });
	    
	    avbryt.setOnMouseClicked(e -> {
	    	System.out.println(partiListe.size());
	    });
	}

}
