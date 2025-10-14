public class ThreadPool {
    static int[] data = new int[100_000];
    static long[] results = new long[10];

    static class SumThread extends Thread {
        int start;
        int end;
        int id;

        SumThread(int id, int start, int end) {
            this.id = id;
            this.start = start;
            this.end = end;
        }

        public void run() {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += data[i];

                if (i % 10000 == 0) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        // Error
                    }
                }
            }

            results[id] = sum;
            System.out.println("Thread " + id + " finished with sum " + sum);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < data.length; i++) {
            data[i] = 1;
        }

        int numThreads = 10;
        int blockSize = data.length / numThreads;
        Thread[] pool = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            int start = i * blockSize;
            int end;
            
            if (i == numThreads - 1) {
                end = data.length;
            } else {
                end = (i + 1) * blockSize;
            }

            pool[i] = new SumThread(i, start, end);
            pool[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            pool[i].join();
        }

        long total = 0;
        for (int i = 0; i < numThreads; i++) {
            total += results[i];
        }

        System.out.println("Total sum: " + total);
    }
}