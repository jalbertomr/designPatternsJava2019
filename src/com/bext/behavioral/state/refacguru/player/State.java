package com.bext.behavioral.state.refacguru.player;

abstract public class State {
    protected AudioPlayer player;
    protected boolean playing;

    public State(AudioPlayer player) {
        this.player = player;
    }

    abstract void clickLock();
    abstract void clickPlay();
    abstract void clickNext();
    abstract void clickPrevious();

    public boolean isPlaying() { return this.playing; }
}

class LockedState extends State {

    public LockedState(AudioPlayer player) {
        super(player);
    }

    @Override
    void clickLock() {
        if ( super.isPlaying()){
            player.changeState( new PlayingState( player));
        } else {
            player.changeState( new ReadyState( player ));
        }
    }

    @Override
    void clickPlay() {
        System.out.println("locked Play no effect");
    }

    @Override
    void clickNext() {
        System.out.println("locked Next no effect");
    }

    @Override
    void clickPrevious() {
        System.out.println("locked Previous no effect");
    }
}

class ReadyState extends State {

    public ReadyState(AudioPlayer player) {
        super(player);
    }

    @Override
    void clickLock() {
        player.changeState( new LockedState( player));
    }

    @Override
    void clickPlay() {
        player.startPlayback();
        super.playing = true;
        player.changeState( new PlayingState( player ));
    }

    @Override
    void clickNext() {
        player.nextSong();
    }

    @Override
    void clickPrevious() {
        player.previousSong();
    }
}

class PlayingState extends State {

    public PlayingState(AudioPlayer player) {
        super(player);
    }

    @Override
    void clickLock() {
        player.changeState(new LockedState( player));
    }

    @Override
    void clickPlay() {
        player.stopPlayback();
        player.changeState(new ReadyState( player));
    }

    @Override
    void clickNext() {
        player.nextSong();
    }

    @Override
    void clickPrevious() {
        player.previousSong();
    }
}