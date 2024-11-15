//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BigNumber num1 = new BigNumber("-246895246");
        BigNumber num2 = new BigNumber("-12093");
        int num3 = 3;
        int num4 = 6;

        System.out.println(num1 + " + " + num2 + " = " + num1.add(num2));
        System.out.println(num1 + " - " + num2 + " = " + num1.subtract(num2));
        System.out.println(num1 + " * " + num2 + " = " + num1.multiply(num2));
        System.out.println(num1 + " * " + num2 + " = " + num1.karatsubaMultiply(num2) + " (karatsuba multiplication)");
        System.out.println(num1 + " / " + num2 + " = " + num1.divide(num2));
        System.out.println(num1 + " ^ " + num3 + " = " + num1.power(num3));
        System.out.println("factorial of " + num4 + " = " + num1.factorial(6));
    }
}
