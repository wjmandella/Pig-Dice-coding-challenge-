import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;

public class PigDiceApp {

	private static long startTime;
	private static NumberFormat number = NumberFormat.getNumberInstance();
	private static final int NUMBER_OF_SIDES=6;

	public static void main(String[] args) {

		ArrayList<Integer> scores = new ArrayList<>();
		ArrayList<Integer> rolls = new ArrayList<>();
		int[] die = new int[NUMBER_OF_SIDES];
		int gameNbr = 0;
		int score = 0;
		int nbrOfRolls = 0;
		int maxScore = 0;
		int maxRoll = 0;
		int totalNbrOfRolls = 0;

		System.out.println("Welcome to Pig Dice!!!!!!!");
		System.out.println("\nGAME RULES:");
		String rules = "A player rolls a " +NUMBER_OF_SIDES+ "-sided die over and over again, until a 1 is rolled.\n"
				+ "The player's score is the accumulation of all of the die rolls\n"
				+ "up until (but not including!) the roll that results in a \"1\".\n";
		System.out.println(rules);
		String choice = ConsolePigDice.getString("Would you like to play a game of \"Pig Dice\"? (y/n): ", "y", "n");
		startTime = System.nanoTime();
		
		while (choice.equalsIgnoreCase("y")) {
			gameNbr++;
			score = 0;

			System.out.println("\nHere we go with game #" + gameNbr + "....");
			int dieRoll = 0;
			nbrOfRolls = 0;
			maxRoll = 0;
			while (dieRoll != 1) {
				nbrOfRolls++;
				totalNbrOfRolls++;
				dieRoll = rollDie();				
				if (dieRoll > 1) {
					score += dieRoll;
				}
				die[dieRoll - 1]++;
				System.out.println("Die Roll: " + dieRoll);
				maxRoll = Math.max(maxRoll, dieRoll);
			}
			System.out.println("Your score for game #" + gameNbr + " was " + score + ".");
			maxScore = Math.max(maxScore, score);
			System.out.println("Your highest roll of game #" + gameNbr + " was a \"" + maxRoll + "\".");
			scores.add(score);
			rolls.add(nbrOfRolls);
			choice = ConsolePigDice.getString("\nDo you want to play another game? (y/n): ", "y", "n");
		}
		getMostCommonRoll(die, totalNbrOfRolls);
			if (gameNbr > 1) {
				getHighestScore(scores);
				getAverageScorePerGame(scores, gameNbr);
				getAveragePerRoll(scores, totalNbrOfRolls, gameNbr);
			}
		double elapsedTime = (double) (System.nanoTime() - startTime) / 1000000000;
		number.setMaximumFractionDigits(2);
		System.out.println(
				"\nEstimated elapsed time to play all " + gameNbr + " games: " + number.format(elapsedTime) + " seconds");
		System.out.println("\nOink!Oink!...or, rather,..Bye-bye!");
	}

	public static int rollDie() {
		Random rand = new Random();
		int dieRoll = rand.nextInt(NUMBER_OF_SIDES) + 1;
		return dieRoll;
	}
	
	public static void getMostCommonRoll(int[] die, int totNbrRolls) {
		int maxRoll = 0;
		int maxRollDieNbr = 0;
		for (int i = 0; i < die.length; i++) {
			if (die[i] >= maxRoll) {
				maxRoll = die[i];
				maxRollDieNbr = i + 1;
			}
		}
		System.out.println("\nDIE NUMBER MOST FREQUENTLY ROLLED:");
		System.out.println("The die roll that most frequently occurred was a \"" + maxRollDieNbr + "\" .");
		System.out.println("(A roll of \"" + maxRollDieNbr + "\" occurred " + maxRoll + " times out of " 
							+totNbrRolls+ " total rolls.)");
	}

	public static void getHighestScore(ArrayList<Integer> scores) {
		int maxScore = 0;
		int indexOfMaxScore = 0;
		for (int score : scores) {
			if (score >= maxScore) {
				maxScore = Math.max(maxScore, score);
				indexOfMaxScore = scores.indexOf(score) + 1;
			}
		}
		System.out.println("MAXIMUM SCORE:");
		System.out.println("Your maximum score was " + maxScore + ", in game #" + indexOfMaxScore + ".");
	}
	
	public static int getTotalOfAllScores (ArrayList<Integer> scores) {
		int totalOfAllScores = 0;
		for (int score : scores) {
			totalOfAllScores += score;
		}
		return totalOfAllScores;
	}

	public static void getAverageScorePerGame(ArrayList<Integer> scores, int nbrOfGames) {
		int totalOfAllScores = getTotalOfAllScores(scores);
		
		double avgOfAllScores = (double) totalOfAllScores / nbrOfGames;
		number.setMaximumFractionDigits(2);
		number.setMinimumFractionDigits(1);
		String avgScoreString = number.format(avgOfAllScores);
		System.out.println("AVERAGE SCORE:");
		System.out.println("Your average score for all " + nbrOfGames + " games was " + avgScoreString + " per game.");

	}
	
	public static void getAveragePerRoll(ArrayList<Integer> scores, int totalNbrOfRolls, int nbrOfGames) {
		int totalOfAllScores = getTotalOfAllScores(scores);

		double avgOfAllRolls = (double) totalOfAllScores / totalNbrOfRolls;
		number.setMaximumFractionDigits(2);
		number.setMinimumFractionDigits(1);
		String avgRollString = number.format(avgOfAllRolls);
		System.out.println("AVERAGE DIE VALUE PER ROLL:");
		System.out.println("Your average die roll for all " + nbrOfGames + " games (" + totalNbrOfRolls
				+ " rolls in all) was " + avgRollString + " per roll.");
	}
}
