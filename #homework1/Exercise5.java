public class Exercise5 {
    public static int findMax(int[] array, int n) {

        if (n == 1) {
            return array[0];
        }

        int max = findMax(array, n - 1);

        if (array[n - 1] > max) {
            return array[n - 1];
        } else {
            return max;
        }
    }

    public static void main(String[] args) {
        int[] numbers = {3, 5, 7, 2, 8, 6};
        int max = findMax(numbers, numbers.length);
        System.out.println("The maximum value is: " + max);
    }
}
