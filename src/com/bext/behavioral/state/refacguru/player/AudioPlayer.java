package com.bext.behavioral.state.refacguru.player;

public class AudioPlayer {
    private State state;

    public AudioPlayer() {
        this.state = new ReadyState( this);

        /*UI = new UserInterface();
        UI.lockButton.onClick( this.clickLock());
        UI.playButton.onClick( this.clickPlay());
        UI.nextButton.onClick( this.clickNext());
        UI.previousButton.onClick( this.clickPrevious());
        UI.startButton.onClick( this.startPlayback());
        UI.stopButton.onClick( this.stopPlayback());

         */
    }

    public void changeState( State state) {
        this.state = state;
    }

    public void clickLock() {
        state.clickLock();
    }
     public void clickPlay() {
        state.clickPlay();
     }
     public void clickNext() {
        state.clickNext();
     }
     public void clickPrevious() {
        state.clickPrevious();
     }

     public void startPlayback() {
         System.out.println("startPlayback()");
     }
     public void stopPlayback() {
         System.out.println("stopPlayback()");
     }
     public void nextSong(){
         System.out.println("nextSong()");
     }
     public void previousSong() {
         System.out.println("previousSong()");
     }
     public void fastForward() {
         System.out.println("fastForward()");
     }
     public void rewind() {
         System.out.println("rewind()");
     }
}
