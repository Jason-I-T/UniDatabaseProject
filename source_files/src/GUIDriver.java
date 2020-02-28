package tejada_jason;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;

public class GUIDriver extends Application {
    //Database Datafield
    private Database db = new Database();

    //Button Datafields
    private Button btUpload = new Button("Upload");
    private Button btSave = new Button(" Save ");

    //Display area datafield
    private TextArea display = new TextArea();

    //Datafields for the panes that have all the methods and stuff
    private DatabaseMethodPane databaseMethods = new DatabaseMethodPane(this.db, this.display);
    private DBUtilityMethods dbExtraMethods = new DBUtilityMethods(this.db, this.display);

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Setting restrictions for display area
        display.setEditable(false);

        //Adding filchooser buttons to dbExtraMethods pane
        dbExtraMethods.add(this.btUpload, 5, 1);
        dbExtraMethods.add(this.btSave, 5, 0);

        //Formatting buttons
        this.btUpload.setStyle("-fx-font: 13 monospace;");
        this.btSave.setStyle("-fx-font:13 monospace;");

        //Formatting Pane
        this.dbExtraMethods.setAlignment(Pos.CENTER);

        //Initializing FileChooser
        FileChooser fileUpload = new FileChooser();
        fileUpload.setTitle("Select Database");
        fileUpload.getExtensionFilters().add(new ExtensionFilter("CSV Files", "*.csv")); 
        fileUpload.setInitialDirectory(fileUpload.getInitialDirectory());

        FileChooser fileSave = new FileChooser();
        fileSave.setTitle("Save Database");
        fileSave.getExtensionFilters().add(new ExtensionFilter("CSV Files", "*.csv"));
        fileSave.setInitialDirectory(fileSave.getInitialDirectory());

        //Assigning action events to file chooser buttons/////////////////////////////////
        //Upload
        this.btUpload.setOnAction(e-> {
            File selectedFile = fileUpload.showOpenDialog(primaryStage);
            
            if(selectedFile != null) {
                try {
                    //Changing Database
                    this.db = (new DatabaseFileIO()).readFromFile(selectedFile.getPath());

                    //Clearing text
                    this.display.setText(" ");

                    //Assining panes to work with the new database
                    this.databaseMethods = new DatabaseMethodPane(this.db, this.display);
                    this.dbExtraMethods = new DBUtilityMethods(this.db, this.display);

                    //Formatting pane
                    this.dbExtraMethods.setAlignment(Pos.CENTER);
                    
                    //Replacing the upload button and save button
                    this.dbExtraMethods.add(this.btUpload, 5, 1);
                    this.dbExtraMethods.add(this.btSave, 5, 0);

                    //Re-Adding the panes to a borderpane
                    BorderPane borderPane = new BorderPane();

                    borderPane.setLeft(this.databaseMethods);
                    borderPane.setBottom(this.dbExtraMethods);
                    borderPane.setCenter(this.display);
                    
                    //Creating new scene with the updated panes
                    Scene scene = new Scene(borderPane, 960, 720);

                    //Setting the new scene to the primary stage
                    primaryStage.setScene(scene);
                }

                catch(NullPointerException ex) {
                    //Do nothing
                }
            }
        });
        
        //Save
        this.btSave.setOnAction(e-> {
            File destination = fileSave.showSaveDialog(primaryStage);
            
            if(destination != null) {
                (new DatabaseFileIO()).writeToFile(this.db, destination.getPath()); 
            }
        });
        //////////////////////////////////////////////////////////////////////////////////

        BorderPane borderPane = new BorderPane();

        borderPane.setLeft(this.databaseMethods);

        borderPane.setBottom(this.dbExtraMethods);


        borderPane.setCenter(this.display);

        Scene scene = new Scene(borderPane, 960, 720);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setMinWidth(960);
        primaryStage.setMinHeight(720);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
