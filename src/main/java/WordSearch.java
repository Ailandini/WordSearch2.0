import java.util.Arrays;

public class WordSearch {

    public Boolean findWordInList(String[] listToFindString, String stringToFind){
        return String.join("",listToFindString).equals(stringToFind);
    }

}
