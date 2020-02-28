/* Name:  Tejada, Jason
 * CIN:  306896517
 * Course:  CS2012 - 07/08
 * 
 * Description: Subclass of the Person class. The student class will describe
 * properties of a student.
 *
 */
package tejada_jason;

import java.util.ArrayList;

public class Student extends Person
{
	//Datafields
	private String classStanding;
	private double gpa;


	//Constructors
	public Student()
	{
		super();
		this.classStanding = "graduate";
		this.gpa = 4.01;
	}

	public Student(String firstName, String lastName, String email, 
		           Address address, ArrayList<PhoneNumber> phoneNumbers, 
		           String classStanding, double gpa)
	{
		super(firstName, lastName, email, address, phoneNumbers);
		checkStanding(classStanding);
		this.gpa = gpa;
	}


	//Getters
	public String getClassStanding()
	{
		return this.classStanding;
	}

	public double getGPA()
	{
		return this.gpa;
	}


	//Other Methods
	@Override
	public String toString()
	{
		String result = super.toString();

		result += String.format("%-20s", "Class Standing: ") + this.classStanding + "\n";
		result += String.format("%-20s", "GPA: ") + this.gpa + "\n";

		return result;
	}

	@Override
	public boolean equals(Object o)
	{
		boolean check;

		if(!(o instanceof Student))
		{
			return false;
		}

		Student s = (Student)o;

		return check = super.equals(s) && 
					   this.classStanding.equals(s.getClassStanding()) && 
					   this.gpa == s.getGPA();
	}

	//Method to see if the class standing was entered correctly.
	private void checkStanding(String classStanding) 
	{
		boolean check = classStanding.equalsIgnoreCase("freshman") 
						|| classStanding.equalsIgnoreCase("sophomore") 
						|| classStanding.equalsIgnoreCase("junior") 
						|| classStanding.equalsIgnoreCase("senior") 
						|| classStanding.equalsIgnoreCase("graduate");

		if(!(check))
		{
			this.classStanding = "ERROR Incorrect Class Standing";
		}

		else
		{
			this.classStanding = classStanding.toLowerCase();
		}
	}
}
