import java.util.*;
public class ShortCharacters {
    public static List<Character> shortChar(String s){
        //1.frequency map
        Map<Character,Integer> map=new HashMap<>();
        for(int i=0;i<s.length();i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0)+1);
        }
        //2.map stored in list
        List<Map.Entry<Character,Integer>> list=new ArrayList<>(map.entrySet());
        //3.sort
        
       /*  Collections.sort(list,new Comparator<Map.Entry<Character,Integer>>(){
            @Override
            public int compare(Map.Entry<Character,Integer> a,Map.Entry<Character,Integer> b){
                if(!a.getValue().equals(b.getValue())){
                    return b.getValue()-a.getValue();
                }
                return a.getKey()-b.getKey();
            }
        });*/
        Collections.sort(list,(a,b)->{
            if(!a.getValue().equals(b.getValue())){
                return b.getValue()-a.getValue();
            }
            return a.getKey()-b.getKey();
        });
        List<Character> result=new ArrayList<>();
        for(Map.Entry<Character,Integer> entry:list){
            result.add(entry.getKey());
        }
        return result;
    }
    public static void main(String[] args) {
        String s="tree";
        System.out.println(shortChar(s));
    }
}