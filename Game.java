import java.io.*;
import java.util.*;
//Class for the game//
public class Game
{
  
  //Code for user input//
  public static int userInput(int wordLength)
  {
    System.out.println("Please input the word length you would like");
    Scanner keyboard = new Scanner (System.in);
    boolean x = false;
    while (x == false)
    {
      try
      {   
        wordLength = keyboard.nextInt();
        if (wordLength > 1 && wordLength < 15)
        {
          x = true;
          break;
        }
        System.out.println("Invalid input, please input valid number");
      }
      catch(InputMismatchException e)
      {
        System.out.println("Invalid input, please input valid number");
        keyboard.next();
        continue;
      }
    }
    return wordLength;
  }
  
  //Code to shorten array depending on user input//
  public static String[] selectWordLength(String[] dictionary, int wordLength)
  {        
    for(int i =0; i < dictionary.length; i++)
    {
      if (dictionary[i].length() != wordLength)
      {
        dictionary[i] = "zzzz";
      }
    }
    return dictionary; 
  }
  
  //Code to add number of guesses//
  public static int selectGuess(int guesses)
  {
    System.out.println("Please enter the number of guesses you would like to have");
    Scanner keyboard = new Scanner(System.in);
    boolean x = false;
    while (x == false)
    {
      try
      {
        guesses = keyboard.nextInt();
        if (guesses > 0)
        {
          x = true;
          break;
        }
        System.out.println("Invalid input, please input valid number");
      }
      catch(InputMismatchException e)
      {
        System.out.println("Invalid input, please input valid number");
        keyboard.next();
        continue;
      }
    }
    return guesses;
  }
  
  //Code to input remaining word count (promts user for wordCount)//
  public static boolean remainingWords(String [] array, boolean wordCount)
  {
    System.out.println("Would you like to see the remaining words in the dictionary, please write yes or no");
    Scanner keyboard = new Scanner(System.in);
    for (int i = 0; i < array.length; i++)
    {
      String remWords = keyboard.next();
      if (remWords.equals("yes"))
      {
        wordCount = true;
        
        break;
      }
      else if (remWords.equals("no"))
      {
        break;
      }
      else 
      {
        System.out.println("Invalid input, please input yes or no");
        continue;
      }
    }
    return wordCount;
  }
  
  //Code to allow user input of letter guess//
  public static char selectLetter(String usedChar)
  {
    System.out.println("Please enter a letter");
    Scanner keyboard = new Scanner(System.in);
    String letterInput = "";
    char ltrInput =  '\0';
    boolean check = false;
    boolean isCharUsed = false;
   
    while (check == false)
    {
      try
      {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        letterInput = keyboard.next();
        ltrInput = letterInput.charAt(0);
        for(int i = 0; i < usedChar.length(); i++)
        {
          if(usedChar.charAt(i) == ltrInput)
            isCharUsed = true;
        }
        for (int i = 0; i < alphabet.length(); i++)
        {
          if (ltrInput == alphabet.charAt(i))
          {
            if(letterInput.length() == 1)
            {
              if(isCharUsed == false)
              {
                check = true; 
                break;
              }
            }
          } 
        }
        isCharUsed = false;
        if (check == false)
          System.out.println("Invalid input, please input letter");
      }
      catch (InputMismatchException e)
      {
        System.out.println("Invalid input, please input letter");
        keyboard.next();
        continue;
      }
    }
    return ltrInput;
  }
  
  //Not finding instance of letter after being inputted by user//
  public static int noLetter(String[] array, char letterInput)
  {
    int count = 0;
    count = array.length;
    for (int i = 0; i < array.length; i++)
    {
      for (int j = 0; j < array[i].length(); j++)
      {
        if (array[i].charAt(j) == letterInput)
        {
          count--;
          break;
        }
      }
    }
    return count;
  }
  
  //Method for finding amount of instances of chosen letter//
  public static int[] yesLetter(String[] array, char chosenChar, int wordLength)
  {
    int count = 0;
    int [] numArray = new int [wordLength];
    for (int i = 0; i < wordLength; i++)
    {
      for (int j = 0; j < array.length; j++)
      {
        if (array[j].charAt(i) == chosenChar)
        {
          count++; 
        }
      }
      numArray[i] = count;
      count = 0;
    } 
    return numArray;
  }
  
  //Method to compare if yesLetter or noLetter is bigger//
  public static int compareLetter (int [] array, int count)
  {
    int c = 0;
    int largest = array[0];
    for(int i = 1; i < array.length; i++)
    {
      if(array[i] > largest)
      {
        largest = array[i];
        c = i;
      }
      if(count > largest)
      {
        c = 20;
      }
    }
    return c;
  }
  
  //code to pick catagory//
  public static String [] selectCat(int comparison, String [] array, int [] intArray, char chosenLetter, boolean wordCount)
  {
    boolean t = false;
    int count = 0;
    if (comparison == 20)
    {
      for(int i = 0; i < array.length; i++)
      {
        for(int j = 0; j < array[i].length(); j++)
        {
          if(array[i].charAt(j) == chosenLetter)
          {
            count++; 
          }
        }
      }
      String [] temp = new String[array.length - count]; 
      int x = 0;
      for(int j = 0; j < array.length; j++)
      {
        for(int y = 0; y < array[j].length(); y++)
        {
          if(array[j].charAt(y) == chosenLetter)
          {
            t = true; 
          }
        }
        if(t == false)
        {
          temp[x] = array[j];
          x++;
        }
        t = false;
      }
      array = temp;
    }
    else if (comparison != 20)
    {
      String [] temp = new String[intArray[comparison]];
      for(int i = 0; i < temp.length; i++)
      {
        for(int j = 0; j < array.length; j++)
        {
          if(array[j].charAt(comparison) == chosenLetter)
          {
            temp[i] = array[j];
            i++;
          }
        }        
      }
      array = temp;
    }
    if (wordCount == true)
      System.out.println("Remaining words: " + array.length + " ");
    return array;
  }
  
  //code to display dashes//
  public static String display(int wordLength, String display)
  {
    
    for(int i = 0; i < wordLength; i++)
    {
      display += "-";
    }
    
    return display;
  }
  
  //code to change dashes to letters//
  public static String modDisplay(int comparison, char chosenLetter, String display)
  {
    if(comparison != 20)
    {
      String temp = "";
      temp = display.substring(0, comparison);
      temp += chosenLetter;
      temp += display.substring((comparison + 1), display.length());
      display = temp;
    }
    System.out.println(display);
    return display;
  }
  
  //Searches array for double characters//
  public static String[] doubles(String[] dictionary)
  {
    int c = 0;
    String abc = "abcdefghijklmnopqrstuvwxyz";
    for (int i = 0; i < abc.length(); i++)
    {
      for (int j = 0; j < dictionary.length; j++)
      {
        int count = 0;
        for (int x = 0; x < dictionary[j].length(); x++)
        {
          if (abc.charAt(i) == dictionary[j].charAt(x))
          {
            count++;
          }
          if (count == 2)
          {
            c++;
          }
        }
      }
    }
    if(c > 0){
      for (int i = 0; i < abc.length(); i++)
      {
        for (int j = 0; j < dictionary.length; j++)
        {
          int count = 0;
          for (int x = 0; x < dictionary[j].length(); x++)
          {
            if (abc.charAt(i) == dictionary[j].charAt(x))
            {
              count++;
            }
            if (count == 2)
            {
              dictionary[j] = "zzzz";
            }
          }
        }
      }
    }
    return dictionary;
  }
  
  //Removes zzzz instances//
  public static String[] zzzzInstances(String[] dictionary)
  {
    int count = 0;
    for(int i = 0; i < dictionary.length; i++)
    {
      if (dictionary[i] == "zzzz")
        count++;
    }
    String [] dictionaryShortened = new String[127142 - count];
    for(int j = 0; j < dictionaryShortened.length; j++)
    {
      dictionaryShortened[j] = dictionary[j];
    }
    return dictionaryShortened;
  }
  
  //Method to end game//
  public static int checkWin(int guesses, String display)
  {  
    if (display.indexOf('-') < 0)
    {
      guesses = 0;
    }
    return guesses;
  }
  
  //Method to end game and display win or lose and display word//
  public static String endGame(String display, String [] array, String word)
  {
    int findRandom = new Random().nextInt(array.length);
    String randomWord = (array[findRandom]);
    
    boolean win = false;
    for (int i = 0; i < display.length(); i++)
    {
      if (display.indexOf('-') < 0)
      {
        win = true;
      }
    }
    if (win)
    {
      System.out.println("Congratulations you won the game!!");
    }
    else 
    {
      System.out.println("Thankyou for playing but this time you lost");
    }
    System.out.println("The word was: " + randomWord);
    return randomWord;
  }
  
  //Method to replay game//
  public static boolean replay(boolean playAgain, int wordLength, int guesses,int noLetterCount,
                               int comparison, int guessCount, String display, String word, String usedChars, boolean wordCount)
  {
    System.out.println("Would you like to play again? Please enter yes or no");
    Scanner keyboard = new Scanner(System.in);
    do 
    {
      String replay = keyboard.next();
      if (replay.equals("yes"))
      {        
        playAgain = true;
         wordLength = 0;
         guesses = 0;
         noLetterCount = 0;
         comparison = 0;
         guessCount = 1;
         display = "";
         word = "";
         usedChars = " ";
         wordCount = false;
         playAgain = false;
         break;
      }
      if (replay.equals("no"))
      {
        System.out.println("Thankyou for playing!!");
        break;
      }
      else 
      {
        System.out.println("Invalid input, please input yes or no");
        continue;
      }
    }
    while(playAgain == false);
    return playAgain;
  }
  
  public static String chars(char letter, String usedChars){
   usedChars += letter;
    System.out.println("Used letters: " + usedChars);
    return usedChars;
  }
  
  
  
  //End code//
}