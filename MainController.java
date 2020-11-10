package sample;

import javafx.scene.control.Alert;
import javafx.scene.shape.Rectangle;
import javafx.application.Platform;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.math.*;
public class MainController extends Thread {
    MainWindow mw;
    Ball ball;
    Rectangle playerPaddle;
    Rectangle compPaddle;
    double angle = Math.PI;
    boolean shouldRun = true;
    int playerScore = 0;
    int compScore = 0;
    int moveVal = 10;
    public void run() {

        while (shouldRun) {
            moveBall();
            ball.fillArr();
            checkCollision(playerPaddle);
            checkCollision(compPaddle);
            bounce();
            score();

            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
            }
        }

    }


    public MainController(MainWindow mw, Rectangle playerPaddle,Rectangle compPaddle) {
        this.mw = mw;
        this.playerPaddle = playerPaddle;
        this.compPaddle = compPaddle;
        ball = new Ball(mw.getBall());
    }

    public void moveBall() {
        Platform.runLater(() -> {
            ball.getBall().setCenterX(ball.getBall().getCenterX() + Math.cos(angle));
            ball.getBall().setCenterY(ball.getBall().getCenterY() + Math.sin(angle));
        });

    }

    public void setRun(boolean shouldRun) {
        this.shouldRun = shouldRun;
    }

    public boolean checkCollision(Rectangle paddle) {

        for (int i = 0; i < 8; i += 2) {
            double x = ball.arr[i];
            double y = ball.arr[i + 1];
            if (x >= paddle.getX() && x <= paddle.getX() + paddle.getWidth() && y >= paddle.getY() && y <= paddle.getY() + paddle.getHeight()) {
                angle = calcAngle(paddle);
                return true;
            }


        }

        return false;

    }

    public double calcAngle(Rectangle paddle) {

        if (paddle == playerPaddle) {
            double xDiff = ball.getBall().getCenterX() - (paddle.getX() + paddle.getWidth() / 2);
            double yDiff = ball.getBall().getCenterY() - (paddle.getY() + paddle.getHeight() / 2);
            double angle = Math.atan((Math.abs(yDiff) / Math.abs(xDiff)));
            if (yDiff > 0) {
                return angle;
            }
            return -angle;
        } else {
            double xDiff = ball.getBall().getCenterX() - (paddle.getX() + paddle.getWidth() / 2);
            double yDiff = ball.getBall().getCenterY() - (paddle.getY() + paddle.getHeight() / 2);
            double angle = Math.atan((Math.abs(yDiff) / Math.abs(xDiff)));
            if (yDiff > 0) {
                return (Math.PI / 2) + angle;
            }
            return -angle + (3 * Math.PI / 2);
        }

    }

    public void bounce() {
        if (ball.getBall().getCenterY() - ball.getBall().getRadius() <= 0) {
            angle = -angle;
        } else if (ball.getBall().getCenterY() - ball.getBall().getRadius() >= 350) {
            angle = -angle;
        }
    }

    public void score() {
        if (ball.getBall().getCenterX() - ball.getBall().getRadius() <= 0) {
            compScore++;
            ball.getBall().setCenterX(400);
            ball.getBall().setCenterY(200);
            angle = 0;

            Platform.runLater(() -> {
                compPaddle.setY(200);
                playerPaddle.setY(200);
            });

        } else if (ball.getBall().getCenterX() + ball.getBall().getRadius() >= 800) {
            playerScore++;
            ball.getBall().setCenterX(400);
            ball.getBall().setCenterY(200);
            angle = Math.PI;

            Platform.runLater(() -> {
                compPaddle.setY(200);
                playerPaddle.setY(200);
            });

        }

        Platform.runLater(() -> {
            mw.getPlayerScore().setText(Integer.toString(playerScore));
            mw.getCompScore().setText(Integer.toString(compScore));

        });


    }
    public Ball getBall(){
      return ball;
    }

    public double getAngle(){
        return angle;
    }


}