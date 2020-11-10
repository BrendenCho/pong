package sample;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindow {

    Pane pane = new Pane();
    Stage stage;
    Scene scene = new Scene(pane,800,400);
    PlayerInputReceiver p;
    Rectangle playerPaddle = new Rectangle();
    Rectangle compPaddle = new Rectangle();
    Circle ball = new Circle(400,200,5);
    MainController mc;
    Text playerScore = new Text("0");
    Text compScore = new Text("0");
    CompPaddleController cpc;


    public MainWindow(Stage stage, PlayerInputReceiver p){
this.stage = stage;
this.p = p;

stage.setOnCloseRequest(e -> {
    p.setRun(false);
    mc.setRun(false);
});

playerPaddle.setWidth(10);
playerPaddle.setHeight(50);
playerPaddle.setX(20);
playerPaddle.setY(200);
compPaddle.setWidth(10);
compPaddle.setHeight(50);
compPaddle.setX(760);
compPaddle.setY(200);
playerScore.setFont(Font.font(25));
playerScore.setX(200);
playerScore.setY(25);
compScore.setFont(Font.font(25));
compScore.setX(600);
compScore.setY(25);
pane.getChildren().add(playerPaddle);
pane.getChildren().add(ball);
pane.getChildren().add(playerScore);
pane.getChildren().add(compScore);
pane.getChildren().add(compPaddle);
stage.setTitle("Pong");
stage.setScene(scene);
stage.setWidth(800);
stage.setHeight(400);
stage.setResizable(false);
stage.show();
}

public Scene getScene(){
return scene;
}

public Rectangle getPlayerPaddle(){
return playerPaddle;
}

public Rectangle getCompPaddle(){
return compPaddle;
}

public Circle getBall(){
return this.ball;
}

public void setMainController(MainController mc){
        this.mc = mc;
}

public Text getPlayerScore(){
return playerScore;
}

public Text getCompScore(){
return compScore;
}

public void setCpc(CompPaddleController cpc){
this.cpc = cpc;
stage.setOnCloseRequest( e -> {
cpc.setRun(false);
    p.setRun(false);
    mc.setRun(false);
});
}

}
