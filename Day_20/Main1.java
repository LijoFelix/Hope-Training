class SampleThread extends Thread{
    String name;
    @Override
    public void run(){
        for(int i=0;i<=5;i++){
       System.out.println("name" + i);}
    }
    SampleThread(String name){
        this.name = name;
    }
}


public class Main1 {
    public static void main(String[] args) {
        SampleThread s1 = new SampleThread("Task1");
         SampleThread s2 = new SampleThread("Task2");
         s1.start();
         s2.start();

    }
}