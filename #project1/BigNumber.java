import java.util.Arrays;

public class BigNumber {
    private int[] digits;
    private boolean isPositive;


    //constructor with string as argument
    public BigNumber(String number) {

        // checking if the number is negative
        if (number.charAt(0) == '-') {
            isPositive = false;
            number = number.substring(1);
        } else {
            isPositive = true;
        }

        // storing the number in array
        digits = new int[number.length()];
        for (int i = 0; i < number.length(); i++) {
            digits[i] = Character.getNumericValue(number.charAt(i));
        }
    }


    //constructor with array as argument
    public BigNumber(int[] number) {
        this.digits = Arrays.copyOf(number, number.length);
        this.isPositive = true;
    }


    //constructor with no argument to initialize an empty array
    public BigNumber(){
        this.digits = new int[]{0};
        this.isPositive = true;
    }


    public void shiftLeft(int n) {

        int[] newDigits = Arrays.copyOf(digits, digits.length + n);
        for (int i = digits.length; i < newDigits.length; i++) {
            newDigits[i] = 0;
        }
        digits = newDigits;
    }


    public void shiftRight(int n) {

        if (n >= digits.length) {
            digits = new int[]{0};
            return;
        }
        int[] newDigits = new int[digits.length - n];
        System.arraycopy(digits, 0, newDigits, 0, newDigits.length);
        digits = newDigits;
    }


    private boolean isBiggerOrEqual(BigNumber other) {
        if (this.digits.length > other.digits.length) {
            return true;
        } else if (this.digits.length < other.digits.length) {
            return false;
        }

        for (int i = 0; i < this.digits.length; i++) {
            if (this.digits[i] > other.digits[i]) {
                return true;
            } else if (this.digits[i] < other.digits[i]) {
                return false;
            }
        }
        return true;
    }


    //method to identify the sign of an addition
    private void addResultSign (BigNumber other, BigNumber result) {

        if (this.isPositive != other.isPositive && other.isBiggerOrEqual(this)) {
            result.isPositive = other.isPositive;
        } else {
            result.isPositive = this.isPositive;
        }
    }


    //method to identify the sign of a subtraction
    private void subResultSign (BigNumber other, BigNumber result) {

        if (this.isPositive == other.isPositive && other.isBiggerOrEqual(this)) {
            result.isPositive = !this.isPositive;
        } else {
            result.isPositive = this.isPositive;
        }
    }

    public BigNumber add(BigNumber other) {

        if (this.isPositive == other.isPositive) {
            BigNumber result = this.addPositive(other);
            this.addResultSign(other,result);
            return result;
        }

        BigNumber result = this.subtractPositive(other);
        this.addResultSign(other,result);
        return result;

    }

    public BigNumber subtract(BigNumber other) {
        if (this.isPositive == other.isPositive) {
            BigNumber result = this.subtractPositive(other);
            this.subResultSign(other,result);
            return result;
        }

        BigNumber result = this.addPositive(other);
        this.subResultSign(other,result);
        return result;
    }

    //method to add only the numbers regardless of the sign
    private BigNumber addPositive(BigNumber other) {
        StringBuilder result = new StringBuilder();
        int carry = 0;
        int maxLength = Math.max(this.digits.length, other.digits.length);

        for (int i = 0; i < maxLength; i++) {
            int digit1 = (i < this.digits.length) ? this.digits[this.digits.length - 1 - i] : 0;
            int digit2 = (i < other.digits.length) ? other.digits[other.digits.length - 1 - i] : 0;
            int sum = digit1 + digit2 + carry;
            result.append(sum % 10);
            carry = sum / 10;
        }
        if (carry > 0) {
            result.append(carry);
        }

        return new BigNumber(result.reverse().toString());
    }


    //method to subtract only the numbers regardless of the sign
    private BigNumber subtractPositive(BigNumber other) {
        StringBuilder result = new StringBuilder();
        int borrow = 0;
        boolean isOtherBigger = other.isBiggerOrEqual(this);

        int[] larger = isOtherBigger ? other.digits : this.digits;
        int[] smaller = isOtherBigger ? this.digits : other.digits;

        for (int i = 0; i < larger.length; i++) {
            int digit1 = larger[larger.length - 1 - i];
            int digit2 = (i < smaller.length) ? smaller[smaller.length - 1 - i] : 0;

            int sub = digit1 - digit2 - borrow;

            if (sub < 0) {
                sub += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            result.append(sub);
        }

        //remove leading zeros
        while (result.length() > 1 && result.charAt(result.length() - 1) == '0') {
            result.deleteCharAt(result.length() - 1);
        }

        BigNumber finalResult = new BigNumber(result.reverse().toString());
        finalResult.isPositive = !isOtherBigger;
        return finalResult;
    }


    public BigNumber multiply(BigNumber other) {
        int m = this.digits.length;
        int n = other.digits.length;
        int[] res = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = this.digits[i] * other.digits[j];
                int p1 = i + j;
                int p2 = i + j + 1;
                int sum = mul + res[p2];

                res[p1] += sum / 10;
                res[p2] = sum % 10;
            }
        }

        // Remove leading zeros
        int start = 0;
        while (start < res.length - 1 && res[start] == 0) {
            start++;
        }

        int[] finalRes = Arrays.copyOfRange(res, start, res.length);
        BigNumber result = new BigNumber(finalRes);

        // Handle sign
        result.isPositive = this.isPositive == other.isPositive;

        return result;
    }


    public BigNumber karatsubaMultiply(BigNumber other) {
        int m = this.digits.length;
        int n = other.digits.length;

        int maxLength = Math.max(m, n);

        int[] a = new int[maxLength];
        int[] b = new int[maxLength];
        System.arraycopy(this.digits, 0, a, maxLength - m, m);
        System.arraycopy(other.digits, 0, b, maxLength - n, n);

        BigNumber A = new BigNumber(a);
        BigNumber B = new BigNumber(b);

        if (maxLength == 1) {
            return A.multiply(B);
        }

        int halfLength = maxLength / 2;

        BigNumber A1 = new BigNumber(Arrays.copyOfRange(A.digits, 0, halfLength));
        BigNumber A2 = new BigNumber(Arrays.copyOfRange(A.digits, halfLength, maxLength));
        BigNumber B1 = new BigNumber(Arrays.copyOfRange(B.digits, 0, halfLength));
        BigNumber B2 = new BigNumber(Arrays.copyOfRange(B.digits, halfLength, maxLength));

        BigNumber AC = A1.karatsubaMultiply(B1);
        BigNumber BD = A2.karatsubaMultiply(B2);
        BigNumber AB_CD = (A1.addPositive(A2)).karatsubaMultiply(B1.addPositive(B2));
        BigNumber AD_BC = AB_CD.subtractPositive(AC.addPositive(BD));

        AC.shiftLeft(2 * (maxLength - halfLength));
        AD_BC.shiftLeft(maxLength - halfLength);

        BigNumber result = AC.add(BD).add(AD_BC);

        // Remove leading zeros
        int start = 0;
        while (start < result.digits.length - 1 && result.digits[start] == 0) {
            start++;
        }

        int[] finalRes = Arrays.copyOfRange(result.digits, start, result.digits.length);
        BigNumber finalResult = new BigNumber(finalRes);

        // Handle sign
        finalResult.isPositive = this.isPositive == other.isPositive;
        return finalResult;
    }


    public BigNumber divide(BigNumber other) {
        if (other.digits.length == 1 && other.digits[0] == 0) {
            System.out.println("divisor can't be zero");
        }

        BigNumber dividend = new BigNumber(this.digits);
        BigNumber divisor = new BigNumber(other.digits);
        BigNumber quotient = new BigNumber("0");
        BigNumber current = new BigNumber("0");

        for (int i = 0; i < dividend.digits.length; i++) {
            current.shiftLeft(1);
            current.digits[current.digits.length - 1] = dividend.digits[i];

            int x = 0;
            while (current.isBiggerOrEqual(divisor)) {
                current = current.subtractPositive(divisor);
                x++;
            }

            quotient.shiftLeft(1);
            quotient.digits[quotient.digits.length - 1] = x;
        }

        //remove leading zeros
        while (quotient.digits.length > 1 && quotient.digits[0] == 0) {
            quotient = new BigNumber(Arrays.copyOfRange(quotient.digits, 1, quotient.digits.length));
        }

        quotient.isPositive = this.isPositive == other.isPositive;
        return quotient;
    }


    public BigNumber power(int exponent) {
        if (exponent < 0) {
            System.out.println("Exponent must be non-negative");
        }
        if (exponent == 0) {
            return new BigNumber("1");
        }
        if (exponent == 1) {
            return this;
        }

        BigNumber result = new BigNumber("1");
        BigNumber base = this;
        int currentExponent = exponent;

        while (currentExponent > 0) {
            if (currentExponent % 2 == 1) {
                result = result.multiply(base);
            }
            base = base.multiply(base);
            currentExponent /= 2;
        }

        // Handle sign
        result.isPositive = this.isPositive || (exponent % 2 == 0);

        return result;
    }


    public BigNumber factorial(int n) {
        if (n < 0 || n > 100) {
            throw new IllegalArgumentException("Enter a number between 1 and 100");
        }
        BigNumber result = new BigNumber("1");
        for (int i = 2; i <= n; i++) {
            result = result.karatsubaMultiply(new BigNumber(String.valueOf(i)));
        }
        return result;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!isPositive && !(digits.length == 1 && digits[0] == 0)) {
            sb.append("-");
        }

        for (int digit : digits) {
            sb.append(digit);
        }

        return sb.toString();
    }
}
