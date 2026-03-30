class User{
    String name;
    String phone;
    String location;
    User(String name,String phone,String location){
        this.name=name;
        this.phone=phone;
        this.location=location;
    }
    void displayinfo(){
         System.out.println("------------USER DETAILS-----------------");
        System.out.println("Name: "+name);
        System.out.println("Phone: "+phone);
        System.out.println("Location: "+location);
    }
}

class RideUser extends User{
    String vehicle;
    RideUser(String name,String phone,String location,String vehicle){
       super(name,phone,location);
       this.vehicle=vehicle;
    }
    void displayride(){
        super.displayinfo();
        System.out.println("Vehicle Type: "+vehicle);
    }
}
class FoodUser extends User{
    String favfood;
    FoodUser(String name,String phone,String location,String favfood){
       super(name,phone,location);
       this.favfood=favfood;
    }
    void displayfood(){
        super.displayinfo();
        System.out.println("Favorate Food: "+favfood);
    }
}

class ParcelUser extends User{
    String weight;
    ParcelUser(String name,String phone,String location,String weight){
       super(name,phone,location);
       this.weight=weight;
    }
    void displayparcel(){
        super.displayinfo();
        System.out.println("ParcelWeight: "+weight);
    }
}

public class DiffUser{
    public static void main(String[] args) {
        
        FoodUser u1= new FoodUser( "user1", "999", "chennai", "Briyani");
        u1.displayfood();
        RideUser u2= new RideUser( "user2", "998", "chennai", "Bike");
        u2.displayride();
        ParcelUser u3= new ParcelUser( "user3", "997", "chennai", "90kg");
        u3.displayparcel();
    }
}