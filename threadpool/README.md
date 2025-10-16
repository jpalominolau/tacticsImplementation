# Thread Pooling
The implemented ThreadPool create a fixed set of 10 threads (SumThread) that collectively sum different portions of a large array of integers. Each thread is assigned a segment of the array, performs a time consuming sum operation simulating work by sleeing intermittently and stores its partial result. The threads are started once and reused for their assigned work, representing a basic thread pool prototype that manages threads efficiently for a performance critical task.

## Expected behavior
1. Exactly 10 threads are created and started, each handeling a unique portion of the array
2. Each thread performs a substantial computation task including simulated delays, ensuring the task is time consuming
3. Threads operate concurrently, maximazing CPU utilization by processing different segments simultaneously
4. Threads execute their task independently, then store results in a shred structure for aggregation
5. The main program waits until all threads complete, then sums their results to output the total sum of the array

## Activity diagram
![](activity_diagram_thread_pooling.png)

This diagram ilustrates how the Main process uses a ThreadPool to execute multiple summation tasks concurrently. The Main component starts by invoking the ThreadPool, which initializes its data and parameters, creates worker threads and submits ten SumThread tasks to the queue. Each SumThread computes a partial sum, records its execution time and stores the result in a synchronized manner to ensure thread satefy.

After all tasks finish the ThreadPool waits for completion, shut down the workers, aggregates the partial results and returns the total sum to the Main process. Finally the Main component displays the computed total completing the workflow.

## Sequence diagram
![](sequence_diagram_thread_pooling.png)

The main program calls ThreadPool.runPool(), where 10 worker threads are initialized. A for loop creates SumThread tasks that implement Runnable and submits them to the thread pool. These worker threads continuously fetch tasks from the shared queue and execute them in parallel. Each SumThread computes the total of its assigned block and records the execution time; both the computed sums and their execution times are stored in synchronized shared arrays to prevent race conditions.

After allowing the pool to shut down gracefully (by sleeping for 6 seconds), the workers finish any pending tasks and all worker threads terminate. Finally, all partial results are aggregated into a final sum, which is printed in the main program.
