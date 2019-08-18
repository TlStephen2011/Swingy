package za.co.wethinkcode.exceptions;

import za.co.wethinkcode.models.Artifact;

public class ArtifactDroppedException extends Exception {
	
	private Artifact congratz;

	public ArtifactDroppedException(Artifact a) {
		super("An artifact has been dropped");
		congratz = a;
	}
	
	public Artifact getArtifact() {
		return congratz;
	}
	
}
