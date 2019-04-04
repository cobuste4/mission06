package edu.isu.cs.cs3308;

import java.util.List;
import java.util.Scanner;

public class Driver {
    /**
     * Runs the program
     * @author Steve Coburn
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean noErrors = true;
        SpellCheckerImpl checker = new SpellCheckerImpl();

        System.out.print("Enter a sentence to check: ");
        Scanner in = new Scanner(System.in).useDelimiter("\\n");
        String input = in.next().toLowerCase();

        // Credit: https://stackoverflow.com/questions/4674850/converting-a-sentence-string-to-a-string-array-of-words-in-java
        String[] words = input.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].replaceAll("[^\\w]", "");
        }

        int counter = 1;
        for (String word : words) {
            List<String> answer = checker.check(word);

            if (answer.size() > 1) {
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < (answer.size()); i++) {
                    if (i < 6) sb.append(answer.get(i) + ", ");
                }

                String formatted = sb.substring(0, sb.length() - 2);

                if (counter == 1) System.out.println("Misspelled Words:");
                System.out.printf("%3d. %s: [%s]\r\n", counter, answer.get(0), formatted);
                noErrors = false;
                counter++;
            }
        }
        if (noErrors) System.out.println("No misspellings!");
    }
}
