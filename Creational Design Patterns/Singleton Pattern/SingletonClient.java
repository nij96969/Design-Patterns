class MongoDB {
    private static MongoDB instance;

    // Private constructor to prevent instantiation
    private MongoDB() {
        System.out.println("MongoDB is started");
    }

    // Public method to provide access to the instance
    public static MongoDB getInstance() {
        if (instance == null) {
            instance = new MongoDB();
        }
        return instance;
    }

    // Non-static query method to operate on the instance
    public void query() {
        System.out.println("Perform query");
    }
}


public class SingletonClient {
    public static void main(String[] args) {
        MongoDB.getInstance().query();
        MongoDB.getInstance().query();
        MongoDB.getInstance().query();

        //try this with public MongoDB u can see multiple instantiation
        // MongoDB db1 = new MongoDB();
        // MongoDB db2 = new MongoDB();

        // db1.query();
        // db2.query();

    }
}