package com.tictactoe;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Block {
    private Image imageFile;
    private ImageView image;
    private final int xPos;
    private final int yPos;
    private String player;
    private String placedBy;

    public Block(int xPos, int yPos){
        this.imageFile = new Image("file:blankButton.png");
        this.image = new ImageView(this.imageFile);
        this.xPos= xPos;
        this.yPos = yPos;
        this.image.setOnMouseClicked(e-> clickButton());
        this.player = "X";
        this.placedBy = " ";
    }

    public ImageView getBlock(){
        return this.image;
    }

    public void clickButton(){
        if(this.player.equals("X")){
            this.imageFile = new Image("file:xButton.png");
            this.placedBy = "X";
        }else{
            this.imageFile = new Image("file:oButton.png");
            this.placedBy="O";
        }
        this.image = new ImageView(this.imageFile);
    }

    public int getXPos(){
        return this.xPos;
    }

    public int getYPos(){
        return this.yPos;
    }

    public void setPlayer(String player){
        this.player = player;
    }

    public String getPlacedBy(){
        return this.placedBy;
    }
}
