//Import Statments//
import java.io.*;
import java.util.*;

public class WordGuessing
{
  //Main method//
  static public void main (String[] args) throws java.io.IOException
  {
    //File Reader and Array//
    String[] dictionary = new String[127142];
    try {
      BufferedReader in = new BufferedReader(new FileReader ("dictionary.txt")); 
      String x = "";
      int i = 0;
      while ((x = in.readLine()) != null)
      {
        dictionary[i] = x.toLowerCase();
        i++;
      }
      in.close();
    }
    catch (IOException iox)
    {
      
      System.out.print("File I/O Error " + iox.getMessage());
    }  
    
    //User Variables// 
    int wordLength = 0;
    int guesses = 0;
    int noLetterCount = 0;
    int comparison = 0;
    int guessCount = 1;
    String display = "";
    String word = "";
    String usedChars = " ";
    char letterInput;
    boolean wordCount = false;
    boolean playAgain = false;
    
    //Array Variables//
    String[] dictionaryShortened;
    String[] arrayShortened;
    String[] arrayGame;
    int [] yesLetterCount;
    
    //Brings in Game.java file//
    Game game = new Game();
    
    //Calling Methods//
    do
    {
      wordCount = Game.remainingWords(dictionary, wordCount);
      wordLength = Game.userInput(wordLength);
      Game.selectWordLength(dictionary, wordLength);
      guesses = Game.selectGuess(guesses);
      
      Arrays.sort(dictionary);
      arrayShortened = Game.doubles(dictionary);
      Arrays.sort(arrayShortened);
      arrayGame = Game.zzzzInstances(arrayShortened);
      Arrays.sort(arrayGame);
      display = Game.display(wordLength, display);
      
      for(int i =0; i < guesses; i++)
      {
        letterInput = Game.selectLetter(usedChars);
        usedChars = Game.chars(letterInput, usedChars);
        noLetterCount = Game.noLetter(arrayGame, letterInput);
        yesLetterCount = Game.yesLetter(arrayGame, letterInput, wordLength);
        comparison = Game.compareLetter(yesLetterCount, noLetterCount);
        arrayGame = Game.selectCat(comparison, arrayGame, yesLetterCount, letterInput, wordCount);
        display = Game.modDisplay(comparison, letterInput, display);
        guesses = Game.checkWin(guesses, display);
        System.out.println("Amount of Guesses: " + guesses + "   " + "Guesses used: " + guessCount++);
      }
      Game.endGame(display, arrayGame, word);
    }
    while(Game.replay(playAgain, wordLength, guesses, noLetterCount,
                      comparison, guessCount, display, word, usedChars, wordCount));
  }}

