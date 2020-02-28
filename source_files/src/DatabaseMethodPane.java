package tejada_jason;

import javafx.scene.layout.Region;
import javafx.scene.input.KeyCode;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.lang.NumberFormatException;

public class DatabaseMethodPane extends GridPane {
    //Data to be passed around
    private Database db;
    private TextArea displayArea;

    //ArrayList datafield to be used in conjunction with ComboBox
    ArrayList<String> choiceList = new ArrayList<>();

    //Lable Datafields
    private Label lblPrintMethods = new Label("Display Options:");
    private Label lblFilterGPA = new Label("Filter GPA:");
    private Label lblGreaterThanGPA = new Label("Greater Than/Equal");
    private Label lblLessThanGPA = new Label("Less Than/Equal");
    private Label lblNumOfPeople = new Label("Number Of People:");

    //Button Datafields
    private Button displayButton = new Button("Display\n  All");

    //Combobox Datafield
    private ComboBox<String> displayChoices = new ComboBox<>();

    //TextField Datafields
    private TextField tfFilterStudentGtGPA = new TextField();
    private TextField tfFilterStudentLtGPA = new TextField();

    //TextArea Datafield
    private TextArea taShowNumberOfPeople = new TextArea();

    
    //Constructor
    public DatabaseMethodPane(Database db, TextArea displayArea) {
        //Passing values to program
        this.db = db;
        this.displayArea = displayArea;
        
        //Setting nodes inside the pane
        this.add(this.displayButton, 0, 1);
        this.add(this.displayChoices, 0, 2);
        this.add(this.lblPrintMethods, 0, 0);
        this.add(this.lblFilterGPA, 0, 5);
        this.add(this.lblGreaterThanGPA, 0, 6);
        this.add(this.tfFilterStudentGtGPA, 0, 7);
        this.add(this.lblLessThanGPA, 0, 8);
        this.add(this.tfFilterStudentLtGPA, 0, 9);
        this.add(this.lblNumOfPeople, 0, 3);
        this.add(this.taShowNumberOfPeople, 0, 4);

        //Calling methods important in making the program work
        this.assignEventsToButtons();
        this.assignEventsToComboBox();
        this.assignEventsToFilters();
        this.format();
        this.assignChoices();
        this.assignChoiceList();

        //Formatting pane 
        this.setPadding(new Insets(20, 20, 20, 20));
        this.setVgap(10);
        this.setHgap(10);

    }


    //Assigning action events to buttons
    private void assignEventsToButtons() {
        //Display button event
        this.displayButton.setOnAction(e -> {

            String result = this.db.toString();
            String numOfPeople = this.numberOfPersonObjects(-1);

            this.makeGPAFilterVisible(0);            
            
            this.updateNumberArea(numOfPeople);
            this.updateTextArea(result);
        });
    }

    //Assigning action event to the combobox
    private void assignEventsToComboBox() {
        this.displayChoices.setOnAction(e -> {

            int index = this.choiceList.indexOf(this.displayChoices.getValue()); 
            
            //If the index pertains to printing out students, display the filter datafields (Make this its own method)
            this.makeGPAFilterVisible(index);

            String numOfPeople = this.numberOfPersonObjects(index);
            String result = this.printMethodSelection(index);

            this.updateNumberArea(numOfPeople);

            this.updateTextArea(result);
        });
    }

    //Assinging key event to filter text fields
    private void assignEventsToFilters() {
        //Filter gpa greater than/equal to
        this.tfFilterStudentGtGPA.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                try {
                    double studGPA = this.readStudentGPA(this.tfFilterStudentGtGPA.getText().trim());
                    String result = this.db.printStudentsGreaterThanOrEqualToGPA(studGPA);
                    this.updateTextArea(result);

                }
                catch(NumberFormatException ex) {
                    this.tfFilterStudentGtGPA.setText("Enter Number");
                }
            }
        });
        
        //Filter gpa less than/equal to
        this.tfFilterStudentLtGPA.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                try {
                    double studGPA = this.readStudentGPA(this.tfFilterStudentLtGPA.getText().trim());
                    String result = this.db.printStudentsLessThanOrEqualToGPA(studGPA);
                    this.updateTextArea(result);

                }
                catch(NumberFormatException ex) {
                    this.tfFilterStudentLtGPA.setText("Enter Number");
                }
            }
        });

    }
    
    //Method that formats the nodes in the pane
    private void format() {
        //Display button formatting
        this.displayButton.setStyle("-fx-font: 13 monospace;");

        //Combobox Formatting
        this.displayChoices.setStyle("-fx-font: 13 monospace;");

        //Label formatting
        this.lblPrintMethods.setStyle("-fx-font: 13 monospace;");
        this.lblFilterGPA.setStyle("-fx-font: 13 monospace;");
        this.lblGreaterThanGPA.setStyle("-fx-font: 13 monospace;");
        this.lblLessThanGPA.setStyle("-fx-font: 13 monospace;");
        this.lblNumOfPeople.setStyle("-fx-font: 13 monospace;");

        this.lblFilterGPA.setVisible(false);
        this.lblGreaterThanGPA.setVisible(false);
        this.lblLessThanGPA.setVisible(false);

        //TextArea formatting
        this.displayArea.setStyle("-fx-font: 18 monospace;"); //Might move this to main method
        this.taShowNumberOfPeople.setStyle("-fx-font: 13 monospace;");

        this.taShowNumberOfPeople.setEditable(false);
        this.taShowNumberOfPeople.setPrefColumnCount(14);
        this.taShowNumberOfPeople.setPrefRowCount(5);

        //TextField formatting
        this.tfFilterStudentGtGPA.setPrefColumnCount(1);
        this.tfFilterStudentGtGPA.setVisible(false);
        this.tfFilterStudentLtGPA.setPrefColumnCount(1);
        this.tfFilterStudentLtGPA.setVisible(false);
    }

    //Assigning values to combobox
    private void assignChoices() {
        this.displayChoices.getItems().addAll("Employees", "Faculty", "Staff", "Students");
    }

    //Assigning values to choicesList datafield
    private void assignChoiceList() {
        this.choiceList.add("Employees");
        this.choiceList.add("Faculty");
        this.choiceList.add("Staff");
        this.choiceList.add("Students");
    }

    //Method that takes an index, and if it pertains to a student, make filter options for GPA visible
    private void makeGPAFilterVisible(int index) {
        if(index == 3) {
                 
             this.lblFilterGPA.setVisible(true);
             this.lblGreaterThanGPA.setVisible(true);
             this.lblLessThanGPA.setVisible(true);
             this.tfFilterStudentGtGPA.setVisible(true);
             this.tfFilterStudentLtGPA.setVisible(true);
        }

        else {

            this.lblFilterGPA.setVisible(false);
            this.lblGreaterThanGPA.setVisible(false);
            this.lblLessThanGPA.setVisible(false);
            this.tfFilterStudentGtGPA.setVisible(false);
            this.tfFilterStudentLtGPA.setVisible(false);
        }

    }

    //Method that takes an index, then returns a string pertaining to the selected option in the combo box
    private String printMethodSelection(int index) {
        String result = "";

        if(index == 0) {
            result = this.db.printEmployees();
        }

        else if(index == 1) {
            result = this.db.printFaculty();
        }

        else if(index == 2) {
            result = this.db.printStaff();
        }

        else if(index == 3) {
            result = this.db.printStudents();
        }

        return result;
    }

    //Methods that takes a string, puts it in the text area.
    private void updateTextArea(String result) {
        this.displayArea.setText(result);
    }

    private void updateNumberArea(String result) {
        this.taShowNumberOfPeople.setText(result);
    }

    //Method that takes a string, converts it to a double and then returns it.
    private Double readStudentGPA(String gpa) throws NumberFormatException {
        double value = Double.parseDouble(gpa);
        return value;
    }

    //Method that takes an index, calls the appropriate getNumberOf... method, and then spits out a string.
    private String numberOfPersonObjects(int index) {
        String result = "";

        if(index == 0) {
            //Get num for employees, staff, and faculty
            result += String.format("Employees: %d%nFaculty: %d%nStaff: %d%n", 
                                    this.db.getNumberOfEmployees(), this.db.getNumberOfFaculty(),
                                    this.db.getNumberOfStaff());
        }

        else if(index == 1) {
            //Get num for faculty
            result += String.format("Faculty: %d", this.db.getNumberOfFaculty());
        }

        else if(index == 2) {
            //Get num for staff 
            result += String.format("Staff: %d", this.db.getNumberOfStaff());
        }

        else if(index == 3) {
            //Get num for students
            result += String.format("Freshman: %d%nSophomores: %d%nJuniors: %d%nSeniors: %d%nGraduates: %d%n",
                                    this.db.getNumberOfStudentsByClassStanding("freshman"), this.db.getNumberOfStudentsByClassStanding("sophomore"),
                                    this.db.getNumberOfStudentsByClassStanding("senior"), this.db.getNumberOfStudentsByClassStanding("junior"), 
                                    this.db.getNumberOfStudentsByClassStanding("graduate"));
        }

        else {
            //Get num for display all method
            result += String.format("Employees: %d%nFaculty: %d%nStaff: %d%nStudents: %d%n",
                                    this.db.getNumberOfEmployees(), this.db.getNumberOfFaculty(), 
                                    this.db.getNumberOfStaff(), this.db.getNumberOfStudents());
        }

        return result;
    }
}
