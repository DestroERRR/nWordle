import java.util.Arrays;
import java.util.ArrayList; 
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
public class Wordle{
    private String theWord;
    ArrayList<String> input = new ArrayList<String>();
    ArrayList<String> moreWords = new ArrayList<String>();
    boolean chooseAgain = true; 

    public Wordle(){
        moreWords = new ArrayList<String>(3300);
        loadWords("words.txt");
        while(chooseAgain) theWord = randomWord(); //rechooses word if duplicate letter
        theWord = theWord.toUpperCase();
    }

    public String getWord(){
        return theWord;
    }

    public void userInput(String guessedWord){
        input.clear();
        guessedWord = guessedWord.toUpperCase();
        for(int i = 0; i < guessedWord.length(); i++){
            boolean rightLetter = false;
            boolean wrongLetter = true;
            String charOfGuess = guessedWord.substring(i,i+1);
            String charOfWord = theWord.substring(i,i+1);

            if(charOfGuess.equals(charOfWord) ){
                input.add(charOfGuess);    
                input.add("!");
                input.add(" ");
                rightLetter = true;
                wrongLetter = false;
            } 

            if(wrongLetter == true){
                for(int j = 0; j < guessedWord.length(); j++){
                    if(charOfGuess.equals(theWord.substring(j,j+1))){
                        input.add(charOfGuess);
                        input.add("?");
                        input.add(" ");
                        rightLetter = true;
                        break;
                    }
                }
            }

            if(rightLetter == false){
                input.add(charOfGuess);
                input.add("#");
                input.add(" ");
            }

        }

    }

    public String userGuess(){
        String guessedWord = " ";
        for(int i = 0; i < input.size(); i++){
            guessedWord += input.get(i);
        }
        return guessedWord;
    }

    public void loadWords(String filename){
        File wordfile = new File(filename);
        try {
            Scanner fileScanner = new Scanner(wordfile);
            while(fileScanner.hasNext()){
                String w = fileScanner.nextLine();
                if(w.length() == 5 && !Character.isUpperCase(w.charAt(0))){
                    moreWords.add(w);
                }
            }

        } catch(FileNotFoundException e){
            System.out.println(e);   
        }
        //need to write code so the word cannot be repeated letter word 
    }

    public String randomWord(){ //picks random word from words.txt file
        String ra = "";
        int r = (int) (Math.random()*moreWords.size());
        ra = moreWords.get(r);
        int threshold = 0; //if threshold reaches above 5 then there is a dupe
        chooseAgain = false; //so this doesn't become a stackoverflow error

        for(int i = 0; i < 5; i++){
            String toCheck = ra.substring(i,i+1);
            for(int j = 0; j < 5; j++){
                if(toCheck.equals(ra.substring(j,j+1))){
                    threshold++; 
                    //threshold will atleast be 5 
                }
            }

        }

        if(threshold > 5) {
            chooseAgain = true;
        }

        return ra;
    }

    public boolean trueWord(String guess){
        if(guess.length() != 5) return false; 
        for(int i = 0; i < moreWords.size(); i++){
            if(guess.equals(moreWords.get(i))){
                return true;
            }
        }
        return false;
    }

}