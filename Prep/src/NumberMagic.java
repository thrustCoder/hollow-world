import java.util.ArrayList;

/**
 * Created by rpsin on 10/12/2016.
 */
public class NumberMagic
{
    // Print factor multipliers without repitition
    // https://www.careercup.com/page?pid=linkedin-interview-questions
    public static void printPrimeFactors(int n)
    {
        System.out.println(n);
        ArrayList<Integer> factorsToPrune = new ArrayList<>((int)n/2);
        for (int i = 1; i < n/2; i++)
        {
            if (factorsToPrune.contains(i))
            {
                continue;
            }

            if (n % i == 0)
            {
                System.out.println(i + " * " + n/i);
                factorsToPrune.add(n/i);
            }
        }
    }
}
