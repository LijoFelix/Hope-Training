import java.io.*;
public class DeSerialization{
    public static void main(String[] args) throws Exception{
        FileInputStream fi=new FileInputStream("student.txt");
        ObjectInputStream objstream=new ObjectInputStream(fi);
        //Student s=(Student)objstream.readObject();
        while(true){
            try{
                Student s=(Student)objstream.readObject();
                System.out.println(s.name+" "+s.mark);
            }
            catch(EOFException e){
                break;
            }
        }
        fi.close();
        objstream.close(); 
    }
}