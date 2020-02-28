/* Name:   Tejada, Jason
 * CIN:    306896517
 * Course: CS2012 - 07/08
 * 
 * Description: This class adds the add, remove, filechoose, and search functionality to the database
 *
 */
package tejada_jason;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import java.util.InputMismatchException;

public class DBUtilityMethods extends GridPane {
    //Necessary Datafields
    private Database db;
    private TextArea displayArea;

    //Label Datafields
    private Label lblSearch = new Label("Search: ");

    //Textfield Datafields
    private TextField tfSearch = new TextField();
    private TextField tfRemoveData = new TextField();

    //Button Datafields
    private Button btRemove = new Button("   REMOVE   ");
    private Button btAdd = new Button(" ADD ");
    private Button btClear = new Button("Clear");

    //Constructor
    public DBUtilityMethods(Database db, TextArea displayArea) {
        //Initializing Data
        this.db = db;
        this.displayArea = displayArea; 


        //Calling methods
        this.format();
        this.assignEventToSearch();
        this.assignEventToRemove();
        this.assignEventToAdd();
        this.assignEventToClear();

        //Placing things in pane
        this.add(this.lblSearch, 0, 0);
        this.add(this.tfSearch, 0, 1);
        this.add(this.btRemove, 18, 0);
        this.add(this.tfRemoveData, 18, 1);
        this.add(this.btAdd, 12, 0);
        this.add(this.btClear, 12, 1);

        //Formatting pane 
        this.setPadding(new Insets(20, 20, 20, 20));
        this.setVgap(10);
        this.setHgap(10);

    }

    private void assignEventToSearch() {
        
        this.tfSearch.setOnKeyPressed(e -> {
             if(e.getCode() == KeyCode.ENTER) {
                try {
                   String query = this.tfSearch.getText();
                   String result = this.parseSearchValue(query);
                   this.updateTextArea(result);
                }
                
                catch(InputMismatchException ex) {
                    this.tfSearch.setText("Enter a name");
                }
            }
        });
    }

    private void assignEventToRemove() {
        this.btRemove.setOnAction(e -> {
            String query = tfRemoveData.getText();
          
            String[] valueToRemove = this.parseRemoveValue(query);
            this.db.removePerson(valueToRemove);

            this.updateTextArea(this.db.toString());
        });
    }

    private void assignEventToClear() {
        this.btClear.setOnAction(e -> {
            this.updateTextArea("");
        });
    }

    private void assignEventToAdd() {
        this.btAdd.setOnAction(e -> {
            Stage addStage = new Stage();
            addStage.setTitle("Add Person");
            
            DBAddPersonPane addPane = new DBAddPersonPane(this.db, addStage);
            
            Scene addScene = new Scene(addPane, 400, 400);

            addStage.setScene(addScene);

            addStage.setResizable(false);
            addStage.setMinHeight(400);
            addStage.setMinWidth(400);

            addStage.show();
        });
    }


    private void updateTextArea(String result) {
        this.displayArea.setText(result);
    }

    private void format() {
        //Label formatting
        this.lblSearch.setStyle("-fx-font: 13 monospace;");
        
        //Textfield formatting
        this.tfSearch.setStyle("-fx-font: 13 monospace;");
        this.tfRemoveData.setStyle("-fx-font: 13 monospace;");

        //Button formatting
        this.btRemove.setStyle("-fx-font: 13 monospace;");
        this.btAdd.setStyle("-fx-font: 13 monospace;");
        this.btClear.setStyle("-fx-font: 13 monospace;");

        //Display area formatting
        this.displayArea.setStyle("-fx-font: 18 monospace;");
    }

    private String parseSearchValue(String search) {
        String result = ""; 

        if(search.matches("\\w{1,}\\s{1}\\w{1,}")) {
            String[] arr = search.split("\\s{1}"); 
            result = db.searchDatabase(arr);
        }

        else if(search.matches("\\w{1,}")) {
            String[] arr = {search};
            result = db.searchDatabase(arr);
        }

        return result;
    }

    private String[] parseRemoveValue(String remValue) {
        String[] result = {""};

        if(remValue.matches("\\w{1,}\\s{1}\\w{1,}")) {
            result = remValue.split("\\s{1}");
        }

        return result;
    }
}
