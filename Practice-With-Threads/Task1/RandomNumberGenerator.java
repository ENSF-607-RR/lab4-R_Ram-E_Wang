import java.math.*;

/**
 * RandomNumberGenerator generators are random number and store it.
 */
public class RandomNumberGenerator implements Runnable{
    private int randomNumber;
    private String name;

    public RandomNumberGenerator(String name){
        this.name = name;
    }

    public int getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(int randomNumber) {
        this.randomNumber = randomNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        setRandomNumber((int)(Math.random() * 100 + 1));
    }
}
