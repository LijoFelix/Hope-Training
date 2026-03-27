public class zigzag
{
    public static void main(String[] args)
    {
        String s="paypalishirinh";
        int numRows=3;
        if (numRows == 1)
        {
            System.out.print(s);
            return;
        }

        String[] rows = new String[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = "";
        }

        int currentRow = 0;
        boolean goingDown = false;

        for (int i = 0; i < s.length(); i++) {
            rows[currentRow] += s.charAt(i);

            if (currentRow == 0 || currentRow == numRows - 1) {
                goingDown = !goingDown;
            }

            if (goingDown) currentRow++;
            else currentRow--;
        }

        String result = "";
        for (int i = 0; i < numRows; i++) {
            result += rows[i];
        }
        System.out.print(result);
    }
}