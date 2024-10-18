public class Exercise10 {
    public static int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static double seriesSum(int n) {
        if (n == 1) {
            return 1.0 / factorial(1);
        } else {
            return 1.0 / factorial(n) + seriesSum(n - 1);
        }
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println("The sum of the first " + n + " terms of the series is: " + seriesSum(n));
    }
}
