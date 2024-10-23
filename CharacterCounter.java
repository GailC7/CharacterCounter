//=====================================
// Author:              Gail C
// Date Created:        10/19/2024
// Date Last Revised:   10/23/2024
//=====================================

import java.util.*;

public class CharacterCounter 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        boolean using = true;

        while (using) 
        {
            // Display the menu
            System.out.println("Menu:");
            System.out.println("-------------------------");
            System.out.println("1. Start character counting");
            System.out.println("2. Exit");
            System.out.print("Choose an option (1 or 2): ");
            System.out.println(" ");
            String choice = scanner.nextLine();

            switch (choice) 
            {
                case "1":
                    // Takes input from the user
                    System.out.println("Enter a string:");
                    String input = scanner.nextLine();
                    
                    //Prints the sting to be computed
                    System.out.println("---------------------------");
                    System.out.println("String Entered: " + input);
                    System.out.println("---------------------------");

                    // Count occurrences of each ASCII character
                    int[] charCount = new int[128]; // ASCII characters range from 0 to 127

                    //Loops through the input string to count the number of charaters
                    for (char c : input.toCharArray()) 
                    {
                        if (c < 128) { // Only consider ASCII characters
                            charCount[c]++;
                        }
                    }

                    // Create a list of character counts with corresponding characters
                    List<Map.Entry<Character, Integer>> countList = new ArrayList<>(); //count array

                    //Loop for reading each charater and adding them to a list to be counted,
                    for (int i = 0; i < charCount.length; i++) 
                    {
                        if (charCount[i] > 0) 
                        {
                            countList.add(new AbstractMap.SimpleEntry<>((char) i, charCount[i]));
                        }
                    }

                    // Sort the list character counts based on occurrences in descending order
                    countList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

                    // Print top three characters that are used the most in the input string
                    System.out.println("Top three characters:");
                    for (int i = 0; i < Math.min(3, countList.size()); i++) 
                    {
                        Map.Entry<Character, Integer> entry = countList.get(i);
                        System.out.printf("Character: '%c' - Count: %d%n", entry.getKey(), entry.getValue());
                    }

                    // Calculate and print undetected characters from the input string
                    int undetectedCount = 128 - countList.size();
                    System.out.println("Number of undetected ASCII characters: " + undetectedCount);

                    // Print the string in reverse order
                    String reversedString = new StringBuilder(input).reverse().toString();
                    System.out.println("String Reversed: " + reversedString);
                    break;

                case "2":
                    using = false;
                    break;

                default:
                    //Invalid option handler
                    System.out.println("Invalid option. Please choose 1 or 2.");
            }
        }

        scanner.close(); //Closes scanner

        System.out.println("Program exited.");
    }//End Main
}

