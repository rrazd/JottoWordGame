/* Copyright (c) 2003 by  
 * Byron Weber Becker, University of Waterloo, Waterloo, Ontario, Canada. 
 * All rights reserved.  Source code is released only with the written
 * permission of the author and may not be redistributed.
 */

import java.util.Scanner;

/** @author Byron Weber Becker (modifications made by Rashna Razdan)*/
public class Word extends Object
{
   private String theWord;
   private int difficulty;

   public Word(Scanner in)
   {  super();
      this.theWord = in.next();
      this.difficulty = in.nextInt();
      in.nextLine();
		
      if (this.theWord.length() != Model.NUM_LETTERS)
      {  throw new Error(
               "The word '" + this.theWord + "' does not have "
               + Model.NUM_LETTERS + " letters.");
      }
      if (this.difficulty < 0)
      {  throw new Error(
               "The word '" + this.theWord + "' has an invalid difficulty: "
               + this.difficulty);
      }
   }
	
   /** Construct a new Word object.
    * @param aWord a word with the length specified by Model.NUM_LETTERS
    * @param aLevel a difficulty level.  One of {0, 1, 2}. */
   public Word(String aWord, int aLevel)
   {  super();
      this.theWord = aWord;
      this.difficulty = aLevel;
   }
	
  
   
   /** Get the difficulty level.
    * @return the word's difficulty level */
   public int getDifficulty()
   {  return this.difficulty;
   }
	
   /** Get the word.
    * @return the word. */
   public String getWord()
   {  
	   return this.theWord;
   }
   

}
