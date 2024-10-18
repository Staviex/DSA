public class Exercise8 {
    public static void generateTruthTable(boolean[] values, int n) {
        if (n == values.length) {

            for (boolean value : values) {
                System.out.print((value ? "1" : "0") + " ");
            }
            System.out.println();
            return;
        }

        values[n] = false;
        generateTruthTable(values, n + 1);

        values[n] = true;
        generateTruthTable(values, n + 1);
    }

    public static void main(String[] args) {
        int n = 3;
        boolean[] values = new boolean[n];
        generateTruthTable(values, 0);
    }
}
