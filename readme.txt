/* *****************************************************************************
 *  Name:     Devin Plumb
 *  NetID:    dplumb
 *  Precept:  P06
 *
 *  Partner Name:       N/A
 *  Partner NetID:      N/A
 *  Partner Precept:    N/A
 *
 *  Hours to complete assignment (optional): ~5
 *
 **************************************************************************** */

Programming Assignment 3: Autocomplete


/* *****************************************************************************
 *  Describe how your firstIndexOf() method in BinarySearchDeluxe.java
 *  to find the first index of a key that equals the search key.
 **************************************************************************** */

 First the function detects any null arguments and throws an exception. Next it
 keeps track of the top and bottom of a series of progressively smaller
 intervals in which the first index of a key that equals the search key could
 be, checking each midpoint to see if its key is smaller than, larger than, or
 equal to the search key. If smaller, it eliminates the bottom half of the
 interval. If larger, it eliminates the top half. If equal, it eliminates the
 top half and updates the value of the index to return.

 Succinctly, it executes the binary search algorithm with the modification that
 instead of stopping when it finds any index of a key that equals the search
 key, it simply stores the value of the most recent index and continues
 narrowing until the interval reduces to 0.

/* *****************************************************************************
 *  Identify which sorting algorithm (if any) that your program uses in the
 *  Autocomplete constructor and instance methods. Choose from the following
 *  options:
 *
 *    none, selection sort, insertion sort, mergesort, quicksort, heapsort
 *
 *  If you are using an optimized implementation, such as Arrays.sort(),
 *  select the principal algorithm.
 **************************************************************************** */

Autocomplete() : mergesort

    Arrays.sort() uses Timsort for reference types, terms2 are of the type Term

allMatches() : mergesort

    prefixes are of the type Term, thus Timsort

numberOfMatches() : none

/* *****************************************************************************
 *  What is the order of growth of the number of compares (in the worst case)
 *  that each of the operations in the Autocomplete data type make, as a
 *  function of the number of terms n and the number of matching terms m?
 *
 *  Recall that with order-of-growth notation, you should discard
 *  leading coefficients and lower-order terms, e.g., m^2 + m log n.
 **************************************************************************** */

Autocomplete(): n * log n

    Mergesort is stable, meaning any case in which all n entries are non-null
    requires O(n*log n) compares to sort.

allMatches(): log n + m * log m

    The worst case would be that all terms match, or m=n, which would require
    the program to take time 2 log n + m log m = 2 log n + n log n =
    (n+2) * (log n), which is O(n*log n). Each binary search, to find the first
    and last indices of keys that equal the search key, takes O(log n) time.
    Sorting m items requires O(m*log m) time (mergesort still stable).

numberOfMatches(): log n

    Each binary search, to find the first and last indices of keys that equal
    the search key, takes O(log n) time. There are only two binary searches to
    execute, thus the function is O(log n) time.

/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */

 I get a checkstyle check for creating substrings, though I am confident the
 length of the substrings I create is proportional to the number of characters
 needed to compare two terms.

/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 *
 *  Also include any resources (including the web) that you may
 *  may have used in creating your design.
 **************************************************************************** */

 No persons helped me. I did use code from precept and the course textbook as a
 template for parts of my program, and commented citations accordingly.

/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */

 None.

/* *****************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **************************************************************************** */

 N/A.

/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */

 Fun!
