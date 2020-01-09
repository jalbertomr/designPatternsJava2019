package com.bext.structural.facade.wiki.Computer;

import java.util.Arrays;

public class ComputerFacadeDemo {
    public static void main(String ... args) {
        ComputerFacade computerFacade = new ComputerFacade();
        computerFacade.start();
    }
}

// complex part
class CPU {
    public void freeze() {
        System.out.println("freezing...");
    }
    public void jump( long position) {
        System.out.println("jump to " + position); }
    public void execute() {
        System.out.println("executing.");
    }
}

class HardDrive {
    public byte[] read(long lba, int size) {
        /*...*/
        return new byte[]{00,12,32,54,64,43,32};
    }
}

class Memory {
    public void load( long position, byte[] data ) {
        System.out.println("memory loading position: " + position + " data: " +data.toString()); }
}

// Facade
class ComputerFacade {
    final long BOOT_ADDRESS = 10;
    final long BOOT_SECTOR = 123;
    final int SECTOR_SIZE = 256;

    private final CPU processor;
    private final Memory ram;
    private final HardDrive hd;

    public ComputerFacade() {
        this.processor = new CPU();
        this.ram = new Memory();
        this.hd = new HardDrive();
    }

    public void start() {
        processor.freeze();
        ram.load(BOOT_ADDRESS, hd.read( BOOT_SECTOR, SECTOR_SIZE));
        processor.jump( BOOT_ADDRESS);
        processor.execute();
    }
}


