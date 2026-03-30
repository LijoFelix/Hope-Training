
import java.util.Scanner;

public class Reverse{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char[] arr = str.toCharArray();
        int count=1;    
        int flag=0;
        for(int i=0;i<arr.length-1;i++)
        {
            if(arr[i]==' '&&arr[i+1]!=' ')
            {
                count++;
                flag=1;
            }
        }
        if(flag==1)
        {
            System.out.println("Number of words in the string is: "+count);
        }
        else
        {
            System.out.println("Number of words in the string is: "+flag);
        }
    }
}
