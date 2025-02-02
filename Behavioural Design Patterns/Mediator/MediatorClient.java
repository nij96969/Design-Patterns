import java.util.ArrayList;
import java.util.List;

// Mediator Interface
interface NotificationMediator {
    void sendMessage(String message, User sender, User receiver);
    void addUser(User user);
}

// Concrete Mediator
class ConcreteNotificationMediator implements NotificationMediator {
    private List<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }
    @Override
    public void sendMessage(String message, User sender, User receiver) {
        if (users.contains(receiver)) {
            receiver.receive(message, sender);
        } else {
            System.out.println("User not found in the system.");
        }
    }
}

// Abstract Colleague
abstract class User {
    protected NotificationMediator mediator;
    protected String name;

    public User(NotificationMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void send(String message, User receiver);
    public abstract void receive(String message, User sender);

    public String getName() {
        return name;
    }
}

// Concrete Colleagues
class EmailUser extends User {
    public EmailUser(NotificationMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String message, User receiver) {
        System.out.println(name + " sends Email to " + receiver.getName() + ": " + message);
        mediator.sendMessage(message, this, receiver);
    }

    @Override
    public void receive(String message, User sender) {
        System.out.println(name + " received Email from " + sender.getName() + ": " + message);
    }
}

class SMSUser extends User {
    public SMSUser(NotificationMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String message, User receiver) {
        System.out.println(name + " sends SMS to " + receiver.getName() + ": " + message);
        mediator.sendMessage(message, this, receiver);
    }

    @Override
    public void receive(String message, User sender) {
        System.out.println(name + " received SMS from " + sender.getName() + ": " + message);
    }
}

class PushUser extends User {
    public PushUser(NotificationMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String message, User receiver) {
        System.out.println(name + " sends Push Notification to " + receiver.getName() + ": " + message);
        mediator.sendMessage(message, this, receiver);
    }

    @Override
    public void receive(String message, User sender) {
        System.out.println(name + " received Push Notification from " + sender.getName() + ": " + message);
    }
}

// Demo Class
public class MediatorClient {
    public static void main(String[] args) {
        NotificationMediator mediator = new ConcreteNotificationMediator();

        User alice = new EmailUser(mediator, "Alice");
        User bob = new SMSUser(mediator, "Bob");
        User charlie = new PushUser(mediator, "Charlie");

        mediator.addUser(alice);
        mediator.addUser(bob);
        mediator.addUser(charlie);

        alice.send("Hey Bob, can you check the report?", bob);
        System.out.println();
        bob.send("Sure Alice, I'll send it shortly.", alice);
        System.out.println();
        charlie.send("Bob, the system is down. Can you check?", bob);
    }
}
