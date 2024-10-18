public class Exercise13 {
    public static void solveHanoi(int n, char s, char d, char a) {
        if (n == 0) {
            return;
        }
        solveHanoi(n - 1, s, a, d);
        System.out.println(s + " -> " + d);
        solveHanoi(n - 1, a, d, s);
    }

    public static void main(String[] args) {
        int n = 2;
        solveHanoi(n, 's', 'd', 'a');
    }
}
