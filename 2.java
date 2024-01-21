import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int attemptsLimit = 10;
        int score = 0;

        System.out.println("===== Number Guessing Game =====");
        System.out.println("Guess the number between " + lowerBound + " and " + upperBound);

        int numberToGuess = random.nextInt(upperBound - lowerBound + 1) + lowerBound;

        for (int attempts = 1; attempts <= attemptsLimit; attempts++) {
            System.out.print("Attempt " + attempts + ": Enter your guess: ");
            int userGuess = scanner.nextInt();

            if (userGuess == numberToGuess) {
                System.out.println("Congratulations! You guessed the correct number.");
                score = calculateScore(attempts, attemptsLimit);
                break;
            } else if (userGuess < numberToGuess) {
                System.out.println("Try a higher number.");
            } else {
                System.out.println("Try a lower number.");
            }
        }

        System.out.println("Game over! The number was: " + numberToGuess);
        System.out.println("Your score is: " + score);
    }

    private static int calculateScore(int attempts, int attemptsLimit) {
        // Calculate score based on the number of attempts
        double percentage = ((double) attemptsLimit - attempts + 1) / attemptsLimit * 100;
        return (int) percentage;
    }
}