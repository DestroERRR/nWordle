import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class WordleDriver{

    private static boolean hasWon = false;
    private static int attempts = 1;

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        Wordle wordle = new Wordle("SHANK");
        String guess;

        System.out.println("WORDLE!\n\nHow to play:");
        System.out.println("A random six letter word has been picked.");
        System.out.println("You have six attempts to guess it.\nI will give you hints about how the letters in your guess match\nTHE SECRET WORD\n");
        System.out.println("The Hints:");
        System.out.println("! means this letter is the right letter in the right place.\n? means this letter is the right letter in the wrong place.\n# means this letter does not appear in the secret word at all.\n");

        while(attempts < 7 && !hasWon){
            System.out.println();
            System.out.println("Enter guess: ");
            guess = sc.nextLine();
            if(guess.length()==5){
                System.out.println();
                System.out.print("Attempt " + attempts + ": ");
                wordle.userInput(guess);
                System.out.println();
                if(wordle.userGuess().equals(wordle.getWord()) || wordle.getWord().equals(guess.toUpperCase()))hasWon=true;
                System.out.print(wordle.userGuess());
                attempts++;
            }else{
                System.out.println("\n\nYour input must be 5 letter word.");
            }
            System.out.println(); //For UI
        }

        if(hasWon)System.out.println("Excellent Work!");
        else System.out.println("Oops! The SECRET WORD was: " + wordle.getWord());
    }
}