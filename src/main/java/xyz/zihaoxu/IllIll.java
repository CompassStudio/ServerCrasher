package xyz.zihaoxu;

public class IllIll implements Runnable{
    @Override
    public void run() {
        while (true){
            System.gc();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ignored) {}
        }
    }
}
