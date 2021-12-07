public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        int sum = 0;
        int n = 10;
        while (x < n) {
            sum = sum + x;
            System.out.print(sum + " ");
            if (x == n - 1){
              System.out.println();
            }
            x = x + 1;
        }
    }
}