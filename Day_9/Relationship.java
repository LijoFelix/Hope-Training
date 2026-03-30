class Battery{
    public void powerSupply(){
        System.out.println("Battery is supplying power");
    }
}
class Remote{
    Battery miniBattery=new Battery();//has a relationship
    public void changeChannel(){
        miniBattery.powerSupply();
        System.out.println("Changing channel");
    }
}
public class Relationship{
    public static void main(String[] args) {
        Remote tvRemote=new Remote();
        tvRemote.changeChannel();
    }
}