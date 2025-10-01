package heartbeatmonitor;
import java.util.Random;

public class Heartbeat {

    public static void main(String[] args) {
        Random random = new Random();
        int total = 0;
        int missed = 0;
        int max = 3;

        Sender sender = new Sender();
        Receiver receiver = new Receiver();
        Monitor monitor = new Monitor();

        while (true) {
            total++;
            
            boolean sent = sender.send(total, random);
            if (!sent) {
                System.out.println("System failed at Sender.");
            }

            boolean received = receiver.receive(sent);
            if (!received) {
                System.out.println("System failed at Receiver.");
                missed++;
            } else {
                missed = 0;
            }

            if (!monitor.checkStatus(missed, max)) {
                System.out.println("System failed at Monitor.");
                break;
            }

            System.out.println("--------------------------");

            // This is just to add the tick simulation
            try { Thread.sleep(500); } catch (InterruptedException e) {}
        }
    }
}
