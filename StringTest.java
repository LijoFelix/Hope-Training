public class StringTest{
    public static void main(String[] args) {
        String s="hello,Mohamed balaji";
        String[] arr=s.split("[,\\.\\s]");
        String s1="lijo@vs#boboboy omlete";
        String[] arr1=s1.split("[., @#!]+");
        System.out.println(arr.length);
        System.out.println(arr1.length);
    }
}