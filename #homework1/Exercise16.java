public class Exercise16 {
    public static void printSubsets(int[] set, boolean[] included, int index) {
        if (index == set.length) {
            System.out.print("{ ");
            for (int i = 0; i < set.length; i++) {
                if (included[i]) {
                    System.out.print(set[i] + " ");
                }
            }
            System.out.println("}");
            return;
        }

        included[index] = true;
        printSubsets(set, included, index + 1);


        included[index] = false;
        printSubsets(set, included, index + 1);
    }

    public static void main(String[] args) {
        int[] set = {1, 2, 3}; // Example set
        boolean[] included = new boolean[set.length];
        printSubsets(set, included, 0);
    }
}
