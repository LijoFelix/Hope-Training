abstract class application{
    String device;
    abstract void turnon();
    abstract void turnoff();
}
 class light extends application{
    light(){
        System.out.println("light is on");
    }
    void turnon(){
        System.out.println("light is on");
    }
    void turnoff(){
        System.out.println("light is off");
    }
}
 class AC extends application{
    void turnon(){
        System.out.println("AC is on");
    }
    void turnoff(){
        System.out.println("AC is off");
    }
}
interface Trigger{
    boolean isTriggered();
}
class motiontrigger implements Trigger{
    public boolean isTriggered(){
        System.out.print("motion is detected");
        return true;
        
    }
}
class temptrigger implements Trigger{
       public boolean isTriggered(){
        System.out.print("temperature  is greater than 30");
        return true;
        
    }   
}
class timetrigger implements Trigger{
       public boolean isTriggered(){
        System.out.print("time  is greater than 30");
        return true;
        
    }   
}
interface action{
    void execute();
}
class turnon implements action{
    application device;
    turnon(application device){
        this.device = device;
    }
    public void execute(){
        device.turnon();
    }
}
class turnoff implements action{
    application device;
    turnoff(application device){
        this.device = device;
    }
    public void execute(){
        device.turnoff();
    }
}
class rule{
    Trigger trigger;
    action action;
    rule(Trigger trigger, action action){
    this.trigger = trigger;
    this.action = action;
}
    void result(){
        if(trigger.isTriggered()){
            action.execute();
        }
        else{
            System.out.print("Trigger condition not met");
        }
    }

}



public class smarthome {
    public static void main(String[] args) {
        application lightDevice = new light();
        application acDevice = new AC();

        Trigger motion = new motiontrigger();
        Trigger temp = new temptrigger();

        action lightOn = new turnon(lightDevice);
        action acOn = new turnon(acDevice);

        rule r1 = new rule(motion, lightOn);
        rule r2 = new rule(temp, acOn);

        r1.result();
        System.out.println();
        r2.result();

    }
}