import java.util.ArrayList;

//Converts Roman numerals to Numbers.
//The main idea is to check two numbers at a time
//if the first is smaller than we subtract its value from the total
//if it's the same size or larger we add its value to the total
//There are several additional checks to make sure it's a valid Roman Numeral
public class RomanToNumberConverter {

    public static String run(String input) throws Exception{

        ArrayList<Integer> valueList = convertRomanNumeralsToIntList(input);
        int total = calculateTotal(valueList);

        return String.valueOf(total);
    }

    private static ArrayList<Integer> convertRomanNumeralsToIntList(String romanNumerals) throws Exception {
        romanNumerals = romanNumerals.toUpperCase();
        ArrayList<Integer> valueList = new ArrayList<>();

        //iterate through the string add corresponding value to list
        for (char c : romanNumerals.toCharArray()) {

            if (c == 'I') {
                valueList.add(1);
            }
            else if (c == 'V') {
                valueList.add(5);
            }
            else if (c == 'X') {
                valueList.add(10);
            }
            else if (c == 'L') {
                valueList.add(50);
            }
            else if (c == 'C') {
                valueList.add(100);
            }
            else if (c == 'D') {
                valueList.add(500);
            }
            else if (c == 'M') {
                valueList.add(1000);
            }
            else {
                throw new Exception("Input includes non Roman Numeral Character");
            }
        }

        return valueList;
    }

    private static int calculateTotal(ArrayList<Integer> valueList) throws Exception {

        int total = 0;

        if(tooManyConsecutiveSymbolsCheck(valueList, 0,0,valueList.get(0))) {
            throw new Exception("invalid Roman Numeral");
        }

        //loop through number list and add or subtract values
        for (int i = 0; i < valueList.size(); i++) {

            //make sure we're not on the last index to avoid out of bounds errors
            if (i < valueList.size() - 1) {

                int curNum = valueList.get(i);
                int nextNum = valueList.get(i + 1);

                //checks invalid Roman numeral order
                if (!sizeCheck(curNum, nextNum)) {
                    throw new Exception("Invalid Roman Numeral");
                }

                //if the current number is smaller than the next, then subtract it from the total. Otherwise, add it
                if ( curNum < nextNum) {
                    total -= valueList.get(i);
                }
                else {
                    total += valueList.get(i);
                }
            }
            //we're on the last number. just add to total.
            else {
                total += valueList.get(i);
            }
        }

        return total;
    }

    private static boolean sizeCheck (int curNumber, int nextNumber) {
        if ( 10 * curNumber >= nextNumber) {
            return true;
        }
        else {
            return false;
        }
    }

    //recursive function to make sure Roman numeral is in a valid form.
    //It makes sure Tens place symbol never repeats more than 3 times.
    //makes sure 5 place value never repeats more than once.
    private static boolean tooManyConsecutiveSymbolsCheck(ArrayList<Integer> valueList,
                                                          int index, int consecutiveNumbersSeen, int previousNumber) {

        //if a fives place value has been seen more than once in a row return true
        if (String.valueOf(previousNumber).charAt(0) == '5' && consecutiveNumbersSeen == 2) {
            return true;
        }
        //if a number repeats more than 3 times return true
        if (consecutiveNumbersSeen == 4) {
            return true;
        }
        //if we reach the end of the list with no illegal repeats return false
        if (index >= valueList.size()) {
            return false;
        }

        if (previousNumber == valueList.get(index)){
            return tooManyConsecutiveSymbolsCheck(valueList, index + 1,
                    consecutiveNumbersSeen + 1, previousNumber );
        }
        else {
            return tooManyConsecutiveSymbolsCheck(valueList, index + 1, 0, valueList.get(index));
        }
    }
}
