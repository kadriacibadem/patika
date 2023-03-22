import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> dividedNumbers = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> evenNumbers = new ArrayList<Integer>();
        ArrayList<Integer> oddNumbers = new ArrayList<Integer>();

        for (int i = 0;i<4;i++){
            dividedNumbers.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < 10000; i++) {
            dividedNumbers.get(i%4).add(i);
        }

        for(int i = 0;i<4;i++){
            Threads th1 = new Threads(dividedNumbers.get(i),oddNumbers,evenNumbers);
            th1.run();
        }

        System.out.println("Even Numbers:");
        for (Integer number : evenNumbers) {
            System.out.print(number+" ");
        }
        System.out.println("\nOdd Numbers:");
        for (Integer number : oddNumbers) {
            System.out.print(number+" ");
        }
        System.out.println("\n"+oddNumbers.size());
        System.out.println(evenNumbers.size());

    }

}