package loadbalancer;

public class RequestThread extends Thread {
    private int requestId;
    private LoadBalancer loadBalancer;

    public RequestThread(int id, LoadBalancer loadBalancer) {
        this.requestId = id;
        this.loadBalancer = loadBalancer;
    }

    @Override
    public void run() {
        String server = loadBalancer.getNextServer();
        System.out.println("Request " + requestId + ": Routed to " + server);
    }
}
