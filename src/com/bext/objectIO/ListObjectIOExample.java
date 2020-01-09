package com.bext.objectIO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListObjectIOExample {
    private static final String pathName = "D:/temp/ObjectListContact.app";

    public static void main(String... args) {
        List<Contact> listContacts = new ArrayList<>();
        List<Contact> listContactsReaded;

        ListObjectIOExample objectIOExample = new ListObjectIOExample();
        Contact contact = new Contact("Beto","1234556");
        listContacts.add( contact);
        contact = new Contact("Guille", "333333333222");
        listContacts.add( contact);
        contact = new Contact("Rod", "4334234235");
        listContacts.add( contact);
        objectIOExample.writeObjectToFile( listContacts);
        listContactsReaded = objectIOExample.readObjectFromFile();
        System.out.println( listContacts.toString());
    }

    private void writeObjectToFile(List<Contact> listContacts) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(pathName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream( fileOutputStream);
            objectOutputStream.writeObject( listContacts);
            objectOutputStream.close();
            System.out.println("Object to file written");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Contact> readObjectFromFile() {
        List<Contact> listContacts = null;
        try {
            FileInputStream fis = new FileInputStream(pathName);
            ObjectInputStream ois = new ObjectInputStream( fis);
            listContacts = (List<Contact>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return listContacts;
        }

    }

}
