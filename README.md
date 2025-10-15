# Tactics implementation

## Requirements
- Ensure you have JDK installed (version 23 or higher recommended)

## How to compile and run
From the root of this repository you will find two separate project folders:
- heartbeat/ - Prototype implementing the heartbeat tactic
- faultrecovery/ - Prototype extending detection to include fault recovery tactic
- threadpool/ - Prototype of thread pool tactic

## Compiling projects
Navigate to the root directory and compile each project separately as follows:

```
javac heartbeat/*.java
javac faultrecovery/*.java
javac threadpool/*.java
```

## Running projects
Run the main class of each project by specifiying its full package and class name for example (Use gitbash to run the project):

```
java heartbeat.Heartbeat
java faultrecovery.FaultRecovery
java threadpool.Main
```
