package Task3;

import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Task1.RandomNumberApp generates five random numbers using theads and sums them.
 */
public class RandomNumberApp {

    // the thread pool
    private ExecutorService pool;

    public RandomNumberApp(int poolSize){
        // create the executor service with fixed pool size
        pool = Executors.newFixedThreadPool(poolSize);
    }

    public void run(){
        // execute 5 tasks on the pool
        for (int i = 0; i < 5; i++){
            pool.execute(new RandomNumberGenerator());
        }
        pool.shutdown();

        // wait till all tasks complete
        while(!pool.isTerminated()){}

    }

    public static void main(String[] args){
        RandomNumberApp app = new RandomNumberApp(5);
        app.run();

        System.out.println("Sum of random Numbers: " + RandomNumberGenerator.getSum());
    }
}
