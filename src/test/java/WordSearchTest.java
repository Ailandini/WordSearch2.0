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

}
