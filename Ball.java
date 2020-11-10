package sample;

import javafx.scene.shape.Circle;

public class Ball {

Circle ball;
double arr[];

public Ball(Circle ball){
this.ball = ball;
}

public void fillArr(){
arr = new double[8];

arr[0] = ball.getCenterX() - ball.getRadius();
arr[1] = ball.getCenterY() - ball.getRadius();

arr[2] = ball.getCenterX() - ball.getRadius();
arr[3] = ball.getCenterY() + ball.getRadius();

arr[4] = ball.getCenterX() + ball.getRadius();
arr[5] = ball.getCenterY() - ball.getRadius();

arr[6] = ball.getCenterX() + ball.getRadius();
arr[7] = ball.getCenterY() + ball.getRadius();
}

public Circle getBall(){
return this.ball;
}





}
