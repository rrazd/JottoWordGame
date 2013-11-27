
public class Row {
	private String guessWord;
	private int exactMatch;
	private int partialMatch;
	
	public void setGuessWord(String guess){
		guessWord = guess;
	}
	
	public void setExactMatch(int numExact){
		exactMatch = numExact;
	}
	
	public void setPartialMatch(int numPar){
		partialMatch = numPar;
	}
	
	public String getGuessWord(){
		return guessWord;
	}
	
	public int getExactMatch(){
		return exactMatch;
	}
	
	public int getPartialMatch(){
		return partialMatch;
	}
}
