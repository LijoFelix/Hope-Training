public class Method1{
    static int mul(int a,int b){
        return a*b;
    }
    static int square(int a){
        return mul(a,a);
    }
    static void print(int a){
        System.out.println(square(a));

    }

    public static void main(String[] args){
        int num=2;
        print(num);
    }
}