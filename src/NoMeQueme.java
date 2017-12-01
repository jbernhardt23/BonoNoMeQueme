import java.io.DataInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Jose Bernhardt
 *
 */
public class NoMeQueme {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		System.out.println("Inserte bits de data para el usuario");
		char[] userData = s.nextLine().toCharArray();
		System.out.println("Inserte el Spreading Code de 4 Bits");
		int spreadCode = Integer.parseInt(s.nextLine(), 2);
		spreadData(userData, spreadCode);
	}

	/**
	 * Apply spreading code to the user Data
	 */
	public static List<Integer> spreadData(char[] userDataChars,
			int spreadingCode) {
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

		for (int i = 0; i < chips.size(); i++) {
			if(i == 0)
			System.out.print("Spread message para usuario: "
					+ String.format("%4s", Integer.toBinaryString(chips.get(i)).replace(' ', '0')));
			else
				System.out.println( String.format("%4s",Integer.toBinaryString(chips.get(i)).replace(' ', '0')));
		}
		return chips;
	}

}
