# Thread Pooling
The implemented ThreadPool manages a fixed set of 10 worker threads that execute submitted SumTread tasks. Each SumTread is responsible for summing a specific portion of a large interger array. The pool distributes the workload by submitting multiple tasks to the queue and the worker threads fetch and execute them concurrently. Each task simulates computational effort by performing intermittent delays and stores its partial result safely in shared data structures. This design demonstrates and efficient multithreading apporach where a reusable thread pool handles multiple computational tasks in parallel.

## Expected behavior
1. The ThreadPool initializes 10 workers threads ready to execute tasks concurrently
2. The program divides the array into multiple segments and creates one SumThread task per segment
3. Each SumThread computes the partial sum of its assigned segment simulating workload with delays
4. Worker threads pick up available tasks from the queue and run them independently
5. After all tasks complete the ThreadPool aggregates the partial results and returns the total sum to the main program

## Activity diagram
![](activity_diagram_thread_pooling.png)

This diagram ilustrates how the Main process uses a ThreadPool to execute multiple summation tasks concurrently. The Main component starts by invoking the ThreadPool, which initializes its data and parameters, creates worker threads and submits ten SumThread tasks to the queue. Each SumThread computes a partial sum, records its execution time and stores the result in a synchronized manner to ensure thread satefy.

After all tasks finish the ThreadPool waits for completion, shut down the workers, aggregates the partial results and returns the total sum to the Main process. Finally the Main component displays the computed total completing the workflow.

## Sequence diagram
![](sequence_diagram_thread_pooling.png)

The main program calls ThreadPool.runPool(), where 10 worker threads are initialized. A for loop creates SumThread tasks that implement Runnable and submits them to the thread pool. These worker threads continuously fetch tasks from the shared queue and execute them in parallel. Each SumThread computes the total of its assigned block and records the execution time; both the computed sums and their execution times are stored in synchronized shared arrays to prevent race conditions.

After allowing the pool to shut down gracefully (by sleeping for 6 seconds), the workers finish any pending tasks and all worker threads terminate. Finally, all partial results are aggregated into a final sum, which is printed in the main program.
