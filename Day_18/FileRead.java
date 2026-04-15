import java.io.*;
public class FileRead{
    public static void main(String[] args){
        try{
            BufferedReader fr=new BufferedReader(new FileReader("test1.txt"));
            //FileReader fr=new FileReader("test1.txt");
            String line;
            //int character;
            /*while((character=fr.read())!=-1){
                System.out.print((char)character);
            }*/
            while((line=fr.readLine())!=null){
                System.out.println(line);
            }
            fr.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }
}   