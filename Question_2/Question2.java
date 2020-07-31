import java.io.*;
import java.util.*;

public class Question2 {

    public static HashMap<Character, Character> open = new HashMap<Character, Character>() {
            {
                put('{', '}');
                put('(', ')');
                put('[', ']');
                put('<', '>');
            }
        };

    public static void main(String[] args) {
        //String test = "{([]){()()}}";
        //String test = "([])<>]}";
        String test = "(<[{}({>)]>";
        Scanner scan = null;
        try {
            scan = new Scanner(System.in);

            String w = "Enter a string to test:";

            test = getInput(w, scan);
            if (matcher(test)) {
                System.out.println("The string is correct! There are no mismatched brackets.");
            }
        } catch (Exception e) {
            // blind catch
            System.out.println("Could not start scanner");
        }
        try {
            scan.close();
        } catch (Exception e) {
            //blind
        }
    }

    public static boolean matcher(String str) {
        Stack<Character> stack = new Stack<Character>();
        boolean complete = true;
        int m = -1;

        char c;
        for(int i=0; i < str.length(); i++) {
            c = str.charAt(i);
            switch (c) {
            case '(':
            case '[':
            case '{':
            case '<':
                stack.push(c);
                break;
            case ')':
            case ']':
            case '}':
            case '>':
                if ((m = match(stack, c)) == 0) {
                    stack.pop();
                } else {
                    complete = false;
                    if (m == 2) { i = str.length(); }
                }
                break;
            default:
                break;
            }
        }
        return complete;
    }

    public static int match(Stack<Character> stack, Character c) {
        if (stack.empty()) {
            System.out.printf("error at end: the close bracket '%c' does not have a corresponding opening bracket.%n", c);
            return 1;
        }
        if (!open.get(stack.peek()).equals(c)) {
            System.out.printf("error: '%c' does not match with '%c'.%n", c, stack.peek());
            return 2;
        }
        return 0;
    }

    public static String getInput(String msg, Scanner scan) {
        String in = new String();
        System.out.println(msg);
        try {
            in = scan.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid");
        }
        return in.toLowerCase();
    }
}
