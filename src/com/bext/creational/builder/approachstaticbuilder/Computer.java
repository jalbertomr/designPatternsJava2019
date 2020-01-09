package com.bext.creational.builder.approachstaticbuilder;

public class Computer {
    //required parameters
    private String HDD;
    private String RAM;

    // optional parameters
    private boolean withMonitor;
    private boolean withWifi;

    public String getHDD() {
        return HDD;
    }

    public String getRAM() {
        return RAM;
    }

    public boolean isWithMonitor() {
        return withMonitor;
    }

    public boolean isWithWifi() {
        return withWifi;
    }

    private Computer( ComputerBuilder builder) {
        this.HDD = builder.HDD;
        this.RAM = builder.RAM;
        this.withMonitor = builder.withMonitor;
        this.withWifi = builder.withWifi;
    }
    // inner builder class
    public static class ComputerBuilder {
        //required parameters
        private String HDD;
        private String RAM;

        // optional parameters
        private boolean withMonitor;
        private boolean withWifi;

        public ComputerBuilder(String HDD, String RAM) {
            this.HDD = HDD;
            this.RAM = RAM;
        }

        public ComputerBuilder setWithMonitor(boolean withMonitor) {
            this.withMonitor = withMonitor;
            return this;
        }

        public ComputerBuilder setWithWifi(boolean withWifi) {
            this.withWifi = withWifi;
            return this;
        }

        public Computer build(){
            return new Computer( this);
        }
    }

}
