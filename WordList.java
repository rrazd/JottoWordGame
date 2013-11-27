import java.io.*;
import java.util.Scanner;

/** @author Byron Weber Becker (modified by Rashna Razdan) */
public final class WordList extends Object implements IWordList
{
	private Word[] words = new Word[10]; // must not be 0
	private int numOfWordsInDictionary = 0;

	/** Create a word list from a file. */
	public WordList(Scanner in)
	{  
		super();
		this.readWords(in);
	}

	public WordList(String filename)
	{ 
		super();
		try {
			Scanner s = new Scanner(new File(filename));
			this.readWords(s);
			s.close();         
		} 
		catch (FileNotFoundException e) {
			System.out.println("File " + filename + " not found.");
		}
	}

	private void readWords(Scanner in) 
	{ 
		while (in.hasNextLine())
		{  
			this.add(new Word(in));
		}
	}

	/** Add a new word to the list. */
	public void add(Word wd)
	{  
		if (this.numOfWordsInDictionary >= this.words.length)
		{  
			//make our list bigger and continue adding words so can store ALL words from dictionary this way
			Word[] newList = new Word[this.words.length * 2];
			for (int i = 0; i < this.numOfWordsInDictionary; i++)
			{  
				newList[i] = this.words[i];
			}
			this.words = newList;
		}
		this.words[this.numOfWordsInDictionary] = wd;
		this.numOfWordsInDictionary++;
	}



	@Override
	public int numWords()
	{  
		return this.numOfWordsInDictionary;
	}

	private Word get(int i)
	{  
		if (i >= this.numOfWordsInDictionary)
		{  
			throw new IllegalArgumentException(
					"words in list = " + this.numOfWordsInDictionary + "; requested " + i);
		}
		return this.words[i];
	}

	@Override
	public Word randomWord()
	{  
		int r = (int) (Math.random() * this.numOfWordsInDictionary);
		return this.words[r];
	}

	@Override
	public Word randomWord(int difficulty)
	{  
		int r = (int) (Math.random() * this.numOfWordsInDictionary);
		// check the end of the array
		for (int i = r; i < this.numOfWordsInDictionary; i++)
		{  
			if (this.words[i].getDifficulty() == difficulty)
			{  
				return this.words[i];	
			}
		}

		// nothing in the end of the array;  check the beginning
		for (int i = 0; i < r; i++)
		{  
			if (this.words[i].getDifficulty() == difficulty)
			{  
				return this.words[i];	
			}
		}
		return null;
	}

	@Override
	public Word getWord(IWordPredicate test)
	{  
		int r = (int) (Math.random() * this.numOfWordsInDictionary);

		// check the end of the array
		for (int i = r; i < this.numOfWordsInDictionary; i++)
		{  
			if (test.isOK(this.words[i]))
			{  
				return this.words[i];	
			}
		}

		// nothing in the end of the array;  check the beginning
		for (int i = 0; i < r; i++)
		{  
			if (test.isOK(this.words[i]))
			{  
				return this.words[i];	
			}
		}

		// didn't find one
		return null;
	}

	@Override
	public Word[] getWords(IWordPredicate test)
	{  
		int count = 0;
		for (int i = 0; i < this.numOfWordsInDictionary; i++)
		{  
			if (test.isOK(this.words[i]))
			{  
				count++;
			}
		}

		Word[] answer = new Word[count];
		int next = 0;
		for (int i = 0; i < this.numOfWordsInDictionary; i++)
		{  
			if (test.isOK(this.words[i]))
			{  
				answer[next] = this.words[i];
				next++;
			}
		}
		return answer;
	}

	@Override
	public Word[] getWords(int maxDesired, IWordPredicate test)
	{  
		int count = 0;
		final int start = (int) (Math.random() * this.numOfWordsInDictionary);
		for (int i = 0; i < this.numOfWordsInDictionary && count < maxDesired; i++)
		{  
			if (test.isOK(this.words[(start + i) % this.numOfWordsInDictionary]))
			{  
				count++;
			}
		}

		int numToGet = Math.min(count, maxDesired);
		Word[] answer = new Word[numToGet];
		int next = 0;
		for (int i = 0; i < this.numOfWordsInDictionary && next < numToGet; i++)
		{  
			int w = (start + i) % this.numOfWordsInDictionary;
			if (test.isOK(this.words[w]))
			{  
				answer[next] = this.words[w];
				next++;
			}
		}
		return answer;
	}
	
	public Word[] getWords(){
		return words;
	}
}
