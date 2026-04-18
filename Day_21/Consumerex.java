import java.util.function.Consumer;
import java.util.*;
class Student{
    String name;
Student(String name){
    this.name = name;
}

}
public class Consumerex{
    public static void main(String[] args){
        Consumer<String> printString = (s) ->System.out.println(s);
        Consumer<Student> printName = (s)->System.out.println(s.name);
        printName.accept(new Student("test1"));
        printString.accept("From Consumer");
        List<String> names = List.of("abc","def","ghi");
        names.forEach(name -> System.out.println(name));
        names.forEach(System.out::println);
    }
}