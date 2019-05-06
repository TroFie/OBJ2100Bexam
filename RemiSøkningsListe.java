
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RemiSøkningsListe extends Application
{

    private TableView<Person> table = new TableView<Person>();
    private final ObservableList<Person> data
            = FXCollections.observableArrayList(
                    new Person("Simon", "Seier", "05-05-2018"),
                    new Person("Henrik", "Tap", "01-02-2018"),
                    new Person("Syria", "Remi", "04-02-2019"),
                    new Person("Simon", "Seier", "05-05-2018"),
                    new Person("Henrik", "Tap", "01-02-2018"),
                    new Person("Syria", "Remi", "04-02-2019"),
                    new Person("Simon", "Seier", "05-05-2018"),
                    new Person("Henrik", "Tap", "01-02-2018"),
                    new Person("Syria", "Remi", "04-02-2019"),
                    new Person("Simon", "Seier", "05-05-2018"),
                    new Person("Henrik", "Tap", "01-02-2018"),
                    new Person("Syria", "Remi", "04-02-2019"),
                    new Person("Simon", "Seier", "05-05-2018"),
                    new Person("Henrik", "Tap", "01-02-2018"),
                    new Person("Syria", "Remi", "04-02-2019"),
                    new Person("Simon", "Seier", "05-05-2018"),
                    new Person("Henrik", "Tap", "01-02-2018"),
                    new Person("Syria", "Remi", "04-02-2019"),
                    new Person("Simon", "Seier", "05-05-2018"),
                    new Person("Henrik", "Tap", "01-02-2018"),
                    new Person("Syria", "Remi", "04-02-2019")
            );

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        Scene scene = new Scene(new Group());
        stage.setTitle("Parti-liste");
        stage.setWidth(450);
        stage.setHeight(550);

        final Label label = new Label("Parti-liste");
        label.setFont(new Font("Arial", 19));

        table.setEditable(true);

        TableColumn navnSpiller = new TableColumn("Navn");
        navnSpiller.setMinWidth(100);
        navnSpiller.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));

        TableColumn resultatSpiller = new TableColumn("Resultat");
        resultatSpiller.setMinWidth(100);
        resultatSpiller.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName"));

        TableColumn datoSpiller = new TableColumn("Dato");
        datoSpiller.setMinWidth(200);
        datoSpiller.setCellValueFactory(
                new PropertyValueFactory<Person, String>("email"));

        FilteredList<Person> flPerson = new FilteredList(data, p -> true);//Pass the data to a filtered list
        table.setItems(flPerson);//Set the table's items using the filtered list
        table.getColumns().addAll(navnSpiller, resultatSpiller, datoSpiller);

        //Adding ChoiceBox and TextField here!
        ChoiceBox<String> choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll("Navn", "Resultat");
        choiceBox.setValue("Navn");

        TextField textField = new TextField();
        textField.setPromptText("Søk her");
        textField.setOnKeyReleased(keyEvent ->
        {
            switch (choiceBox.getValue())//Switch on choiceBox value
            {
                case "Navn":
                    flPerson.setPredicate(p -> p.getFirstName().toLowerCase().contains(textField.getText().toLowerCase().trim()));//filter table by first name
                    break;
                case "Resultat":
                    flPerson.setPredicate(p -> p.getLastName().toLowerCase().contains(textField.getText().toLowerCase().trim()));//filter table by first name
                    break;
            }
        });

        choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->
        {//reset table and textfield when new choice is selected
            if (newVal != null)
            {
                textField.setText("");
                flPerson.setPredicate(null);//This is same as saying flPerson.setPredicate(p->true);
            }
        });
        HBox hBox = new HBox(choiceBox, textField);//Add choiceBox and textField to hBox
        hBox.setAlignment(Pos.CENTER);//Center HBox
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hBox);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

    
    
    
    
    public static class Person
    {

        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;

        private Person(String fName, String lName, String email)
        {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
        }

        public String getFirstName()
        {
            return firstName.get();
        }

        public void setFirstName(String fName)
        {
            firstName.set(fName);
        }

        public String getLastName()
        {
            return lastName.get();
        }

        public void setLastName(String fName)
        {
            lastName.set(fName);
        }

        public String getEmail()
        {
            return email.get();
        }

        public void setEmail(String fName)
        {
            email.set(fName);
        }
    }
}