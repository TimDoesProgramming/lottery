import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        int win = 1;
        int lastWinIndex = 0;
        int currentWinIndex = 0;
        int totalWins = 0;
        int totalOfWinIndex = 0;
        ArrayList<Integer> numberOfRunsForAWin = new ArrayList<>();

        for(int i = 1; i<1000000000; i++) {
            //generates a random number between 1 and 625, origin is inclusive, bound is exclusive
            int randomNum = rand.nextInt(1,626);
            if(randomNum == win) {
                currentWinIndex = i;
                totalWins++;
                totalOfWinIndex += currentWinIndex -lastWinIndex;
                lastWinIndex = currentWinIndex;
            }
        }
        averageWinIndex(totalOfWinIndex, totalWins);

    }
    public static void averageWinIndex(int totalOfWinIndex, int totalWins) {
        double averageWinIndex = (double) totalOfWinIndex / totalWins;
        System.out.println("Average Win Index: " + averageWinIndex);
        System.out.println("Total Wins: " + totalWins);
    }
}