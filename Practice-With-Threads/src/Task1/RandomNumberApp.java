package Task1;

/**
 * Task1.RandomNumberApp generates five random numbers using theads and sums them.
 */
public class RandomNumberApp {

    public static void main(String[] args){
        RandomNumberGenerator rng1 = new RandomNumberGenerator("Gen1");
        RandomNumberGenerator rng2 = new RandomNumberGenerator("Gen2");
        RandomNumberGenerator rng3 = new RandomNumberGenerator("Gen3");
        RandomNumberGenerator rng4 = new RandomNumberGenerator("Gen4");
        RandomNumberGenerator rng5 = new RandomNumberGenerator("Gen5");

        Thread t1 = new Thread(rng1);
        Thread t2 = new Thread(rng2);
        Thread t3 = new Thread(rng3);
        Thread t4 = new Thread(rng4);
        Thread t5 = new Thread(rng5);

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

        int rndNum1 = rng1.getRandomNumber();
        int rndNum2 = rng2.getRandomNumber();
        int rndNum3 = rng3.getRandomNumber();
        int rndNum4 = rng4.getRandomNumber();
        int rndNum5 = rng5.getRandomNumber();

        int sum = rndNum1 + rndNum2 + rndNum3 + rndNum4 + rndNum5;

        System.out.println("Sum of random Numbers: " + sum);
    }
}
