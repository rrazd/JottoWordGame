import java.awt.Dimension;
import java.awt.Label;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

//View interface
interface IView {
	public void updateView();
}

public class Model {
	private ArrayList<IView> views = new ArrayList<IView>();
	private ArrayList<String> dictionaryWords = new ArrayList<String>();
	private ArrayList<String> jListWords = new ArrayList<String>();
	private WordList listOfWords;
	public static int NUM_LETTERS = 5;
	private static final String WORDS_FILE = "words.txt";
	private int guessesRemaining = 10;
	private String guessMessage;
	//holds target word
	private String[] target;
	//holds guess word
	private String[] guess;
	private ArrayList<Row> tableRows = new ArrayList<Row>();
	private int exactMatchesCount = 0;
	private int partialMatchesCount = 0;
	private boolean validGuess = false;	
	private ArrayList<String> letterNotInTarget = new ArrayList<String>(26);
	//keeps track of exact matches
	private boolean[] exactMatches = {false, false, false, false, false};
	//keeps track of partial matches
	private boolean[] partialMatches = {false, false, false, false, false};
	private int frameHeight = 0;
	//value of variable changes according to user click
	private String showHintButtonText = "<html>Filter the Dictionary List<br/>(Not Selected)</html>";
	//value of variable changes according to user click
	private String listIntroText = "Dictionary ('filtering currently OFF')";
	private boolean buttonShowHintJPanel = false;
	//value of variable changes according to user click
	private ImageIcon showHint = new ImageIcon("images/hints.png");
	private String targetWord;
	private ArrayList<String> lettersToColor = new ArrayList<String>(26);
	private ArrayList<String> lettersInAlphabet = new ArrayList<String>(26);
	private boolean guessedTargetCorrectly = false;

	public Model(){
		getWordsFromDictionary();
	}


	public void computeWordsNotInTarget(){
		for(String guessLetter : guess){
			boolean isPresent = false;
			for(String targetLetter : target){
				if(guessLetter.equals(targetLetter)){
					isPresent = true;
				}
			}
			if(isPresent == false){
				letterNotInTarget.add(guessLetter);
			}
		}
		//		System.out.println("letters not in target " + letterNotInTarget.toString());
	}

	public void getWordsFromDictionary(){
		listOfWords = new WordList(WORDS_FILE);


		for(Word word : listOfWords.getWords()){
			if(word != null){
				dictionaryWords.add(word.getWord());
			}
			else{
				break;
			}
		}
	}

	public ArrayList<String> getDictionaryWordsList(){
		return dictionaryWords;
	}

	public void addView(IView view) {
		views.add(view);
		// update the view to current state of the model
		notifyObservers();
	}



	public void setGameMessage(String strMssg){
		String guessWord;
		strMssg = strMssg.trim();
		guessWord = strMssg.toUpperCase();
		if(guessesRemaining <= 0 || guessedTargetCorrectly){
			if(guessedTargetCorrectly){
				guessMessage = "You have already guessed the target word. You can choose to start a new game.";
			}
			else{
				guessMessage = "GAME OVER: You have reached the maximum amount of guesses.";
			}
			validGuess = false;
		}
		else if(guessWord.length() == 5 && checkIfInDictionary(guessWord)){
			validGuess = true;
			guessesRemaining--;
			guessMessage = "Good job, you have entered a valid word. You have " + guessesRemaining + " guesses remaining.";

		}
		else if(guessWord.length() != 5){			
			guessMessage = "Woops...you did not enter a 5 letter word. You have " + guessesRemaining + " guesses remaining.";
			validGuess = false;
		}
		else{
			guessMessage = guessWord + " isn't in dictionary. Ensure you don't enter proper nouns or contractions.";
			validGuess = false;
		}
		notifyObservers();
	}

	public boolean checkIfInDictionary(String strMssg){	
		for(String str : dictionaryWords){
			if(str.equals(strMssg)){
				return true;
			}
		}
		return false;
	}

	public String getGameMessage(){
		return guessMessage;
	}

	public void processGuess(){
		exactMatchesCount = 0;
		partialMatchesCount = 0;
		//says if letters in target word have already participated in a match
		boolean[] targetLetterParticipated = {false, false, false, false, false};
		boolean[] exactMatches = {false, false, false, false, false};
		//exact match
		int i = 0;
		for(String guessLetter : guess){
			if(guessLetter.equals(target[i])){
				targetLetterParticipated[i] = true;
				exactMatches[i] = true;
				exactMatchesCount++;
			}
			i++;
		}
		//partial match
		i = 0;
		int j = 0;
		for(boolean exactMatchElem : exactMatches){
			if(exactMatchElem == false){
				String guessLetter = guess[i];
				j = 0;
				for(String targetLetter : target){
					if(guessLetter.equals(targetLetter) && targetLetterParticipated[j] == false){
						partialMatches[i] = true;
						targetLetterParticipated[j] = true;
						partialMatchesCount++;
						break;
					}
					j++;
				}
			}
			i++;
		}
		computeWordsNotInTarget();
		getUpdatedDictionaryWordList();

		if(exactMatchesCount == 5){
			guessMessage = "Congratulations, you guessed the target word in " + (10 - guessesRemaining) + " guess(es).";
			this.guessedTargetCorrectly = true;
		}
	}



	public ArrayList<String> getUpdatedDictionaryWordList(){
		listOfWords = new WordList(WORDS_FILE);
		//have lettersNotInTarget filled so use it as filter
		jListWords = new ArrayList<String>();
		for(Word word : listOfWords.getWords()){
			boolean doNotAdd = false;
			if(word != null){
				for(String letter : letterNotInTarget){
					if(word.getWord().toUpperCase().contains(letter.toUpperCase())){
						doNotAdd = true;
					}
				}
				if(doNotAdd == false){
					jListWords.add(word.getWord());
				}
			}
			else{
				break;
			}

		}
		//return filtered result only if buttonShowHintJPanel is true
		if(!buttonShowHintJPanel){
			Collections.sort(jListWords);
			return jListWords;
		}
		else{
			Collections.sort(dictionaryWords);
			return dictionaryWords;
		}
	}

	public void setGuessArray(String guessWordEntered){
		guessWordEntered = guessWordEntered.toUpperCase();
		guessWordEntered = guessWordEntered.trim();
		guess = guessWordEntered.split("(?!^)");

	}

	public void newGame(){
		//start over
		guessMessage = "Welcome to JOTTO, please enter your first guess. You have 10 guesses remaining.";
		guessesRemaining = 10;
		guessedTargetCorrectly = false;
		//pick out our random target word, save into target array
		targetWord = listOfWords.randomWord().getWord();
		System.out.println("Target word is: " + targetWord);
		setTargetArray(targetWord);

		//clear certain variables
		tableRows.clear();
		buttonShowHintJPanel = false;
		lettersToColor.clear();

		//this method also calls notifyObservers, so don't have to at the end here
		setHintsRelatedText();
	}

	public void setTargetArray(String targetWord){
		targetWord = targetWord.trim();
		target = targetWord.split("(?!^)");
	}

	public String convertGuessArrayToString(){
		StringBuilder builder = new StringBuilder();
		for(String tmp : guess) {
			builder.append(tmp);
		}
		return builder.toString();
	}

	public void populateTable(){
		if(validGuess){
			processGuess();
			//now we have all row data
			Row tableRow = new Row();

			//array of strings to string
			String guessString = convertGuessArrayToString();
			tableRow.setGuessWord(guessString);
			tableRow.setExactMatch(exactMatchesCount);
			tableRow.setPartialMatch(partialMatchesCount);
			tableRows.add(tableRow);
		}
		notifyObservers();
	}

	public void setFrameDim(Dimension dim){
		frameHeight = dim.height;
		notifyObservers();
	}

	public int getFrameHeight(){
		return frameHeight;
	}

	public ArrayList<Row> getRowList(){
		return tableRows;
	}


	public String getShowHintButtonText(){
		return showHintButtonText;
	}

	public void setHintsRelatedText(){
		if(buttonShowHintJPanel){
			showHintButtonText = "<html>Filter the Dictionary List<br/>(*** Selected)</html>";
			listIntroText = "Dictionary ('filtering currently ON')";

			showHint = new ImageIcon("images/hintsSelected.png");
			buttonShowHintJPanel = false;
		}
		else{
			showHintButtonText = "<html>Filter the Dictionary List<br/>(Not Selected)</html>";
			listIntroText = "Dictionary ('filtering currently OFF')";

			showHint = new ImageIcon("images/hints.png");
			buttonShowHintJPanel = true;
		}
		notifyObservers();
	}

	public ImageIcon getShowHintIcon(){
		return showHint;
	}

	public String getListIntroText(){
		return listIntroText;
	}


	public void giveUp(){
		//guessMessage = "The target word is: " + targetWord + ". Too bad you didn't guess it by yourself.";
		JFrame frame2 = new JFrame();
		JOptionPane.showMessageDialog(frame2, "The target word is: " + targetWord + ". Good luck with your new game.");
		newGame();
		notifyObservers();
	}

	public void setLabelsToColor(){
		if(guess != null && validGuess){
			for(String guessLetter : guess){
				lettersToColor.add(guessLetter);
			}
		}
		notifyObservers();
	}

	public ArrayList<String> getLabelsToColor(){
		return lettersToColor;
	}

	public ArrayList<String> getLettersInAlphabet(){
		lettersInAlphabet.add("A");
		lettersInAlphabet.add("B");
		lettersInAlphabet.add("C");
		lettersInAlphabet.add("D");
		lettersInAlphabet.add("E");
		lettersInAlphabet.add("F");
		lettersInAlphabet.add("G");
		lettersInAlphabet.add("H");
		lettersInAlphabet.add("I");
		lettersInAlphabet.add("J");
		lettersInAlphabet.add("K");
		lettersInAlphabet.add("L");
		lettersInAlphabet.add("M");
		lettersInAlphabet.add("N");
		lettersInAlphabet.add("O");
		lettersInAlphabet.add("P");
		lettersInAlphabet.add("Q");
		lettersInAlphabet.add("R");
		lettersInAlphabet.add("S");
		lettersInAlphabet.add("T");
		lettersInAlphabet.add("U");
		lettersInAlphabet.add("V");
		lettersInAlphabet.add("W");
		lettersInAlphabet.add("X");
		lettersInAlphabet.add("Y");
		lettersInAlphabet.add("Z");


		return lettersInAlphabet;
	}

	public boolean getGuessedTargetCorrectly(){
		return this.guessedTargetCorrectly;
	}
	
	private void notifyObservers() {
		for (IView view : this.views) {
			view.updateView();
		}
	}
}
