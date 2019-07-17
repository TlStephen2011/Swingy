package za.co.wethinkcode.exceptions;

import za.co.wethinkcode.models.Villain;

public class OccupiedByVillainException extends Exception {
	private static final long serialVersionUID = 2480086454127801370L;
	private Villain primeSuspect;

	public OccupiedByVillainException(Villain v) {
		super("A villain occupied the space you are trying to move to.");
		this.primeSuspect = v;
	}

	public Villain getVillain() {
		return this.primeSuspect;
	}
}
