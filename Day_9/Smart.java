abstract class SmartDevice{
    abstract void turnOn();
    abstract void turnOff();
    void deviceInfo(){
        System.out.println("This is a smart device.");
    }
}
class Light extends SmartDevice{
    @Override
    void turnOn() {
        System.out.println("Light is turned on.");
    }

    @Override
    void turnOff() {
        System.out.println("Light is turned off.");
    }
}
public class Smart
{
    public static void main(String[] args){
        SmartDevice device = new Light();
        device.turnOn();
        device.deviceInfo();
        device.turnOff();
    }
}