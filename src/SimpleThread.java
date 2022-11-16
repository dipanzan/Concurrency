import java.util.ArrayList;
import java.util.List;

public class SimpleThread {
    private final Numbers numbers;
    private final int numOfThreads;

    public SimpleThread(int numOfThreads, int size) {
        this.numOfThreads = numOfThreads;
        this.numbers = Numbers.ofSize(size);
    }
    public static void main(String[] args) {
        int threads = Integer.parseInt(args[0]);
        int exp = Integer.parseInt(args[1]);
        int size = (int) Math.pow(10, exp) ;

        System.err.println("Threads: " + threads + ", size: " + size);

        SimpleThread st = new SimpleThread(threads, size);
        st.shuffleNumbers();
        st.sortWithThreads();
        //st.displayNumbers();
    }
    private void sortWithThreads() {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < numOfThreads; i++) {
            Thread t  = new Thread(numbers::sortNumbers);
            System.err.println(t.getName() + " created: ");
            threads.add(t);
        }
        for (Thread t : threads) {
            t.start();
        }
    }
    private void shuffleNumbers() {
        numbers.shuffleNumbers();
    }
}
