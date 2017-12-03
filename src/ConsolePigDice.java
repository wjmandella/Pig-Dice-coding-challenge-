import java.util.Scanner;

public class ConsolePigDice {
	 
	private static Scanner sc = new Scanner(System.in);
	
	public static void printToConsole(String str) {
		System.out.println(str);
	}
	
	public static String getString(String prompt) {
		System.out.print(prompt);
		String s = sc.nextLine();
//		sc.nextLine();
		return s;
	}
	
	public static String getString(String prompt, String a, String b) {
		String entry = "";
		boolean isValid = false;

		while (!isValid) {
			entry = "";
			System.out.print(prompt);
			entry = sc.nextLine();
			if (entry.isEmpty()) {
				System.out.println("Error! This entry is required. Try again.");
			} else if (!entry.equalsIgnoreCase(a) && !entry.equalsIgnoreCase(b)) {
				System.out.println("Error! Entry must be \"" + a + "\" or \"" + b + "\". Try again.");
			} else {
				isValid = true;
			}
		}
		return entry;
	}
	public static String getString(String prompt, String a, String b, String c, String d, String e, String f) {
		String entry = "";
		boolean isValid = false;

		while (!isValid) {
			entry = "";
			System.out.print(prompt);
			entry = sc.nextLine();
			if (entry.equals("")) {
				System.out.println("Error! This entry is required. Try again.");
			} else if (!entry.equalsIgnoreCase(a) && !entry.equalsIgnoreCase(b) && !entry.equalsIgnoreCase(c)
					&& !entry.equalsIgnoreCase(d)&& !entry.equalsIgnoreCase(e)&& !entry.equalsIgnoreCase(f)) {
				System.out.println("Error! Entry must be \"" + a + "\" or \"" + b + "\" or \""
									+ c + "\" or \"" + d + "\" or \"" + e + "\" or \"" + f + "\". Try again.");
			} else {
				isValid = true;
			}
		}
		return entry;
	}

    public static int getInt(String prompt) {
        int i = 0;
        boolean isValid = false;
        while (!isValid) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                i = sc.nextInt();
                isValid = true;
            } else {
                System.out.println("Error! Invalid integer. Try again.");
            }
            sc.nextLine();  // discard any other data entered on the line
        }
        return i;
    }

    public static int getInt(String prompt, int min, int max) {
        int i = 0;
        boolean isValid = false;
        while (!isValid) {
            i = getInt(prompt);
            if (i < min) {
                System.out.println(
                        "Error! Number must be greater than " + min + ".");
            } else if (i > max) {
                System.out.println(
                        "Error! Number must be less than " + max + ".");
            } else {
                isValid = true;
            }
        }
        return i;
    }

}	
	
