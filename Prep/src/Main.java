import java.io.IOException;

/**
 * Created by rpsin on 2/27/2016.
 */
public class Main
{
    public static void main(String[] arg)
    {
        System.out.println("Hollow world prep from local intelliJ!!");

        swapBitiWithj();
    }

    private static void swapBitiWithj()
    {
        // Q: https://www.careercup.com/question?id=1941662
        int i=0, j=2;
        int a=4;
        int ithBit = (a >> i) & 1;
        int jthBit = (a >> j) & 1;

        a = updateBit(a, j, ithBit);
        a = updateBit(a, i, jthBit);

        System.out.println(a); // should be 1
    }

    private static int updateBit(int num, int i, int v)
    {
        int mask = ~(1 << i);
        return (num & mask) | (v << i);
    }

    private static final void parseFile()
    {
        // Q: https://www.careercup.com/question?id=5695752246394880
        try
        {
            FileParser parser = new FileParser();
            parser.parseFile("D:\\code\\github\\hollow-world\\Prep\\static\\FileParserFile");

            System.out.println("employee.name: " + parser.getElement("employee.name"));
            System.out.println("employee.address.houseno: " + parser.getElement("employee.address.houseno"));
            System.out.println("employee.address.location.state: " + parser.getElement("employee.address.location.state"));
            System.out.println("employee.manager: " + parser.getElement("employee.manager"));
        }
        catch (IOException e)
        {
            // do nothing here
        }
    }
}
