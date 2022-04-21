import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class DriverHeap {

    public static void main(String[] args) throws IOException {

        File data = new File("src/data_random.txt");
        FileWriter output = new FileWriter("output.txt", false);
        Scanner textReader = new Scanner(data);
        MaxHeap<Integer> simpleHeap = new MaxHeap(); // default capcaity
        while(textReader.hasNext()){
            //add this to heap using initial method
            Integer i = textReader.nextInt();
            simpleHeap.add(i);
        }

        System.out.println("Heap built using add method: " + getFirst10(simpleHeap)); //call getFirst10()
        output.write("Heap built using add method: " + getFirst10(simpleHeap) + "\n"); //call getFirst10()
        System.out.println("Number of swaps in the heap creation: " );
        output.write("Number of swaps in the heap creation: " + "\n");
        for (int i = 0; i < 10; i++){
            simpleHeap.removeMax();
        }

        System.out.println ("Heap after 10 removals: " + getFirst10(simpleHeap));
        output.write("Heap after 10 removals: " + getFirst10(simpleHeap) + "\n");

        ArrayList<Integer> arr2 = new ArrayList<Integer>();
        File data2 = new File("src/data_random.txt");
        Scanner textReader2 = new Scanner(data2);
        MaxHeap<Integer> optimalHeap;
        while (textReader.hasNext()){
            //add this to heap using optimal
            int j = textReader2.nextInt();
            arr2.add(j); //adding element to the array
        }
        optimalHeap = new MaxHeap(arr2.toArray(new Integer[0]));

        System.out.println("Heap built using optimal method: " + getFirst10(optimalHeap)); //call getFirst10()
        output.write("Heap built using optimal method: " + getFirst10(optimalHeap) + "\n"); //call getFirst10()
        System.out.println("Number of swaps in the heap creation: ");
        output.write("Number of swaps in the heap creation: " + "\n");
        for (int i = 0; i < 10; i++){
            optimalHeap.removeMax();
        }
        System.out.println ("Heap after 10 removals: " + getFirst10(optimalHeap));
        output.write("Heap after 10 removals: " + getFirst10(optimalHeap) + "\n");
        output.close();
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
