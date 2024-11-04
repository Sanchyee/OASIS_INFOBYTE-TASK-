import javax.swing.*;
import java.util.Random;

public class GuessTheNumberGame {
    private static final int MAX_ATTEMPTS = 10; 
    private static final int TOTAL_ROUNDS = 3; 

    public static void main(String[] args) {
        Random random = new Random();
        int totalScore = 0;

        JOptionPane.showMessageDialog(null, "Welcome to the Guess the Number Game!");

        
        for (int round = 1; round <= TOTAL_ROUNDS; round++) {
            int numberToGuess = random.nextInt(100) + 1; 
            int attemptsLeft = MAX_ATTEMPTS;
            int roundScore = 0;
            boolean guessedCorrectly = false;

            while (attemptsLeft > 0 && !guessedCorrectly) {
                
                String userInput = JOptionPane.showInputDialog("Round " + round + " - Enter your guess (1-100):");
                int userGuess;

                
                try {
                    userGuess = Integer.parseInt(userInput);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                    continue;
                }

                
                if (userGuess < 1 || userGuess > 100) {
                    JOptionPane.showMessageDialog(null, "Please enter a number between 1 and 100.");
                } else if (userGuess == numberToGuess) {
                    guessedCorrectly = true;
                    roundScore = attemptsLeft * 10; 
                    JOptionPane.showMessageDialog(null, "Congratulations! You've guessed the number!");
                    JOptionPane.showMessageDialog(null, "Score for this round: " + roundScore);
                } else if (userGuess < numberToGuess) {
                    JOptionPane.showMessageDialog(null, "The number is higher than " + userGuess);
                } else {
                    JOptionPane.showMessageDialog(null, "The number is lower than " + userGuess);
                }

                attemptsLeft--;

                if (attemptsLeft > 0 && !guessedCorrectly) {
                    JOptionPane.showMessageDialog(null, "Attempts left: " + attemptsLeft);
                }
            }

            if (!guessedCorrectly) {
                JOptionPane.showMessageDialog(null, "Out of attempts! The correct number was: " + numberToGuess);
            }

            totalScore += roundScore; // Update total score
            JOptionPane.showMessageDialog(null, "Total score so far: " + totalScore);
        }


        JOptionPane.showMessageDialog(null, "Game over! Your final score is: " + totalScore);
    }
}
