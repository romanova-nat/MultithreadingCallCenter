import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        CallCenter callCenter = new CallCenter();
        final int COUNT_OF_THREAD = 5; // количество потоков

        new Thread(() -> {
            try {
                callCenter.workToATS();
            } catch (InterruptedException e) {
                Thread.currentThread().isInterrupted();
            }
        }).start();

        ExecutorService threadPool = Executors.newFixedThreadPool(COUNT_OF_THREAD);

        for (int i = 0; i < COUNT_OF_THREAD; i++) {
            threadPool.submit(() -> {
                try {
                    callCenter.workToOperators();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
    }
}

