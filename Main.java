//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int[] num1 = {7,3,1,8,6,5};
        int[] num2 = {5,7,3,4,5,6};

        System.out.println("sum of the two numbers:");
        Sum.sum(num1, num2);
        System.out.println("num2 subtracted from num1 " +
                "(first element of result represents the sign of the number\n" +
                "(0 for positive and 9 for negative)):");
        Sub.Subtract(num1, num2);
        System.out.println("num1 shifted to right:");
        Shift.shiftRight(num1);
        System.out.println("num1 shifted to left:");
        Shift.shiftLeft(num1);
    }
}