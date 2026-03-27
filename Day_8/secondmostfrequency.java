public class secondmostfrequency
{
    public static void main(String[] args)
    {
        String str = "aabbbc";
        
        int first = 0, second = 0;
        char firstChar = '\0', secondChar = '\0';
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int count = 0;
            
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == ch) {
                    count++;
                }
            }
            
            boolean alreadyChecked = false;
            for (int k = 0; k < i; k++) {
                if (str.charAt(k) == ch) {
                    alreadyChecked = true;
                    break;
                }
            }
            if (alreadyChecked) continue;
            
            if (count > first) {
                second = first;
                secondChar = firstChar;
                first = count;
                firstChar = ch;
            } else if (count > second && count != first) {
                second = count;
                secondChar = ch;
            }
        }
        
        System.out.println("Second most frequent character: " + secondChar);
    }
}
 