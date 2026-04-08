class Solution {
    public boolean canConstruct(String r, String m) {
        HashMap<Character,Integer> map1=new HashMap<>();
        HashMap<Character,Integer> map2=new HashMap<>();
        for(char i:r.toCharArray())
        {
            map1.put(i,map1.getOrDefault(i,0)+1);
        }
        for(char j:m.toCharArray())
        {
            map2.put(j,map2.getOrDefault(j,0)+1);
        }
        for(char c: map1.keySet())
        {
            if(map2.getOrDefault(c,0)<map1.get(c))
            {
                return false;
            }
        }
        return true;
    }
}