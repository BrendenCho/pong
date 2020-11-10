package sample;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

import javax.swing.*;
import java.util.Scanner;

public class PlayerInputReceiver extends Thread{

boolean shouldRun = true;
private Scene scene;
private Rectangle playerPaddle;
int moveVal = 10;
final int height = 400;

public PlayerInputReceiver(){}

    @Override
    public void run() {

        while(shouldRun == true){
           handleInput();
        }

}



    public boolean shouldRun() {
        return shouldRun;
    }

    public void setRun(boolean run) {
        shouldRun = run;
    }

    public void handleInput() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                KeyCode key = keyEvent.getCode();
                if(key.getName() == "W" || key.getName() == "Up"){
                    movePaddle(key.getName());
                }else if(key.getName() == "S" || key.getName() == "Down"){
                    movePaddle(key.getName());
                }
            }
        });


    }

    public void setScene(Scene scene){
    this.scene = scene;
    }

    public void setPlayerPaddle(Rectangle playerPaddle){
    this.playerPaddle = playerPaddle;
    }

    public void movePaddle(String input){
        if(input == "W" || input == "Up"){
            movePaddle(true);
        }else if(input == "S" || input == "Down"){
            movePaddle(false);
        }
    }

    public void movePaddle(boolean down){
        if(down == true){
            if(playerPaddle.getY() - moveVal < 0){
                Platform.runLater( ()->{
                 playerPaddle.setY(0);
                });
            }else{
                Platform.runLater( ()->{
                    playerPaddle.setY(playerPaddle.getY() - moveVal);
                });
            }
        }else{
            if(playerPaddle.getY() + moveVal > 310){
                Platform.runLater( ()->{
                    playerPaddle.setY(310);
                });
            }else{
                Platform.runLater( ()->{
                    playerPaddle.setY(playerPaddle.getY() + moveVal);
                });
            }
        }




}


}
