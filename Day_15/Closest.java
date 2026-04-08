/*TreeSet-> uses self-balancing tree(red-black tree) internally

tree traversal: 
-inorder(LNR)
-preorder(NLR)
-postorder(LRN)

treeSet methods:
-add()
-remove()
-contains()
->first()
->last()
->ceil()  \\[10,20,30,40,50]
checks highest-> ceil(25)=30
->floor()
checks lowest->floor(25)=20
-higher()
-lowest()*/
import java.util.*;

public class Closest {

    public static int closest(int[] arr, int target) {
        TreeSet<Integer> set = new TreeSet<>();

        for (int i : arr) {
            set.add(i);
        }

        Integer low = set.lower(target);
        Integer high = set.higher(target);

        if (low == null) {
            return high;
        }
        if (high == null) {
            return low;
        }

        int diff1 = Math.abs(target - low);
        int diff2 = Math.abs(high - target);

        if (diff1 <= diff2) {
            return low;
        } else {
            return high;
        }
    }

    public static void main(String[] args) {
        int[] arr = {10, 15, 20, 25, 27, 30};
        int target = 10;

        System.out.println(closest(arr, target));
    }
}