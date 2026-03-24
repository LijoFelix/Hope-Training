class User
{
    String name;
    String phone;
    String address;
    User()
    {
        System.out.println("Using default constructor of User class");
    }
}
class FoodUser extends User
{
    String favouriteFood;
    FoodUser()
    {
        System.out.println("Using default constructor of FoodUser class");
    }
}
class TravelUser extends User
{
    String preferredVehicle;
    TravelUser()
    {
        System.out.println("Using default constructor of TravelUser class");
    }

}
public class App{
    public static void main(String[] args)
    {
        User u1=new User();
        FoodUser fu1=new FoodUser();
        TravelUser tu1=new TravelUser();
        System.out.println("User name: "+u1.name);
        System.out.println("Food User name: "+fu1.name);    
        System.out.println("Travel User name: "+tu1.name);  
    }
}
