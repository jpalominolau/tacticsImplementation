package threadpool;

public class ThreadPool {
    private static int[] data = new int[100_000];
    private static long[] results = new long[10];

    public static long runPool() throws InterruptedException {
        for (int i = 0; i < data.length; i++) {
            data[i] = 1;
        }

        int numThreads = 10;
        int blockSize = data.length / numThreads;
        Thread[] pool = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            int start = i * blockSize;
            int end = (i == numThreads - 1) ? data.length : (i + 1) * blockSize;
            pool[i] = new SumThread(i, start, end, data, results);
            pool[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            pool[i].join();
        }

        long total = 0;
        for (int i = 0; i < numThreads; i++) {
            total += results[i];
        }

        return total;
    }
}
