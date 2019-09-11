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

    protected String[] getPuzzleRow(ArrayList<ArrayList<String>> wordSearchPuzzle, int rowToGet){
        if(wordSearchPuzzle.size() == 0 || rowToGet > wordSearchPuzzle.size() || rowToGet < 0){
            return new String[]{};
        }
        return wordSearchPuzzle.get(rowToGet).toArray(new String[0]);
    }

    protected String[] getPuzzleCol(ArrayList<ArrayList<String>> wordSearchPuzzle, int colToGet){
        if(wordSearchPuzzle.size() == 0 || colToGet > wordSearchPuzzle.size() || colToGet < 0){
            return new String[]{};
        }
        String[] outputColumnToGet = new String[wordSearchPuzzle.size()];
        for(int i=0; i < wordSearchPuzzle.size(); i++){
            outputColumnToGet[i] = wordSearchPuzzle.get(i).get(colToGet);
        }
        return outputColumnToGet;
    }

    protected String[] getPuzzleDiagonal(ArrayList<ArrayList<String>> wordSearchPuzzle, int diagToGetCol, int diagToGetRow, String direction ){
        if(wordSearchPuzzle.size() == 0 || diagToGetCol > wordSearchPuzzle.size() || diagToGetCol < 0
        || diagToGetRow > wordSearchPuzzle.size() || diagToGetRow < 0){
            return new String[]{};
        }
        ArrayList<String> outputDiagonalToGet = new ArrayList<>();
        if(direction.equals("HL")) {
            for (int i = 0; i < wordSearchPuzzle.size(); i++) {
                outputDiagonalToGet.add(wordSearchPuzzle.get(i).get(i));
            }
        }
        else if(direction.equals("LH")){
            for (int i = 0; i < wordSearchPuzzle.size(); i++) {
                outputDiagonalToGet.add(wordSearchPuzzle.get(diagToGetRow - i).get(diagToGetCol+i));
            }
        }
        return outputDiagonalToGet.toArray(new String[0]);
    }
}
