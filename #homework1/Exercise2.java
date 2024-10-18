public class Exercise2 {
    public static int sumArray(int[] array, int n) {
        if (n <= 0) {
            return 0;
        }
        return array[n - 1] + sumArray(array, n - 1);
    }

    public static double calculateAverage(int[] array) {
        int sum = sumArray(array, array.length);
        return (double) sum / array.length;
    }

    public static void main(String[] args) {
        int[] numbers = {5, 10, 15, 20, 25};
        double average = calculateAverage(numbers);
        System.out.println("The average is: " + average);
    }
}
