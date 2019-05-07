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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class admapp extends Application{

	public ArrayList<Deltaker> deltakerListe = new ArrayList<Deltaker>();
	
	public ArrayList<Parti> partiListe = new ArrayList<Parti>();
	
	public String[]trekkHvit = new String[500];
	public String[]trekkSort = new String[500];
	
	MenuItem a;
	MenuItem remisItem;
	MenuItem b;
	MenuItem b2;
	int trekkTellerHvit = 0;
	int trekkTellerSort = 0;
	
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
		subStage.setHeight(700);
		subStage.setResizable(false);
	    subStage.setTitle("Registrer resultat");
	            
	    TextField navnFelt1 = new TextField();
	    TextField navnFelt2 = new TextField();
	    TextField datoFelt = new TextField();
	    TextField trekkFeltHvit = new TextField();
	    TextField trekkFeltSort = new TextField();
	    TextField resultatFelt = new TextField();
	    
	    MenuButton meny = new MenuButton("Parti");
	    MenuButton resultatMeny = new MenuButton("Resultat");
	    
	    Text deltakerNavn1 = new Text("Navn på deltaker 1 (Hvit)");
	    Text deltakerNavn2 = new Text("Navn på deltaker 2 (Sort)");
	    Text datoTxt = new Text("Dato");
	    Text trekkTxt = new Text("Fyll inn trekk");
	    
	    Button byttPlass = new Button("Bytt plass");
	    Button regTrekkHvit = new Button("Registrer trekk (Hvit)");
	    Button regTrekkSort = new Button("Registrer trekk (Sort)");
	    Button lagreResultat = new Button("Lagre resultat");
	    Button seResultatListe = new Button("Se liste");
	    Button avbryt = new Button("Avbryt");
	    
	    
	    regTrekkSort.setMinWidth(100);
	    regTrekkSort.setMaxWidth(150);
	    regTrekkHvit.setMinWidth(100);
	    regTrekkHvit.setMaxWidth(150);
	    resultatMeny.setMinWidth(100);
	    resultatMeny.setMaxWidth(150);
	    byttPlass.setMinWidth(100);
	    byttPlass.setMaxWidth(150);
	    meny.setMinWidth(100);
	    meny.setMaxWidth(150);
	    seResultatListe.setMinWidth(100);
	    seResultatListe.setMaxWidth(150);
	    lagreResultat.setMinWidth(100);
	    lagreResultat.setMaxWidth(150);
	    avbryt.setMinWidth(100);
	    avbryt.setMaxWidth(150);
	    
	    navnFelt1.setEditable(false);
	    navnFelt2.setEditable(false);
	    datoFelt.setEditable(false);
	    resultatFelt.setEditable(false);
	    
	    VBox layout = new VBox(10);
	    layout.setPadding(new Insets(10,10,10,10));
	    layout.getChildren().addAll(meny, deltakerNavn1, navnFelt1, deltakerNavn2, navnFelt2,  byttPlass, datoTxt, datoFelt,
	    		trekkTxt, trekkFeltHvit, regTrekkHvit, trekkFeltSort, regTrekkSort, resultatMeny, resultatFelt, lagreResultat, seResultatListe, avbryt);
	    
	    Scene scene = new Scene(layout, 300, 200);
	    subStage.setScene(scene);
	    subStage.show();
	    
	    MenuItem d1 = new MenuItem("Deltaker 1");
	    MenuItem d2 = new MenuItem("Deltaker 2");
	    MenuItem r = new MenuItem("Remis");
	    resultatMeny.getItems().addAll(d1,d2,r);
	    
	    // Bruker registrerte partier til å fylle ut meny-valgmuligheter
	    for(int i=0; i<partiListe.size(); i++) {
	    	String navn1 = partiListe.get(i).getName1();
	    	String navn2 = partiListe.get(i).getName2();
	    	String dato = partiListe.get(i).getDato();
	    	MenuItem a = new MenuItem(navn1 + " - " + navn2 + " - " + dato);
	    	meny.getItems().add(a);
	    	// Fyller ut felter
	    	a.setOnAction(e->{
	    		navnFelt1.setText(navn1);
	    		navnFelt2.setText(navn2);
	    		datoFelt.setText(dato);
	    		removeTxt(resultatFelt);
	    	});
	    }
	    
	    d1.setOnAction(e->{
	    	resultatFelt.setText(navnFelt1.getText());
	    });
	    d2.setOnAction(e->{
	    	resultatFelt.setText(navnFelt2.getText());
	    });
	    r.setOnAction(e->{
	    	resultatFelt.setText(r.getText());
	    });
	    byttPlass.setOnMouseClicked(e->{
	    	String n1 = navnFelt1.getText();
	    	navnFelt1.setText(navnFelt2.getText());
	    	navnFelt2.setText(n1);
	    });
	    regTrekkHvit.setOnMouseClicked(e->{
	    	if(trekkFeltHvit.getText().isEmpty() == false) {
	    		trekkHvit[trekkTellerHvit] = trekkFeltHvit.getText();
	    		trekkFeltHvit.setText("");
	    		trekkTellerHvit ++;
	    	}
	    });
	    regTrekkSort.setOnMouseClicked(e->{
	    	if(trekkFeltSort.getText().isEmpty() == false) {
	    		trekkSort[trekkTellerSort] = trekkFeltSort.getText();
	    		trekkFeltSort.setText("");
	    		trekkTellerSort ++;
	    	}
	    });
	    

	    // Oppretter resultat-filer
	    lagreResultat.setOnMouseClicked(e -> {
	    	for(int i=0; i<trekkTellerHvit; i++) {
	    		System.out.println(trekkHvit[i]);
	    	}
	    });
	    
	    avbryt.setOnMouseClicked(e -> {
	    	System.out.println(partiListe.size());
	    });
	}
	
	public void removeTxt(TextField r) {
		r.setText("");
	}

}
