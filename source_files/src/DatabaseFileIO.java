/* Name:  Tejada, Jason
 * CIN:  306896517
 * Course:  CS2012 - 07/08
 * 
 * Description: This program will have functionality to input and output from a
 * CSV file.
 *
 */
package tejada_jason;

import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class DatabaseFileIO 
{
	//Datafields
	private Database db = new Database();
	private Address address;
	private ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
	private String[] personData;

	//Constructor
	public DatabaseFileIO() {

	}


	//read/////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	//Method to populate the database object with csv file things.
	public Database readFromFile(String path) 
	{
		File inFile = new File(path);
		try 
		{
			Scanner reader = new Scanner(inFile);

			while(reader.hasNextLine()) 
			{
				String line = reader.nextLine();
				String[] tokens = line.split(",");

				if(personCheck(tokens[0])) 
				{
					if(this.phoneNumbers.size() > 0) 
					{
						createPerson();
						this.phoneNumbers.clear();
					}

					this.personData = tokens;

                     
				}

				else if(tokens[0].equals("Address")) 
				{
					this.address = createAddress(tokens);
				}

				else if(tokens[0].equals("Phone")) 
				{
					this.phoneNumbers.add(createPhone(tokens));
				}				
			}
			
			createPerson(); //Creating the last person in the CSV file
			reader.close();
		}

		
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}

		return this.db;
	}

	
	//Method to check if the line refers to a person 
	private boolean personCheck(String personType) 
	{
		if(personType.equals("Student") || personType.equals("Faculty") ||
		   personType.equals("Staff")) 
		{
			return true;
		}

		else 
		{
			return false;
		}
	}

	
	//Method to return address object.
	private Address createAddress(String[] addressData) 
	{
        if(addressData.length == 7)
        {
            String streetNumber = addressData[1];
		    String aptNumber = addressData[2];
		    String streetName = addressData[3];
		    String city = addressData[4];
		    String state = addressData[5];
		    int zipCode = Integer.parseInt(addressData[6]);
            
            return new Address(streetNumber, aptNumber, streetName, city, state, zipCode);
        }

        else
        {
            String streetNumber = addressData[1];
		    String streetName = addressData[2];
		    String city = addressData[3];
		    String state = addressData[4];
		    int zipCode = Integer.parseInt(addressData[5]);

            return new Address(streetNumber, streetName, city, state, zipCode); 
        }
		
	}

	
	//Method to return phone number object.
	private PhoneNumber createPhone(String[] phoneData) 
	{
		String phoneType = phoneData[1];

		int[] phoneDigits = parsePhoneDigits(phoneData[2]);

		return new PhoneNumber(phoneType, phoneDigits[0], phoneDigits[1], phoneDigits[2]);
	}

	
	//Method that parses out the phone number components.
	private int[] parsePhoneDigits(String digitData) 
	{
		String[] tokens = digitData.split("-");

		int areaCode = Integer.parseInt(tokens[0]);
		int prefix = Integer.parseInt(tokens[1]);
		int suffix = Integer.parseInt(tokens[2]);

		int[] digits = {areaCode, prefix, suffix};

		return digits;
	}


	//Method that creates a person object and adds them to the database.
	private void createPerson() 
	{
		switch(this.personData[0]) 
		{
			case "Student": 
			{
				double gpa = Double.parseDouble(personData[5]);
				
				this.db.add(new Student(personData[1], personData[2], personData[3], 
							this.address, phoneList(this.phoneNumbers), 
							personData[4], gpa));
				
				break;
			}
				
			case "Faculty": 
			{
				double salary = Double.parseDouble(personData[5]);
				
				this.db.add(new Faculty(personData[1], personData[2], personData[3], 
							this.address, phoneList(this.phoneNumbers), 
							personData[4], salary, personData[6], personData[7]));

				break;
			}
				
			case "Staff": 
			{
				double salary = Double.parseDouble(personData[5]);
				
				this.db.add(new Staff(personData[1], personData[2], personData[3],
							this.address, phoneList(this.phoneNumbers),
							personData[4], salary, personData[6]));
				
				break;
			}

			default: 
			{
				System.out.println("ERROR");
				break;
			}
		}
	}

	
	//Method that returns the correct phone number list for a person object.
	private ArrayList<PhoneNumber> phoneList(ArrayList<PhoneNumber> phoneNumbers) 
	{
		ArrayList<PhoneNumber> newPhoneList = new ArrayList<>();

		for(int i = 0; i < phoneNumbers.size(); i++) 
		{
			newPhoneList.add(phoneNumbers.get(i));
		}

		return newPhoneList;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////end//


	//write////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	public void writeToFile(Database db, String path)
	{
		File inFile = new File(path);

		try
		{
			PrintWriter pw = new PrintWriter(inFile);

			for(int i = 0; i < db.size(); i++)
			{
				String[] csvData = personInformation(db.get(i));

				for(int j = 0; j < csvData.length; j++)
				{
					pw.println(csvData[j]);
				}
			}

			pw.close();
		}

		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}


	//Method to extract the information for the csv file
	private String[] personInformation(Person p)
	{
		String individualValues = parseIndividualValues(p);
		String addresValues = parseAddressValues(p.getAddress());

		ArrayList<PhoneNumber> phones = p.getPhoneNumbers();
		String[] phoneListValues = parsePhoneValues(phones);

		String[] csvLines = new String[2 + phoneListValues.length];

		csvLines[0] = individualValues;
		csvLines[1] = addresValues;

		for(int i = 2; i < csvLines.length; i++)
		{
			csvLines[i] = phoneListValues[i-2];
		}

		return csvLines;
	}

	
	//Method to parse out a persons data
	private String parseIndividualValues(Person p)
	{
		String values = "";

		if(p instanceof Staff)
		{
			Staff s = (Staff)p;

			values += String.format("Staff,%s,%s,%s,%s,%.2f,%s", s.getFirstName(),
								s.getLastName(), s.getEmail(), s.getOfficeLocation(), 
								s.getSalary(), s.getJobTitle());
		}

		else if(p instanceof Faculty)
		{
			Faculty f = (Faculty)p;

			values += String.format("Faculty,%s,%s,%s,%s,%.2f,%s,%s", f.getFirstName(), 
								f.getLastName(), f.getEmail(), f.getOfficeLocation(), 
								f.getSalary(), f.getOfficeHours(), f.getRank());
		}
	
		else if(p instanceof Student)
		{
			Student s = (Student)p;

			values += String.format("Student,%s,%s,%s,%s,%.2f", 
								s.getFirstName(), s.getLastName(), s.getEmail(), 
								s.getClassStanding(), s.getGPA());
		}

		
		return values;
	}

	
	//Method to parse out phone data and return the values it as an array
	private String[] parsePhoneValues(ArrayList<PhoneNumber> phones)
	{
		String[] values = new String[phones.size()];

		for(int i = 0; i < values.length; i++)
		{
			PhoneNumber p = phones.get(i);

			values[i] = String.format("Phone,%s,%d-%d-%d", p.getType(), 
						p.getAreaCode(), p.getPrefix(), p.getSuffix());
		}

		return values;
	}


	//Method to parse out address data and return it as a string
	private String parseAddressValues(Address address)
	{
        String values;

        if(address.getAptNumber().length() > 0)
        {
		    values = String.format("Address,%s,%s,%s,%s,%s,%d", address.getStreetNumber(), 
						address.getAptNumber(), address.getStreetName(), address.getCity(), 
						address.getState(), address.getZipCode());
        }

        else 
        {
             
		    values = String.format("Address,%s,%s,%s,%s,%d", address.getStreetNumber(), 
						address.getStreetName(), address.getCity(), 
						address.getState(), address.getZipCode());
        }

		return values;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////end//
}
