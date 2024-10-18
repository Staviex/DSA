public class Exercise4 {
    public static void convertToBinary(int n) {
        if (n > 1) {
            convertToBinary(n / 2);
        }
        System.out.print(n % 2);
    }

    public static void main(String[] args) {
        int number = 10;
        System.out.print("Binary representation of " + number + " is: ");
        convertToBinary(number);
        System.out.println();
    }
}
