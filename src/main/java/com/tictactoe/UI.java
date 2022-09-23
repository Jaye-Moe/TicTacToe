package com.tictactoe;

import java.util.ArrayList;

public class UI {
    private String player;
    private String priorPlayer;

    public UI(){
        this.player = "X";
        this.priorPlayer = "O";
    }
    public void changeTurn(){
        if (this.player.equals("X")){
            this.player="O";
            this.priorPlayer="X";
        }else{
            this.player="X";
            this.priorPlayer="O";
        }
    }

    public boolean checkNoMoreOpenSpots(ArrayList<String> spots){
        if (spots.contains(" ")){
            return false;
        }else{
            System.out.println("No more open spots");
            return true;
        }

    }

    public boolean checkWinner(ArrayList<String> spots){
        int length = spots.size();
        System.out.println(length);
        if(!spots.get(0).equals(" ")){
           if(spots.get(0).equals(spots.get(1)) && spots.get(0).equals(spots.get(2))){
                return true;
            }
        }
        if(!spots.get(3).equals(" ")){
            if (spots.get(3).equals(spots.get(4)) && spots.get(3).equals(spots.get(5))) {
                return true;
            }
        }
        if(!spots.get(6).equals(" ")){
            if (spots.get(6).equals(spots.get(7)) && spots.get(6).equals(spots.get(8))) {
                return true;
            }
        }
        if(!spots.get(0).equals(" ")){
            if(spots.get(0).equals(spots.get(3)) && spots.get(0).equals(spots.get(6))){
                return true;
            }
        }
        if(!spots.get(1).equals(" ")){
            if (spots.get(1).equals(spots.get(4)) && spots.get(1).equals(spots.get(7))) {
                return true;
            }
        }
        if(!spots.get(2).equals(" ")){
            if (spots.get(2).equals(spots.get(5)) && spots.get(2).equals(spots.get(8))) {
                return true;
            }
        }
        if(!spots.get(0).equals(" ")){
            if (spots.get(0).equals(spots.get(4)) && spots.get(0).equals(spots.get(8))) {
                return true;
            }
        }
        if(!spots.get(6).equals(" ")){
            return spots.get(6).equals(spots.get(4)) && spots.get(6).equals(spots.get(2));
        }
    return false;
    }


    public String getPlayer(){
        return this.player;
    }

    public String getPriorPlayer(){
        return this.priorPlayer;
    }
}
