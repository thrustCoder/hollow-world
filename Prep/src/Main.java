import java.io.IOException;

/**
 * Created by rpsin on 2/27/2016.
 */
public class Main
{
    public static void main(String[] arg)
    {
        System.out.println("Hollow world prep from local intelliJ!!");

        NumberMagic.printPrimeFactors(12);
    }

    private static void reverseLinkedList() {
        // create list
        LinkedListNode l11 = new LinkedListNode(1);
        LinkedListNode l12 = new LinkedListNode(3);
        LinkedListNode l13 = new LinkedListNode(5);
        LinkedListNode l14 = new LinkedListNode(7);
        l11.setNext(l12);
        l12.setNext(l13);
        l13.setNext(l14);
        System.out.println("--List--");
        LinkedList.print(l11);

        // merge lists
        LinkedListNode l21 = LinkedList.reverse(l11);
        System.out.println("--Reversed List--");
        LinkedList.print(l21);
    }

    private static void mergeSortedLinkedLists() {
        // Oracle interview question- merge sorted linked lists

        // create lists
        LinkedListNode l11 = new LinkedListNode(1);
        LinkedListNode l12 = new LinkedListNode(3);
        LinkedListNode l13 = new LinkedListNode(5);
        l11.setNext(l12);
        l12.setNext(l13);
        System.out.println("--List 1--");
        LinkedList.print(l11);

        LinkedListNode l21 = new LinkedListNode(2);
        LinkedListNode l22 = new LinkedListNode(4);
        LinkedListNode l23 = new LinkedListNode(6);
        LinkedListNode l24 = new LinkedListNode(7);
        l21.setNext(l22);
        l22.setNext(l23);
        l23.setNext(l24);
        System.out.println("--List 2--");
        LinkedList.print(l21);

        // merge lists
        LinkedListNode l31 = mergeSortedLinkedLists(l11, l21);
        System.out.println("--Merge List--");
        LinkedList.print(l31);
    }

    private static LinkedListNode mergeSortedLinkedLists(LinkedListNode l1, LinkedListNode l2) {
        // 1->3->5 2->4->6->8

        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }

        LinkedListNode l3 = l1;
        LinkedListNode currentL2 = l2;
        LinkedListNode currentL3 = l3;
        LinkedListNode prevL3 = currentL3;

        while (currentL2 != null && currentL3 != null) {
            if (currentL3.data() >= currentL2.data()) {
                // insert node
                LinkedListNode newNode = new LinkedListNode(currentL2.data());
                newNode.setNext(prevL3.next());
                prevL3.setNext(newNode);

                currentL2 = currentL2.next();
            }

            prevL3 = currentL3;
            currentL3 = currentL3.next();
        }

        if (currentL3 == null) {
            prevL3.setNext(currentL2);
        }

        return l3;
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
