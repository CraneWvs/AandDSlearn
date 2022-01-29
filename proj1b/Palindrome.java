/** A class for palindrome operations. */
public class Palindrome {
    //helper method
//    private
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            char temp = word.charAt(i);
            d.addLast(temp);
        }
        return d;
        // recursion version

    }
    public boolean isPalindrome(String word) {
        if (word.length() <= 1) {
            return true;
        }
        Deque<Character> d = wordToDeque(word);
        boolean temp = true;
        while (d.size() > 1) {
            char a = d.removeFirst();
            char b = d.removeLast();
            if (a != b) {
                temp = false;
                break;
            }
        }
        return temp;
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() <= 1) {
            return true;
        }
        Deque<Character> d = wordToDeque(word);
        boolean temp = true;
        while (d.size() > 1) {
            char a = d.removeFirst();
            char b = d.removeLast();
            if (!cc.equalChars(a, b)) {
                temp = false;
                break;
            }
        }
        return temp;
    }
}
