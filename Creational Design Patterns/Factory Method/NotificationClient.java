import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

interface Notification{
    void sendNotification();
}

class EmailNotification implements Notification{
    @Override
    public void sendNotification() {
        System.out.println("Notification sent using Email");
    }
}
class SMSNotification implements Notification {
    @Override
    public void sendNotification() {
        System.out.println("Notification sent using SMS");
    }
}

class PushNotification implements Notification {
    @Override
    public void sendNotification() {
        System.out.println("Notification sent using Push Notification");
    }
}

class NotificationFactory {
    private static final Map<String, Supplier<Notification>> notificationMap = new HashMap<>();

    static {
        notificationMap.put("EMAIL", EmailNotification::new);
        notificationMap.put("SMS", SMSNotification::new);
        notificationMap.put("PUSH", PushNotification::new);
    }

    public static Notification createNotification(String type) {
        Supplier<Notification> notification = notificationMap.get(type.toUpperCase());
        if (notification != null) {
            return notification.get();
        } else {
            throw new IllegalArgumentException("No such notification type");
        }
    }
}

public class NotificationClient {
    public static void main(String[] args) {
        Notification emailNotification = NotificationFactory.createNotification("email");
        emailNotification.sendNotification();

        Notification smsNotification = NotificationFactory.createNotification("sms");
        smsNotification.sendNotification();

        Notification pushNotification = NotificationFactory.createNotification("push");
        pushNotification.sendNotification();
    }    
}
