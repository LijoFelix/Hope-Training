class Product
{
    int id;
    String name;
    double price;
    int quantity;

    Product(int id,String name,double price,int quantity)
    {
        this.id=id;
        this.name=name;
        this.price=price;//this is used to refer the current object instance variable when there is a naming conflict between instance variable and local variable or parameter variable
        this.quantity=quantity;
    }
    double getTotalPrice()
    {
        return price*quantity;
    }
    void display()
    {
        System.out.println("product id: "+id);
        System.out.println("product name: "+name);
        System.out.println("product price: "+price);    
        System.out.println("product quantity: "+quantity);
        System.out.println("total Price: "+getTotalPrice());
    }
    
}
public class Cart{
    public static void main(String[] args) {
        Product p1=new Product(101,"Laptop",50000,2);
        Product p2=new Product(102,"Smartphone",20000,3);
        Product p3=new Product(103,"Headphones",3000,5);
        Product[] cart={p1,p2,p3};
        double grandTotal=0.0;
        for(Product p:cart)
        {
            p.display();
            System.out.println("-----------------------------------------------------------");
            grandTotal+=p.getTotalPrice();
        }
        System.out.println("Grand Total Price:"+grandTotal);
    }
}