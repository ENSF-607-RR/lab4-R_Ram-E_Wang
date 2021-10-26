package Task2;

/**
 * Task1.RandomNumberGenerator generators are random number and store it.
 */
public class RandomNumberGenerator implements Runnable{

    private static int sum = 0;

    public static int getSum() {
        return sum;
    }

    @Override
    public void run() {
        int randomNumber = (int)(Math.random() * 100 + 1);
        System.out.println("Random Number Generated is: " + randomNumber);

        synchronized (this){
            sum += randomNumber;
        }
    }
}
