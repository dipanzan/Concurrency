import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Numbers {
    private static final int MIN = -1000;
    private static final int MAX = 1000;
    private final int size;
    private List<Integer> numbers;

    private Numbers(int size) {
        this.size = size;
        this.numbers = new ArrayList<>(size);
        generateNumbers();
    }

    public static Numbers ofSize(int size) {
        return new Numbers(size);
    }

    public void sortNumbers() {
        synchronized (this) {
            System.err.println("\n" + "---- Sorting Start ----");

            String threadName = Thread.currentThread().getName();
            long start = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
//            System.out.println(threadName + " started sorting at: " + start + "s");

            // critical part
            Collections.sort(numbers);

            long end = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
            long elapsedTime = end - start;
            System.err.println(threadName + " finished sorting at: " + elapsedTime + "s");

            System.err.println("---- Sorting Finished ----");
        }
    }

    public void shuffleNumbers() {
        Collections.shuffle(numbers);
    }

    public synchronized void displayNumbers() {
        System.err.print("Displaying Numbers: ");
        numbers.forEach(e -> System.out.print(e + ", "));
        System.err.println();
    }

    private void generateNumbers() {
        for (int i = 0; i < size; i++) {
            int randomNumber = getRandomNumber();
            numbers.add(randomNumber);
        }
    }
    private int getRandomNumber() {
        return ThreadLocalRandom.current().nextInt(MIN, MAX);
    }
}