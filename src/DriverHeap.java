import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class DriverHeap {

    public static void main(String[] args) throws FileNotFoundException {

        File data = new File("src/data_random.txt");
        Scanner textReader = new Scanner(data);
        MaxHeap<Integer> simpleHeap = new MaxHeap(); // default capcaity
        while(textReader.hasNext()){
            //add this to heap using initial method
            Integer i = textReader.nextInt();
            simpleHeap.add(i);
        }

        System.out.println("Heap built using optimal method: " + getFirst10(simpleHeap)); //call getFirst10()
        System.out.println("Number of swaps in the heap creation: " );
        for (int i = 0; i < 10; i++){
            simpleHeap.removeMax();
        }

        System.out.println ("Heap after 10 removals: ");

        //sorted data
        ArrayList<Integer> arr2 = new ArrayList<Integer>();
        File data2 = new File("src/data_sorted.txt");
        Scanner textReader2 = new Scanner(data2);
        while (textReader.hasNext()){
            //add this to heap using optimal
            int j = textReader2.nextInt();
            arr2.add(j); //adding element to the array
        }

        System.out.println("Heap built using optimal method: "); //call getFirst10()
        System.out.println("Number of swaps in the heap creation: ");
        for (int i = 0; i < 10; i++){
            // do remove() 10 times
        }
        System.out.println ("Heap after 10 removals: ");

    }

    public static String getFirst10(MaxHeap heap){
        Comparable[] workingArray = heap.toArray();
        String returnString = "";
        for (int i = 1; i <= 10; i++){
            returnString += workingArray[i];
            if (i < 10){
                returnString += " ";
            }
        }
        return returnString;
    }
}
