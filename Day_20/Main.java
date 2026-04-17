class SampleThread extends Thread{
    @Override
    public void run(){
       System.out.println("Thread running using Thread class");
    }
}
class SampleThread2 implements Runnable{
    @Override
    public void run(){//3. running state
     try{
         System.out.println("Thhread running usable  Runnable");
         Thread.sleep(4000); //4.blocked / waiting state
         System.out.println("After 4 seconds");
     }
     catch(Exception e){
         System.out.println(e);
     }
        System.out.println("Thread running using Runnable");
    }
}
public class Main {
    public static void main (String[] args){
          SampleThread t = new SampleThread();
          t.start();// we should give start() method not run() method.
          Thread th = new Thread(new SampleThread2());//1.new state
          // or samplethread2 s2 = new SampleThread(); , thread th = new thread(s2).
          th.start();// 2.runnable state
    }
    
}