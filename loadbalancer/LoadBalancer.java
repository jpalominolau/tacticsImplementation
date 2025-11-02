package loadbalancer;

import java.util.List;
import java.util.ArrayList;

public class LoadBalancer {
    private List<String> servers;
    private int currentIndex;

    public LoadBalancer(List<String> servers) {
        this.servers = new ArrayList<>(servers);
        this.currentIndex = 0;
    }

    public synchronized String getNextServer() {
        String server = servers.get(currentIndex);
        currentIndex = (currentIndex + 1) % servers.size();
        return server;
    }
}
