package com.bext.objectIO;

import java.io.*;

public class ObjectIOContactExample {
    private static final String pathName = "D:/temp/ObjectContact.app";

    public static void main(String... args) {
        Contact contactReaded = null;

        ObjectIOContactExample objectIOContactExample = new ObjectIOContactExample();
        Contact contact = new Contact("Beto","1234556");
        objectIOContactExample.writeObjectToFile( contact);
        contactReaded = objectIOContactExample.readObjectFromFile();
        System.out.println( contactReaded.toString());
    }

    private void writeObjectToFile(Contact contact) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(pathName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream( fileOutputStream);
            objectOutputStream.writeObject( contact);
            objectOutputStream.close();
            System.out.println("Object to file written");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Contact readObjectFromFile() {
        Contact Contacts = null;
        try {
            FileInputStream fis = new FileInputStream(pathName);
            ObjectInputStream ois = new ObjectInputStream( fis);
            Contacts = (Contact) ois.readObject();
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
