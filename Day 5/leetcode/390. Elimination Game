class Solution {
    public int lastRemaining(int n) {
        int head = 1;
        int step = 1;
        int remain = n;
        boolean leftToRight = true;
        while(remain>1){
            if(remain%2!=0 || leftToRight)
                head+=step;
            step*=2;
            remain/=2;
            leftToRight = !leftToRight;
        }
        return head;
    }
}