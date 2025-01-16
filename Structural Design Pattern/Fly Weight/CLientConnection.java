import java.util.HashMap;
import java.util.Map;

interface Connection {
    void connect(String extrinstic_state);
}

class ConcreteConnection implements Connection{
    private final String server_address;
    private final Integer port;

    ConcreteConnection(String server_address , Integer port){
        this.server_address = server_address;
        this.port = port;
    }

    @Override
    public void connect(String extrinstic_state) {
        System.out.println("Connecting to " + server_address + " at port : " + port + " with Session " + extrinstic_state);
        
    }
}

class ConnectionPool {
    private final Map<String , Connection> pool = new HashMap<>();

    public Connection getConnection(String server_address , Integer port){
        String key = server_address + ":" + port.toString();

        if(!pool.containsKey(key))
            pool.put(key, new ConcreteConnection(server_address , port));

        return pool.get(key);
    }
}

public class CLientConnection {
    public static void main(String[] args) {
        ConnectionPool pool = new ConnectionPool();
        
        Connection conn1 = pool.getConnection("192.168.1.1", 8080);
        conn1.connect("UserSession1");
        
        Connection conn2 = pool.getConnection("192.168.1.1", 8080);
        conn2.connect("UserSession2");
        
        System.out.println(conn1 == conn2);
        
        Connection conn3 = pool.getConnection("192.168.1.2", 9090);
        conn3.connect("UserSession3");
        
        System.out.println(conn1 == conn3);
    }
}
