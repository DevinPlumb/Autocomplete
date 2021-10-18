/* *****************************************************************************
 *  Name:    Devin Plumb
 *  NetID:   dplumb
 *  Precept: P06
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description:  Able to return either the first or last key in an array equal
 *                to the search key.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Comparator;

// CITATION: Modeled off of BinarySearch, from the course textbook p. 9
public class BinarySearchDeluxe {

    // Returns the index of the first key in a[] that equals the search key,
    // or -1 if no such key.
    public static <Key> int
    firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null)
            throw new IllegalArgumentException("null argument");
        int returnValue = -1;
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int comparison = comparator.compare(key, a[mid]);
            if (comparison < 0) hi = mid - 1;
            else if (comparison > 0) lo = mid + 1;
            else {
                returnValue = mid;
                hi = mid - 1;
            }
        }
        return returnValue;
    }

    // Returns the index of the last key in a[] that equals the search key,
    // or -1 if no such key.
    public static <Key> int
    lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null)
            throw new IllegalArgumentException("null argument");
        int returnValue = -1;
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int comparison = comparator.compare(key, a[mid]);
            if (comparison < 0) hi = mid - 1;
            else if (comparison > 0) lo = mid + 1;
            else {
                returnValue = mid;
                lo = mid + 1;
            }
        }
        return returnValue;
    }

    // unit testing (required)
    // code largely borrowed from Term.java
    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]); // number of terms
        int maxLength = Integer.parseInt(args[1]); // maximum length of any term
        int r = Integer.parseInt(args[2]); // lexicographic limit

        // Store n random terms in an array.
        Term[] array = new Term[n];
        for (int i = 0; i < array.length; i++) {
            StringBuilder string = new StringBuilder();
            int j = StdRandom.uniform(1, maxLength);
            for (int k = 0; k < j; k++) {
                int c = StdRandom.uniform(97, 123);
                string.append((char) c);
            }
            array[i] = new Term(string.toString(),
                                (long) StdRandom.uniform(n + 1));
        }
        Comparator<Term> cmp = Term.byPrefixOrder(r);
        Arrays.sort(array, cmp);

        boolean found = false;
        while (!found) {
            StringBuilder string = new StringBuilder();
            int j = StdRandom.uniform(1, maxLength);
            for (int k = 0; k < j; k++) {
                int c = StdRandom.uniform(97, 123);
                string.append((char) c);
            }
            Term key = new Term(string.toString(),
                                (long) StdRandom.uniform(n + 1));
            if (firstIndexOf(array, key, cmp) >= 0) {
                found = true;
                StdOut.println(firstIndexOf(array, key, cmp));
                StdOut.println(lastIndexOf(array, key, cmp));
            }
        }
    }
}
