/**
 * Menu:
 * A class represents the menu,
 * The class has several methods to show the introduction and start a new game.
 */
public class Menu {

    public static void main(String[] args) {
        Menu menu = new Menu();
        boolean ifStop = true;
        while (ifStop) {
            System.out.println("----------------------------------------");
            System.out.println("                Welcome!                ");
            System.out.println("----------------------------------------");
            System.out.println("I. Introduction.");
            System.out.println("S. Start a game.");
            System.out.println("E. Exit the game.");
            System.out.println("Please enter your choice");
            boolean ifChoiceValid = false;
            while (!ifChoiceValid) {
                String choice = Lottery.safeInputString();
                switch (choice) {
                    case "i", "I" -> {
                        menu.intro();
                        ifChoiceValid = true;
                    }
                    case "s", "S" -> {
                        menu.startGame();
                        ifChoiceValid = true;
                    }
                    case "E", "e" -> {
                        System.out.println("Thank you for playing!");
                        System.exit(0);
                    }

                    default -> System.out.println("Please enter valid choice!");

                }
            }
            System.out.println("Do you want to go back to menu? Yes(y)/No(n)");
            boolean ifBackValid = false;
            while (!ifBackValid) {
                String ifBack = Lottery.safeInputString();
                switch (ifBack) {
                    case "y", "Y" -> ifBackValid = true;
                    case "n", "N" -> {
                        ifBackValid = true;
                        ifStop = false;
                    }
                    default -> System.out.println("Please enter valid choice!");
                }
            }

        }
    }

    /**
     * A method to display the introduction.
     */
    public void intro() {
        System.out.println("This is a Lottery Game.");
        System.out.println("You can choose the number of users, the range of lottery numbers, and how many weeks do you want to play.");
        System.out.println("You should choose your own lottery numbers to keep it during the game(2 pounds each week).");
        System.out.println("Each week the system will provide 6 random number as the lottery numbers of this week.");
        System.out.println("Each week the system will check the winning situation and display the information.");
        System.out.println("After weeks of game, the system wil should a check out menu to display the result of all the users.");
        System.out.println();
        System.out.println("Winning rules:");
        System.out.println("Match 0,1,2 number(s) -> 0 pound");
        System.out.println("Match 3 numbers -> 25 pounds");
        System.out.println("Match 4 numbers -> 100 pounds");
        System.out.println("Match 5 numbers -> 1,000 pounds");
        System.out.println("Match 6 numbers -> 1,000,000 pounds");
        System.out.println();
        System.out.println("Have fun!");
    }

    /**
     * A method to start a new game.
     */
    public void startGame() {
        Lottery lottery = new Lottery();
        lottery.runLottery();
        lottery.checkOut();
    }
}
