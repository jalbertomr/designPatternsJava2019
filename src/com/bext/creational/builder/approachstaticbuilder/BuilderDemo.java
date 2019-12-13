package com.bext.creational.builder.approachstaticbuilder;

public class BuilderDemo {
    public static void main(String... args) {
        Computer computer = new Computer.ComputerBuilder("1 Tera", "24 GB")
                .setWithMonitor(true)
                .setWithWifi(true)
                .build();
    }
}
