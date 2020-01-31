package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 

	// The starting "word"
	private String starter;

	// The random number generator
	private Random rnGenerator;

	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}


	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		if (sourceText == null) {
			throw new IndexOutOfBoundsException("Empty text");
		}
		if (wordList.isEmpty()) {
		String[] words = sourceText.split("[ ]+");

		starter = words[0];
		String prevWord = starter;

		for (int i = 1; i < words.length; i++) {
			String w = words[i];
			ListNode prevNode = getListNode(prevWord);
			if ( prevNode == null) {
				ListNode newNode = new ListNode(prevWord);
				wordList.add(newNode);
				newNode.addNextWord(w);
			} else {
				prevNode.addNextWord(w);
			}
			prevWord = w;
		}
		//System.out.println(prevWord);
		ListNode prevNode = new ListNode(prevWord);
		wordList.add(prevNode);
		prevNode.addNextWord(starter);}

	}


	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
		
		if (wordList.size() == 0) {
			throw new IndexOutOfBoundsException("No training has been completed");
		}
		
		if (wordList.isEmpty()) {
			throw new IndexOutOfBoundsException("WordList is empty");
		}
		
		String currWord = starter;
		String output = "";
		
		if (numWords == 0) {
			return output;
		}
		
		output = output + currWord;
		ListNode currNode = new ListNode(getListNode(currWord));
		for (int i = 1; i < numWords; i++) {
			currNode = getListNode(currWord);
			if (currNode == null) {
				currNode = getListNode(starter);
			}
			String randomWord = currNode.getRandomNextWord(rnGenerator);
			output = output + " " + randomWord;
			currWord = randomWord;
		}
		return output;
	}


	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}

	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		wordList.clear();
		train(sourceText);
	}

	// TODO: Add any private helper methods you need here.
	
	private ListNode getListNode (String word) {
		
		for (ListNode ln : wordList) {
			if (ln.getWord().equals(word)) {
				return ln;
			}
		}
		
		return null;
	}

	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString3 = "Firefox's toolbar provides easy access to common features. Do you miss something you use a lot? The toolbar is easy to customize. Is there something you don't use all the time? Try adding it to the overflow menu. We'll show you how. A special tab will open which allows you to drag and drop items in or out of the overflow menu and the toolbar. Feel free to experiment with what works best for you. You can always start over by clicking the button at the bottom of the screen. When you are done, click the button.";
		System.out.println(textString3);
		gen.retrain(textString3);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString4 = "Following our reporting from 2018 that Oracle was seeking $8.8 billion in damages from Google, the Supreme Court of the United States has decided to hear Google’s petition appealing that its use of open-source Java Application Programming Interfaces (APIs) to build the Android platform violated Oracle’s copyrights. The court battle between the two tech giants has been ongoing since 2010. Oracle’s main claim is that Google (i.e. the petitioner) directly copied 11,500 lines of copyrighted code and used it in the Android operating system. Petitioner copied 11,500 lines of respondent’s copyrighted code. In doing so, petitioner also copied the complex architecture of the 37 packages at issue, including the names and specifications of the thousands of methods and classes in those packages and their hierarchical and interdependent relationships to each other. Google holds fast that APIs are not copyrightable and the reuse of software interfaces is necessary to make systems interoperable. The issue is whether copyright law prohibits reimplementing—i.e., reusing—the software interfaces that are necessary to connect dozens of platforms to millions of applications on billions of devices. Without interfaces, your contact list cannot access your email program, which cannot send a message using the operating system, which cannot access your phone in the first place. Each is an island. Countless other examples abound. The information age depends on the reuse of interfaces. In 2018, an appeals court ruled in favor of Oracle and overturned previous rulings that favored Google. Dissatisfied with the lower court’s decision, Google petitioned the Supreme Court to hear its case. Previously, the Supreme Court had refused to hear Google’s petition but finally granted it on November 15th, 2019. Given that Google filed the petition, the case is now dubbed \"Google v. Oracle\" instead of \"Oracle v. Google\". On the same day the petition was granted, a tweet from Joshua Bloch, a former Java architect at Google, indicated his excitement about the decision.";
		System.out.println(textString4);
		gen.retrain(textString4);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
	// The word that is linking to the next words
	private String word;

	// The next words that could follow it
	private List<String> nextWords;

	ListNode(ListNode ln) {
		this.word = ln.word;
		nextWords = ln.nextWords;
	}

	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}

	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}

	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
		// The random number generator should be passed from 
		// the MarkovTextGeneratorLoL class
		int randomVal = generator.nextInt(this.nextWords.size());
		String[] wordlist = new String[this.nextWords.size()];
		this.nextWords.toArray(wordlist);
		String randWord = "";
		for (int i = 0; i <= randomVal; i++) {
			randWord = wordlist[i];
		}
		
		return randWord;
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}

}


