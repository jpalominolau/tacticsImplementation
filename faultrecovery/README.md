# Fault recovery
Fault recovery ensures continous operation of critical autonomous vehicle subsystems like navigation and perception by switching to backup components or restoring system state after failure. This maintains satefy and reliability in real time vehicle control. The recovery mechanism is key to fault tolerant autonomous driving systems.

## Expected behavior
1. The program will print heartbeat send/receive messages for both primary and secondary replicas
2. When the primary replica fails, the secondary activates and takees over sending heartbeats
3. The system stops if both replicas fail, indicating a critical fault that cannot be recovered automatically

## Class diagram

The diagram show how backup components (Called "replicas") use simple heartbeat signals to detect failures.

- There is a main class called FaultRecovery, which manages the whole process.
- Each replica contains a sender, receiver and monitor to send and check heartbeat signals.
- If a heartbeat is missed too many times, the system knows something failed and can try to recover by switching replicas.

This way, the vehicle can quickly find problems in navigation or perception and use backups to stay safe and reliable.

![Sequence Diagram](class_diagram.png)

## Sequence diagram

This sequence diagram shows the hearbeat process for fault recovery between two replicas in the vehicle subsystem.

- The fault recovery system first activates a primary and secondary replica and starts its heartbeat
- If replica 1 is active, it sends a hearbeat signal via sender 1. A receiver checks if the signal was successfully sent and received
- The system tracks whether sending or receiving the heartbeat fails
- If replica 1 becomes inactive and replica 2 is inactive, replica 2 activates and run its heartbeat similarly using sender 2 and receiver 2.
- This process continously loops, checking the status of each replica to detect faults and ensure one replica is always active.

It shows two backup system take turns sending and receiving status signals to confirm each is working and switch control if a failure occurs. This supports fault detection and recovery in the vehicle navigation or perception subsystem.

![Sequence Diagram](sequence_diagram.png)
