/** @author Byron Weber Becker modifications made by Rashna Razdan */
public interface IWordPredicate 
{

   /** Does the given Word meet a condition?
    * @param w The word-difficulty object to test
    * @return true if it meets the condition;  false otherwise. */
   public boolean isOK(Word w);
}
