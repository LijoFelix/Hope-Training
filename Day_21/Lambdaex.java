interface Math{
    int add(int a,int b);
}
interface Exercise{
    void sayHello();
}

public class Lambdaex{
         public static void main(String[] args){
            Math obj = (a,b) -> a + b;  //Lambda
            Exercise ex =  () -> System.out.println("Hello world");
            ex.sayHello();
            /*Math obj = new Math() {
                @Override                                  // here in this we are normally creating a  oobject and overrriding the methods

                public int add(int a,int b){
                    return a + b;

                }
            };*/

            System.out.println(obj.add(11,22));
         }
}