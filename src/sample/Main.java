package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.jibble.pircbot.IrcException;
import sample.games.Chess.TwitchPlaysChess;
import sample.games.GameBase;
import java.io.IOException;


public class Main extends Application {
    public static GameBase currentGame = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = new Pane();
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 1000, 1000);
        primaryStage.setScene(scene);
        primaryStage.show();

        //Key handeling
        scene.setOnKeyPressed(e -> {
            if (currentGame != null) {
                currentGame.handleKeyInput(e.getCode(), false);
                currentGame.drawScreen();
            }
        });

        currentGame = new TwitchPlaysChess(scene);
        currentGame.start();
    }

    //Initialises the bot
    public static void startBot() throws IOException, IrcException {
        Bot bot = new Bot();
        bot.setVerbose(true);
        bot.connect("irc.twitch.tv", 6667, ""/*needs method to input after start*/);
        bot.joinChannel("#imdutch21");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
