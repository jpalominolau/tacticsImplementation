# Tactics implementation

## How to compile and run
From the root of this repository you will find two separate project folders:
- heartbeatmonito/ - Prototype implementing the heartbeat tactic
- faultrecovery/ - Prototype extending detection to include fault recovery tactic

## Compiling projects
Navigate to the root directory and compile each project separately as follows:

```
javac heartbeatmonitor/*.java
javac faultrecovery/*.java
```

## Running projects
Run the main class of each project by specifiying its full package and class name for example:

```
java heartbeatmonitor.HeartbeatMonitor
java faultrecovery.FaultRecovery
```