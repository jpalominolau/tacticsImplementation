package heartbeat;
public class Monitor {

    public boolean checkStatus(int missed, int max) {
        if (missed >= max) {
            System.out.println("Critical fault");
            return false;
        }
        return true;
    }
}
