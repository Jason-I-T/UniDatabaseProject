/* Name:  Tejada, Jason
 * CIN:  306896517
 * Course:  CS2012 - 07/08
 * 
 * Description: This is a subclass of Employee. Describes the properties
 * of a university faculty member.
 *
 */
package tejada_jason;

import java.util.ArrayList;

public class Faculty extends Employee
{
	//Datafields
	String officeHours;
	String rank;


	//Constructors
	public Faculty()
	{
		super();
		this.officeHours = "12:00 - 14:00";
		this.rank = "full time";
	}

	public Faculty(String firstName, String lastName, String email, 
			        Address address, ArrayList<PhoneNumber> phoneNumbers,
			        String officeLocation, double salary, String officeHours,
			        String rank)
	{
		super(firstName, lastName, email, address, phoneNumbers, officeLocation, salary);
		
		this.officeHours = officeHours;
		this.checkRank(rank);

	}


	//Getters
	public String getOfficeHours()
	{
		return this.officeHours;
	}

	public String getRank()
	{
		return this.rank;
	}


	//Other Methods
	@Override
	public String toString()
	{
		String result = super.toString();

		result += String.format("\n%-20s", "Office Hours: ") + this.officeHours;
		result += String.format("\n%-20s", "Rank: ") + this.rank + "\n";
		
		return result;
	}	

	@Override
	public boolean equals(Object o)
	{
		boolean check;

		if(!(o instanceof Faculty))
		{
			return false;
		}

		Faculty f = (Faculty)o;

		return check = super.equals(f) && this.officeHours.equals(f.getOfficeHours())
					   && this.rank.equals(f.getRank());
	}

	//Method that checks to see if the rank was entered correctly.
	private void checkRank(String rank) 
	{
		boolean check = rank.equalsIgnoreCase("full time") 
						|| rank.equalsIgnoreCase("part time"); 

		if(!(check))
		{
			this.rank = "ERROR Incorrect Rank";
		}

		else
		{
			this.rank = rank.toLowerCase();
		}
	}
}
