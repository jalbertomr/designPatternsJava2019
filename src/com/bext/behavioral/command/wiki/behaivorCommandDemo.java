package com.bext.behavioral.command.wiki;

import javax.naming.CompositeName;
import java.util.HashMap;


    // Commnad interface
    interface Command {
        void execute();
    }

    // Invoker class
    class Switch {
        private final HashMap<String, Command> commandMap = new HashMap<>();

        public void register(String commandName, Command command) {
            commandMap.put( commandName, command);
        }

        public void execute(String commandName) {
            Command command = commandMap.get( commandName);
            if ( command == null) {
                throw new IllegalStateException("Command no registrado :" + commandName);
            }
            command.execute();
        }
    }

    // Receiver Class
    class Light {
        public void turnOn() {
            System.out.println("Encender Luz.");
        }

        public void turnOff() {
            System.out.println("Apagar Luz.");
        }
    }

    // Concrete Command
    class SwitchOnCommand implements Command {
        private final Light light;

        public SwitchOnCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.turnOn();
        }
    }

    // Concrete Command
    class SwitchOffCommand implements Command {
        private final Light light;

        public SwitchOffCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.turnOff();
        }
    }

public class behaivorCommandDemo {

    public static void main(String... args){
        Light lamp = new Light();

        Command switchOn = new SwitchOnCommand( lamp);
        Command switchOff = new SwitchOffCommand( lamp);

        Switch miSwitch = new Switch();
        miSwitch.register("ON", switchOn);
        miSwitch.register("OFF", switchOff);

        miSwitch.execute("ON");
        miSwitch.execute("OFF");
    }
}
