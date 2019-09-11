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
        switch(direction){
            case "forwardH":
                for(int i=1; i < stringToFind.length(); i++){
                    output.append("(").append(startColOfWord+i).append(",").append(startRowOfWord).append("),");
                }
                break;
            case "backwardH":
                for(int i=1; i < stringToFind.length(); i++){
                    output.append("(").append(startColOfWord-i).append(",").append(startRowOfWord).append("),");
                }
                break;
            case "forwardV":
                for(int i=1; i < stringToFind.length(); i++){
                    output.append("(").append(startColOfWord).append(",").append(startRowOfWord+i).append("),");
                }
                break;
            case "backwardV":
                for(int i=1; i < stringToFind.length(); i++){
                    output.append("(").append(startColOfWord).append(",").append(startRowOfWord-i).append("),");
                }
                break;
            case "forwardDLH":
                for(int i=1; i < stringToFind.length(); i++){
                    output.append("(").append(startColOfWord+i).append(",").append(startRowOfWord-i).append("),");
                }
                break;
            case "backwardDLH":
                for(int i=1; i < stringToFind.length(); i++){
                    output.append("(").append(startColOfWord-i).append(",").append(startRowOfWord+i).append("),");
                }
                break;
            case "forwardDHL":
                for(int i=1; i < stringToFind.length(); i++){
                    output.append("(").append(startColOfWord+i).append(",").append(startRowOfWord+i).append("),");
                }
                break;
            case "backwardDHL":
                for(int i=1; i < stringToFind.length(); i++){
                    output.append("(").append(startColOfWord-i).append(",").append(startRowOfWord-i).append("),");
                }
                break;
            default:
                return "Direction Provided is Invalid.";
        }

        return output.toString().substring(0, output.length() - 1);
    }

}
