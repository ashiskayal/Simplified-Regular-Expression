import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Regular Expression : ");
        String RE = sc.next();

        String expressionFormat = "[[0-9]*[+*/-][0-9]*]*";
        Pattern pattern = Pattern.compile(expressionFormat);
        Matcher matcher = pattern.matcher(RE);

        if(matcher.matches()) {
            ExpressionTree exp = new ExpressionTree(RE);
            exp.make();
            exp.initializePrinting();
            System.out.println();
            exp.printParents();
        }



    }
}