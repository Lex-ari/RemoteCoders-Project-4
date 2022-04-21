import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class DriverHeap {

    public static void main(String[] args) throws IOException {
        File dataSorted = new File("src/data_sorted.txt");
        File dataRandom = new File("src/data_random.txt");
        File classExample1 = new File("src/class_example_1.txt");
        File classExample2 = new File("src/class_example_2.txt");
        File classExample3 = new File("src/class_example_3.txt");
        File outputtxt = new File("output.txt");
        File classExampleTestingtxt = new File("class_example_testing.txt");
        doHeapProcedures(dataSorted, outputtxt);
        doHeapProcedures(dataRandom, outputtxt);
        doHeapProcedures(classExample1, classExampleTestingtxt);
        doHeapProcedures(classExample2, classExampleTestingtxt);
        doHeapProcedures(classExample3, classExampleTestingtxt);

    }

    public static String getFirst10(MaxHeap heap){
        Comparable[] workingArray = heap.toArray();
        String returnString = "";
        for (int i = 1; i <= 10; i++){
            if (workingArray[i] != null) {
                returnString += workingArray[i];
                if (i < 10) {
                    returnString += " ";
                }
            }
        }
        returnString += "...";
        return returnString;
    }

    public static void doHeapProcedures(File inputFile, File outputFile) throws IOException {
        File data = inputFile;
        FileWriter output = new FileWriter(outputFile, true);
        Scanner textReader = new Scanner(data);
        output.write("\n=====================================================================\n");
        output.write("File: " + inputFile.getPath() + "\n\n");

        MaxHeap<Integer> simpleHeap = new MaxHeap(); // default capcaity
        while(textReader.hasNext()){
            //add this to heap using initial method
            Integer i = textReader.nextInt();
            simpleHeap.add(i);
        }
        output.write("Heap built using add method: " + getFirst10(simpleHeap) + "\n"); //call getFirst10()
        output.write("Number of swaps in the heap creation: " + simpleHeap.getSwaps() + "\n");
        for (int i = 0; i < 10; i++){
            simpleHeap.removeMax();
        }
        output.write("Heap after 10 removals: " + getFirst10(simpleHeap) + "\n");

        ArrayList<Integer> arr2 = new ArrayList<Integer>();
        textReader = new Scanner(data);
        MaxHeap<Integer> optimalHeap;
        while (textReader.hasNext()){
            //add this to heap using optimal
            int j = textReader.nextInt();
            arr2.add(j); //adding element to the array
        }
        optimalHeap = new MaxHeap(arr2.toArray(new Integer[0]));

        output.write("Heap built using optimal method: " + getFirst10(optimalHeap) + "\n"); //call getFirst10()
        output.write("Number of swaps in the heap creation: " + optimalHeap.getSwaps() + "\n");
        for (int i = 0; i < 10; i++){
            optimalHeap.removeMax();
        }
        output.write("Heap after 10 removals: " + getFirst10(optimalHeap) + "\n");
        output.write("\n=====================================================================\n");
        output.close();
    }
}
