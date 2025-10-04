# Heartbeat
The heartbeat monitors a critical autonomous vehicle subsystem such as navigation and perception. It ensures the subsystem is functioning by detecting missing heartbeat signals. This helps identify faults early and maintain safe vehicle operation.

## Expected behavior
2. The program will print heartbeat send/receive messages
3. The program stops if three consecutive heartbeats are missed, indicating a critical fault

## Class diagram

The class diagram models the structure of the heartbeat fault-detection system.

* HeartbeatMonitor is the orchestrator. It holds references to Sender, Receiver, and Monitor, and manages the execution loop.

* Sender is responsible for generating a heartbeat using the send() method. It may sometimes fail, simulating a fault in the control module.

* Receiver accepts the signal through the receive() method and returns whether the heartbeat was successfully delivered.

* Monitor maintains a counter of consecutive missed heartbeats and checks the overall health of the system through the checkStatus() method. If the number of missed beats exceeds the threshold, it declares a critical fault.

![Class Diagram](Heartbeat_Class.png)

## Sequence diagram

The sequence diagram illustrates the runtime behavior of the heartbeat tactic across multiple iterations:

1. The System triggers the runLoop() method in HeartbeatMonitor.

2. In each cycle:

* HeartbeatMonitor calls Sender.send(), which produces a boolean signal (true = heartbeat sent, false = missed).

* The signal is passed to Receiver.receive(), which returns whether the signal was delivered.

* HeartbeatMonitor then calls Monitor.checkStatus(). The monitor updates its internal counter:

    - If the heartbeat is delivered → the counter is reset.

    - If the heartbeat is missed → the counter is incremented.

3. When the counter of missed heartbeats reaches 3 (or the configured threshold), Monitor declares a Critical Fault, and the system stops or raises an alert.

This sequence demonstrates how the heartbeat tactic detects failures by continuously monitoring a control process and reacting quickly if it becomes unresponsive.

![Sequence Diagram](Heartbeat_Sequence.png)

## Activity diagram
This Activity Diagram shows the activation process and fault recovery of both Replicas within the Vehicle Subsystem

- The Fault Recovery Starts and checks to see if there are Replicas that are active; if there are none, it shuts off and goes to its final state
- If there are replicas, it will go to the Active Primary Replica Placement and begin running the Heartbeat Function
- Once the function completes, it will send the R1 (Replica 1) status back to the Fault Recovery
- If Replica 1 is offline, then it will be sent a deactivation command and the activation command to Replica 2
- Replica 2 will go through the Heartbeat Function in R1's stead, ultimately sending back its status.
- It shows two backup systems taking turns sending and receiving status signals to confirm each is working and switch control if a failure occurs. This supports fault detection and recovery in the vehicle navigation or perception subsystem.

With that, the fault detector within the Vehicle Navigation or the Perception Subsystem will constantly stay online, depending on the number of replica heartbeats one has, while the others can be fixed in the interim


![Fault recovery activity diagram](./assets/HeartbeatReplicaActivity.png)
