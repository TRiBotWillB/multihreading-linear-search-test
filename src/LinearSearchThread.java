import java.util.ArrayList;

public class LinearSearchThread extends Thread {

    private int array[];
    private int startIndex, endIndex, searchKey;

    public int result;
    public boolean foundResult = false;

    public LinearSearchThread(int array[], int startIndex, int endIndex, int searchKey) {
        this.array = array;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.searchKey = searchKey;
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();

        for(int i = startIndex; i <= endIndex; i++) {
            //Could be set from main thread
            if(foundResult == true) break;

            if(array[i] == searchKey) {
                result = array[i];
                foundResult = true;
                break;
            }
        }

        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000; // Calculate the run time in ms

        System.out.println("MultithreadedSearch took: " + duration + " ms to complete");
    }
}
