import java.util.ArrayList;
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

    protected String findWordCoordinates(String stringToFind, int startRowOfWord, int startColOfWord, String direction){
        StringBuilder output = new StringBuilder(stringToFind + ": (" + startColOfWord + "," + startRowOfWord + "),");
        int rowAdjustment = 0;
        int colAdjustment = 0;
        switch(direction){
            case "forwardH":
                colAdjustment = 1;
                break;
            case "backwardH":
                colAdjustment = -1;
                break;
            case "forwardV":
                rowAdjustment = 1;
                break;
            case "backwardV":
                rowAdjustment = -1;
                break;
            case "forwardDLH":
                colAdjustment = 1;
                rowAdjustment = -1;
                break;
            case "backwardDLH":
                colAdjustment = -1;
                rowAdjustment = 1;
                break;
            case "forwardDHL":
                colAdjustment = 1;
                rowAdjustment = 1;
                break;
            case "backwardDHL":
                colAdjustment = -1;
                rowAdjustment = -1;
                break;
            default:
                return "Direction Provided is Invalid.";
        }

        for(int i=1; i < stringToFind.length(); i++){
            output.append("(").append(startColOfWord+(colAdjustment*i)).append(",").append(startRowOfWord+(rowAdjustment*i)).append("),");
        }

        return output.toString().substring(0, output.length() - 1);
    }

    protected String[] getPuzzleRow(ArrayList<ArrayList<String>> wordSearchPuzzle, int puzzleRowToGet){
        return wordSearchPuzzle.get(puzzleRowToGet).toArray(new String[0]);
    }

}
