package sample.games;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import java.util.ArrayList;

public class GameBase {
    public ArrayList<String> messages = new ArrayList<String>();
    public String name;
    public Scene scene;

    /**
     * Constructor for games
     * @param scene
     * @param name
     */
    public GameBase(Scene scene, String name) {
        this.name = name;
        this.scene = scene;
    }

    public void start() {

    }

    /**
     * Get's called when a message is send in titch chat
     *
     * @param channel the channel the message is send in
     * @param sender the nickname of the person that send the message
     * @param login the login name of the person that send the message
     * @param hostname the hostname of the person that send the message
     * @param message the message that was send
     */
    public void onMessage(String channel, String sender, String login, String hostname, String message) {

    }

    /**
     * handles a specified key (not used atm)
     * @param keyCode the keycode of the put in key
     * @return whether the key was used for something
     */
    public boolean handleKeyInput(KeyCode keyCode) {
        return false;
    }

    /**
     * Handles a mouse input
     * @param button the button that is pressed
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void handleMouseClick(MouseButton button, double x, double y){

    }

    public void drawScreen() {

    }
}
