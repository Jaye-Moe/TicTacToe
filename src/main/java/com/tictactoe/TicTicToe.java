//DONE 1 - Add Player's turn display
//DONE 2 - Check for wins (clean up code), code added, need to stop game
//DONE 3 - Check for no more possible moves, code added, need to stop game
//DONE 4 - Ask to play again
//TODO 5 - Add background color
//Done 6 - Resize screen
//TODO 7 - Keep track of cumulative score


package com.tictactoe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TicTicToe extends Application {
    @Override
    public void start(Stage stage) {

        ArrayList<Block> blocks = new ArrayList<>();
        ArrayList<String> oldSpots = new ArrayList<>();
        ArrayList<String> newSpots = new ArrayList<>();

        createBlocks(blocks);

        //SET UP THE PLAYING WINDOW
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(340, 500);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        UI ui = new UI();

        //SET UP THE PLAYER STATUS DISPLAY
        Label label = new Label();
        label.setText("Player " + ui.getPlayer() +"'s Turn");
        label.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 36));
        gridPane.add(label,1,0,3,1);

        //ADD BLOCKS TO THE BOARD
        for (Block block : blocks) {
            gridPane.add(block.getBlock(), block.getXPos(), block.getYPos());
        }


        final boolean[] gameIsOn = {true};

        //ACTIONS TO PERFORM WHEN SOMETHING IS CLICKED
        gridPane.setOnMouseClicked(e -> {
            if (gameIsOn[0]) {

                //CLEAR THE BOARD
                gridPane.getChildren().clear();

                //CREATE A NEW LIST OF SPOTS PLAYED TO COMPARE TO THE OLD LIST
                for (Block block : blocks) {
                    newSpots.add(block.getPlacedBy());
                }

                //CHANGE TURNS IF THE MOST RECENT MOVE WAS VALID
                if (!oldSpots.equals(newSpots)) {
                    ui.changeTurn();
                }

                //PLACE THE NEW SPOTS
                for (Block block : blocks) {
                    gridPane.add(block.getBlock(), block.getXPos(), block.getYPos());
                    block.setPlayer(ui.getPlayer());
                }

                //PLACE THE UPDATED PLAYER STATUS
                label.setText("Player " + ui.getPlayer() + "'s Turn");
                gridPane.add(label, 1, 0);

                //COPY THE NEW LIST OF SPOTS PLAYED TO THE OLD LIST AND CLEAR THE NEW LIST
                oldSpots.clear();
                oldSpots.addAll(newSpots);
                newSpots.clear();
            }

            //CHECK FOR A WINNER AND REMAINING MOVES AND DISPLAY MESSAGE
            if (ui.checkWinner(oldSpots)){
                Label winner = new Label();
                winner.setText(" " + ui.getPriorPlayer() + " won the game");
                winner.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
                gridPane.add(winner,1,4,3,1);
                gameIsOn[0] = false;
            }else if(ui.checkNoMoreOpenSpots(oldSpots)){
                Label gameOver = new Label();
                gameOver.setText(" No more valid moves");
                gameOver.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 24));
                gridPane.add(gameOver,1,4,3,1);
                gameIsOn[0] = false;
            }

            //DISPLAY PLAY AGAIN MESSAGE IF GAME IS OVER
            if (!gameIsOn[0]){
                Button playAgain = new Button();
                playAgain.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 24));
                playAgain.setText("Click to play again");
                gridPane.add(playAgain,1,5,3,1);
                playAgain.setOnMouseClicked(ev ->{

                    System.out.println("play again");
                    createBlocks(blocks);
                    oldSpots.clear();
                    newSpots.clear();
                    gridPane.getChildren().clear();
                    gridPane.add(label,1,0,3,1);

                    //PLACE THE NEW SPOTS
                    for (Block block : blocks) {
                        gridPane.add(block.getBlock(), block.getXPos(), block.getYPos());
                        block.setPlayer(ui.getPlayer());
                    }

                    gameIsOn[0]=true;

                });

            }

        });

        stage.setTitle("TicTacToe");
        stage.setScene(new Scene(gridPane));
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

    public void createBlocks(ArrayList<Block> blocks){
        blocks.clear();
        int x;
        int y = 1;
        while (y <= 3) {
            x = 1;
            while (x <= 3) {
                Block block = new Block(x, y);
                blocks.add(block);
                x = x + 1;
            }
            y = y + 1;
        }
    }


}