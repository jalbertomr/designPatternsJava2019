package com.bext.structural.bridge.refacguru;

public class BridgeDemoRemoteControl {
    static public void main(String... args){
        RemoteControl remoteControlRadio = new RemoteControl( new Radio());
        RemoteControl remoteControlTV = new RemoteControl( new TV());
        //AdvancedRemoteControl advancedRemoteControlTV = new AdvancedRemoteControl( new TV());
    }

    static class RemoteControl{
        protected Device device;
        private final int MAX_CHANNEL = 300;

        public RemoteControl(Device device) {
            this.device = device;
        }

        public void togglePower() {
            if (device.isEnabled()){
                device.disable();
            } else {
                device.enable();
            }
        }
        public void volumeUp() {
            if (device.getVolume() >= 100) return;
            device.setVolume( device.getVolume() + 10);
        }
        public void volumeDown() {
            if (device.getVolume() <= 0) return;
            device.setVolume( device.getVolume() - 10);
        }

        public void channelUp() {
            if ( device.getChannel() + 1 >= MAX_CHANNEL){
                return;
            }
            device.setChannel( device.getChannel() + 1);
        }

        public void channelDown() {
            if ( device.getChannel() - 1 <= 0){
                return;
            }
            device.setChannel( device.getChannel() - 1);
        }

        public void setVolume(int percent) {
            device.setVolume( percent);
        }

        public int getVolume() {
            return device.getVolume();
        }
    }

    static class AdvancedRemoteControl extends RemoteControl{
        private int volume;
        private boolean isMuted = false;

        public AdvancedRemoteControl(Device device) {
            super(device);
        }

        public void mute() {
            if ( !isMuted) {
                volume = super.getVolume();
                super.setVolume( 0);
            } else {
                super.setVolume( volume);
            }
        }
    }

    interface Device{
        public boolean isEnabled();
        public void enable();
        public void disable();
        public int getVolume();
        public void setVolume(int percent);
        public int getChannel();
        public void setChannel(int channel);
    }

    static class TV implements Device{
        boolean enabled = false;
        int volume = 20;
        int channel = 14;



        @Override
        public boolean isEnabled() { return this.enabled; }
        @Override
        public void enable() { this.enabled = true; }
        @Override
        public void disable() { this.enabled = false; }
        @Override
        public int getVolume() { return this.volume; }
        @Override
        public void setVolume(int percent) { this.volume = percent; }
        @Override
        public int getChannel() { return this.channel;  }
        @Override
        public void setChannel(int channel) { this.channel = channel;  }
    }

    static class Radio implements Device {
        boolean enabled = false;
        int volume = 20;
        int channel = 14;
        @Override
        public boolean isEnabled() { return this.enabled; }
        @Override
        public void enable() { this.enabled = true; }
        @Override
        public void disable() { this.enabled = false; }
        @Override
        public int getVolume() { return this.volume; }
        @Override
        public void setVolume(int percent) { this.volume = percent; }
        @Override
        public int getChannel() { return this.channel;  }
        @Override
        public void setChannel(int channel) { this.channel = channel;  }
    }
}
