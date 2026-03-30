public class shortestword{
    public static void main(String[] args)
    {
        String sentence = "I like Java Programming";
        String shortest = "";
        String temp = "";
        for (int i = 0; i < sentence.length(); i++) {
            char ch = sentence.charAt(i);
            if (ch != ' ') {
                temp += ch;
            } else {
                if (shortest.equals("") || temp.length() < shortest.length()) {
                    shortest = temp;
                }
                temp = "";
            }
        }
        if (temp.length() > 0 && (shortest.equals("") || temp.length() < shortest.length())) {
            shortest = temp;
        }
        System.out.println("Shortest word: " + shortest);
    }
}
