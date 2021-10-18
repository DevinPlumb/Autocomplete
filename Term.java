/* *****************************************************************************
 *  Name:    Devin Plumb
 *  NetID:   dplumb
 *  Precept: P06
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description:  Immutable data type that represents an autocomplete term: a
 *                query string and an associated integer weight.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Comparator;

// CITATION: Code heavily influenced by Precept 3, Point2D.java
public class Term implements Comparable<Term> {

    private final String query; // String of letters of the term
    private final long weight; // weight of the term, higher is more important

    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
        if (query == null)
            throw new IllegalArgumentException("query is null");
        if (weight < 0)
            throw new IllegalArgumentException("weight is negative");
        this.query = query;
        this.weight = weight;
    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return new ByReverseWeightOrder();
    }

    private static class ByReverseWeightOrder implements Comparator<Term> {
        // no parameters to initialize, weight is all that matters
        // no explicit constructor necessary

        public int compare(Term p, Term q) {
            return Long.compare(q.weight, p.weight);
        }
    }

    // Compares the two terms in lexicographic order,
    // but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0)
            throw new IllegalArgumentException("r is negative");
        return new ByPrefixOrder(r);
    }

    private static class ByPrefixOrder implements Comparator<Term> {
        private final int r; // number of letters included in comparison

        // constructor, only the first r letters matter
        private ByPrefixOrder(int r) {
            this.r = r;
        }

        public int compare(Term p, Term q) {
            String pEdited, qEdited;
            if (p.query.length() >= r && q.query.length() >= r) {
                pEdited = p.query.substring(0, r);
                qEdited = q.query.substring(0, r);
            }
            else if (p.query.length() < q.query.length()) {
                pEdited = p.query;
                qEdited = q.query.substring(0, p.query.length() + 1);
            }
            else if (p.query.length() > q.query.length()) {
                pEdited = p.query.substring(0, q.query.length() + 1);
                qEdited = q.query;
            }
            else {
                pEdited = p.query;
                qEdited = q.query;
            }
            return pEdited.compareTo(qEdited);
        }
    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {
        return this.query.compareTo(that.query);
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        return weight + "\t" + query;
    }

    // unit testing (required)
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

        // unsorted
        for (Term p : array)
            StdOut.println(p);

        // sort using natural order
        Arrays.sort(array);

        StdOut.println("\nAfter Sorting Lexicographically: ");
        for (Term p : array)
            StdOut.println(p);

        // Check if the terms in the array are distinct.
        boolean distinct = true;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].compareTo(array[i + 1]) == 0) {
                distinct = false;
                break;
            }
        }
        StdOut.println("Distinct: " + distinct);

        // Sort the terms lexicographically, up to r.
        Comparator<Term> cmp = Term.byPrefixOrder(r);
        Arrays.sort(array, cmp);

        StdOut.println("\nAfter Sorting with ByPrefixOrder: ");
        for (Term p : array)
            StdOut.println(p);

        // Check if the terms in the array have distinct weights
        distinct = true;
        for (int i = 0; i < array.length - 1; i++) {
            if (cmp.compare(array[i], array[i + 1]) == 0) {
                distinct = false;
                break;
            }
        }
        StdOut.println("Distinct: " + distinct);

        // Sort the terms based on their weights.
        Comparator<Term> cmp2 = Term.byReverseWeightOrder();
        Arrays.sort(array, cmp2);

        StdOut.println("\nAfter Sorting with ByReverseWeightOrder: ");
        for (Term p : array)
            StdOut.println(p);

        // Check if the terms in the array have distinct weights
        distinct = true;
        for (int i = 0; i < array.length - 1; i++) {
            if (cmp2.compare(array[i], array[i + 1]) == 0) {
                distinct = false;
                break;
            }
        }
        StdOut.println("Distinct: " + distinct);
    }

}
