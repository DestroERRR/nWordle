import java.util.Arrays;
import java.util.ArrayList; 
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
public class Wordle{
    private String theWord;
    ArrayList<String> input = new ArrayList<String>();
    ArrayList<String> theWords = new ArrayList<String>();

    public Wordle(String myWord){
        theWords = new ArrayList<String>(3300);
        loadWords("words.txt");
        int r = (int) (Math.random()*theWords.size());
        theWord = theWords.get(r);
        theWord = myWord;
        theWord.toUpperCase();
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
                    theWords.add(w);
                }
            }

        } catch(FileNotFoundException e){
            System.out.println(e);   
        }
    }

    public boolean specialChar(String special){
        for (int i = 0; i < special.length(); i++) {
            if (special.charAt(i) < 'a' || special.charAt(i) > 'z') {
                return true;
            }
        }
        return false;
    }

}