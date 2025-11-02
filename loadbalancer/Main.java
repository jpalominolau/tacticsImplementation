package loadbalancer;

import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        startLoadBalancerDemo();
    }

    public static void startLoadBalancerDemo() {
        List<String> servers = new ArrayList<>();
        servers.add("Server1");
        servers.add("Server2");
        servers.add("Server3");

        LoadBalancer loadBalancer = new LoadBalancer(servers);

        for (int i = 1; i <= 10; i++) {
            new RequestThread(i, loadBalancer).start();
        }
    }
}
