import java.util.ArrayList;
import java.util.List;

//Converts Numbers to Roman Numeral String
//the main idea is to check each didget from smallest value to largest
// i.e. for 123. we deal with 3 then 2 then 1.
// depending on the value we add the correct type and order of corresponding Roman numerals
public class NumberToRomanConverter {

    public static String run(String input) throws Exception, NumberFormatException {

        StringBuilder stringBuilder = new StringBuilder();
        List<Integer> inputNumbers = new ArrayList<>();
        List<String> romanNumeralsTens = initializeRomanTens();
        List<String> romanNumeralsFives = initializeRomanFives();


        //Converts the input to an Int list separated by tens place
        for (Character c : input.toCharArray()) {
            inputNumbers.add(0, Integer.parseInt(c.toString()));
        }

        //Determines if input is too large
        if (input.length() > 3) {
            if (inputNumbers.get(inputNumbers.size() - 1) >= 4
                    ||  input.length() > 4) {
                throw new Exception("number is too large");
            }
        }


        //reads input starting from ones place and inserts correct roman numeral
        for (int i = 0; i < inputNumbers.size(); i++) {

            if (inputNumbers.get(i) == 9) {
                stringBuilder.insert(0, romanNumeralsTens.get(i + 1));
                stringBuilder.insert(0, romanNumeralsTens.get(i));
            }

            else if (inputNumbers.get(i) >= 5) {
                int numRomExtra =  inputNumbers.get(i) % 5;

                for (int j = 0; j < numRomExtra; j++) {
                    stringBuilder.insert(0, romanNumeralsTens.get(i));
                }
                stringBuilder.insert(0, romanNumeralsFives.get(i));
            }

            else if (inputNumbers.get(i) == 4) {
                stringBuilder.insert(0, romanNumeralsFives.get(i));
                stringBuilder.insert(0, romanNumeralsTens.get(i));
            }

            else if (inputNumbers.get(i) >= 0) {
                for (int j = 0; j < inputNumbers.get(i); j++) {
                    stringBuilder.insert(0, romanNumeralsTens.get(i));
                }
            }
            else {
                throw new Exception("Invalid Input");
            }

        }


        return stringBuilder.toString();
    }

    private static List<String> initializeRomanTens(){
        List<String> romTens = new ArrayList<>();
        romTens.add("I");
        romTens.add("X");
        romTens.add("C");
        romTens.add("M");

        return romTens;
    }

    private static List<String> initializeRomanFives(){
        List<String> romFives = new ArrayList<>();
        romFives.add("V");
        romFives.add("L");
        romFives.add("D");

        return romFives;
    }


}
