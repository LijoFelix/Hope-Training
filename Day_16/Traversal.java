import java.util.*;
public class Traversal{
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));
        /*for(int num:list){//Iterator is used here to avoid ConcurrentModeificationException 
            if(num==4){
                list.remove(num);
            }
            System.out.print(num);
        }*/
       ListIterator<Integer> it = list.listIterator();
         while(it.hasNext()){
              int num = it.next();
              if(num==4){
                it.remove();
              }
         }
         while(it.hasPrevious()){
            int prev=it.previous();
            System.out.print(prev);
         }

    }
}