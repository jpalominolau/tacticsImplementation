package faultrecovery;
import java.util.Random;

import heartbeatmonitor.Monitor;
import heartbeatmonitor.Receiver;
import heartbeatmonitor.Sender;

public class Replica {
    private String name;
    private boolean active;
    private Sender sender;
    private Receiver receiver;
    private Monitor monitor;
    private int missed;
    private final int maxMissed;
    private int heartbeatCount;
    private boolean crashed;

    public Replica(String name, boolean active, int maxMissed) {
        this.name = name;
        this.active = active;
        this.sender = new Sender();
        this.receiver = new Receiver();
        this.monitor = new Monitor();
        this.missed = 0;
        this.maxMissed = maxMissed;
        this.heartbeatCount = 0;
        this.crashed = false;
    }

    public boolean runHeartbeat(Random random) {
        if (crashed) {
            System.out.println(name + " is crashed, heartbeat stopped.");
            return false;
        }
        
        heartbeatCount++;
        boolean sent = sender.send(heartbeatCount, random);
        boolean received = receiver.receive(sent);

        if (!received) {
            missed++;
        } else {
            missed = 0;
        }

        boolean status = monitor.checkStatus(missed, maxMissed);

        if (!status) {
            crashed = true;
        }

        return !crashed;
    }

    public void activate() {
        active = true;
        crashed = false;
        missed = 0;
        heartbeatCount = 0;
        System.out.println(name + " activated");
    }

    public void deactivate() {
        active = false;
        System.out.println(name + " deactivated");
    }

    public boolean isActive() {
        return active;
    }
}
