/* Name:  Tejada, Jason
 * CIN:  306896517
 * Course:  CS2012 - 07/08
 * 
 * Description: This class is a custom exception to be thrown when the phone
 * number format is not correct. 
 *
 */
package tejada_jason;

public class PhoneNumberFormatException extends RuntimeException {
	//Constructor
	public PhoneNumberFormatException(String message) {
		super(message);
	}
}