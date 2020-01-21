import java.util.ArrayList;

public class LinearSearchThread extends Thread {

    private int array[];
    private int startIndex, endIndex, searchKey;

    public int result;
    public boolean foundResult = false;

    public LinearSearchThread(int array[], int startIndex, int endIndex, int searchKey, String threadName) {
        this.array = array;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.searchKey = searchKey;

        this.setName(threadName);
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();

        for(int i = startIndex; i <= endIndex; i++) {
            //Could be set from main thread
            if(foundResult == true) {
                break;
            }
            if(array[i] == searchKey) {
                result = array[i];
                foundResult = true;
                break;
            }
        }

        long endTime = System.currentTimeMillis();

        long duration = (endTime - startTime); // Calculate the run time in ms

        System.out.println(this.getName() + " took: " + duration + " ms to complete");
    }
}
