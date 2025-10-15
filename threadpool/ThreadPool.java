package threadpool;

import java.util.LinkedList;
import java.util.Queue;

public class ThreadPool {
    private final Worker[] workers;
    private final Queue<Runnable> taskQueue;
    private volatile boolean isRunning = true;

    public ThreadPool(int numThreads) {
        taskQueue = new LinkedList<>();
        workers = new Worker[numThreads];

        for (int i = 0; i < numThreads; i++) {
            workers[i] = new Worker();
            workers[i].setName("Worker-" + i);
            workers[i].start();
        }
    }

    public synchronized void submit(Runnable task) {
        taskQueue.offer(task);
        notify();
    }

    private synchronized Runnable getTask() throws InterruptedException {
        while (taskQueue.isEmpty() && isRunning) {
            wait();
        }
        return taskQueue.poll();
    }

    public synchronized void shutdown() {
        isRunning = false;
        notifyAll();
    }

    private class Worker extends Thread {
        public void run() {
            while (isRunning) {
                try {
                    Runnable task = getTask();
                    if (task != null)
                        task.run();
                } catch (InterruptedException ignored) {}
            }
        }
    }

    public static long runPool() throws InterruptedException {
        int dataSize = 100000;
        int blockSize = 10000;
        int[] data = new int[dataSize];
        for (int i = 0; i < dataSize; i++)
            data[i] = 1;

        int numThreads = 10;
        int numTasks = (dataSize + blockSize - 1) / blockSize;
        long[] results = new long[numTasks];

        ThreadPool pool = new ThreadPool(numThreads);

        for (int i = 0; i < numTasks; i++) {
            int start = i * blockSize;
            int end = Math.min(dataSize, start + blockSize);
            pool.submit(new SumThread(i, start, end, data, results));
        }

        Thread.sleep(6000);

        pool.shutdown();

        long total = 0;
        for (int i = 0; i < numTasks; i++)
            total += results[i];
        return total;
    }
}
