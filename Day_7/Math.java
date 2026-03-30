class Calculator
{
    int add(int a, int b)
    {
        return a+b;
    }
    int add(int a, int b, int c)
    {
        return a+b+c;
    }
    double add(double a, double b)
    {
        return a+b;
    }
}
public class Math
{
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        int sum = calc.add(5, 3);
        System.out.println("Sum: " + sum);
        int sumThree = calc.add(5, 3, 2);
        System.out.println("Sum of three numbers: " + sumThree);
        double sumDecimal = calc.add(5.5, 3.3);
        System.out.println("Sum of decimal numbers: " + sumDecimal);
    }
}
    
