import java.util.Arrays;

public class WordSearch {

    protected Boolean findWordInList(String[] listToFindString, String stringToFind){
        String stringOfListToFindString = String.join("",listToFindString);
        StringBuilder mutableListToFindString = new StringBuilder(stringOfListToFindString);
        mutableListToFindString.reverse();
        String reverseStringOfListToFindString = mutableListToFindString.toString();

        if(stringToFind.length() == 0){
            return false;
        }
        return(stringOfListToFindString.contains(stringToFind) || reverseStringOfListToFindString.contains(stringToFind));

    }

}
