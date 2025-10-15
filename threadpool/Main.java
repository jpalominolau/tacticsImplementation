package threadpool;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        long total = ThreadPool.runPool();
        System.out.println("Total sum: " + total);
    }
}
