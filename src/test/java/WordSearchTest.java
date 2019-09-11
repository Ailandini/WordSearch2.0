import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class WordSearchTest {
    private WordSearch wordSearch;

    @Before
    public void setup(){
        wordSearch = new WordSearch();
    }

    @Test
    public void findWordInListCanFindItself(){
        String[] listToFindWordIn = new String[]{"C","A","T"};
        Boolean actual = wordSearch.findWordInList(listToFindWordIn, "CAT");
        assertTrue(actual);
    }

    @Test
    public void findWordInListCanIdentifyWordIsNotInProvidedList(){
        String[] listToFindWordIn = new String[]{"C","A","T"};
        Boolean actual = wordSearch.findWordInList(listToFindWordIn, "DOG");
        assertFalse(actual);
    }

    @Test
    public void findWordInListCanFindWordWhenListContainsExtraCharacters(){
        String[] listToFindWordIn = new String[]{"Z","X","C","A","T","R","Q"};
        Boolean actual = wordSearch.findWordInList(listToFindWordIn, "CAT");
        assertTrue(actual);
    }

    @Test
    public void findWordInListCanCatchNullInput(){
        String[] listToFindWordIn = new String[]{"Z","X","C","A","T","R","Q"};
        Boolean actualNullList = wordSearch.findWordInList(new String[]{}, "CAT");
        Boolean actualEmptyWord = wordSearch.findWordInList(listToFindWordIn, "");
        assertFalse(actualNullList);
        assertFalse(actualEmptyWord);
    }

    @Test
    public void findWordInListCanCatchWordLongerThanList(){
        String[] listToFindWordIn = new String[]{"Z","X","C","A","T","R","Q"};
        String wordToFind = "LENGTHIER";
        Boolean actual = wordSearch.findWordInList(listToFindWordIn, wordToFind);
        assertFalse(actual);
    }

    @Test
    public void findWordInListCanFindWordsBackwards(){
        String[] listToFindWordIn = new String[]{"Z","X","T","A","C","R","Q"};
        String wordToFind = "CAT";
        Boolean actual = wordSearch.findWordInList(listToFindWordIn, wordToFind);
        assertTrue(actual);
    }

    @Test
    public void findWordCoordinatesWhenListIsWord(){
        String[] listToFindWordIn = new String[]{"C","A","T"};
        String wordToFind = "CAT";
        int startRowOfWord  = 0;
        int startColOfWord = 0;
        String direction = "forwardH";
        String expected = "CAT: (0,0),(1,0),(2,0)";
        String actual = wordSearch.findWordCoordinates(listToFindWordIn, wordToFind, startRowOfWord, startColOfWord, direction);

        assertEquals(expected, actual);
    }

    @Test
    public void findWordCoordinatesWhenListHasExtraCharacters(){
        String[] listToFindWordIn = new String[]{"P","A","D","O","G","R","Q"};
        String wordToFind = "DOG";
        int startRowOfWord = 0;
        int startColOfWord = 2;
        String direction = "forwardH";
        String expected = "DOG: (2,0),(3,0),(4,0)";
        String actual = wordSearch.findWordCoordinates(listToFindWordIn, wordToFind, startRowOfWord, startColOfWord, direction);

        assertEquals(expected, actual);
    }

    @Test
    public void findWordCoordinatesWhenWordIsBackwards(){
        String[] listToFindWordIn = new String[]{"P","G","O","D","G","R","Q"};
        String wordToFind = "DOG";
        int startRowOfWord = 0;
        int startColOfWord = 3;
        String direction = "backwardH";
        String expected = "DOG: (3,0),(2,0),(1,0)";
        String actual = wordSearch.findWordCoordinates(listToFindWordIn, wordToFind, startRowOfWord, startColOfWord, direction);

        assertEquals(expected, actual);
    }

}
