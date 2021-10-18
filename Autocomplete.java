/* *****************************************************************************
 *  Name:    Devin Plumb
 *  NetID:   dplumb
 *  Precept: P06
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description:  Provides autocomplete functionality for a given set of strings
 *                and weights, using Term and BinarySearchDeluxe. To do so,
 *                sorts the terms in lexicographic order; uses binary search to
 *                find the all query strings that start with a given prefix; and
 *                sorts the matching terms in descending order by weight.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class Autocomplete {

    private final Term[] terms; // naturally-ordered array of terms

    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        if (terms == null)
            throw new IllegalArgumentException("null argument");
        Term[] terms2 = new Term[terms.length];
        for (int i = 0; i < terms.length; i++) {
            if (terms[i] == null)
                throw new IllegalArgumentException("null entry in argument");
            terms2[i] = terms[i];
        }
        Arrays.sort(terms2);
        this.terms = terms2;
    }

    // Returns all terms that start with the given prefix,
    // in descending order of weight.
    public Term[] allMatches(String prefix) {
        if (prefix == null)
            throw new IllegalArgumentException("null argument");

        Term term = new Term(prefix, 0);
        Comparator<Term> cmp = Term.byPrefixOrder(prefix.length());
        int first = BinarySearchDeluxe.firstIndexOf(terms, term, cmp);
        if (first == -1) return new Term[0];
        int last = BinarySearchDeluxe.lastIndexOf(terms, term, cmp);
        Term[] prefixes = Arrays.copyOfRange(terms, first, last + 1);

        Comparator<Term> cmp2 = Term.byReverseWeightOrder();
        Arrays.sort(prefixes, cmp2);
        return prefixes;
    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        if (prefix == null)
            throw new IllegalArgumentException("null argument");

        Term term = new Term(prefix, 0);
        Comparator<Term> cmp = Term.byPrefixOrder(prefix.length());
        int first = BinarySearchDeluxe.firstIndexOf(terms, term, cmp);
        if (first == -1) return 0;
        int last = BinarySearchDeluxe.lastIndexOf(terms, term, cmp);
        return last + 1 - first;
    }

    // unit testing (required)
    // CITATION: Test Client Provided in Assignment Spec
    public static void main(String[] args) {
        // read in the terms from a file
        String filename = args[0];
        In in = new In(filename);
        int n = in.readInt();
        Term[] terms = new Term[n];
        for (int i = 0; i < n; i++) {
            long weight = in.readLong();           // read the next weight
            in.readChar();                         // scan past the tab
            String query = in.readLine();          // read the next query
            terms[i] = new Term(query, weight);    // construct the term
        }

        // read in queries from standard input
        // and print out the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            StdOut.printf("%d matches\n", autocomplete.numberOfMatches(prefix));
            for (int i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }
    }

}
