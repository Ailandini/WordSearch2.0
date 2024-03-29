import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class WordSearch {
    private String[] wordsToFind;
    private ArrayList<ArrayList<String>> wordSearchPuzzle = new ArrayList<>();

    WordSearch(String puzzleFilePath){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(puzzleFilePath));
            wordsToFind = bufferedReader.readLine().split(",");
            String nextLine = bufferedReader.readLine();
            ArrayList<String> puzzleRow;
            while(nextLine != null){
                puzzleRow = new ArrayList<>(Arrays.asList(nextLine.split(",")));
                wordSearchPuzzle.add(puzzleRow);

                nextLine = bufferedReader.readLine();
            }
        }
        catch(Exception e){
            System.out.println("Puzzle at path: " + puzzleFilePath + " does not exist.");
        }
    }

    public String[] getWordsToFind(){
        return wordsToFind;
    }

    public String printWordSearchPuzzle(){
        StringBuilder outputWordSearchPuzzle = new StringBuilder();
        for(int i=0 ; i < wordSearchPuzzle.size(); i++){
            for(int j=0; j < wordSearchPuzzle.size(); j++){
                outputWordSearchPuzzle.append(wordSearchPuzzle.get(i).get(j)).append(" ");
            }
            outputWordSearchPuzzle.replace(outputWordSearchPuzzle.length() - 1, outputWordSearchPuzzle.length(), "\n");
        }

        return outputWordSearchPuzzle.toString().substring(0, outputWordSearchPuzzle.length() -1);
    }

    protected int findWordInList(String[] listToFindString, String stringToFind, Boolean isBackward){
        if(stringToFind.length() == 0){
            return -1;
        }
        String stringOfListToFindString = String.join("",listToFindString);

        if(isBackward){
            StringBuilder mutableListToFindString = new StringBuilder(stringOfListToFindString);
            mutableListToFindString.reverse();
            String reverseStringOfListToFindString = mutableListToFindString.toString();
            int reverseIndex = (listToFindString.length - 1) - reverseStringOfListToFindString.indexOf(stringToFind);
            if(reverseIndex < listToFindString.length){
                return reverseIndex;
            }
            return -1;
        }
        else {
            return stringOfListToFindString.indexOf(stringToFind);
        }

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

    protected Boolean validatePuzzle(ArrayList<ArrayList<String>> puzzle){
        if(puzzle.size() == 0){
            return false;
        }
        for(ArrayList<String> row : puzzle){
            if(row.size() != puzzle.size()){
                return false;
            }
        }

        return true;
    }

    protected String checkAllWordsAgainstList(String[] remainingWordsToCheckFor, ArrayList<String> rowToCheck){
        String[] rowToCheckArray = rowToCheck.toArray(new String[0]);
        StringBuilder wordsFoundInList = new StringBuilder();
        for(String word : remainingWordsToCheckFor){
            if(findWordInList(rowToCheckArray, word, true) > -1 || findWordInList(rowToCheckArray, word, false) > -1){
                wordsFoundInList.append(word).append('\n');
            }
        }

        return wordsFoundInList.toString().substring(0, wordsFoundInList.length()-1);
    }

}
