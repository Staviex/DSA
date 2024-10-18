public class Exercise18and19 {

    public static boolean duplicate(String s) {

        if (s.length() == 2 && s.charAt(0) == s.charAt(1)) {
            return true;
        } else if (s.length() == 2 && s.charAt(0) != s.charAt(1)
                || s.length() == 1 || s.isEmpty()) {
            return false;
        }
        return (duplicate(s.substring(1,s.length()/2)));
    }
    public static void main(String[] args) {
        System.out.println(duplicate("ALILIALILI"));
        System.out.println(duplicate("HTMMTMMHTMMTMM"));
    }
}
