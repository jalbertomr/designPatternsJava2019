package com.bext.behavioral.mediator.wiki;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

class Storage<T> {
    T value;

    T getValue(){
        return value;
    }

    void setValue( Mediator<T> mediator, String storageName, T value) {
        this.value = value;
        mediator.notifyObservers( storageName);
    }
}

class Mediator<T> {
    private final HashMap<String, Storage<T>> storageMap = new HashMap<>();
    private final CopyOnWriteArrayList<Consumer<String>> observers = new CopyOnWriteArrayList<Consumer<String>>();

    public void setValue( String storageName, T value) {
        Storage storage =storageMap.computeIfAbsent( storageName, name -> new Storage<>());
        storage.setValue( this, storageName, value);
    }

    public Optional<T> getValue( String storageName) {
        return Optional.ofNullable( storageMap.get( storageName)).map( Storage::getValue);
    }

    public void addObserver( String storageName, Runnable observer){
        observers.add( eventName -> {
           if (eventName.equals( storageName)){
               observer.run();
           }
        });
    }

    public void notifyObservers(String eventName) {
        observers.forEach( observers -> observers.accept( eventName));
    }
}

public class MediatorDemo {
    public static void main(String... args) {
        Mediator<Integer> mediator = new Mediator<>();
        mediator.setValue("Beto", 49);
        mediator.setValue("Guille", 30);
        mediator.getValue( "Guille").ifPresent( edad -> System.out.println("Edad de Guille: " + edad ));

        mediator.addObserver( "Beto", () -> {
            System.out.println("Nueva edad de Beto: " + mediator.getValue( "Beto").orElseThrow(RuntimeException::new));
        });
        mediator.setValue("Beto", 50);
    }
}
