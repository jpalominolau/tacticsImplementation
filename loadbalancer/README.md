# Load Balancer Prototype

This module provides a prototype implementation of a load balancer using Java.
It simulates basic load balancing functionality by distributing incoming requests to a pool of backend servers.

## How to Run

1. Ensure you have Java installed on your machine (Java 23 or higher recommended).
2. Clone the repository to your local machine.
3. Navigate to the project directory (tacticsImplementation/loadBalancer).
4. Compile the Java files using the following command:

    ```console
    javac *.java
    ```

5. Run the load balancer simulation:

    ```console
    java Main
    ```

## Architectural approach

The primary goal of this solution is to maximize the system's performance, ensuring that thousands of inventory events and messages are handled in real time, efficiently and reliably. This is especially critical for regional supermarkets chains where accurate up to the minute stock information is essential for preventing stock-outs, reducing waste and improving operational efficiency. Modern inventory management in supermarkets depends on seamless integration between real time tracking, point of sale systems and cloud platforms enabling immediate visibility into stock levels for timely replenishment and smart decision making.

Several classic resource management tactics are implemented in the architecture, with the load balancer pattern at the core. This approach directly focuses on performance by addressing the core demands of distributed inventory management such as scalability, resilience and coordinated data processing across multiple stores or distribution centers. These demands are critical performance drivers for handling high volumes of real time inventory data effectively.

### Increasing and distributing resources

The solution uses a set of servers to process requests which allows horizontal scaling as more servers can be dynamically added when the load increases. This is essential for operations spanning many stores or  distribution points. The load balancer distributes requests evenly avoiding overload of any single resource and improving response time. This guarantees that spikes in traffic (e.g. during sales or busy seasons) are absorbed without degrading performance. Distributed resources reduce the risk of bottlenecks and ensure system throughput during variable load.

* In the code this is represented by ```servers``` list in ```Main.java``` which can be extended dynamically as needed.

### Introducing concurrency

Processing requests in parallel reduces blocked time and increases overall system throughput. This is vital for inventory platforms that face high message volumes from many stores simultaneously such as real time stock updates and order placements. Each request runs on a separate thread (```RequestThread```), leveraging multi-core CPUs to boost processing capacity and reduce latency.

* This implemented in the ```RequestThread``` class where each request runs concurrently, demonstrating the concurrency tactic.

### Computation replication

Replicating services enables the workload to be divided among several instances, using the load balancer pattern to distribute requests. This prevents bottlenecks and maintains high throughput even if certain nodes fail. For regional supermarkets, replication ensures continuous performance by rerouting requests to active instances seamlessly.

* This tactic is reflected by the load balancer distributing requests across multiple server instances listed in ```servers```.

### Queue size control

Maintaining stable performance requires managing the queue sizes to avoid request accumulation beyond processing capacity, which would increases latency. The load balancer serves as a arbitrator to distribute requests evenly and prevent overload.

* Although the current code doesn't enforce strict queue limits. It can be extended with overflow management and queuing policies to sustain performance under heavy load.

### Resources scheduling

Effective scheduling policies reduce contention and resource conflicts, further improving performance. The implemented round robin scheduling (```getNextServer```) distributes requests evenly among servers, maximizing throughput and avoiding bottlenecks or resource exhaustion.

* The ```getNextServer()``` method uses a simple but efficient round robin algorithm, exemplifying resource scheduling. Round robin is a simple scheduling method where tasks are assigned one by one in a fixed repeating order. Each task get a turn before the cycle repeats, ensuring fair and equal distribution of resources.

## Design Diagrams

The load balancer prototype is designed using the following UML diagrams:

### Class Diagram

![Class Diagram](diagrams/load_balancer_class_diagram.png)

This diagram illusttrate a simple load balancer simulation. The ```Main``` class servers as the entry point, containing the ```main``` method and a static method ```startLoadBalancerDemo()```. The ```Main``` class creates instanes of the ```LoadBalancer``` class, which maintains a list of server names and the current index for load balancing and provides a synchronized method ```getNextServer()``` to retrieve the next server in a thread safe manner. The ```Main``` class also starts multiple ```RequestThread``` instances, each representing a request handled by the load balancer. Each ```RequestThread``` holds a reference to the ```LoadBalancer``` and a unique request ID and its ```run()``` method executes the request logic. The ```RequestThread``` class extends the ```Thread``` class, allowing each request to run concurrently.

### Sequence Diagram

![Sequence Diagram](diagrams/load_balancer_sequence_diagram.png)

The sequence diagram illustrates the round-robin load balancing mechanism in action, showing how the Main Class initializes the LoadBalancer with three servers and spawns multiple RequestThreads that concurrently access the synchronized getNextServer() method. The diagram demonstrates the thread-safe distribution of requests across servers in a cyclical pattern, with each thread obtaining its assigned server through the load balancer's modulo-based indexing system that ensures even distribution while maintaining synchronization under concurrent access.

### Activity Diagram

![Activity Diagram](diagrams/load_balancer_activity_diagram.png)

The activity diagram outlines the workflow of the load balancer prototype, starting from the initialization of the LoadBalancer with a list of servers. It demonstrates the flow of the program from the client to the load balancer and then to the selected server for request processing. This activity repeats as long as there are incoming requests.
