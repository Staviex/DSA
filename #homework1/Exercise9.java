public class Exercise9 {
    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static int sumOfFactorials(int n) {
        if (n == 0) {
            return 0;
        }
        return factorial(n) + sumOfFactorials(n - 1);
    }

    public static void main(String[] args) {
        int n = 5;
        int sum = sumOfFactorials(n);
        System.out.println("Sum of first " + n + " factorials is: " + sum);
    }
}
