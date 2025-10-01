import java.util.Random;

public class FaultRecovery {

    public static void main(String[] args) {
        Random random = new Random();

        int maxMissed = 2;

        Replica primary = new Replica("Primary", true, maxMissed);
        Replica secondary = new Replica("Secondary", false, maxMissed);

        int time = 0;

        while (true) {
            time++;
            System.out.println("Tick " + time);

            boolean primaryAlive = primary.runHeartbeat(random);

            if (primaryAlive) {
                if (!secondary.isActive()) {
                    secondary.deactivate();
                }
            } else {
                if (!secondary.isActive()) {
                    secondary.activate();
                }
                secondary.runHeartbeat(random);
            }

            if (!primary.isActive() && !secondary.isActive()) {
                System.out.println("Both replicas failed. Stopping system.");
                break;
            }

            System.out.println("------------------------------");

            // This is just to add the tick simulation
            try { Thread.sleep(500); } catch (InterruptedException e) {}

            if (time > 50) break;
        }
    }
}
