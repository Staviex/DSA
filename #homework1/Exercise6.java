public class Exercise6 {
    public static int multiply(int a, int b) {

        if (b == 0) {
            return 0;
        }

        if (b < 0) {
            return -multiply(a, -b);
        }

        return a + multiply(a, b - 1);
    }

    public static void main(String[] args) {
        int a = 5;
        int b = 3;
        int result = multiply(a, b);
        System.out.println(a + " * " + b + " = " + result);
    }
}
