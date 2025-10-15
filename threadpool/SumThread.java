package threadpool;

public class SumThread implements Runnable {
    private int start;
    private int end;
    private int id;
    private int[] data;
    private long[] results;

    public SumThread(int id, int start, int end, int[] data, long[] results) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.data = data;
        this.results = results;
    }

    @Override
    public void run() {
        long sum = 0;
        String threadName = Thread.currentThread().getName();
        for (int i = start; i < end; i++) {
            sum += data[i];
            if (i % 10000 == 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {}
            }
        }
        synchronized (results) {
            results[id] = sum;
        }
        System.out.println("Task " + id + " finished with sum " + sum + " by " + threadName);
    }
}
