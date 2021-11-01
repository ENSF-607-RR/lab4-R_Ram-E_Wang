package Task2;

import java.util.ArrayList;

/**
 * Task1.RandomNumberGenerator generators are random number and store it.
 */
public class RandomNumberGenerator implements Runnable{

    private static ArrayList<Integer> sum = new ArrayList<>();

    public static ArrayList<Integer> getSum() {
        return sum;
    }

    public void generateNumber(){
        int randomNumber = (int)(Math.random() * 100 + 1);
        System.out.println("Random Number Generated is: " + randomNumber);

        synchronized (this) {
            sum.add(randomNumber);
        }
    }

    @Override
    public void run() {
        generateNumber();
    }
}
