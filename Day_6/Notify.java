class Notification
{   String name;
    String message;
    Notification(String name,String message)
    {
        this.name = name;
        this.message = message;
    } 
    void send()
    {
        System.out.println("Notification sent");
    }
}
class EmailNotification extends Notification
{
    EmailNotification(String name,String message)
    {
        super(name,message);
    }   
    @Override
    void send()
    {
        System.out.println("Sending email to "+name+" with message: "+message);
    }  
}
class SMSNotification extends Notification
{
    SMSNotification(String name,String message)
    {
        super(name,message);
    }
    @Override
    void send()
    {
        System.out.println("Sending SMS to "+name+" with message: "+message);
    }   
}           
public class Notify
{
    public static void main(String[] args) {
        Notification notification;
        notification= new EmailNotification("John Doe", "Hello, John! This is an email notification."); //left side reference variable of parent class and right side object of child class
        notification.send(); // Output: Email notification sent
        notification= new SMSNotification("Jane Smith", "Hello, Jane! This is an SMS notification.");
        notification.send(); // Output: SMS notification sent
    }
}