import java.io.DataInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.*;
import java.util.Scanner;

/**
 * @author Jose Bernhardt
 *
 */
public class NoMeQueme {

	private static List<User> usersList = new ArrayList<>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		String done = "";

		do {
			User user = new User();

			System.out.println("Inserte bits de data para el usuario");
			user.setUserData(s.nextLine().toCharArray());

			System.out.println("Inserte el Spreading Code de 4 Bits");
			user.setSpreadingCode(Integer.parseInt(s.nextLine(), 2));

			usersList.add(user);

			System.out.println("Entre 0 para si termino o ENTER para continuar");
			done = s.nextLine();

		} while (!done.equals("0"));

		for (User user : usersList) {
			user.setSpreadingMessage(spreadData(user.getUserData(), user.getSpreadingCode()));
		}

	}

	/**
	 * Apply spreading code to the user Data. Spreading code must be 4 bits
	 * 
	 * @param userDataChars array of chars with bits entered for user data
	 * @param spreadingCode spreading code to be apply to the user
	 * @return String representation of spreadingMessage
	 */
	private static String spreadData(char[] userDataChars, int spreadingCode) {
		String userData = "";
		List<Integer> chips = new ArrayList<Integer>();

		for (int k = 0; k < userDataChars.length; k++) {
			for (int i = 0; i < 4; i++) {
				userData += userDataChars[k];
			}
			int newBit = Integer.parseInt(userData, 2);
			chips.add(newBit ^ spreadingCode);
			userData = "";

		}

		String finalBinaryString = "";

		for (int i = 0; i < chips.size(); i++) {

			String binaryString = Integer.toBinaryString(chips.get(i));
			int binaryBits = Integer.valueOf(binaryString);
			finalBinaryString  = finalBinaryString + String.format("%04d", binaryBits);

		}	
		System.out.println(finalBinaryString);
		return finalBinaryString;
	}



	private static void combineSignals(){

	}

}
