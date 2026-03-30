import java.util.*;
public class Kpattern{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int len = str.length();
        int j=len-1;
        for(int i=0;i<len;i++)
        {
            System.out.print(str.charAt(i));
            int times=Math.abs(i-j)/2;
            while(times>0)
            {
                System.out.print(" ");
                times--;
            }
            System.out.println(str.charAt(j));
            j--;
        }
    }
}