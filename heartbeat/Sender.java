package heartbeat;
import java.util.Random;

public class Sender {

    public boolean send(int count, Random random) {
        int chance = random.nextInt(3);
        boolean failure;

        if (chance == 0) {
            failure = true;
        } else {
            failure = false;
        }

        if (failure) {
            System.out.println("Failed to send " + count);
            return false;
        } else {
            System.out.println("Sent heartbeat " + count);
            return true;
        }
    }
}
