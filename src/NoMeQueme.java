import java.util.*;

/**
 * @author Jose Bernhardt
 *
 */
public class NoMeQueme {

	private static List<User> usersList = new ArrayList<>();
	private static String[] finalCombineSignal = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		String done = "";

		do {
			User user = new User();

			System.out.println("Inserte bits de data para el usuario");
			user.setUserData(s.nextLine().split(""));
			

			System.out.println("Inserte el Spreading Code de 4 Bits");
			int pepe = Integer.parseInt(s.nextLine(), 2);
			user.setSpreadingCode(pepe);
			
			String binaryString = Integer.toBinaryString(pepe);
			int binaryBits = Integer.valueOf(binaryString);
			String pepeFinal =  String.format("%04d", binaryBits);

			if(pepeFinal.length()> 4){
				System.out.println("ERROR:  Spreading debe ser 4 Bits, terminando programa");
				System.exit(0);
			}
			
			usersList.add(user);

			System.out
					.println("Entre 0 para si termino o ENTER para continuar");
			done = s.nextLine();
		

		} while (!done.equals("0"));

		for (int i = 0; i < usersList.size(); i++) {
			usersList.get(i).setSpreadingMessage(spreadData(usersList.get(i).getUserData(),
					usersList.get(i).getSpreadingCode(), i));
		}

		combineSignals(usersList);
		System.out.println("Combine Signal de todos los usuarios: " + Arrays.toString(finalCombineSignal));
		
		for(User user: usersList){
			String[] getOriginDaltaArray = getOriginalData(user);
			if(Arrays.equals(user.getUserData(), getOriginDaltaArray))
				System.out.println("Usuario con Data " + Arrays.toString(user.getUserData()) +  ", recuperado");
			else
				System.out.println("Usuario con Data " + Arrays.toString(user.getUserData()) +  ", NO recuperado");
				
		}
	
		
		
		
	

	}

	/**
	 * Apply spreading code to the user Data. Spreading code must be 4 bits
	 * 
	 * @param userDataString
	 *            array of String with bits entered for user data
	 * @param spreadingCode
	 *            spreading code to be apply to the user
	 * @return String[] representation of spreadingMessage
	 */
	private static String[] spreadData(String[] userDataString,
			int spreadingCode, int a) {
		String userData = "";
		List<Integer> chips = new ArrayList<Integer>();

		for (int k = 0; k < userDataString.length; k++) {
			for (int i = 0; i < 4; i++) {
				userData += userDataString[k];
			}
			int newBit = Integer.parseInt(userData, 2);
			chips.add(newBit ^ spreadingCode);
			userData = "";

		}

		String finalBinaryString = "";

		for (int i = 0; i < chips.size(); i++) {

			String binaryString = Integer.toBinaryString(chips.get(i));
			int binaryBits = Integer.valueOf(binaryString);
			finalBinaryString = finalBinaryString
					+ String.format("%04d", binaryBits);

		}
		System.out.println("Spreading message para usuario" + a + ": " +finalBinaryString);
		return finalBinaryString.split("");
	}

	/**
	 * Sums all spreading messages for each user to get the composite signal
	 * 
	 * @param userList
	 * 
	 */
	private static void combineSignals(List<User> userList) {
		finalCombineSignal = new String[userList.get(0).getSpreadingMessage().length];

		for (int i = 0; i < userList.size(); i++) {
			if (i + 1 == userList.size())
				break;

			String[] spreadArray1 = null;
			String[] spreadArray2 = null;

			if (i == 0) {
				spreadArray1 = Arrays.copyOf(userList.get(i)
						.getSpreadingMessage(), userList.get(i)
						.getSpreadingMessage().length);

				spreadArray2 = Arrays.copyOf(userList.get(i + 1)
						.getSpreadingMessage(), userList.get(i + 1)
						.getSpreadingMessage().length);
			} else {
				spreadArray1 = Arrays.copyOf(userList.get(i + 1)
						.getSpreadingMessage(), userList.get(i + 1)
						.getSpreadingMessage().length);

				spreadArray2 = finalCombineSignal;
			}

			for (int k = 0; k < spreadArray1.length; k++) {
				int currentBit1 = Integer.parseInt(String
						.valueOf(spreadArray1[k]));
				int currentBit2 = Integer.parseInt(String
						.valueOf(spreadArray2[k]));

				if (currentBit1 == 0)
					currentBit1 = 1;
				else
					currentBit1 = -1;

				if (i == 0) {
					if (currentBit2 == 0)
						currentBit2 = 1;
					else
						currentBit2 = -1;
				}

				int sumBits = 0;

				if (i == 0)
					sumBits = currentBit1 + currentBit2;
				else
					sumBits = currentBit2 + currentBit1;

				finalCombineSignal[k] = String.valueOf(sumBits);

			}

		}

	}

	/***
	 * Gets the original data from the composite signal, if possible, base on
	 * the spreading code of the user.
	 * 
	 * @param userCode
	 */
	private static String[] getOriginalData(User user) {

		String binaryString = Integer.toBinaryString(user.getSpreadingCode());
		int binaryBits = Integer.valueOf(binaryString);
		String[] userCodeArray = String.format("%04d", binaryBits).split("");

		String[] deSpread1 = new String[finalCombineSignal.length];
		int count = 0;

		for (int i = 0; i < userCodeArray.length; i++) {

			if (count == 4)
				i = 0;

			int currentBit1 = Integer
					.parseInt(String.valueOf(userCodeArray[i]));

			if (currentBit1 == 0)
				currentBit1 = 1;
			else
				currentBit1 = -1;

			deSpread1[count] = String.valueOf(currentBit1
					* Integer.valueOf(finalCombineSignal[count]));

			if (count == 3)
				i = 0;

			count++;

		}

		int sumBits1 = 0;
		int sumBits2 = 0;

		for (int i = 0; i < deSpread1.length; i++) {

			if (i < 4)
				sumBits1 += Integer.valueOf(deSpread1[i]);
			else
				sumBits2 += Integer.valueOf(deSpread1[i]);

		}

		int userCode1 = sumBits1 / 4;
		int userCode2 = sumBits2 / 4;

		if (userCode1 == 1)
			userCode1 = 0;
		else
			userCode1 = 1;

		if (userCode2 == 1)
			userCode2 = 0;
		else
			userCode2 = 1;
		
		return new String[] {String.valueOf(userCode1), String.valueOf(userCode2)};

	}
}
