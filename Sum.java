import java.lang.Math;

public class Sum {
    public static void sum (int[] a,int[] b){

        int maxLength = Math.max(a.length, b.length) + 1;
        int[] res = new int[maxLength];
        int carry = 0;

        for (int i = 0; i < maxLength; i++) {

            int digit1 = (i < a.length) ? a[a.length-1-i] : 0;
            int digit2 = (i < b.length) ? b[b.length-1-i] : 0;
            int sum = digit1 + digit2 + carry;
            res[maxLength-1-i] = sum % 10;
            carry = sum / 10;

        }
        for (int i = 0; i < maxLength; i++) {
            System.out.print(res[i] + " ");
        }
        System.out.println();
    }
}
