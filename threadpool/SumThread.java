package threadpool;

public class SumThread extends Thread {
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

    public void run() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += data[i];
            if (i % 10000 == 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {}
            }
        }
        results[id] = sum;
        System.out.println("Thread " + id + " finished with sum " + sum);
    }
}
