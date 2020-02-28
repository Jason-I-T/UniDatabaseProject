/* Name:  Tejada, Jason
 * CIN:  306896517
 * Course:  CS2012 - 07/08
 * 
 * Description: This is a subclass of Employee. Desribes the properies
 * of a university staff member.
 *
 */
package tejada_jason;

import java.util.ArrayList;

public class Staff extends Employee
{
	//Datafields
	String jobTitle;

	//Constructors
	public Staff()
	{
		super();
		this.jobTitle = "Head Librarian";
	}

	public Staff(String firstName, String lastName, String email, 
			        Address address, ArrayList<PhoneNumber> phoneNumbers,
			        String officeLocation, double salary, String jobTitle)
	{
		super(firstName, lastName, email, address, phoneNumbers, officeLocation, salary);

		this.jobTitle = jobTitle;
	}


	//Getters
	public String getJobTitle()
	{
		return this.jobTitle;
	}


	//Other Methods
	@Override
	public String toString()
	{
		String result = super.toString() + "\n";

		result += String.format("%-20s", "Job Title: ") + this.jobTitle + "\n";


		return result;
	}

	@Override
	public boolean equals(Object o)
	{
		boolean check;

		if(!(o instanceof Staff))
		{
			return false;
		}

		Staff s = (Staff)o;

		return check = super.equals(s) && this.jobTitle.equals(s.getJobTitle());
	}
}
