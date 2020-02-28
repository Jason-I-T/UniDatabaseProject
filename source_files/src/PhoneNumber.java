/* Name:  Tejada, Jason
 * CIN:  306896517
 * Course:  CS2012 - 07/08
 * 
 * Description: This class handles the phonenumbers for each person in the 
 * tejada_jason package. It stores 3 diferent numbers in an arraylist. 
 *
 */
package tejada_jason;

public class PhoneNumber
{
	//Datafields
	private String type;
	private int areaCode;
	private int prefix;
	private int suffix;


	//Constructors
	public PhoneNumber()
	{
		this.type = "Work";
		this.areaCode = 800;
		this.prefix = 321;
		this.suffix = 1234;
	}

	public PhoneNumber(String type, int areaCode, int prefix, int suffix)
	{
		this.type = type;
		this.areaCode = areaCode;
		this.prefix = prefix;
		this.suffix = suffix;
	}


	//Getters
	public String getType()
	{
		return this.type;
	}

	public int getAreaCode()
	{
		return this.areaCode;
	}

	public int getPrefix()
	{
		return this.prefix;
	}

	public int getSuffix()
	{
		return this.suffix;
	}


	//Other Methods
	@Override
	public String toString()
	{
		String result = String.format("%s: (%d) %d-%d\n", this.type, this.areaCode, 
									 this.prefix, this.suffix);
		return result; 
	}

	
	@Override
	public boolean equals(Object o)
	{
		boolean check;

		if(!(o instanceof PhoneNumber))
		{
			return false;
		}

		PhoneNumber p = (PhoneNumber)o;

		check = this.type.equals(p.getType()) && 
		        this.areaCode == p.getAreaCode() && 
		        this.prefix == p.getPrefix() &&
		        this.suffix == p.getSuffix();

		return check;
	}

}