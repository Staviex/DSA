public class Sub {

    // method to check if B can be subtracted from A
    static boolean isBiggerOrEqual(int[] a,int[] b) {
        if (a.length > b.length) {
            return true;
        } else if (a.length < b.length) {
            return false;
        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] > b[i]) {
                return true;
            } else if (a[i] < b[i]) {return false;
            }
        }
        return true;
    }


    public static void Subtract(int[] a, int[] b) {

        int maxLength = Math.max(a.length, b.length) + 1;
        int[] res = new int[maxLength];

        /* first element of result represents the sign of the number
        (0 for positive numbers and 9 for negative numbers)*/
        res[0] = 0;

        int borrow = 0;

        // if B is bigger than A subtract A from B but set the sign to negative
        if (!isBiggerOrEqual(a, b)) {
            res[0] = 9;
            int[] temp = a;
            a = b;
            b = temp;
        }


        for (int i = 0; i < maxLength-1; i++) {

            int digit1 = (i < a.length) ? a[a.length-1-i] : 0;
            int digit2 = (i < b.length) ? b[b.length-1-i] : 0;
            int sub = digit1 - digit2 - borrow;

            if (sub < 0) {
                sub += 10;
                borrow = 1;
            } else {borrow = 0;}

            res[maxLength-1-i] = sub;

        }

        for (int i = 0; i < maxLength; i++) {
            System.out.print(res[i] + " ");
        }
        System.out.println();
    }
}
