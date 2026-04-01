public class CompileTimeException{
    public static void main(String[] args) {
        Thread t=new Thread(()->{
        try {
            System.out.println("Thread sleep");
            Thread.sleep(2000);
            System.out.println("Thread wake");
        } catch (InterruptedException e) {
            //TODO:Handle exception
            System.out.println("Sleep interrupted");
        }
    });
    t.start();
    try {
        Thread.sleep(6000);
        System.out.println("main method thread");
    } catch (InterruptedException e) {
        //TODO:handle exception
        System.out.println("Not T");
    }
    t.interrupt();
    }
}