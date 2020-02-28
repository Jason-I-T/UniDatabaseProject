/* Name:   Tejada, Jason
 * CIN:    306896517
 * Course: CS2012 - 07/08
 *
 * Description: This program serves as the backend for the addition of a person
 *
 */

package tejada_jason;

import java.util.ArrayList;

public class AddPerson {
    //Datafields
    private Database db;
    private String[] personData;
    private int personType;
    private Address address;
    private ArrayList<PhoneNumber> phoneList = new ArrayList<>();

    //Constructor
    public AddPerson(Database db, String[] personData, int personType) {
        this.db = db;
        this.personData = personData;
        this.personType = personType;
    }

    public void setAddressValues(String[] addressValues) {
        if(addressValues.length == 6) {
            String streetNumber = addressValues[0];
            String aptNumber = addressValues[1];
            String streetName = addressValues[2];
            String city = addressValues[3];
            String state = addressValues[4];
            int zipCode = Integer.parseInt(addressValues[5]);

            this.address = new Address(streetNumber, aptNumber, streetName, city, state, zipCode);
        } 

        else {
            String streetNumber = addressValues[0];
            String streetName = addressValues[1];
            String city = addressValues[2];
            String state = addressValues[3];
            int zipCode = Integer.parseInt(addressValues[4]);
            
            this.address = new Address(streetNumber, streetName, city, state, zipCode);
        }
    }

    public void addPhoneNumber(String type, String digits) throws PhoneNumberFormatException {
        if(!(digits.matches("\\d{3}-\\d{3}-\\d{4}"))) {
            throw new PhoneNumberFormatException("ERROR");
        }

        else {
            String[] tokens = digits.split("-");
            
            int areaCode = Integer.parseInt(tokens[0]);
            int prefix = Integer.parseInt(tokens[1]);
            int suffix = Integer.parseInt(tokens[2]);

            this.phoneList.add(new PhoneNumber(type, areaCode, prefix, suffix));
        }
    }

    public void addToDatabase() {
        if(this.personType == 0) { //Faculty
            Double salary = Double.parseDouble(this.personData[4]);
            Faculty faculty = new Faculty(this.personData[0], this.personData[1], this.personData[2], 
                    this.address, this.phoneList, this.personData[3], salary, this.personData[5], this.personData[6]);

            this.db.add(faculty);
        }

        else if(this.personType == 1) { //Staff
            Double salary = Double.parseDouble(this.personData[4]);
            Staff staff = new Staff(this.personData[0], this.personData[1], this.personData[2], 
                    this.address, this.phoneList, this.personData[3], salary, this.personData[5]);

            this.db.add(staff);
        }

        else if(this.personType == 2) { //Student
            Double gpa = Double.parseDouble(this.personData[4]);
            Student student = new Student(this.personData[0], this.personData[1], this.personData[2],
                    this.address, this.phoneList, this.personData[3], gpa);

            this.db.add(student);
        }

        else {
            return;
        }
    }
}
