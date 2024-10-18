public class Exercise11and12 {
    public static void findCombinations(int n, int count2, int count5, int count10) {
        if (n == 0) {
            System.out.println("2: " + count2 + ", 5: " + count5 + ", 10: " + count10);
            return;
        }
        if (n >= 2) {
            findCombinations(n - 2, count2 + 1, count5, count10);
        }
        if (n >= 5) {
            findCombinations(n - 5, count2, count5 + 1, count10);
        }
        if (n >= 10) {
            findCombinations(n - 10, count2, count5, count10 + 1);
        }
    }

    public static void main(String[] args) {
        int n = 12;
        findCombinations(n, 0, 0, 0);
    }
}
