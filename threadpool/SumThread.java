package threadpool;

public class SumThread implements Runnable {
    private int start;
    private int end;
    private int id;
    private int[] data;
    private long[] results;
    private long[] times;

    public SumThread(int id, int start, int end, int[] data, long[] results, long[] times) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.data = data;
        this.results = results;
        this.times = times;
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        long sum = 0;
        String threadName = Thread.currentThread().getName();
        for (int i = start; i < end; i++) {
            sum += data[i];
            if (i % 100 == 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {}
            }
        }
        long endTime = System.nanoTime();
        long durationMillis = (endTime - startTime) / 1_000_000;
        synchronized(results) {
            results[id] = sum;
        }
        synchronized(times) {
            times[id] = durationMillis;
        }
        System.out.println("Task " + id + " finished with sum " + sum + " by " + threadName + " in " + durationMillis + " ms");
    }
}
