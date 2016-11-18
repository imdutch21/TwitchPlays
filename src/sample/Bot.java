package sample;

import javafx.scene.input.KeyCode;
import org.jibble.pircbot.PircBot;

public class Bot extends PircBot {

    public Bot() {
        this.setName("dutchBot21");
    }

    @Override
    protected void onMessage(String channel, String sender, String login, String hostname, String message) {
        super.onMessage(channel, sender, login, hostname, message);
        if (Main.currentGame != null)
            Main.currentGame.onMessage(channel, sender, login, hostname, message);
    }
}