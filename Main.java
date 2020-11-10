package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {


    public void start(Stage primaryStage) throws Exception{
    PlayerInputReceiver p = new PlayerInputReceiver();
    MainWindow mw = new MainWindow(primaryStage,p);

    p.setPlayerPaddle(mw.getPlayerPaddle());
    p.setScene(mw.getScene());
    p.start();
    MainController mc = new MainController(mw,mw.getPlayerPaddle(),mw.getCompPaddle());
    mc.start();
    mw.setMainController(mc);
    CompPaddleController cpc = new CompPaddleController(mw.getCompPaddle(),mc.getBall());
    mw.setCpc(cpc);
    cpc.setMc(mc);
    cpc.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
