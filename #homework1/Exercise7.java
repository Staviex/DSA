public class Exercise7 {
    public static int gcd(int a, int b) {

        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        int a = 48;
        int b = 18;
        int result = gcd(a, b);
        System.out.println("GCD of " + a + " and " + b + " is: " + result);
    }
}
