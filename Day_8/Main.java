import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String str=sc.nextLine();
        char[] arr=str.toCharArray();
        if(str.length()==1)
        {
            System.out.println(str);
            return;
        }
        StringBuilder sb1=new StringBuilder();
        StringBuilder sb2=new StringBuilder();
        for(int i=0;i<arr.length;i++)
        {
            if(i%2==0)
            {
                sb1.append(arr[i]);
            }
            else
            {
                sb2.append(arr[i]);
            }
        }
        sb1.append(sb2);
        System.out.println(sb1);
    }
}