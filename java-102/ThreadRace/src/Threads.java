import java.util.ArrayList;

public class Threads implements Runnable{
    private final Object LOCK = new Object();
    ArrayList<Integer> evenNumbers = new ArrayList<Integer>();
    ArrayList<Integer> oddNumbers = new ArrayList<Integer>();
    ArrayList<Integer> Numbers = new ArrayList<Integer>();
    public Threads(ArrayList<Integer> numbers,ArrayList<Integer> oddNumbers,ArrayList<Integer> evenNumbers) {
        this.Numbers = numbers;
        this.evenNumbers = evenNumbers;
        this.oddNumbers = oddNumbers;
    }
    @Override
    public void run() {
        for (Integer number : Numbers) {
            if (number % 2 == 0) {
                synchronized (LOCK) {
                    evenNumbers.add(number);
                }
            } else {
                synchronized (LOCK) {
                    oddNumbers.add(number);
                }
            }
        }
    }

}
