package Task2;

/**
 * Task1.RandomNumberApp generates five random numbers using theads and sums them.
 */
public class RandomNumberApp {

    public static void main(String[] args){
        RandomNumberGenerator rng = new RandomNumberGenerator();

        Thread t1 = new Thread(rng);
        Thread t2 = new Thread(rng);
        Thread t3 = new Thread(rng);
        Thread t4 = new Thread(rng);
        Thread t5 = new Thread(rng);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        // main thread must wait for all threads to finish
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Sum of random Numbers: " + RandomNumberGenerator.getSum());
    }
}
