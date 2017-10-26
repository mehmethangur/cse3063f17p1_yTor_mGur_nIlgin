package monopolygame;

public class Die {
	
	int face;

	public void setFace(int face) {
		this.face = face;
	}

	public int playDice(){
		setFace( (int)(1 + Math.random()* 6) );
		return this.face;
	}
	public int getFace(){
		return this.face;
	}
}
