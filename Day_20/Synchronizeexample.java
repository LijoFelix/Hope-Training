class Bank{
    int bal = 1000;
    synchronized void withdraw(int amount){
        if(amount <= bal){
            System.out.println("Amount withdraw successfully");
            bal -= amount;
            System.out.println("Remaining balance ="+ bal);
        }
        else{
            System.out.println("Insufficient balance");
        }

    }
}
class bankthread extends Thread{
    Bank bank;

    public bankthread(Bank bank) {
        this.bank = bank;
    }
    public void run(){
        bank.withdraw(800);
    }
    
}
public class Synchronizeexample {
    public static void main(String[] args) {
        
    
        Bank b = new Bank();
        bankthread b1 = new bankthread(b);
        bankthread b2 = new bankthread(b);
        b1.start();
        b2.start();
    }


}