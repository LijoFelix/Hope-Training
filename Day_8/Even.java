import java.util.*;
public class Even{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        StringBuilder st=new StringBuilder();
        int l=str.length();
        for(int i=0;i<=l;i++){
            if(i==l||str.charAt(i)==' '){
                if((st.length()%2==0)&&st.length()>0){
                System.out.print(st+" ");
            }
            st.setLength(0);
        }
         else{
             st.append(str.charAt(i));
       } 
    }
    }
}