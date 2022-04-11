import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String args[]) {

        //create reader to take input
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        String input = "";

        //Program loop that will repeatedly take input
        while (!input.equalsIgnoreCase("quit")) {
            try {

                System.out.println("");
                System.out.println("Enter a number or a roman numeral under 4000");
                System.out.println("Enter \"quit\" to quit");

                input = reader.readLine();
                String response;

                if(!input.equalsIgnoreCase("quit")) {

                    //Determines if we're reading a number or a roman Numeral
                    if (Character.isAlphabetic(input.charAt(0))) {
                        input = input.toUpperCase();
                        response = RomanToNumberConverter.run(input);
                    }
                    else {
                        response = NumberToRomanConverter.run(input);
                    }

                    //output the conversion
                    System.out.println(input + " = " + response);
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid Input: mixed Numbers and Roman Numerals");
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

}
