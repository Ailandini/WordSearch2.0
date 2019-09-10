import java.util.Arrays;

public class WordSearch {

    public Boolean findWordInList(String[] listToFindString, String stringToFind){
        if(stringToFind.length() == 0){
            return false;
        }
        return(String.join("",listToFindString).contains(stringToFind));

    }

}
