public class Exercise1 {
    public static int f(int a, int b) {
        if (a < b){
            return 0;
        }
        return 1+f(a-b , b);

    }
}
