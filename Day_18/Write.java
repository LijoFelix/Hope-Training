import java.io.*;
public class Write{
    public static void main(String[] args){
        try{
              //FileWriter writer=new FileWriter("test.txt");
                BufferedWriter writer=new BufferedWriter(new FileWriter("test1.txt"));
                writer.write("write using FileWriter 4");
                writer.newLine();
                writer.write("write using FileWriter 5");
                writer.newLine();
                writer.write("write using FileWriter 6");
                writer.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }
}