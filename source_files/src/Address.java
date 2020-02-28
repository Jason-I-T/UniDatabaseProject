/* Name:  Tejada, Jason
 * CIN:  306896517
 * Course:  CS2012 - 07/08
 * 
 * Description: This class hadles the major properties of an address, such as
 * the street number,  apartment number, street name, city, state, and zip code
 *
 */
package tejada_jason;

public class Address
{
	//Datafields
	private String streetNumber;
	private String aptNumber;
	private String streetName;
	private String city;
	private String state;
	private int zipCode;


	//Constructors
	public Address()
	{
		this.streetNumber = "1313";
		this.aptNumber = "Apt. 2";
		this.streetName = "California Dr.";
		this.city = "Los Angeles";
		this.state = "CA";
		this.zipCode = 90011;
	}

	public Address(String streetNumber, String streetName, 
				   String city, String state, int zipCode)
	{
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.aptNumber = "";
	}

	//With apartment number
	public Address(String streetNumber, String aptNumber, String streetName,
				   String city, String state, int zipCode)
	{
		this.streetNumber = streetNumber;
		this.aptNumber = aptNumber;
		this.streetName = streetName;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}

	
	//Getters
	public String getStreetNumber()
	{
		return this.streetNumber;
	}

	public String getAptNumber()
	{	
		return this.aptNumber;
	}

	public String getStreetName()
	{
		return this.streetName;
	}

	public String getCity()
	{
		return this.city;
	}

	public String getState()
	{
		return this.state;
	}

	public int getZipCode()
	{
		return this.zipCode;
	}


	//Other methods

	@Override
	public String toString()
	{
		String result = this.streetNumber + " " + this.streetName + " " + this.aptNumber 
				 + "\n" + this.city + ", " + this.state + ", " + String.format("%d",this.zipCode);
	

		return result;
	}

	@Override
	public boolean equals(Object o)
	{
		boolean check;

		if(!(o instanceof Address))
		{
			return false;
		}

		Address a = (Address)o;

		check = this.streetNumber.equals(a.getStreetNumber()) && 
				this.streetName.equals(a.getStreetName()) && 
				this.aptNumber.equals(a.getAptNumber()) && 
				this.city.equals(a.getCity()) && this.state.equals(a.getState()) &&
				this.zipCode == a.getZipCode();

		return check;
	}
}
