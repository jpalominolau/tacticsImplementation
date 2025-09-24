# Heartbeat Monitoring Prototype

## About
This is a simple Java prototype implementing the "Heartbeat" tactic for fault detection in an autonomous vehicle control module. The heartbeat monitors whether the control process is alive by detecting missed heartbeats and simulating non deterministic failure

## How to Compile
1. Ensure you have JDK installed (version 23 or higher recommended)
2. Open command prompt or terminal
3. Navigate to the directory containing the code and compile all using the next command:

```
javac *.java
```

## How to Run
1. After compilation, run the program with:

```
java HeartbeatMonitor
```

2. The program will print heartbeat send/receive messages
3. The program stops if three consecutive heartbeats are missed, indicating a critical fault

## Dependencies
- Java Standard Library (no external libraries or frameworks used)
- Uses `java.util.Random` for simulating failure

## Project Structure
- `HeartbeatMonitor.java` - Main class with heartbeat simulation, sender/receiver/monitor logic

## Class diagram

## Sequence diagram
