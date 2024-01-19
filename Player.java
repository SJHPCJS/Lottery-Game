/**
 * Player:
 * A class represents a player.
 * A player will have its ID, cost, win, and a set of their numbers.
 */
public class Player {
    private final String id;
    private int cost;
    private int win;
    private MySet UserNumbers;

    /**
     * Constructor method.
     * @param id The player's ID.
     */
    public Player(String id) {
        this.id = id;
        cost = 0;
        win = 0;
        UserNumbers = null;
    }

    /**
     * Get the ID of the player.
     * @return The ID of the player.
     */
    public String getId() {
        return id;
    }

    /**
     * Get the cost of the player.
     * @return The cost of the player.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Set the cost of the player.
     * @param cost The cost of the player.
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Get the win of the player.
     * @return The win of the player.
     */
    public int getWin() {
        return win;
    }
    /**
     * Set the win of the player.
     * @param win The win of the player.
     */
    public void setWin(int win) {
        this.win = win;
    }

    /**
     * Get the set of lottery numbers of the player.
     * @return The set of lottery numbers of the player.
     */

    public MySet getUserNumbers() {
        return UserNumbers;
    }
    /**
     * Set the set of lottery numbers of the player.
     * @param  userNumbers Set of lottery numbers of the player.
     */
    public void setUserNumbers(MySet userNumbers) {
        UserNumbers = userNumbers;
    }
}
