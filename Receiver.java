public class Receiver {

    public boolean receive(boolean senderResult) {
        if (senderResult) {
            System.out.println("Received");
            return true;
        } else {
            System.out.println("Missed");
            return false;
        }
    }
}
