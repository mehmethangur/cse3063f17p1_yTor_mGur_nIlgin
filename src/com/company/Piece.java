package com.company;

public class Piece {
    Square location;

    public Piece(Square location){
        this.location = location;
    }
    public void setLocation(Square location){
        this.location = location;
    }

    public Square getLocation(){
        return this.location;
    }
}
