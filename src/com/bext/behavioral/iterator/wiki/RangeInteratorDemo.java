package com.bext.behavioral.iterator.wiki;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RangeInteratorDemo {
    public static void main(String... args){
        var iterator = range(7, 13);
        while (iterator.hasNext()) {
            System.out.println( iterator.next());
        }

        // lambda
        iterator.forEachRemaining(System.out::println);
    }

    public static Iterator<Integer> range(int start, int end) {

        return new Iterator<>() {
            private int index = start;

            @Override
            public boolean hasNext() {
                return index < end;
            }

            @Override
            public Integer next() {
                if ( !hasNext()) {
                    throw new NoSuchElementException();
                }
                return index++;
            }
        };
    }
}
