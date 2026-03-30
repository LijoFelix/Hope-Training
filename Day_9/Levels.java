abstract class SmartService{
    abstract void turnon();
    abstract void turnoff();
    abstract void setlevel(int level);

}
class Light extends SmartService{
    @Override
    void turnon(){
        System.out.println("Light is on");
    }
    @Override
    void turnoff(){
        System.out.println("Light is off");
    }
    @Override
    void setlevel(int level){
        System.out.println(level);
    }
   
}
class AC extends SmartService{
    @Override
    void turnon(){
        System.out.println("AC is on");
    }

    @Override
    void turnoff(){
        System.out.println("AC is off");
    }

    @Override
    void setlevel(int level){
        System.out.println(level);
    }
}
interface remote{
   void change();
}
class TV implements remote{
    public void change(){
        System.out.println("change channel");
    }
}
public class Levels{
    public static void main(String[] args){
        remote device=new TV();
        device.change();
    }
}