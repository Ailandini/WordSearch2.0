import org.junit.Test;

import static junit.framework.TestCase.*;

public class WordSearchTest {

    @Test
    public void findWordInListCanFindItself(){
        WordSearch wordSearch = new WordSearch();
        String[] listToFindWordIn = new String[]{"C","A","T"};
        Boolean actual = wordSearch.findWordInList(listToFindWordIn, "CAT");
        assertTrue(actual);
    }

    @Test
    public void findWordInListCanIdentifyWordIsNotInProvidedList(){
        WordSearch wordSearch = new WordSearch();
        String[] listToFindWordIn = new String[]{"C","A","T"};
        Boolean actual = wordSearch.findWordInList(listToFindWordIn, "DOG");
        assertFalse(actual);
    }

    @Test
    public void findWordInListCanFindWordWhenListContainsExtraCharacters(){
        WordSearch wordSearch = new WordSearch();
        String[] listToFindWordIn = new String[]{"Z","X","C","A","T","R","Q"};
        Boolean actual = wordSearch.findWordInList(listToFindWordIn, "CAT");
        assertTrue(actual);
    }

    @Test
    public void findWordInListCanCatchNullInput(){
        WordSearch wordSearch = new WordSearch();
        String[] listToFindWordIn = new String[]{"Z","X","C","A","T","R","Q"};
        Boolean actualNullList = wordSearch.findWordInList(new String[]{}, "CAT");
        Boolean actualEmptyWord = wordSearch.findWordInList(listToFindWordIn, "");
        assertFalse(actualNullList);
        assertFalse(actualEmptyWord);
    }

    @Test
    public void findWordInListCanCatchWordLongerThanList(){
        WordSearch wordSearch = new WordSearch();
        String[] listToFindWordIn = new String[]{"Z","X","C","A","T","R","Q"};
        String wordToFind = "LENGTHIER";
        Boolean actual = wordSearch.findWordInList(listToFindWordIn, wordToFind);
        assertFalse(actual);
    }

    @Test
    public void findWordInListCanFindWordsBackwards(){
        WordSearch wordSearch = new WordSearch();
        String[] listToFindWordIn = new String[]{"Z","X","T","A","C","R","Q"};
        String wordToFind = "CAT";
        Boolean actual = wordSearch.findWordInList(listToFindWordIn, wordToFind);
        assertTrue(actual);
    }

}
