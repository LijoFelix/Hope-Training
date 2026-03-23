class Student{
    /*int rollno=109;
    String name="John Doe";
    int marks=85;
    char c='A';
    boolean b=true;*/
    int rollno;//rollno, name, marks, c and b are instance variables of Student class and they are stored in heap memory when an object is created
    String name;
    int marks;
    char c;
    //boolean b;
    /*Student(){//default constructor of Student class
        //constructor is a special method that is used to initialize the instance variables of a class when an object is created
        //constructor name should be same as class name and it should not have return type
        //when object is created constructor will be called automatically and it will initialize the instance variables with the values provided in the constructor body
        rollno=109;
        name="John Doe";
        marks=85;
        c='A';
        b=true;
    }*/
    /*Student(int rollno, String name, int marks, char c, boolean b){//parameterized constructor of Student class
        System.out.println("Parameterized constructor called");
        System.out.println("Roll No: "+rollno);
        System.out.println("Name: "+name);
        System.out.println("Marks: "+marks);
        System.out.println("Character: "+c);
        System.out.println("Boolean: "+b);
        Student()
        {
            System.out.println("from constructor");
        }*/
        Student(int rollno,String name)
        {
            this.rollno=rollno;
            this.name=name;//this keyword 
        }
        Student(int m,char ch)
        {
            marks=m;
            c=ch;
        }
    }
    

public class Test{
    public static void main(String[] args){
        //Student s1=new Student();//s1->reference variable stored in stack,new Student()->object creation in heap memory
        //Student s2=new Student(101, "Mohamed Balaji", 90, 'A', true);//parameterized constructor called with arguments
       /*  s1.rollno=101;
        s1.name="Mohamed Balaji";
        s1.marks=90;*/
        //Student s1; In this given line only reference variable is created in stack memory but object is not created in heap memory so default values will be printed
        /*System.out.println(s1.rollno);//without initialization default values will be printed as zero for int and null for String and char
        System.out.println(s1.name);
        System.out.println(s1.marks);
        System.out.println(s1.c);//default value for char is null character
        System.out.println(s1.b);//default value for boolean is false
        Student s1=new Student();*/
        Student s2=new Student(109,"lijo");
        System.out.println(s2.name);
        System.out.println(s2.rollno);
        Student s3=new Student(64,'H');
        System.out.println(s3.marks);
        System.out.println(s3.c);

    }
}