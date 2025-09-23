import java.util.Random;

public class HeartbeatMonitor {

    public static boolean sender(int count, Random random) {
        int chance = random.nextInt(8);
        boolean failure = (chance == 0);

        if (failure) {
            System.out.println("Failed to send " + count);
            return false;
        } else {
            System.out.println("Sent heartbeat " + count);
            return true;
        }
    }

    public static boolean receiver(boolean sender) {
        if (sender) {
            System.out.println("Received");
            return true;
        } else {
            System.out.println("Missed");
            return false;
        }
    }

    public static boolean monitor(int missed, int max) {
        if (missed >= max) {
            System.out.println("Critical fault");
            return false;
        }

         return true;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int total = 0;
        int missed = 0;
        int max = 3;

        while (true) {
            total++;

            boolean sent = sender(total, random);
            boolean received = receiver(sent);

            if (received) {
                missed = 0;
            } else {
                missed++;
            }

            if (!monitor(missed, max)) {
                break;
            }

            System.out.println("--------------------------");
        }
    }
}