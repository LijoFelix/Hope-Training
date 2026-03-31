class BankAccount{
    private double balance;
    public void setBalance(double balance){
        if(balance>0){
        this.balance=balance;
        }
        else{
            System.out.print("Input not valid");
        }
    }

    public void withdraw(int amount){
          if(amount>0&&amount<=balance){
            balance-=amount;
            System.out.println("Amount debited:"+amount);
          }
          else{
            System.out.println("invalid amount entered");
          }
    }
     public void deposit(int amount){
          if(amount>0){
            balance+=amount;
            System.out.println("Amount credited:"+amount);
          }
          else{
            System.out.println("invalid amount entered");
          }
    }

    
    

    public double getBalance(){
        return balance;
    }
}

public class encap1 {
    public static void main(String[] args) {
        BankAccount b=new BankAccount();
        b.setBalance(1000);
        System.out.println(b.getBalance());
        b.withdraw(200);
        b.deposit(100);
        b.withdraw(-2000);
    }
}