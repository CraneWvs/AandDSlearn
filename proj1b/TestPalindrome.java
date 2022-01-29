import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    CharacterComparator n1 = new OffByOne();
    CharacterComparator n5 = new OffByN(5);

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    } /* Uncomment this class once you've created your Palindrome class. */
    @Test
    public void testIsPalindrome() {
//        assertTrue(palindrome.isPalindrome(null));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("a", n1));
        assertTrue(palindrome.isPalindrome("a", n5));

        assertTrue(palindrome.isPalindrome("racecar"));
        assertFalse(palindrome.isPalindrome("Bob"));
        assertFalse(palindrome.isPalindrome("cat"));

        assertTrue(palindrome.isPalindrome("flake", n1));
        assertFalse(palindrome.isPalindrome("flaje", n1));

        assertTrue(palindrome.isPalindrome("ahkmf", n5));
        assertFalse(palindrome.isPalindrome("flajz", n5));
    }
}
