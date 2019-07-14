import java.util.*;

public class DivisibleRuler {
    public String divisibleRuleBaseTen(int n) {
        ArrayList<Integer> remainders = new ArrayList<>();
        int baseTen = 1;
        int counter = 0;
        String phrase = "The sum of\n";
        while (true) {
            int remainder = baseTen % n;
            if (remainders.contains(remainder) || remainder == 0) {
                break;
            }
            counter++;
            remainders.add(remainder);
            phrase += counter + " digit times " + remainder + "\n";
            baseTen *= 10;
        }
        phrase += "and repeatedly until all the digits are covered must be divisible by " + n + ".";
        return phrase;
    }

    public static void main(String[] args) {
        DivisibleRuler divisibleRuler = new DivisibleRuler();
        Scanner input = new Scanner(System.in);
        System.out.println("Type the number to get the divisible rule.");
        int n = input.nextInt();
        System.out.println("The divisible rule is:\n" + divisibleRuler.divisibleRuleBaseTen(n));
        input.close();
    }
}