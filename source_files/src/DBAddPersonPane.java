/* Name:   Tejada, Jason
 * CIN:    306896517 
 * Course: CS2012 - 07/08 
 *
 * Description: Program where the user is able to create a person through a GUI
 *
 */
package tejada_jason;

import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.util.ArrayList;

public class DBAddPersonPane extends GridPane {
    //Datafields
    private Database db;
    private Stage stage;
    private int index;
    private AddPerson addPerson;
    private int count = 1;

    //Arraylist datafield to be used in conjunction with ComboBox
    private ArrayList<String> personChoiceList = new ArrayList<>();

    //Combobox Datafield
    private ComboBox<String> cbPersonChoices = new ComboBox<>();

    //Button Datafields
    private Button btPersonSubmit = new Button("Submit"); 
    private Button btAddressSubmit = new Button("Submit");
    private Button btPhoneNumberSubmit = new Button("+");
    private Button btAddPerson = new Button("Add Person");

    //Label Datafields
    private Label lblChooseFromList = new Label("Choose Person Type");

    private Label lblFirstName = new Label("First Name");
    private Label lblLastName = new Label("Last Name");
    private Label lblEmail = new Label("Email");
    
    private Label lblGPA = new Label("GPA");
    private Label lblClassStanding = new Label("Class Standing");

    private Label lblOfficeLocation = new Label("Office Location");
    private Label lblSalary = new Label("Salary");

    private Label lblJobTitle = new Label("Job Title");

    private Label lblOfficeHours = new Label("Office Hours");
    private Label lblRank = new Label("Rank");

    private Label lblStreetNumber = new Label("Street Number");
    private Label lblAptNumber = new Label("Apt. (Leave blank if N/A)");
    private Label lblStreetName = new Label("Street Name");
    private Label lblCity = new Label("City");
    private Label lblState = new Label("State");
    private Label lblZipCode = new Label("Zip Code");

    private Label lblPhoneType = new Label("Type");
    private Label lblPhoneDigits = new Label("Phone Number");

    //TextField Datafields
    private TextField tfFirstName = new TextField();
    private TextField tfLastName = new TextField();
    private TextField tfEmail = new TextField();

    private TextField tfGPA = new TextField();
    private TextField tfClassStanding = new TextField();

    private TextField tfOfficeLocation = new TextField();
    private TextField tfSalary = new TextField();

    private TextField tfJobTitle = new TextField();

    private TextField tfOfficeHours = new TextField();
    private TextField tfRank = new TextField();

    private TextField tfStreetNumber = new TextField();
    private TextField tfAptNumber = new TextField();
    private TextField tfStreetName = new TextField();
    private TextField tfCity = new TextField();
    private TextField tfState = new TextField();
    private TextField tfZipCode = new TextField();

    private TextField tfPhoneType = new TextField();
    private TextField tfPhoneDigits = new TextField();


    //Constructor
    public DBAddPersonPane(Database db, Stage stage) {
        //Initializing database to program
        this.db = db;
        this.stage = stage;

        //Setting nodes inside the pane
        this.add(this.lblChooseFromList, 0, 1);
        this.add(this.cbPersonChoices, 1, 1);

        //Calling methods
        this.format();
        this.assignCbPersonChoices();
        this.assignPersonChoiceList();
        this.assignEventToPersonChoice();
        this.assignEventToPersonSubmit();
        this.placePersonTextFieldsAndLabels();
        this.hideAllPersonTextFields();
        this.hideAllPersonLabels();
        this.visibilityForAddressNodes(false);
        this.placeAddressTextFieldsAndLabels();
        this.assignEventToAddressSubmit();
        this.assignEventToPhoneNumberSubmit();
        this.assignEventToAddPerson();

        //Formatting pane
        this.setPadding(new Insets(20, 20, 20, 20));
        this.setVgap(10);
        this.setHgap(10);
    }

    //Method to format nodes in the pane
    private void format() {
        //Formatting ComboBox
        this.cbPersonChoices.setStyle("-fx-font: 13 monospace;");
        this.lblChooseFromList.setStyle("-fx-font: 13 monospace;");

        //Format Person TextFields
        this.tfFirstName.setStyle("-fx-font: 13 monospace;");
        this.tfLastName.setStyle("-fx-font: 13 monospace;");
        this.tfEmail.setStyle("-fx-font: 13 monospace;");
        
        this.tfGPA.setStyle("-fx-font: 13 monospace;");
        this.tfClassStanding.setStyle("-fx-font: 13 monospace;");

        this.tfOfficeLocation.setStyle("-fx-font: 13 monospace;");
        this.tfSalary.setStyle("-fx-font: 13 monospace;");

        this.tfJobTitle.setStyle("-fx-font: 13 monospace;");

        this.tfOfficeHours.setStyle("-fx-font: 13 monospace;");
        this.tfRank.setStyle("-fx-font: 13 monospace;");

        //Format Person Labels
        this.lblFirstName.setStyle("-fx-font: 13 monospace;");
        this.lblLastName.setStyle("-fx-font: 13 monospace;");
        this.lblEmail.setStyle("-fx-font: 13 monospace;");
        
        this.lblGPA.setStyle("-fx-font: 13 monospace;");
        this.lblClassStanding.setStyle("-fx-font: 13 monospace;");

        this.lblOfficeLocation.setStyle("-fx-font: 13 monospace;");
        this.lblSalary.setStyle("-fx-font: 13 monospace;");

        this.lblJobTitle.setStyle("-fx-font: 13 monospace;");

        this.lblOfficeHours.setStyle("-fx-font: 13 monospace;");
        this.lblRank.setStyle("-fx-font: 13 monospace;");

        //Format Address Textfields and labels
        this.tfStreetNumber.setStyle("-fx-font: 13 monospace;");
        this.tfAptNumber.setStyle("-fx-font: 13 monospace;");
        this.tfStreetName.setStyle("-fx-font: 13 monospace;");
        this.tfCity.setStyle("-fx-font: 13 monospace;");
        this.tfState.setStyle("-fx-font: 13 monospace;");
        this.tfZipCode.setStyle("-fx-font: 13 monospace;");

        this.lblStreetNumber.setStyle("-fx-font: 13 monospace;");
        this.lblAptNumber.setStyle("-fx-font: 13 monospace;");
        this.lblStreetName.setStyle("-fx-font: 13 monospace;");
        this.lblCity.setStyle("-fx-font: 13 monospace;");
        this.lblState.setStyle("-fx-font: 13 monospace;");
        this.lblZipCode.setStyle("-fx-font: 13 monospace;");

        //Format Phone Address textfield and labels
        this.lblPhoneType.setStyle("-fx-font: 13 monospace;");
        this.lblPhoneDigits.setStyle("-fx-font: 13 monospace;");

        this.tfPhoneType.setStyle("-fx-font: 13 monospace;");
        this.tfPhoneDigits.setStyle("-fx-font: 13 monospace;");

        //Format buttos
        this.btPersonSubmit.setStyle("-fx-font: 13 monospace;");
        this.btAddressSubmit.setStyle("-fx-font: 13 monospace;");
        this.btPhoneNumberSubmit.setStyle("-fx-font: 13 monospace;");
        this.btAddPerson.setStyle("-fx-font: 13 monospace;");
    }
    
    //Assining event to cbPersonChoices datafield
    private void assignEventToPersonChoice() {
        this.cbPersonChoices.setOnAction(e -> {
            //Selecting type of person
            this.index = this.personChoiceList.indexOf(this.cbPersonChoices.getValue());

            //Hiding ComboBox and label
            this.cbPersonChoices.setVisible(false);
            this.lblChooseFromList.setVisible(false);

            //Making person text fields and labels visible
            this.personWhatToMakeVisible(index);

            //Place btPersonSubmit
            this.add(this.btPersonSubmit, 0, 8);
        });
    }

    private void assignEventToPersonSubmit() {
        this.btPersonSubmit.setOnAction(e -> {
            try {
                String[] personData = this.retrivePersonalData(this.index);

                boolean check = true;

                if(this.index == 2) {
                    double gpaCheck = Double.parseDouble(personData[4]);
                }

                else if(this.index == 0 || this.index == 1) {
                    double salaryCheck = Double.parseDouble(personData[4]);
                }

                for(int i = 0; i < personData.length; i++) {

                    if(personData[i].length() == 0) {
                        check = false;
                    }
                }

                if(!check) {
                    this.tfFirstName.setText("Please Enter Values For All Feilds");
                }

                else {
                    this.addPerson = new AddPerson(this.db, personData, this.index);     

                    this.btPersonSubmit.setVisible(false);
                    this.hideAllPersonTextFields();
                    this.hideAllPersonLabels();

                    this.visibilityForAddressNodes(true);
                    this.add(btAddressSubmit, 0, 8);
                }
            }

            catch(NumberFormatException ex) {
                if(this.index == 2) {
                    this.tfGPA.setText("Enter a correct number");
                }

                else if(this.index == 1 || this.index == 0) {
                    this.tfSalary.setText("Enter a correct number");
                }
            }
        });
    }

    private void assignEventToAddressSubmit() {
        this.btAddressSubmit.setOnAction(e -> {
            String streetNumber = this.tfStreetNumber.getText();
            String aptNumber = "Apt. " + this.tfAptNumber.getText();
            String streetName = this.tfStreetName.getText();
            String city = this.tfCity.getText();
            String state = this.tfState.getText();
            String zipCode = this.tfZipCode.getText();

            try {
                //Checking if user entered values
                String[] addyCheck = {streetNumber, streetName, city, state, zipCode};
                boolean check = true;

                for(int i = 0; i < addyCheck.length; i++) {
                    if(addyCheck[i].length() == 0) {
                        check = false;
                    }
                }
                if(!check) {
                    this.tfStreetName.setText("Please enter values for all fields");
                }

                else {
                    if(this.tfAptNumber.getText().length() == 0) {
                        String[] addressValues = {streetNumber, streetName, city, state, zipCode};
                        this.addPerson.setAddressValues(addressValues);
                    }

                    else {
                        String[] addressValues = {streetNumber, aptNumber, streetName, city, state, zipCode};
                        this.addPerson.setAddressValues(addressValues);
                    }

                    //Hide address submit button, hide labels and textfields, make phone number textfields labels and button visible
                    this.btAddressSubmit.setVisible(false);
                    this.visibilityForAddressNodes(false);
                    
                    //Placing phone nodes
                    this.add(this.lblPhoneType, 0, 1);
                    this.add(this.tfPhoneType, 1, 1);

                    this.add(this.lblPhoneDigits, 0, 2);
                    this.add(this.tfPhoneDigits, 1, 2);

                    this.add(this.btPhoneNumberSubmit, 2, 2);
                }
            }

            catch(NumberFormatException ex) {
                this.tfZipCode.setText("Enter a correct zipcode");
            }
        });
    }

    private void assignEventToPhoneNumberSubmit() {
        this.btPhoneNumberSubmit.setOnAction(e -> {
            try {
                String type = this.tfPhoneType.getText();
                String digits = this.tfPhoneDigits.getText();

                if(type.length() == 0 || digits.length() == 0) {
                    this.tfPhoneDigits.setText("Enter Values for All Fields");
                }

                else {
                    this.addPerson.addPhoneNumber(type, digits);
                    if(this.count == 1) {
                        this.add(this.btAddPerson, 0, 4);
                        this.count++;
                    }
                }
            }

            catch(PhoneNumberFormatException ex) {
                this.tfPhoneType.setText("Incorrect Format");
                this.tfPhoneDigits.setText("xxx-xxx-xxxx");
            }
        });
    }

    private void assignEventToAddPerson() {
        this.btAddPerson.setOnAction(e -> {
            this.addPerson.addToDatabase();
            this.stage.close();
        });
    }


    //Assining values to cbPersonChoices datafield
    private void assignCbPersonChoices() {
        this.cbPersonChoices.getItems().addAll("Faculty", "Staff", "Student");
    }

    //Assigning values to person choice list datafield
    private void assignPersonChoiceList() {
        this.personChoiceList.add("Faculty");
        this.personChoiceList.add("Staff");
        this.personChoiceList.add("Student");
    }

    //Method that takes an index, gets strings from textfields, and returns the data as a string array
    private String[] retrivePersonalData(int index) {
        String[] error = {""};

        String firstName = this.tfFirstName.getText();
        String lastName = this.tfLastName.getText();
        String email = this.tfEmail.getText();

        if(index == 0 || index == 1) {
            String officeLocation = this.tfOfficeLocation.getText();
            String salary = this.tfSalary.getText();

            if(index == 0) {
                String officeHours = this.tfOfficeHours.getText();
                String rank = this.tfRank.getText();

                String[] facultyData = {firstName, lastName, email, officeLocation, salary, officeHours, rank};

                return facultyData;
            }

            else {
                String jobTitle = this.tfJobTitle.getText();

                String[] staffData = {firstName, lastName, email, officeLocation, salary, jobTitle};

                return staffData;
            }
        }
        
        else if(index == 2) {
            //get stuff from person and student text fields
            String gpa = this.tfGPA.getText();
            String classStanding = this.tfClassStanding.getText();

            String[] studentData = {firstName, lastName, email, classStanding, gpa};

            return studentData;
        }

        return error;
    }

    //Method that takes in an index, and makes certain person labels and text fields visible
    private void personWhatToMakeVisible(int index) {
        //Making basic person datafields visible
        this.makeGeneralPersonLabelsVisible();
        this.makeGeneralPersonTextFieldsVisible();

        if(index == 0) {
            //Make faculty stuff visible
            this.makeEmployeeTextFieldsVisible();
            this.makeEmployeeLabelsVisible();
            this.makeFacultyTextFieldsVisible();
            this.makeFacultyLabelsVisible();
        }

        else if(index == 1) {
            //Make staff stuff visible
            this.makeEmployeeTextFieldsVisible();
            this.makeEmployeeLabelsVisible();
            this.makeStaffTextFieldsVisible();
            this.makeStaffLabelsVisible();
        }

        else if(index == 2) {
            this.makeStudentTextFieldsVisible();
            this.makeStudentLabelsVisible();
        }
    }

    //Method that places person textfields and labels in the pane
    private void placePersonTextFieldsAndLabels() {
        this.add(this.lblFirstName, 0, 1);
        this.add(this.tfFirstName, 1, 1);
        this.add(this.lblLastName, 0, 2);
        this.add(this.tfLastName, 1, 2);
        this.add(this.lblEmail, 0, 3);
        this.add(this.tfEmail, 1, 3);

        this.add(this.lblGPA, 0, 4);
        this.add(this.tfGPA, 1, 4);
        this.add(this.lblClassStanding, 0, 5);
        this.add(this.tfClassStanding, 1, 5);

        this.add(this.lblOfficeLocation, 0, 4);
        this.add(this.tfOfficeLocation, 1, 4);
        this.add(this.lblSalary, 0, 5);
        this.add(this.tfSalary, 1, 5);
        
        this.add(lblJobTitle, 0, 6);
        this.add(tfJobTitle, 1, 6);

        this.add(lblOfficeHours, 0, 6);
        this.add(tfOfficeHours, 1, 6);
        this.add(lblRank, 0, 7);
        this.add(tfRank, 1, 7);
    }

    //Method to place address text fields and labels
    private void placeAddressTextFieldsAndLabels() {
        this.add(this.lblStreetNumber, 0, 1);
        this.add(this.tfStreetNumber, 1, 1);

        this.add(this.lblAptNumber, 0, 2);
        this.add(this.tfAptNumber, 1, 2);

        this.add(this.lblStreetName, 0, 3);
        this.add(this.tfStreetName, 1, 3);

        this.add(this.lblCity, 0, 4);
        this.add(this.tfCity, 1, 4);
        
        this.add(this.lblState, 0, 5);
        this.add(this.tfState, 1, 5);

        this.add(this.lblZipCode, 0 ,6);
        this.add(this.tfZipCode, 1, 6);
    }

    //Methods to make all the person textfields hidden/visible
    private void hideAllPersonTextFields() {
        this.tfFirstName.setVisible(false);
        this.tfLastName.setVisible(false);
        this.tfEmail.setVisible(false);
        
        this.tfGPA.setVisible(false);
        this.tfClassStanding.setVisible(false);

        this.tfOfficeLocation.setVisible(false);
        this.tfSalary.setVisible(false);

        this.tfJobTitle.setVisible(false);

        this.tfOfficeHours.setVisible(false);
        this.tfRank.setVisible(false);
    }

    private void makeGeneralPersonTextFieldsVisible() {
        this.tfFirstName.setVisible(true);
        this.tfLastName.setVisible(true);
        this.tfEmail.setVisible(true);
    }

    private void makeEmployeeTextFieldsVisible() {
        this.tfOfficeLocation.setVisible(true);
        this.tfSalary.setVisible(true);
    }

    private void makeFacultyTextFieldsVisible() {
        this.tfOfficeHours.setVisible(true);
        this.tfRank.setVisible(true);
    }

    private void makeStudentTextFieldsVisible() {
        this.tfGPA.setVisible(true);
        this.tfClassStanding.setVisible(true);
    }

    private void makeStaffTextFieldsVisible() {
        this.tfJobTitle.setVisible(true);
    }

    //Methods to make person label hidden/visible
    private void hideAllPersonLabels() {
        this.lblFirstName.setVisible(false);
        this.lblLastName.setVisible(false);
        this.lblEmail.setVisible(false);
        
        this.lblGPA.setVisible(false);
        this.lblClassStanding.setVisible(false);

        this.lblOfficeLocation.setVisible(false);
        this.lblSalary.setVisible(false);

        this.lblJobTitle.setVisible(false);

        this.lblOfficeHours.setVisible(false);
        this.lblRank.setVisible(false);
    }

    private void makeGeneralPersonLabelsVisible() {
        this.lblFirstName.setVisible(true);
        this.lblLastName.setVisible(true);
        this.lblEmail.setVisible(true);
    }

    private void makeEmployeeLabelsVisible() {
        this.lblOfficeLocation.setVisible(true);
        this.lblSalary.setVisible(true);
    }

    private void makeFacultyLabelsVisible() {
        this.lblOfficeHours.setVisible(true);
        this.lblRank.setVisible(true);
    }

    private void makeStudentLabelsVisible() {
        this.lblGPA.setVisible(true);
        this.lblClassStanding.setVisible(true);
    }

    private void makeStaffLabelsVisible() {
        this.lblJobTitle.setVisible(true);
    }

    //Methods to make address textfields and labels visible
    private void visibilityForAddressNodes(boolean toggle) {
        this.tfStreetNumber.setVisible(toggle);
        this.tfAptNumber.setVisible(toggle);
        this.tfStreetName.setVisible(toggle);
        this.tfCity.setVisible(toggle);
        this.tfState.setVisible(toggle);
        this.tfZipCode.setVisible(toggle);

        this.lblStreetNumber.setVisible(toggle);
        this.lblAptNumber.setVisible(toggle);
        this.lblStreetName.setVisible(toggle);
        this.lblCity.setVisible(toggle);
        this.lblState.setVisible(toggle);
        this.lblZipCode.setVisible(toggle);
    }
}
