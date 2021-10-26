package Task3;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    }

    public static void main(String[] args){
        RandomNumberApp app = new RandomNumberApp(5);
        app.run();

        System.out.println("Sum of random Numbers: " + RandomNumberGenerator.getSum());
    }
}
