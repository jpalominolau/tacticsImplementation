# Fault recovery
Fault recovery ensures continous operation of critical autonomous vehicle subsystems like navigation and perception by switching to backup components or restoring system state after failure. This maintains satefy and reliability in real time vehicle control. The recovery mechanism is key to fault tolerant autonomous driving systems.

## Expected behavior
1. The program will print heartbeat send/receive messages for both primary and secondary replicas
2. When the primary replica fails, the secondary activates and takees over sending heartbeats
3. The system stops if both replicas fail, indicating a critical fault that cannot be recovered automatically

## Class diagram
![Fault recovery class diagram](./assets/FaultRecoveryClass.png)

This simplified representation of a cold spare is based on the Heartbeat tactic.
This behavior is encapsulated in `Replica`, which mimics the ability of initializing
a new process and monitoring its behavior. The `FaultRecovery` class maintains two
`Replica`s. While the primary is active, the secondary is guarenteed put into a cold state with
`deactivate`. If/when the primary `Replica` fails, the secondary is activated but given no
shared state information other than a `Random` instance. Instead, it picks up with no prior knowledge. This
simplifies the `FaultRecovery` implemenation at the expense of theoretical start time and lost information.

## Sequence diagram
The following diagram tracks the synchronous message flow between classes. In practice, these could be replaced
with asynchronous messages across a network.

![Fault recovery sequence diagram](./assets/FaultRecoverySeq.png)
