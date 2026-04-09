import java.util.*;
class Product{
    double rating;
    String name;
    double price;
    public Product(double rating,String name,double price){
        this.rating=rating;
        this.name=name;
        this.price=price;
    }
    @Override
    public String toString(){
        return rating+":"+name+":"+price;
    }
}
public class ComparatorProd {
    public static void main(String[] args) {
        List<Product> products=new ArrayList<>();
        products.add(new Product(4.5, "Laptop", 90000));
        products.add(new Product(4.2, "Phone", 80000));
        products.add(new Product(4.0, "Tablet", 30000));
        products.add(new Product(4.8, "Watch", 10000));
        Comparator<Product> byPrice=new Comparator<Product>() {
            public int compare(Product a, Product b) {
                return Double.compare(a.price, b.price);
            }
        };
        Collections.sort(products, byPrice);
        System.out.println("Products sorted by price: " + products);
        Collections.sort(products, (a,b)->a.name.compareTo(b.name));
        System.out.println("Products sorted by name: " + products);
        Collections.sort(products, (a,b)->Double.compare(b.rating, a.rating));
        System.out.println("Products sorted by rating (descending): " + products);
    }
}