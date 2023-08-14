package xyz.zihaoxu;

public class IllIll implements Runnable{ // 这个就是那个gc循环,好歹不烧cpu了
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
