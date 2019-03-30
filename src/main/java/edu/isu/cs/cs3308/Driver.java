package edu.isu.cs.cs3308;

import java.util.List;
import java.util.Scanner;

public class Driver {
    /**
     * Runs the program
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.print("Enter a sentence to check: ");
        Scanner in = new Scanner(System.in).useDelimiter("\\n");
        String input = in.next().toLowerCase();

        SpellCheckerImpl checker = new SpellCheckerImpl();
        List<String> answer = checker.check(input);

    }
}
