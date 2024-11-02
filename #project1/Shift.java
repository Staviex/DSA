public class Shift {
    public static void shiftRight(int[] a) {

        int[] shifted = new int[a.length];

        if (a.length == 0) {
            shifted = a;
        } else {
            // when shifting to the right the last element is the new first element
            shifted[0] = a[a.length - 1];

            // other elements shifts one index to the right
            for (int i = 1; i < a.length; i++) {
                shifted[i] = a[i - 1];
            }
        }

        // printing the array
        for (int i = 0; i < a.length; i++) {
            System.out.print(shifted[i] + " ");
        }
        System.out.println();
    }

    public static void shiftLeft(int[] a) {

        int[] shifted = new int[a.length];

        if (a.length == 0) {
            shifted = a;
        } else {
            // when shifting to the left the first element is the new last element
            shifted[a.length - 1] = a[0];

            // other elements shifts one index to the left
            for (int i = 0; i < a.length - 1; i++) {
                shifted[i] = a[i + 1];
            }
        }

        // printing the array
        for (int i = 0; i < a.length; i++) {
            System.out.print(shifted[i] + " ");
        }
        System.out.println();
    }

}
