import java.util.HashMap;
import java.util.Map;

interface User {
    String getRole();
}

class AdminUser implements User {
    @Override
    public String getRole() {
        return "admin_role";
    }
}

class NormalUser implements User {
    @Override
    public String getRole() {
        return "user_role";
    }
}

class UnregisteredUser implements User {
    @Override
    public String getRole() {
        return "unregistered_role";
    }
}

class Database {
    private final Map<String, String[]> users;

    Database() {
        users = new HashMap<>();
        users.put("admin_user", new String[]{"admin_password", "admin_role"});
        users.put("normal_user", new String[]{"user_password", "user_role"});
    }

    boolean doesUserExist(String user_name) {
        return users.containsKey(user_name);
    }

    boolean isPasswordCorrect(String user_name, String password) {
        return users.get(user_name)[0].equals(password);
    }

    User assignRole(String user_role) {
        if(user_role.equals("admin_role")) {
            return new AdminUser();
        } 
        else if(user_role.equals("user_role")) {
            return new NormalUser();
        }
        return new UnregisteredUser();
    }
}

abstract class BaseHandler {
    private BaseHandler next;

    public BaseHandler setNextHandler (BaseHandler next) {
        this.next = next;
        return this.next;
    }

    public abstract User handle(String user_name , String password , String user_role);

    public User handleNext(String user_name , String password , String user_role) {
        if (next == null) {
            return new UnregisteredUser();
        }
        return next.handle(user_name, password, user_role);
    }
}

class UserExistHandler extends BaseHandler{
    private final Database database;

    UserExistHandler(Database database) {
        this.database = database;
    }

    @Override
    public User handle(String user_name, String password, String user_role) {
        if (database.doesUserExist(user_name)) {
            return handleNext(user_name, password, user_role);
        }
        return new UnregisteredUser();
    }
}

class PasswordCorrectHandler extends BaseHandler{
    private final Database database;

    PasswordCorrectHandler(Database database) {
        this.database = database;
    }

    @Override
    public User handle(String user_name, String password, String user_role) {
        if (database.isPasswordCorrect(user_name, password)) {
            return handleNext(user_name, password, user_role);
        }
        return new UnregisteredUser();
    }
}

class AssignRoleHandler extends BaseHandler{
    private final Database database;

    AssignRoleHandler(Database database) {
        this.database = database;
    }

    @Override
    public User handle(String user_name, String password, String user_role) {
        return database.assignRole(user_role);
    }
}
public class RoleBasedAccessControlClient {
    public static void main(String[] args) {
        Database database = new Database();

        BaseHandler userExistHandler = new UserExistHandler(database);
        BaseHandler passwordCorrectHandler = new PasswordCorrectHandler(database);
        BaseHandler assignRoleHandler = new AssignRoleHandler(database);

        userExistHandler.setNextHandler(passwordCorrectHandler).setNextHandler(assignRoleHandler);

        String user_name = "admin_user";
        String password = "admin_password";
        String user_role = "admin_role";

        User user = userExistHandler.handle(user_name, password, user_role);
        System.out.println("User role: " + user.getRole());
    }
}
