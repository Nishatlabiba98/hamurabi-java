package hamurabi;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class hammurabi {
    Random rand = new Random();
    Scanner input = new Scanner(System.in);


    public static void main (String[]args) {
                new hammurabi().playGame();
    }

    void playGame() {
        int year = 1;
        int population = 100;
        int bushels = 2800;
        int acres = 1000;
        int landPrice = 19;
        int starved = 0;
        int harvested = 3000;
        int yieldPerAcre = 3;


        while(year <=10) {

            printSummary(year, starved, population,
                harvested, yieldPerAcre, bushels, acres, landPrice) ;

                int acresToBuy = getNumber("How many acres do you want to buy?");
                if (acresToBuy > 0) {
                    acres += acresToBuy;
                    bushels -= acresToBuy * landPrice;
                }
                if (acresToBuy ==0) {
                    int acresToSell = getNumber("How many acres do you want to sell?");
                    if (acresToSell > 0 && acresToSell <= acres) {
                        acres -= acresToSell;
                        bushels += acresToSell * landPrice;
                    }
                }

                int grainToFeed = getNumber ("How many bushels do you want to feed your people?");
                bushels -= grainToFeed;

                starved = starvationDeaths(population, grainToFeed);
                population -= starved;

                int acresToPlant = getNumber("How many acres do you want to plant with grain?");
                bushels -= acresToPlant / 2;
                harvested = harvest(acresToPlant);
                bushels += harvested;
                yieldPerAcre = (acresToPlant > 0) ? harvested / acresToPlant : 0;
                landPrice = newCostOfLand();
        year++;
        }
    }
        void printSummary(int year, int starved, int population, int harvested, int yieldPerAcre, int bushels, int acres, int landPrice) {

    System.out.println("O great Hammurabi!");
    System.out.println("You are in year " + year + " of your ten year rule.");
    System.out.println("In the previous year " + starved + " people starved to death.");
    System.out.println("The population is now " + population + ".");
    System.out.println("The city owns " + acres + " acres of land.");
    System.out.println("Land is currently worth " + landPrice + " bushels per acre.");
    }
public int starvationDeaths(int population, int bushelsFedToPeople) {
    int peopleFed = bushelsFedToPeople / 20;
    if (peopleFed >= population) {
        return 0;
    }
    return population - peopleFed;
    }
public int newCostOfLand() {
    return rand.nextInt(7) + 17; // Random price between 17 and 23
}

public int harvest(int acresPlanted) {
    int yield = rand.nextInt(6) + 1; // Random yield between 1 and 3 bushels per acre
    return acresPlanted * yield;
}
public int getNumber(String message) {
        while (true) {
            System.out.print(message);
            try {
                return input.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("\"" + input.next() + "\" isn't a number!");
            }
}
}
}