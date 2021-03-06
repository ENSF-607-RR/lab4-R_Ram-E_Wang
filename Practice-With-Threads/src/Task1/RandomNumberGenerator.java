package Task1;

import java.math.*;

/**
 * Task1.RandomNumberGenerator generators are random number and store it.
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
        System.out.println("Random Number Generated by " + getName() + " is: " + getRandomNumber());
    }
}
