package com.bext.objectIO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Contacts implements Serializable {
    private static final long serialVersionUID = -2393604976389871749L;
    List<Contact> contactList = null;

    public Contacts() {
        contactList = new ArrayList<>();
    }

    public void add(Contact contact) {
        contactList.add( contact);
    }

    public void add(int i, Contact contact){
        contactList.add(i, contact);
    }

    public boolean remove(Object obj) {
        return contactList.remove( obj);
    }

    public Contact remove(int i) {
        return contactList.remove(i);
    }

    public boolean isEmpty() {
        return contactList.isEmpty();
    }

    public int size(){
        return contactList.size();
    }

    public void clear(){
        contactList.clear();
    }

    public boolean contains(Object obj){
        return contactList.contains(obj);
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "contactList=" + contactList +
                '}';
    }
}
