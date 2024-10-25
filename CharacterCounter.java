//=====================================
// Author:              Gail C
// Date Created:        10/20/2024
// Date Last Revised:   10/24/2024
//=====================================

import java.util.*;

public class CharacterCounter {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean using = true;

        //Menu loop
        while (using) 
        {
            //Display the menu
            System.out.println("          Menu        ");
            System.out.println("-------------------------");
            System.out.println("1. Start character counting");
            System.out.println("2. Exit");
            System.out.print("Choose an option (1 or 2): ");

            //Gets user's choice
            String choice = scanner.nextLine();

            //Action for the menu choice selected
            switch (choice) 
            {
                case "1":
                    //Takes input from the user
                    System.out.println("Enter a string:");
                    String input = scanner.nextLine().toUpperCase();

                    //Prints the sting to be computed
                    System.out.println("---------------------------");
                    System.out.println("String Entered: " + input);
                    System.out.println("---------------------------");

                    //Calls charCount method to handle character counting and display results
                    //using the user string input
                    charCalculater(input);
                    break;

                case "2":
                //Makes it so it will exit the menu loop
                    using = false;
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1 or 2.");
            }
        }

        scanner.close(); //Closes scanner
        System.out.println("Program exited.");
    }
    
    //=============METHODS===========================

    //Method that calculates and displays the char results
    private static void charCalculater(String input) 
    {
        //Stores the chars from the user string input in to a list
        int[] charCount = countChar(input);

        //Iniiates the count list that is corresponing the the ASCII chars
        List<Map.Entry<Character, Integer>> countList = buildCountList(charCount);

        //Nested Methods
        topChars(countList, 3);
        undetectedChars(countList);
        reversedString(input);
    }

    //Counts the chars insertied from the string input 
    private static int[] countChar(String input) 
    {
        //Each index in this array will represent an ASCII character.
        //The value at that index counts how many times that character 
        //appears in the input string.
        int[] charCount = new int[128]; //ASCII characters range from 0 to 127
        
        //For loop that iterates over each character c in the input string
        for (char c : input.toCharArray()) {
            //If the character is an ASCII character, this line increments the count 
            //at the index corresponding to the ASCII value of c
            if (c < 128) {
                charCount[c]++;
            }
        }
        return charCount;
    }

    //Makes the char and char count list sorting them from greatest to least counts
    private static List<Map.Entry<Character, Integer>> buildCountList(int[] charCount) 
    {
        //This list will hold the entries of characters and their respective counts.
        List<Map.Entry<Character, Integer>> countList = new ArrayList<>();

        //iterates through each index i of the charCount array.
        for (int i = 0; i < charCount.length; i++) {
            if (charCount[i] > 0) {
                // If the count is greater than zero, a new entry is created into the map
                countList.add(new AbstractMap.SimpleEntry<>((char) i, charCount[i]));
            }
        }

        //Sort the list character counts based on occurrences in descending order
        countList.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        return countList;
    }

    //Finds and prints the top three chars in the inputed string 
    private static void topChars(List<Map.Entry<Character, Integer>> countList, int topChars) 
    {
        //Display message
        System.out.println("Top three " + topChars + " characters:");

        //Ensures that if topChars is greater than the rest of the number of entries in countList
        for (int i = 0; i < Math.min(topChars, countList.size()); i++) 
        {
            //Retrives topChar
            Map.Entry<Character, Integer> entry = countList.get(i);

            //Displays topChar
            System.out.printf("Character: '%c' - Count: %d%n", entry.getKey(), entry.getValue());
        }
    }

    //Calculates the number of chars not detected the inputed string
    private static void undetectedChars(List<Map.Entry<Character, Integer>> countList) 
    {
        //Takes total nuimber of chars in the ASCII list(128) and subtracts it from the chars 
        //found in the input string
        int undetectedCount = 128 - countList.size();

        //Displays message
        System.out.println("Number of undetected ASCII characters: " + undetectedCount);
    }

    //Takes the input string and coverts it to the reverse
    private static void reversedString(String input) 
    {
        String reversedString = new StringBuilder(input).reverse().toString();

        //Displays message
        System.out.println("String Reversed: " + reversedString);
    }
}
