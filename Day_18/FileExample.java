import java.io.*;
public class FileExample{
    public static void main(String[] args)throws IOException{
        //File newFile=new File("test.txt");
        File newFile=new File("deletetest.txt");
        /*if(newFile.createNewFile()){
            System.out.println("File created: "+newFile.getName());
        }
        else{
            System.out.println("Not able to create file");
        }*/
        if(newFile.delete()){
            System.out.println("File deleted: "+newFile.getName());
        }
        else{
            System.out.println("Not able to delete file");
        }
    } 
}