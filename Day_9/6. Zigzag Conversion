class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        StringBuilder[] rows = new StringBuilder[numRows];
        for(int i=0;i<numRows;i++){
            rows[i] = new StringBuilder();
        }
        int val = 0;
        Boolean down = false;
        for(int i=0;i<s.length();i++){
            rows[val].append(s.charAt(i));
            if(val==0 || val==numRows-1){
                down = !down;
            }
            if(down) val++;
            else val--;
        }
        StringBuilder out = new StringBuilder();
        for(StringBuilder m : rows){
            out.append(m);
        }
        return out.toString();
    }
}