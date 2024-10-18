public class Exercise3 {
    public static void reverseArray(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }

        int temp = array[start];
        array[start] = array[end];
        array[end] = temp;

        reverseArray(array, start + 1, end - 1);
    }


    public static void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        System.out.println("Original array:");
        printArray(numbers);
        reverseArray(numbers, 0, numbers.length - 1);
        System.out.println("Reversed array:");
        printArray(numbers);
    }
}
