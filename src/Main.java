import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<LinearSearchThread> threads = new ArrayList<>();

    private static int array[];
    private static int searchKey = 15454;

    private static int numberOfThreads = 1;

    public static void main(String[] args) {
        numberOfThreads =  Runtime.getRuntime().availableProcessors();

        userInput();

        startThreads();
    }

    public static void userInput() {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter array length: ");

       populateArray(s.nextInt());

    }

    public static void populateArray(int arrayLength) {
        array = new int[arrayLength];


        // Populate array with random ints
        for(int i = 0; i < array.length; i++) {
            array[i] = randomInt(0, 10000);
        }

        // Put the Search Number at the end position ( We all ready know where it is, but only used for the sake of testing )
        array[array.length - 1] = searchKey;

    }

    public static int randomInt(int min, int max) {
        return (int) (Math.random() * ((max-min) + 1)) + min;
    }

    public static void startThreads() {
        // Number of entries to be checked by each thread
        int sectionNum = array.length / numberOfThreads;

        LinearSearchThread t;

        for(int i = 0; i < numberOfThreads; i++) {
            if(i == 1) {
                t = new LinearSearchThread(array, 0, sectionNum - 1, searchKey, "Thread " + i);

            } else {
                t = new LinearSearchThread(array,i * sectionNum, ((i + 1) * sectionNum) - 1, searchKey, "Thread " + i);

            }
            threads.add(t);
            t.start();
        }

        waitResult();
    }

    public static void waitResult() {
        boolean loop = true;
        while(loop) {
            for(LinearSearchThread t : threads) {
                if(t.foundResult) {
                    System.out.println(t.getName()+ " found: " + t.result);

                    stopThreads();
                    loop = false;
                }
            }
        }
    }

    public static void stopThreads() {
        for(LinearSearchThread t : threads) {
            t.foundResult = true;
        }
    }
}
