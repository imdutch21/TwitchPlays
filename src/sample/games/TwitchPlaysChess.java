package sample.games;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import sample.Main;

/**
 * Created by bart on 12/11/2016.
 */
public class TwitchPlaysChess extends GameBase {


    public TwitchPlaysChess(Scene scene) {
        super(scene, "chess");
    }

    @Override
    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        super.onMessage(channel, sender, login, hostname, message);
    }
}
