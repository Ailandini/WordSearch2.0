import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class WordSearch {
    private String[] wordsToFind;

    WordSearch(String puzzleFilePath){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(puzzleFilePath));
            wordsToFind = bufferedReader.readLine().split(",");
        }
        catch(Exception e){
            System.out.println("Puzzle at path: " + puzzleFilePath + " does not exist.");
        }
    }

    public String[] getWordsToFind(){
        return wordsToFind;
    }

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
            case "forwardDBT":
                colAdjustment = 1;
                rowAdjustment = -1;
                break;
            case "backwardDBT":
                colAdjustment = -1;
                rowAdjustment = 1;
                break;
            case "forwardDTB":
                colAdjustment = 1;
                rowAdjustment = 1;
                break;
            case "backwardDTB":
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
        int adjustedDiagToGetCol = 0;
        int adjustedDiagToGetRow = 0;
        ArrayList<String> outputDiagonalToGet = new ArrayList<>();
        if(direction.equals("TB")) {
            if(diagToGetRow > diagToGetCol){
                adjustedDiagToGetRow = diagToGetRow - diagToGetCol;
            }
            else {
                adjustedDiagToGetCol = diagToGetCol - diagToGetRow;
            }
            for (int i = 0; i < wordSearchPuzzle.size(); i++) {
                if(adjustedDiagToGetCol + i >= wordSearchPuzzle.size() || adjustedDiagToGetRow + i >= wordSearchPuzzle.size()){
                    break;
                }
                outputDiagonalToGet.add(wordSearchPuzzle.get(adjustedDiagToGetRow + i).get(adjustedDiagToGetCol + i));
            }
        }
        else if(direction.equals("BT")){
            if(diagToGetCol > diagToGetRow){
                    adjustedDiagToGetCol = diagToGetRow + diagToGetCol;
            }
            else {

                if(diagToGetRow + diagToGetCol < wordSearchPuzzle.size()) {
                    adjustedDiagToGetRow = diagToGetCol + diagToGetRow;
                }
                else{
                    adjustedDiagToGetCol = diagToGetCol;
                    adjustedDiagToGetRow = diagToGetRow;
                }
            }
            for (int i = 0; i < wordSearchPuzzle.size(); i++) {
                if(adjustedDiagToGetRow - i < 0 || adjustedDiagToGetCol + i >= wordSearchPuzzle.size()){
                    break;
                }
                outputDiagonalToGet.add(wordSearchPuzzle.get(adjustedDiagToGetRow - i).get(adjustedDiagToGetCol + i));
            }
        }
        return outputDiagonalToGet.toArray(new String[0]);
    }
}
