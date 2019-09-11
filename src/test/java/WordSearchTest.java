import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertArrayEquals;

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
    public void findWordCoordinatesWhenIsForward(){
        String wordToFind = "CAT";
        int startRowOfWord  = 0;
        int startColOfWord = 0;
        String direction = "forwardH";
        String expected = "CAT: (0,0),(1,0),(2,0)";
        String actual = wordSearch.findWordCoordinates(wordToFind, startRowOfWord, startColOfWord, direction);

        assertEquals(expected, actual);
    }

    @Test
    public void findWordCoordinatesWhenWordIsBackwards(){
        String wordToFind = "DOG";
        int startRowOfWord = 0;
        int startColOfWord = 3;
        String direction = "backwardH";
        String expected = "DOG: (3,0),(2,0),(1,0)";
        String actual = wordSearch.findWordCoordinates(wordToFind, startRowOfWord, startColOfWord, direction);

        assertEquals(expected, actual);
    }

    @Test
    public void findWordCoordinatesWhenWordIsVertical(){
        String wordToFind = "SANDY";
        int startRowOfWord = 5;
        int startColOfWord = 2;
        String direction = "forwardV";
        String expected = "SANDY: (2,5),(2,6),(2,7),(2,8),(2,9)";
        String actual = wordSearch.findWordCoordinates(wordToFind, startRowOfWord, startColOfWord, direction);

        assertEquals(expected,actual);
    }

    @Test
    public void findWordCoordinatesWhenWordIsBackwardsVertical(){
        String wordToFind = "SANDY";
        int startRowOfWord = 4;
        int startColOfWord = 2;
        String direction = "backwardV";
        String expected = "SANDY: (2,4),(2,3),(2,2),(2,1),(2,0)";
        String actual = wordSearch.findWordCoordinates(wordToFind, startRowOfWord, startColOfWord, direction);

        assertEquals(expected,actual);
    }

    @Test
    public void findWordCoordinatesWhenWordIsDiagonalLowHigh(){
        String wordToFind = "BANANA";
        int startRowOfWord = 7;
        int startColOfWord = 1;
        String direction = "forwardDLH";
        String expected = "BANANA: (1,7),(2,6),(3,5),(4,4),(5,3),(6,2)";
        String actual = wordSearch.findWordCoordinates(wordToFind, startRowOfWord, startColOfWord, direction);

        assertEquals(expected,actual);
    }

    @Test
    public void findWordCoordinatesWhenWordIsBackwardsDiagonalLowHigh(){
        String wordToFind = "PEACH";
        int startRowOfWord = 4;
        int startColOfWord = 6;
        String direction = "backwardDLH";
        String expected = "PEACH: (6,4),(5,5),(4,6),(3,7),(2,8)";
        String actual = wordSearch.findWordCoordinates(wordToFind, startRowOfWord, startColOfWord, direction);

        assertEquals(expected,actual);
    }

    @Test
    public void findWordCoordinatesWhenWordIsDiagonalHighLow(){
        String wordToFind = "PULP";
        int startRowOfWord = 1;
        int startColOfWord = 1;
        String direction = "forwardDHL";
        String expected = "PULP: (1,1),(2,2),(3,3),(4,4)";
        String actual = wordSearch.findWordCoordinates(wordToFind, startRowOfWord, startColOfWord, direction);

        assertEquals(expected,actual);
    }

    @Test
    public void findWordCoordinatesWhenWordIsBackwardsDiagonalHighLow(){
        String wordToFind = "PURPLE";
        int startRowOfWord = 6;
        int startColOfWord = 7;
        String direction = "backwardDHL";
        String expected = "PURPLE: (7,6),(6,5),(5,4),(4,3),(3,2),(2,1)";
        String actual = wordSearch.findWordCoordinates(wordToFind, startRowOfWord, startColOfWord, direction);

        assertEquals(expected,actual);
    }

    @Test
    public void getPuzzleRowReturnsRow(){
        ArrayList<ArrayList<String>> wordSearchPuzzle = new ArrayList<>();
        ArrayList<String> wordSearchPuzzleFirstRow = new ArrayList<>(Arrays.asList("R","Q","C"));
        wordSearchPuzzle.add(wordSearchPuzzleFirstRow);
        int rowToGet = 0;
        String[] expected = new String[]{"R","Q","C"};
        String[] actual = wordSearch.getPuzzleRow(wordSearchPuzzle, rowToGet);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void getPuzzleRowReturnsRowDesired(){
        ArrayList<ArrayList<String>> wordSearchPuzzle = new ArrayList<>();
        ArrayList<String> wordSearchPuzzleFirstRow = new ArrayList<>(Arrays.asList("R","Q","C"));
        ArrayList<String> wordSearchPuzzleSecondRow = new ArrayList<>(Arrays.asList("P","R","T"));
        wordSearchPuzzle.add(wordSearchPuzzleFirstRow);
        wordSearchPuzzle.add(wordSearchPuzzleSecondRow);
        int rowToGet = 1;
        String[] expected = new String[]{"P","R","T"};
        String[] actual = wordSearch.getPuzzleRow(wordSearchPuzzle, rowToGet);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void getPuzzleRowReturnsEmptyWhenNullPuzzleProvided(){
        ArrayList<ArrayList<String>> wordSearchPuzzle = new ArrayList<>();
        String[] expected = new String[]{};
        String[] actual = wordSearch.getPuzzleRow(wordSearchPuzzle, 0);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void getPuzzleRowReturnsEmptyWhenIndexOutOfBounds(){
        ArrayList<ArrayList<String>> wordSearchPuzzle = new ArrayList<>();
        ArrayList<String> wordSearchPuzzleFirstRow = new ArrayList<>(Arrays.asList("R","Q","C"));
        wordSearchPuzzle.add(wordSearchPuzzleFirstRow);
        String[] expected = new String[]{};
        String[] actualTooLarge = wordSearch.getPuzzleRow(wordSearchPuzzle, 4);
        String[] actualTooSmall = wordSearch.getPuzzleRow(wordSearchPuzzle, -1);
        assertArrayEquals(expected, actualTooLarge);
        assertArrayEquals(expected, actualTooSmall);
    }

    @Test
    public void getPuzzleColReturnsCol(){
        ArrayList<ArrayList<String>> wordSearchPuzzle = new ArrayList<>();
        ArrayList<String> wordSearchPuzzleFirstRow = new ArrayList<>(Arrays.asList("R","Q"));
        ArrayList<String> wordSearchPuzzleSecondRow = new ArrayList<>(Arrays.asList("P","R"));
        wordSearchPuzzle.add(wordSearchPuzzleFirstRow);
        wordSearchPuzzle.add(wordSearchPuzzleSecondRow);
        int colToGet = 0;
        String[] expected = new String[]{"R","P"};
        String[] actual = wordSearch.getPuzzleCol(wordSearchPuzzle, colToGet);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void getDiagonalHighLowReturnsDiagonalFromHighToLowFromPuzzleEdge(){
        ArrayList<ArrayList<String>> wordSearchPuzzle = new ArrayList<>();
        ArrayList<String> wordSearchPuzzleFirstRow = new ArrayList<>(Arrays.asList("R","Q"));
        ArrayList<String> wordSearchPuzzleSecondRow = new ArrayList<>(Arrays.asList("P","R"));
        wordSearchPuzzle.add(wordSearchPuzzleFirstRow);
        wordSearchPuzzle.add(wordSearchPuzzleSecondRow);
        int diagToGetCol = 0;
        int diagToGetRow = 0;
        String direction = "HL";
        String[] expected = new String[]{"R","R"};
        String[] actual = wordSearch.getPuzzleDiagonal(wordSearchPuzzle, diagToGetCol, diagToGetRow, direction);

        assertArrayEquals(expected, actual);
    }

}
