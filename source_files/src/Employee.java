/* Name:  Tejada, Jason
 * CIN:  306896517
 * Course:  CS2012 - 07/08
 * 
 * Description: This class is a subclass of Person. This class has the properties of
 * an employee.
 *
 */
package tejada_jason;

import java.util.ArrayList;

public class Employee extends Person
{
	//Datafields
	private String officeLocation;
	private double salary;

	//Constructor
	public Employee()
	{
		super();
		this.officeLocation = "E&T-A210";
		this.salary = 80000; //eighty thousand 
	}

	public Employee(String firstName, String lastName, String email, 
			        Address address, ArrayList<PhoneNumber> phoneNumbers,
			        String officeLocation, double salary)
	{
		super(firstName, lastName, email, address, phoneNumbers);
		this.officeLocation = officeLocation;
		this.salary = salary;
	}


	//Getters
	public String getOfficeLocation()
	{
		return this.officeLocation;
	}

	public double getSalary()
	{
		return this.salary;
	}


	//Other Methods
	@Override
	public String toString()
	{
		String result = super.toString() + String.format("%-20s", "Office Location: ");

		result += this.officeLocation;
		result += String.format("\n%-20s", "Salary: ") + "$" + String.format("%.2f", this.salary);

		return result; 
	}

	@Override
	public boolean equals(Object o)
	{
		boolean check;

		if(!(o instanceof Employee))
		{
			return false;
		}

		Employee e = (Employee)o;

		return check = super.equals(e) && this.salary == e.getSalary() && 
					   this.officeLocation.equals(e.getOfficeLocation()); 
	}
}