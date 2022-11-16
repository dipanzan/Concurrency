import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimpleExecutor {
    private final Numbers numbers;
    private final int numOfThreads;
    public SimpleExecutor(int numOfThreads, int size) {
        this.numOfThreads = numOfThreads;
        this.numbers = Numbers.ofSize(size);
    }
    public static void main(String[] args) {
        int threads = Integer.parseInt(args[0]);
        int exp = Integer.parseInt(args[1]);
        int size = (int) Math.pow(10, exp) ;

        System.out.println("Threads: " + threads + ", size: " + size);

        SimpleExecutor se = new SimpleExecutor(threads, size);
        se.shuffleNumbers();
        se.sortWithExecutors();
        //st.displayNumbers();
    }
    private void sortWithExecutors() {
        ExecutorService es = Executors.newFixedThreadPool(numOfThreads);
        for (int i = 0; i < numOfThreads; i++) {
            es.execute(numbers::sortNumbers);
        }
        es.shutdown();

        try {
            es.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
    }
    private void shuffleNumbers() {
        numbers.shuffleNumbers();
    }
}