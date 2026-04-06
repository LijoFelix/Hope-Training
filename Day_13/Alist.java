import java.util.ArrayList;
import java.util.Arrays;
public class Alist {
    public static void main(String[] args) {
        ArrayList<Integer> alist = new ArrayList<>(Arrays.asList(1,2,2,4,4,6,7,8,9));
        /*for(int i=0;i<alist.size();i++)
        {
            if(alist.get(i)%2==0)
            {
                alist.remove(alist.get(i));
                i--;
            }
        }*/
       for(int i=0;i<alist.size()-1;i++)
        {
            for(int j=i+1;j<alist.size();j++)
            {
                if(alist.get(i)==alist.get(j))
                {
                    alist.remove(j);
                    j--;
                }
            }
        }
        for(int i=0;i<alist.size();i++)
        {
            System.out.print(alist.get(i)+" ");
        }
    }
}     