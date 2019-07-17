package za.co.wethinkcode.exceptions;

import za.co.wethinkcode.models.Villain;

public class RequiredToFightException extends Exception {
    private static final long serialVersionUID = 1479748691511707788L;
    private Villain primeSuspect;

    public RequiredToFightException(Villain v) {
        super("You are required to fight the villain.");
        this.primeSuspect = v;        
    }

    public Villain getVillain() {
        return this.primeSuspect;
    }
}