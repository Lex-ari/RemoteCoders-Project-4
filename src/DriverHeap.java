import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DriverHeap {

    public static void main(String[] args) throws FileNotFoundException {

        File data = new File("src/data_random.txt");
        Scanner textReader = new Scanner(data);
        while (textReader.hasNext()){
            //add this to heap using intial method
        }

        System.out.println("Heap built using optimal method: "); //call getFirst10()
        System.out.println("Number of swaps in the heap creation: ");
        for (int i = 0; i < 10; i++){
            //do remove 10() times
        }

        System.out.println ("Heap after 10 removals: ");


        textReader = new Scanner(data);
        while (textReader.hasNext()){
            //add this to heap using optimal method
        }
        System.out.println("Heap built using optimal method: "); //call getFirst10()
        System.out.println("Number of swaps in the heap creation: ");
        for (int i = 0; i < 10; i++){
            // do remove() 10 times
        }
        System.out.println ("Heap after 10 removals: ");

    }


    public static String getFirst10(MaxHeap heap){
        // code that traverses through the array and retrives the first 10 elements.

        return "not finished";
    }
}
