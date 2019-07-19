package za.co.wethinkcode.exceptions;

public class GameOverException extends Exception {

    private static final long serialVersionUID = 5992602368355037566L;

    public GameOverException() {
        super("You have died");
    }
}