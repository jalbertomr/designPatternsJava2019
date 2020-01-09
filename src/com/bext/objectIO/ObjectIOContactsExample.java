package com.bext.objectIO;

import java.io.*;

public class ObjectIOContactsExample {
    private static final String pathName = "D:/temp/ObjectContacts.app";

    public static void main(String... args) {
        Contacts contactsReaded = null;

        ObjectIOContactsExample objectIOContactsExample = new ObjectIOContactsExample();
        Contacts contacts = new Contacts();
        Contact contact = new Contact("Betito","8938344324");
        contacts.add( contact);
        contact = new Contact("Guillermina" ,"43234342234");
        contacts.add( contact);
        contact = new Contact("Alcon","5453246236");
        System.out.println( contacts.toString());
        objectIOContactsExample.writeObjectToFile( contacts);
        contactsReaded = objectIOContactsExample.readObjectFromFile();
        System.out.println( contactsReaded.toString());
    }

    private void writeObjectToFile(Contacts contacts) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(pathName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream( fileOutputStream);
            objectOutputStream.writeObject( contacts);
            objectOutputStream.close();
            System.out.println("Object to file written");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Contacts readObjectFromFile() {
        Contacts Contacts = null;
        try {
            FileInputStream fis = new FileInputStream(pathName);
            ObjectInputStream ois = new ObjectInputStream( fis);
            Contacts = (com.bext.objectIO.Contacts) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return Contacts;
        }
    }

}
