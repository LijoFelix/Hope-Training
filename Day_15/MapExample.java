/*Map Interface:

storing in key(unique)-value(can store duplicates) pairs
    ex: studenid-name
        100-A 
        101-B
        102-C 
    ->  map<Integer,String>

HashMap:
->hashcode() & equals()

methods:
-map.put(0,1)(to add and update)
        key,value
map.put(0,2)
    if key exist value will update

-map.get(key)->retrieves the value

-containskey(0)-> returns true if key exists else false

-map.remove(key)->removes the key

-map.getorDefault(0,1)
ex:map.put(1,map.getorDefault(1,0)+1)*/
import java.util.*;
public class MapExample {
    public static void main(String[] args)
    {
        int[] arr={1,2,3,4,5};
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<arr.length;i++)
        {
            map.put(i,arr[i]);
        }
        //System.out.println(map);
        System.out.println(map.keySet());//map.keyset()->returns a set of keys in the map
        System.out.println(map.values());//map.values()->returns a set of values in the map
        for(Map.Entry<Integer,Integer> entry:map.entrySet())//entrySet()->returns a set of key-value pairs in the map
        {
            System.out.println(entry.getKey()+"->"+entry.getValue());
        }
    }
}