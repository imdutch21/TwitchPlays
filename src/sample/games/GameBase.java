package sample.games;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class GameBase {
    public ArrayList<String> messages = new ArrayList<String>();
    public String name;
    public Scene scene;

    /** Constructor for games
     *
     * @param scene
     * @param name
     */
    public GameBase(Scene scene, String name){
        this.name = name;
        this.scene = scene;
    }

    public void start(){

    }

    /**
     * Get's called when a message is send in titch chat
     * @param channel
     * @param sender
     * @param login
     * @param hostname
     * @param message
     */
    public void onMessage(String channel, String sender, String login, String hostname, String message) {

    }

    public void drawScreen() {

    }

    public boolean handleKeyInput(KeyCode keyCode, boolean reverse) {
        return false;
    }
}
