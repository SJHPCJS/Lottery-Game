import java.util.HashSet;
import java.util.Set;

/**
 * MySet:
 * A class to handle the complexity of the set operations.
 */
public class MySet {
    private Set<Integer> set;

    /**
     * Constructor method.
     */

    public MySet() {
        set = new HashSet<>();
    }

    /**
     * A method to print all the information in the set.
     */
    public void printSet() {
        System.out.println(set);
    }

    /**
     * A method to add information into the set.
     * @param number The number to be added.
     */
    public void addToSet(int number) {
        set.add(number);
    }

    /**
     * A method to check if the set is empty.
     * @return A boolean represents if the set is empty.
     */
    public boolean isSetEmpty() {
        return set.isEmpty();
    }

    /**
     * A method to get the cardinality of the set.
     * @return The size of the set.
     */
    public int getCardinality() {
        return set.size();
    }

    /**
     * A method to check if the number have already in the set.
     * @param number A number to be checked.
     * @return A boolean represents if the number is in the set.
     */
    public boolean isInSet(int number) {
        return set.contains(number);
    }

    /**
     * A method to get the intersection of two sets.
     * @param setB Another set to be operated.
     * @return The intersection set of two sets.
     */
    public MySet intersection(MySet setB) {
        MySet result = new MySet();
        result.set.addAll(this.set);
        result.set.retainAll(setB.set);
        return result;
    }
}
