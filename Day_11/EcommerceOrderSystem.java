public class EcommerceOrderSystem {
    public static void main(String[] args) {
        Product laptop = new Product("Laptop", 5, 999.99);
        Product phone = new Product("Phone", 0, 499.99);
        User alice = new User("Alice", "123 Main St");
        User bob = new User("Bob", "456 Elm Ave");

        OrderService orderService = new OrderService();

        // Successful order
        Order successfulOrder = new Order(laptop, alice, 2);
        try {
            orderService.placeOrder(successfulOrder);
        } catch (OrderException e) {
            System.out.println(e.getMessage());
        }

        // Product out of stock
        Order outOfStockOrder = new Order(phone, bob, 1);
        try {
            orderService.placeOrder(outOfStockOrder);
        } catch (OrderException e) {
            System.out.println(e.getMessage());
        }

        // Payment failure
        Order expensiveOrder = new Order(laptop, alice, 3);
        try {
            orderService.placeOrder(expensiveOrder);
        } catch (OrderException e) {
            System.out.println(e.getMessage());
        }

        // Order processing failed
        Order invalidOrder = new Order(null, alice, -1);
        try {
            orderService.placeOrder(invalidOrder);
        } catch (OrderException e) {
            System.out.println(e.getMessage());
        }
    }
}

class Product {
    private String name;
    private int stock;
    private double price;

    public Product(String name, int stock, double price) {
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public void reduceStock(int quantity) {
        this.stock -= quantity;
    }
}

class User {
    private String name;
    private String address;

    public User(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}

class Order {
    private Product product;
    private User user;
    private int quantity;

    public Order(Product product, User user, int quantity) {
        this.product = product;
        this.user = user;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public User getUser() {
        return user;
    }

    public int getQuantity() {
        return quantity;
    }
}

class OrderService {
    public void placeOrder(Order order) throws OrderException {
        if (order == null || order.getProduct() == null || order.getUser() == null) {
            throw new OrderProcessingFailedException("Order processing failed: order, product or user cannot be null.");
        }
        if (order.getQuantity() <= 0) {
            throw new OrderProcessingFailedException("Order processing failed: quantity must be greater than zero.");
        }

        Product product = order.getProduct();
        User user = order.getUser();
        int quantity = order.getQuantity();

        if (product.getStock() < quantity) {
            throw new ProductOutOfStockException("Product out of stock: requested " + quantity + " but only " + product.getStock() + " available.");
        }

        double total = product.getPrice() * quantity;
        if (!processPayment(user, total)) {
            throw new PaymentFailureException("Payment failed for " + user.getName() + ". Total amount: $" + String.format("%.2f", total));
        }

        try {
            product.reduceStock(quantity);
            System.out.println("Order placed successfully: " + quantity + " x " + product.getName() + " for " + user.getName() + ". Total: $" + String.format("%.2f", total));
        } catch (Exception e) {
            throw new OrderProcessingFailedException("Order processing failed while updating stock.");
        }
    }

    private boolean processPayment(User user, double amount) {
        // Simulated payment logic: fail when amount exceeds $2500
        return amount <= 2500;
    }
}

abstract class OrderException extends Exception {
    public OrderException(String message) {
        super(message);
    }
}

class ProductOutOfStockException extends OrderException {
    public ProductOutOfStockException(String message) {
        super(message);
    }
}

class PaymentFailureException extends OrderException {
    public PaymentFailureException(String message) {
        super(message);
    }
}

class OrderProcessingFailedException extends OrderException {
    public OrderProcessingFailedException(String message) {
        super(message);
    }
}