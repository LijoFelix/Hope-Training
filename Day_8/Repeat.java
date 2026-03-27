import java.util.*;
public class Repeat{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int len = str.length();
        for(int i=0;i<len-1;i++)
        {   int count=0;
            for(int j=i+1;j<len;j++)
            {
                if(str.charAt(i)==str.charAt(j))
                {
                    count++;
                }
            }
            if(count==0)
            {
                System.out.println(str.charAt(i));
                break;
            }
        }
        
    }
}


/*import java.util.*;
public class firstnonrep {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int len = str.length();
        int[] arr = new int[26];
        for(int i=0;i<len;i++)
        {
            arr[str.charAt(i)-'a']++;
        }
        for(int j=0;j<len;j++)
        {
            if(arr[str.charAt(j)-'a']==1)
            {
                System.out.println(str.charAt(j));
                break;
            }
        }
        
    }
}*/