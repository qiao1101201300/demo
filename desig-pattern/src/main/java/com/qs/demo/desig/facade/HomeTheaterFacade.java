package com.qs.demo.desig.facade;

public class HomeTheaterFacade {
    private TheaterLight theaterLight;
    private Popcron popcron;
    private Stereo stereo;
    private Projector projector;
    private Screen screen;
    private DVDPlayer dvdPlayer;

    public HomeTheaterFacade() {
        this.theaterLight = TheaterLight.getInstance();
        this.popcron = Popcron.getInstance();
        this.stereo = Stereo.getInstance();
        this.projector = Projector.getInstance();
        this.screen = Screen.getInstance();
        this.dvdPlayer = DVDPlayer.getInstance();
    }

    public void ready(){
        popcron.on();
        popcron.pop();
        screen.down();
        projector.on();
        stereo.on();
        dvdPlayer.on();
        theaterLight.dim();
    }
    public void play(){
        dvdPlayer.play();
    }
    public void pause(){
        dvdPlayer.pause();
    }
    public void end(){
        popcron.off();
        theaterLight.off();
        screen.up();
        projector.off();
        stereo.off();
        dvdPlayer.off();
    }
}
