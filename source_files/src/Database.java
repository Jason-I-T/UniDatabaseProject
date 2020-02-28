/* Name:  Tejada, Jason
 * CIN:  306896517
 * Course:  CS2012 - 07/08
 * 
 * Description: Subclass of ArrayList<Person>. This class will act as a database
 * for the person objects.
 *
 */
package tejada_jason;

import java.util.Scanner;
import java.util.ArrayList;

public class Database extends ArrayList<Person>
{
	//Constructor
	public Database()
	{
		
	}


	//Getters
	public int getNumberOfPeople()
	{
		return this.size();
	}

	public int getNumberOfStudents()
	{
		int count = 0;

		for(int i = 0; i < this.size(); i++)
		{
			if(this.get(i) instanceof Student)
			{
				count++;
			}
		}

		return count;
	}

	public int getNumberOfEmployees()
	{
		int count = 0;

		for(int i = 0; i < this.size(); i++)
		{
			if(this.get(i) instanceof Employee)
			{
				count++;
			}
		}

		return count;
	}

	public int getNumberOfStaff()
	{
		int count = 0;

		for(int i = 0; i < this.size(); i++)
		{
			if(this.get(i) instanceof Staff)
			{
				count++;
			}
		}

		return count;
	}

	public int getNumberOfFaculty()
	{
		int count = 0;

		for(int i = 0; i < this.size(); i++)
		{
			if(this.get(i) instanceof Faculty)
			{
				count++;
			}
		}

		return count;
	}

	public int getNumberOfStudentsByClassStanding(String classStanding)
	{
		int count = 0;

		for(int i = 0; i < this.size(); i++)
		{
			if(this.get(i) instanceof Student)
			{
				Student stud = (Student)this.get(i);

				if(classStanding.equalsIgnoreCase(stud.getClassStanding()))
				{
					count++;
				}
			}
		}
		return count;
	}

	//Other methods
	public String toString()
	{
		String result = "";

		for(int i = 0; i < this.size(); i++)
		{
			result += this.get(i).toString() + "\n";
		}

		return result;
	}

	public String printEmployees()
	{
        String results = "";

		for(int i = 0; i < this.size(); i++)
		{
			if(this.get(i) instanceof Employee)
			{
				results += this.get(i).toString() + "\n";
			}
		}
        
        return results;
	}

	public String printStudents()
	{
        String results = "";

		for(int i = 0; i < this.size(); i++)
		{
			if(this.get(i) instanceof Student)
			{
				results += this.get(i).toString() + "\n";
			}
		}

        return results;
	}

	public String printFaculty()
	{
        String results = "";

		for(int i = 0; i < this.size(); i++)
		{
			if(this.get(i) instanceof Faculty)
			{
				results += this.get(i).toString() + "\n";
			}
		}

        return results;
	}

	public String printStaff()
	{
        String results = "";

		for(int i = 0; i < this.size(); i++)
		{
			if(this.get(i) instanceof Staff)
			{
				results += this.get(i).toString() + "\n";
			}
		}

        return results;
	}

	public String printStudentsGreaterThanOrEqualToGPA(double gpa)
	{
        String results = "";

		for(int i = 0; i < this.size(); i++)
		{
			if(this.get(i) instanceof Student)
			{
				Student s = (Student)this.get(i);

				if(s.getGPA() >= gpa)
				{
		            results += this.get(i).toString() + "\n";
				}
			}
		}

        return results;
	}

	public String printStudentsLessThanOrEqualToGPA(double gpa)
	{
        String results = "";

		for(int i = 0; i < this.size(); i++)
		{
			if(this.get(i) instanceof Student)
			{
				Student s = (Student)this.get(i);

				if(s.getGPA() <= gpa)
				{
					results += this.get(i).toString() + "\n";
				}
			}
		}

        return results;
	}
     
    /////////////////////////////////////////////////////////////////////////////////////

    //Remove
    public void removePerson(String[] conditions) {
         
        for(int i = 0; i < this.size(); i++) {
            String firstName = this.get(i).getFirstName();
            String lastName = this.get(i).getLastName();

            if(conditions[0].equals(firstName) && conditions[1].equals(lastName)) {
                this.remove(i);
                return;
            }
        }
    }

    //Search
    public String searchDatabase(String[] conditions) {
        ArrayList<Person> hits = new ArrayList<>();
        String result = "";

        if(conditions.length > 2 || conditions.length < 1) {
            return result;
        }

        for(int i = 0; i < this.size(); i++) {
            String firstName = this.get(i).getFirstName();
            String lastName = this.get(i).getLastName();

            //Half name search
            if(conditions.length == 1) {
                if(firstName.equals(conditions[0]) || lastName.equals(conditions[0])) {
                    hits.add(this.get(i));
                }
            }

            //Full name search
            else if(conditions.length == 2) {
                if(firstName.equals(conditions[0]) && lastName.equals(conditions[1])) {
                    hits.add(this.get(i));
                }
            }
        }  

        for(int i = 0; i < hits.size(); i++) {
            result += hits.get(i).toString() + "\n";    
        }
        
        return result;
    }
}
