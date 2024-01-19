
import java.util.Random;
import java.util.Scanner;

/**
 * Lottery:
 * A class represent lottery game.
 * This class has methods to run the lottery, check the result, generate lottery numbers, let user choose their numbers, check the win for each week, and some tool methods.
 */
public class Lottery {

    private static final int LOTTERY_SIZE = 6;
    private int lotteryMax;
    private Player[] players;

    /**
     * A method to run the main part of the lottery game.
     * The method will let user(s) choose the number of players, the range of the lottery numbers, and how many weeks they want to play.
     * The method will run the lottery week by week after all the users choose their own lottery numbers.
     */
    public void runLottery() {
        System.out.println("Please choose the number of players:");
        int playerNumber;
        boolean ifNumValid = false;
        do {
            playerNumber = safeInputInt();
            if (playerNumber > 0) {
                ifNumValid = true;
            }else System.out.println("Please enter the number more than 0.  ");
        } while (!ifNumValid);
        players = new Player[playerNumber];
        boolean ifValid = false;
        boolean ifWeekValid = false;
        do {
            System.out.println("Please choose the range of the lottery numbers (greater than 6) ");
            lotteryMax = safeInputInt();
            if (lotteryMax >= 6) {
                ifValid = true;
            } else {
                System.out.println("Your choice is less than 6!");
            }
        } while (!ifValid);
        int weekNumber;
        do {
            System.out.println("Please enter how many week do you want to play");
            weekNumber = safeInputInt();
            if (weekNumber >= 1) {
                ifWeekValid = true;
            } else {
                System.out.println("Invalid input!");
            }
        } while (!ifWeekValid);

        for (int i = 0; i < playerNumber; i++) {
            System.out.println("**************************************");
            System.out.println("Welcome! Player" + (i + 1) + ".");
            System.out.println("Please enter your name:");
            String id = safeInputString();
            players[i] = new Player(id);
            players[i].setUserNumbers(chooseUserNumbers());
            int cost = players[i].getCost();
            cost += (2) * weekNumber;
            players[i].setCost(cost);
        }

        for (int j = 0; j < weekNumber; j++) {
            System.out.println("**************************************");
            System.out.println("Welcome to Week" + (j + 1) + "!");
            MySet lotteryNumbers = generateLotteryNumbers();

            System.out.println("Lottery Numbers of the Week:");
            lotteryNumbers.printSet();

            for (int i = 0; i < playerNumber; i++) {
                System.out.println("\n----------------------------------");
                System.out.println(players[i].getId());
                int win = players[i].getWin();
                win += check(lotteryNumbers, players[i].getUserNumbers());
                players[i].setWin(win);
            }
            boolean nextWeek = false;
            while (!nextWeek) {
                if (j+1 == weekNumber){
                    System.out.println("Please enter \"N\" to go to the check out menu.");
                }else {
                    System.out.println("Please enter \"N\" to go to next week.");
                }
                String choice = safeInputString();
                if (choice.equalsIgnoreCase("N")) {
                    nextWeek = true;
                } else {
                    System.out.println("Please enter valid input.");
                }
            }
        }

    }

    /**
     * A method to display the checkout menu.
     */
    public void checkOut() {
        System.out.println("********************Check Out******************");
        for (Player player : players) {
            System.out.println("\n----------------------------------");
            System.out.print("Player name: ");
            System.out.println(player.getId());
            System.out.println("You cost: " + player.getCost() + " pounds");
            System.out.println("You win: " + player.getWin() + " pounds");
            System.out.print("You profit " + (player.getWin() - player.getCost()) + " pounds");
            System.out.println("\n");
        }
    }

    /**
     * A method to generate the lottery number randomly for each week.
     * @return A set of weekly lottery numbers.
     */
    public MySet generateLotteryNumbers() {
        MySet lotteryNumbers = new MySet();
        Random random = new Random();
        while (lotteryNumbers.getCardinality() < LOTTERY_SIZE) {
            int number = random.nextInt(lotteryMax) + 1;
            lotteryNumbers.addToSet(number);
        }
        return lotteryNumbers;
    }

    /**
     * A method to let player choose their own lottery numbers.
     * @return A set of player's lottery number.
     */
    public MySet chooseUserNumbers() {
        MySet userNumbers = new MySet();
        System.out.println("Please enter your lottery numbers (each number should be between 1 and " + lotteryMax + "):");
        while (userNumbers.getCardinality() < LOTTERY_SIZE) {
            int number;
            do {
                System.out.println("Number " + (userNumbers.getCardinality() + 1) + ": ");
                number = safeInputInt();
                if (number < 1 || number > lotteryMax) {
                    System.out.println("Please enter a number between 1 and " + lotteryMax + ".");
                }
                if (userNumbers.isInSet(number)) {
                    System.out.println("You have already chosen this number.");
                }
            } while (number < 1 || number > lotteryMax);
            userNumbers.addToSet(number);
        }
        return userNumbers;
    }

    /**
     * A method to check how many matched numbers the player has and how much he/she wins.
     * The method will display the number of matched number(s).
     * @param lotteryNumbers The set of weekly lottery number.
     * @param userNumbers The set of the player's lottery number.
     * @return The money this player wins.
     */
    public int check(MySet lotteryNumbers, MySet userNumbers) {
        int matchedNumbers = countMatchedNumbers(lotteryNumbers, userNumbers);
        System.out.println("Your Numbers:");
        userNumbers.printSet();
        System.out.println("\nMatched Numbers: " + matchedNumbers);

        switch (matchedNumbers) {
            case 1 -> {
                System.out.println("You matched only 1 number. Sorry about that.");
                return 0;
            }
            case 2 -> {
                System.out.println("You matched only 2 numbers. Sorry about that.");
                return 0;
            }
            case 3 -> {
                System.out.println("You matched 3 numbers! You win 25 pounds this week!");
                return 25;
            }
            case 4 -> {
                System.out.println("You matched 4 numbers! You win 100 pounds this week!");
                return 100;
            }
            case 5 -> {
                System.out.println("You matched 5 numbers! You win 1,000 pounds this week!");
                return 1000;
            }
            case 6 -> {
                System.out.println("You matched all 6 numbers! You win 1,000,000 pounds this week!");
                return 1000000;
            }
            default -> {
                System.out.println("You have no matched numbers. Better luck next time!");
                return 0;
            }
        }
    }

    /**
     * A method to count the number of matched number(s).
     * @param lotteryNumbers The set of weekly lottery number.
     * @param userNumbers The set of the player's lottery number.
     * @return The number of matched number(s).
     */
    public int countMatchedNumbers(MySet lotteryNumbers, MySet userNumbers) {
        MySet matchedNumbers = lotteryNumbers.intersection(userNumbers);
        return matchedNumbers.getCardinality();
    }

    /**
     * A method to get a valid int input.
     * @return A valid int input.
     */
    public static int safeInputInt() {
        while (true) {
            System.out.print(">> ");
            Scanner scan = new Scanner(System.in);
            try {
                return Integer.parseInt(scan.nextLine());
            } catch (Exception e) {
                if (e instanceof NumberFormatException)
                    System.out.println("Invalid data type. Please input an integer.");
                else
                    System.out.println("Unacknowledged exception occurred. Please input again.");
            }
        }
    }

    /**
     * A method to get a valid String input.
     * @return A valid String input.
     */
    public static String safeInputString() {
        while (true) {
            System.out.print(">> ");
            Scanner scan = new Scanner(System.in);
            try {
                String str = scan.nextLine();
                if (!str.equals("")) return str;
                else System.out.println("Empty. Please input again.");
            } catch (Exception e) {
                System.out.println("Unacknowledged exception occurred. Please input again.");
            }
        }
    }

}