package service;

/**
 * Created by rpsin on 10/30/2016.
 */
public class BitManipulation {
    public static void swapBitiWithj()
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

    public static int updateBit(int num, int i, int v)
    {
        int mask = ~(1 << i);
        return (num & mask) | (v << i);
    }
}
