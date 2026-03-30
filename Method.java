class student{
    int marks;
}
public class Method{
    static void change(student s){
        s.marks=20;
        System.out.println("inside method: " + s.marks);
        //if static in method then we can call it directly without creating object of class. if not static then we have to create object of class and then call method using that object.
    }
    public static void main(String[] args){
        student s1=new student();
        s1.marks=90;
        change(s1);
        System.out.println("outside method: " + s1.marks);
    }
}