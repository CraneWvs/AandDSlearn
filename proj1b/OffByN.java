/** A class for off-by-N comparators. */
public class OffByN implements CharacterComparator {
    int num;
    public OffByN(int N) {
        num = N;
    }
    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        if (diff == num || diff == -num) {
            return true;
        }
        return false;
    }
}
