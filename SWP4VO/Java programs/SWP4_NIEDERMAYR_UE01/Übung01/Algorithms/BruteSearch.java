package Algorithms;

public class BruteSearch {

    public static boolean search(String text, String pattern, int startPosition) {
        //check if the parameters are valid
        if (text.length() != 0
                && pattern.length() != 0
                && startPosition >= 0
                && startPosition < text.length()) {

            int lengthOfText = text.length();
            int lengthOfPattern = pattern.length();

            //actual brute force search algorithm
            for (int i = startPosition; i <= (lengthOfText - lengthOfPattern) ; i++){

                int j;

                for ( j = 0 ; j < lengthOfPattern; j++){

                    //if the positions do not match, break
                    if( text.charAt(i + j) != pattern.charAt(j)) {
                        break;
                    }
                }

                //j went through the whole pattern, so it matches
                if( j == lengthOfPattern ) {
                    return true;
                }
            }

        } else {
            System.out.println("One of the parameters is invalid!");
        }

        return false;
    }

}

