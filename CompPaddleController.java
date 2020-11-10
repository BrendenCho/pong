package sample;

import javafx.application.Platform;
import javafx.scene.shape.Rectangle;

public class CompPaddleController extends Thread {

Rectangle compPaddle;
Ball ball;
boolean shouldRun = true;
MainController mc;
int moveVal = 10;

public CompPaddleController(Rectangle compPaddle,Ball ball){
this.compPaddle = compPaddle;
this.ball = ball;
}

public void run(){

    while(shouldRun){
    main();

    try {
        Thread.sleep(50);
    }catch(InterruptedException e){}

    }


}

public void setRun(boolean run){
shouldRun = run;
}

public void main(){

    double angle = mc.getAngle();

    double height = ((760 - ball.getBall().getCenterX())) * Math.tan(Math.abs(angle));
    double newHeight;

    if(angle > 0){
    newHeight = ball.getBall().getCenterY() + height;

    if(newHeight >= compPaddle.getY() && newHeight <= compPaddle.getY() + compPaddle.getHeight()){
    return;
    }

    double temp = compPaddle.getY() + moveVal;

    if(temp >= 310){
    Platform.runLater(() -> {
    compPaddle.setY(310);
    });
    }else{
        Platform.runLater(() -> {
            compPaddle.setY(temp);
        });
    }

    }else{
        newHeight = ball.getBall().getCenterY() - height;

        if(newHeight >= compPaddle.getY() && newHeight <= compPaddle.getY() + compPaddle.getHeight()){
            return;
        }
        double temp = compPaddle.getY() - moveVal;

        if(temp < 0){
        Platform.runLater(() -> {
        compPaddle.setY(0);
        });
        }else{
        Platform.runLater(() -> {
        compPaddle.setY(temp);
        });
        }

    }
}

public void setMc(MainController mc){
this.mc = mc;
}


}
