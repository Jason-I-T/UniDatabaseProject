/* Name:  Tejada, Jason
 * CIN:  306896517
 * Course:  CS2012 - 07/08
 * 
 * Description: This class is the superclass for classes associated with people
 *
 */
package tejada_jason;

import java.util.ArrayList;

public class Person
{
	//Datafields
	private String firstName;
	private String lastName;
	private String email;
	private Address address;
	private ArrayList<PhoneNumber> phoneNumbers;

	
	//Constructors
	public Person()
	{
		this.firstName = "John";
		this.lastName = "Doe";
		this.email = "jdoe@aol.com";
		this.address = new Address();
		this.phoneNumbers = new ArrayList<>();
			this.phoneNumbers.add(new PhoneNumber());
			this.phoneNumbers.add(new PhoneNumber("Home", 800, 321, 1234));
			this.phoneNumbers.add(new PhoneNumber("Cell", 800, 321, 1234));
	}


	public Person(String firstName, String lastName, String email, 
				  Address address, ArrayList<PhoneNumber> phoneNumbers)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.phoneNumbers = phoneNumbers;
	}

	
	//Getters
	public String getFirstName()
	{
		return this.firstName;
	}

	public String getLastName()
	{
		return this.lastName;
	}

	public String getEmail()
	{
		return this.email;
	}

	public Address getAddress()
	{
		return this.address;
	}

	public ArrayList<PhoneNumber> getPhoneNumbers()
	{
		return this.phoneNumbers;
	}
	
	
	//Other methods
	@Override
	public String toString()
	{
		//Formatting name and email output
		String result = String.format("%-20s", "Name: ") + this.firstName + " " + this.lastName;
		result += String.format("%n%-20s", "Email: ") + this.email;

		
		//Formatting the address class output
		result += String.format("%n%-20s", "Address: ") + this.address.getStreetNumber()
				  + " " + this.address.getStreetName() + " " + this.address.getAptNumber()
				  + "\n" + String.format("%-20s", "") + this.address.getCity() + ", "
				  + this.address.getState() + ", " + this.address.getZipCode() + "\n";


		//Formatting the phone number class output
		result += String.format("%-20s", "Phone Number(s): ") + phoneNumbers.get(0);
		
		for(int i = 1; i < phoneNumbers.size(); i++)
		{
			result += String.format("%-20s", "") + phoneNumbers.get(i);
		}

		return result;
	}

	
	@Override
	public boolean equals(Object o)
	{
		boolean check;

		if(!(o instanceof Person))
		{
			return false;
		}

		Person p = (Person)o;

		check = this.firstName.equals(p.getFirstName()) && this.lastName.equals(p.getLastName())
				 && this.email.equals(p.getEmail()) && this.address.equals(p.getAddress()) 
				&& this.phoneNumbers.equals(p.getPhoneNumbers());

		return check;
	}
}