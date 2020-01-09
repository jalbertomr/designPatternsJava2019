package com.bext.behavioral.iterator.geeksforgeeks;

class Notification {
    String notification;

    public Notification(String notification) {
        this.notification = notification;
    }

    public String getNotification() {
        return notification;
    }
}

interface MiIterator {
    boolean hasNext();
    Object next();
}

interface MiCollection {
    public MiIterator getIterator();
}


class NotificationCollection implements MiCollection {
    static final int MAX_ITEMS = 6;
    int numberOfItems = 0;
    Notification[] notificationArray;

    public NotificationCollection() {
        notificationArray = new Notification[MAX_ITEMS];

        addItem("1. Notification");
        addItem( "2. Notification");
        addItem("3. Notification.");
        addItem("4. Notification");
        addItem( "5. Notification");
        addItem("6. Notification.");
        addItem("7. Notification");
        addItem( "8. Notification");
    }

    private void addItem(String itemTxt) {
        Notification notification = new Notification(itemTxt);

        if (numberOfItems >= MAX_ITEMS) {
            System.out.println("NotificationCollection Full!.");
        } else {
            notificationArray[numberOfItems] = notification;
            numberOfItems++;
        }
    }

    @Override
    public MiIterator getIterator() {
        return new NotificationIterator( notificationArray);
    }
}

class NotificationIterator implements MiIterator {
    Notification[] notificationsArray;

    int pos = 0;

    public NotificationIterator(Notification[] notificationsArray) {
        this.notificationsArray = notificationsArray;
    }

    @Override
    public boolean hasNext() {
        if (pos >= notificationsArray.length || notificationsArray[pos] == null)
            return false;
        else
            return true;
    }

    @Override
    public Object next() {
        Notification notification = notificationsArray[pos];
        pos += 1;
        return notification;
    }
}

class NotificationBar {
    NotificationCollection notifications;

    public NotificationBar( NotificationCollection notifications) {
        this.notifications = notifications;
    }

    public void printNotifications() {
        MiIterator miIterator = notifications.getIterator();
        System.out.println("-------- Notification Bar --------");
        while (miIterator.hasNext()) {
            Notification n = (Notification) miIterator.next();
            System.out.println( n.getNotification());
        }
    }
}

public class BehaivoralIteratorDemo {
    public static void main(String... args) {
        NotificationCollection nc = new NotificationCollection();
        NotificationBar nb = new NotificationBar( nc);
        nb.printNotifications();
    }
}
